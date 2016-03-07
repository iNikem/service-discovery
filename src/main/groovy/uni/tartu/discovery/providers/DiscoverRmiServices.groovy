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

	@Override
	int getSize() {
		this.services.size()
	}

	@Override
	Map analyze() {
		println "Hello sir, this is RMI analyzer"
		[:]
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.RMI_DISCOVERY
	}

	@Override
	DiscoveryProcessor group() {
		this.grouped = [:]
		this
	}

	@Override
	void init(List<String> services) {
		this.services = services.findAll {
			def parts = it.split(';')
			!parts[1].startsWith("/") && parts[1].contains(".")
		}
	}
}
