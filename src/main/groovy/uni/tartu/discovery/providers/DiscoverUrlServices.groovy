package uni.tartu.discovery.providers

import groovy.util.logging.Slf4j
import uni.tartu.algorithm.DelimiterAnalyzer
import uni.tartu.algorithm.ServiceGrouping
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

@Slf4j
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
		log.info("started analyzing phase for URL discovery")
		TfIdf tfIdf = new TfIdf(
			/**
			 * first MapReduce job closure specification
			 **/
			FirstIteration.build({ k, v ->
				v.collect { i ->
					i.collect { j ->
						"${k};${j}".toString()
					}
				}.flatten().each {
					def parts = split(it as String, ";")
					if (parts.size() >= 2) {
						def idPart = parts[0],
							 valuePart = parts[1]
						def keys = getKey(valuePart)
						if (keys) {
							def urlPart = keys[0],
								 urlId = keys[1] as int
							def newKey = "${urlPart};${idPart}".toString()
							populate(newKey, urlPart, urlId, originalServices.get(urlId).rawUrl)
						}
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
				def parts = (k as String).split(";"),
					 id = parts[1],
					 urlPart = parts[0] ?: 'null'
				put((urlPart), ("$id;${v};1"))
			}, { map, k, v ->
				def m = v.sum {
					(it as String).split(";")[3] as int
				}
				v.flatten().each {
					def parts = (it as String).split(";"),
						 key = "${k};${parts[0]}" as String,
						 val = "${parts[1]};${parts[2]};$m" as String
					map << [(key): (val)]
				}
				map
			}))
		def D = this.grouped.size()
		this.scores = tfIdf.calculate(grouped, D, configuration).values().toList()
		log.info("got TF-IDF scores with size: {}", scores.size())
		this
	}

	@Override
	IntermediateResultSet reduce() {
		log.info("started reduction phase for URL discovery")
		def originalSize = this.originalServices.size()
		def urlReducer = new UrlReducer(scores)
		def reducesUrls = urlReducer.reduce()
		log.info("reduced and generated URLs with size: {}", reducesUrls.size())
		new IntermediateResultSet(currentProcessId, originalSize, reducesUrls, getType())
	}

	@Override
	DiscoveryProcessor group() {
		log.info("started grouping phase for URL discovery")
		this.grouped = initialGroups.collectEntries { k, v ->
			def result = v.collect { url ->
				def parts = getKey(url)
				def urlId = parts[1]
				split(url, delimiterAnalyzer.getDelimiter(k)).collect { part ->
					if (part.contains("__$urlId".toString())) {
						return part
					}
					"${part}__$urlId".toString()
				}
			}
			[(k): result]
		}
		log.info("created intermediate URL groups with size: {}", grouped.size())
		this
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.URL_DISCOVERY
	}

	@Override
	void init(List<String> services, Configuration configuration) {
		log.info("started initialisation phase for URL discovery")
		this.configuration = configuration
		def serviceGrouping = new ServiceGrouping(services.size(), configuration.getMaxGroupSize())
		services.findAll { it.split(';')[1].startsWith("/") }.eachWithIndex { url, i ->
			def collectionId = serviceGrouping.generateGroupingId()
			def cleaned = clean(url)
			def parts = cleaned.split(';')
			if (parts.size() > 1) {
				def id = parts[0]
				if (!currentProcessId) {
					currentProcessId = id
				}
				originalServices.put(i, new RawUrlData(id: id, rawUrl: parts[1], urlId: i, collectionId: collectionId))
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

	private static void populate(String newKey, String urlPart, int urlId, String originalUrl) {
		putUrlIdHolder(urlPart, new UrlInfoData(urlPart: urlPart, urlId: urlId, originalUrl: originalUrl))
		put((newKey), 1)
	}
}
