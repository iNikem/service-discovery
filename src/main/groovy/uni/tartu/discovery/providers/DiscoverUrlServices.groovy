package uni.tartu.discovery.providers

import uni.tartu.algorithm.TfIdf
import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType

import static uni.tartu.algorithm.MiniMapReduce.put
import static uni.tartu.algorithm.TfIdf.FirstIteration
import static uni.tartu.algorithm.TfIdf.SecondIteration
import static uni.tartu.utils.StringUtils.split

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:12 PM
 **/

@DiscoveryProvider
class DiscoverUrlServices implements DiscoveryProcessor {
	private List<String> services
	private Map<?, ?> grouped

	@Override
	int getSize() {
		this.services.size()
	}

	@Override
	Map analyze() {
		TfIdf tfIdf = new TfIdf(
			/*
			 * first MapReduce job closure specification
			 */
			FirstIteration.build({ k, v ->
				v.collect { i ->
					i.collect { j -> j.equals(k) ?: "$k;$j" }
				}.flatten().each {
					put((it.toString()), 1)
				}
			}, { m, k, v ->
				m << [(k): v.sum(0)]
			}),
			/*
			 * second MapReduce job closure specification
			 */
			SecondIteration.build({ k, v ->
				def arr = (k as String).split(";")
				arr.length < 2 ?: put(arr[0], "${arr[1]};${v}".toString())
			}, { m, k, v ->
				int acc = v.sum { (it as String).split(";")[1] as int }
				v.flatten().each {
					def parts = (it as String).split(";"),
						 key = "${k};${parts[0]}",
						 val = "$acc;${parts[1]}"
					m << [(key): (val)]
				}
				m
			}), null)
		tfIdf.calculate(this.grouped)
	}

	@Override
	DiscoveryProcessor group() {
		this.grouped = this.services
			.collect { split(it, "/") }
			.findAll { it.length > 0 }
			.groupBy { it[0] }
		this
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.URL_DISCOVERY
	}

	@Override
	void init(List<String> services) {
		this.services = services.findAll { it.startsWith("/") }
	}
}
