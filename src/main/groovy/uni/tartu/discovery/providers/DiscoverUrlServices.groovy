package uni.tartu.discovery.providers

import uni.tartu.algorithm.DelimiterAnalyzer
import uni.tartu.algorithm.TfIdf
import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType

import static uni.tartu.algorithm.DelimiterAnalyzer.getInstance
import static uni.tartu.algorithm.MiniMapReduce.put
import static uni.tartu.algorithm.TfIdf.*
import static uni.tartu.utils.StringUtils.clean
import static uni.tartu.utils.StringUtils.split
import static uni.tartu.utils.TextDumper.dump

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:12 PM
 **/

@DiscoveryProvider
class DiscoverUrlServices implements DiscoveryProcessor {

	private final DelimiterAnalyzer analyzer = getInstance()

	/**
	 * The most optimal threshold value to discover parameters in the url.
	 * TODO: Need to figure out more smart way to discover threshold.
	 **/
	private static float PARAMETER_THRESHOLD = 0.001

	private Map<String, List<String>> initialGroups
	private Map<?, ?> grouped

	@Override
	int getSize() {
		this.initialGroups.size()
	}

	@Override
	Map analyze() {
		TfIdf tfIdf = new TfIdf(
			/**
			 * first MapReduce job closure specification
			 **/
			FirstIteration.build({ k, v ->
				v.collect { i ->
					i.collect { j ->
						j.equals(k) ?: "$k;$j"
					}
				}.flatten().each {
					put((it.toString()), 1)
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
		def scores = tfIdf.calculate(this.grouped)
		dump("/Users/lkokhreidze/Desktop",
			scores.findAll { _, v ->
				v > PARAMETER_THRESHOLD
			},
			scores.findAll { _, v ->
				v <= PARAMETER_THRESHOLD
			})
		scores
	}

	@Override
	DiscoveryProcessor group() {
		this.grouped = this.initialGroups.collectEntries { k, v ->
			[(k): v.collect { split(it, analyzer.getDelimiter(k)) }]
		}
		this
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.URL_DISCOVERY
	}

	@Override
	void init(List<String> services) {
		analyzer.build(services
			.findAll { it.split(';')[1].startsWith("/") }
			.collect { clean(it) })
		this.initialGroups = analyzer.initialGroups.collectEntries { k, v ->
			[(k): v.collect { clean(it, analyzer.getDelimiter(k)) }]
		} as Map<String, List<String>>
	}
}
