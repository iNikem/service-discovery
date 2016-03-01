package uni.tartu.discovery.providers

import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType

import static uni.tartu.algorithm.MiniMapReduce.Mapper
import static uni.tartu.algorithm.MiniMapReduce.Reducer
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
	private Map<?, ?> mapped
	private Map<?, ?> reduced

	@Override
	public int getSize() {
		this.services.size()
	}

	@Override
	public DiscoveryProcessor map() {
		this.mapped = Mapper.map(grouped, { k, v ->
			v.collect { i ->
				i.collect { j -> j.equals(k) ?: "$k;$j" }
			}.flatten().each {
				Mapper.put((it.toString()), 1)
			}
		})
		this
	}

	@Override
	public DiscoveryProcessor reduce() {
		this.reduced = Reducer.reduce(this.mapped, { map, key, listValue -> map << [(key): listValue.sum(0)] })
		this
	}

	@Override
	public DiscoveryProcessor group() {
		this.grouped = this.services
			.collect { split(it, "/") }
			.findAll { it.length > 0 }
			.groupBy { it[0] }
		this
	}

	@Override
	public DiscoveryType getType() {
		DiscoveryType.URL_DISCOVERY
	}

	@Override
	public void init(List<String> services) {
		this.services = services.findAll { it.startsWith("/") }
	}
}
