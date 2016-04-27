package uni.tartu.discovery.providers

import uni.tartu.algorithm.DelimiterAnalyzer
import uni.tartu.algorithm.TfIdf
import uni.tartu.algorithm.UrlReducer
import uni.tartu.configuration.Configuration
import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType
import uni.tartu.storage.AnalyzedUrlData
import uni.tartu.storage.IntermediateResultSet
import uni.tartu.storage.RawUrlData
import uni.tartu.storage.UrlInfoData

import static uni.tartu.algorithm.MiniMapReduce.put
import static uni.tartu.algorithm.MiniMapReduce.putUrlIdHolder
import static uni.tartu.algorithm.TfIdf.*
import static uni.tartu.utils.StringUtils.*

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:12 PM
 **/

@DiscoveryProvider
class DiscoverUrlServices implements DiscoveryProcessor {

	private final DelimiterAnalyzer delimiterAnalyzer = DelimiterAnalyzer.getInstance()

	private final Map<Integer, RawUrlData> originalServices = new HashMap<Integer, RawUrlData>()
	private Map<String, List<String>> initialGroups
	private Map<?, ?> grouped
	private List<AnalyzedUrlData> scores
	private String currentProcessId
	private Configuration configuration

	@Override
	DiscoveryProcessor analyze() {
		TfIdf tfIdf = new TfIdf(
			/**
			 * first MapReduce job closure specification
			 **/
			FirstIteration.build({ k, v ->
				v.collect { i ->
					i.collect { j ->
						def keys = getKey(k as String)
						j.equals(keys[0]) ?: "${keys[0]};${j}__${keys[1]}".toString()
					}
				}.flatten().each {
					def keys = getKey(it as String)
					if (keys) {
						def urlPart = keys[0],
							 urlId = keys[1] as int,
							 parts = split(urlPart, ";")
						populate(parts, urlPart, urlId, originalServices.get(urlId).rawUrl)
					}
				}
			}, { map, k, v ->
				map << [(k): v.sum(0)]
			}),
			/**
			 * second MapReduce job closure specification
			 **/
			SecondIteration.build({ k, v ->
				def arr = (k as String).split(";")
				arr.length < 2 ?: put(arr[0], "${arr[1]};${v}".toString())
			}, { map, k, v ->
				int N = v.sum {
					(it as String).split(";")[1] as int
				}
				v.flatten().each {
					def parts = (it as String).split(";"),
						 key = "${k};${parts[0]}",
						 val = "${parts[1]};$N"
					map << [(key): (val)]
				}
				map
			}),
			/**
			 * third MapReduce job closure specification
			 **/
			ThirdIteration.build({ k, v ->
				def parts = (k as String).split(";")
				put((parts[1]), ("${v};1;${parts[0] ?: 'null'}"))
			}, { map, k, v ->
				def m = v.sum {
					(it as String).split(";")[2] as int
				}
				v.flatten().each {
					def parts = (it as String).split(";"),
						 key = "${k};${parts[3]}" as String,
						 val = "${parts[0]};${parts[1]};$m" as String
					map << [(key): (val)]
				}
				map
			}))
		def D = this.originalServices.size()
		this.scores = tfIdf.calculate(grouped, D, configuration).values().toList()
		this
	}

	@Override
	IntermediateResultSet reduce() {
		def originalSize = this.originalServices.size()
		def urlReducer = new UrlReducer(scores)
		new IntermediateResultSet(currentProcessId, originalSize, urlReducer.reduce(), getType())
	}

	@Override
	DiscoveryProcessor group() {
		this.grouped = initialGroups.collectEntries { k, v ->
			def d = delimiterAnalyzer.getDelimiter(k)
			def res = v.collect { split(it, d) }
			[(k): res]
		}
		this
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.URL_DISCOVERY
	}

	@Override
	void init(List<String> services, Configuration configuration) {
		this.configuration = configuration
		services.findAll { it.split(';')[1].startsWith("/") }.eachWithIndex { url, i ->
			def cleaned = clean(url)
			def parts = cleaned.split(';')
			if (parts.size() > 1) {
				def id = parts[0]
				if (!currentProcessId) {
					currentProcessId = id
				}
				originalServices.put(i, new RawUrlData(id: id, rawUrl: parts[1], urlId: i))
			}
		}
		delimiterAnalyzer.build(originalServices.values().toList())
		originalServices.each { k, v ->
			if (v.rawUrl.contains('&') || v.rawUrl.contains('?')) {
				def key = split(v as String, ';')[0]
				v.rawUrl = clean(v.rawUrl, delimiterAnalyzer.getDelimiter(key))
			}
		}
		this.initialGroups = delimiterAnalyzer.initialGroups.collectEntries { k, v ->
			def cleaned = v.collect { it }
			[(k): cleaned]
		} as Map<String, List<String>>
	}

	//TODO find out why we are loosing adyenUrlGenerator service
	private static void populate(String[] parts, String urlPart, int urlId, String originalUrl) {
		parts.length < 2 ? putUrlIdHolder('null', new UrlInfoData(urlPart: urlPart, urlId: urlId, originalUrl: originalUrl)) : putUrlIdHolder(parts[1], new UrlInfoData(urlPart: urlPart, urlId: urlId, originalUrl: originalUrl))
		put((urlPart), 1)
	}
}
