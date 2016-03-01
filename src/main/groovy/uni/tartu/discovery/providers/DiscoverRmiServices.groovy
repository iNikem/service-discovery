package uni.tartu.discovery.providers

import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:04 PM
 **/

@DiscoveryProvider
class DiscoverRmiServices implements DiscoveryProcessor {
	private List<String> services
	private Map<?, ?> grouped
	private Map<?, ?> mapped
	private Map<?, ?> reduced

	@Override
	int getSize() {
		this.services.size()
	}

	@Override
	DiscoveryProcessor map() {
		println "Hello sir, this is RMI Mapper"
		this
	}

	@Override
	DiscoveryProcessor reduce() {
		//RMI_DISCOVERY specific algorithm
		println "Hello sir, this is RMI Reducer"
		this
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.RMI_DISCOVERY
	}

	@Override
	DiscoveryProcessor group() {
		this
	}

	@Override
	void init(List<String> services) {
		this.services = services.findAll { !it.startsWith("/") && it.contains(".") }
	}
}
