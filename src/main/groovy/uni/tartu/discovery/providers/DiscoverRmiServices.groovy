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
	public List<String> services

	@Override
	int getSize() {
		this.services.size()
	}

	@Override
	void tokenize() {
		//RMI_DISCOVERY specific algorithm
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.RMI_DISCOVERY
	}

	@Override
	void init(List<String> services) {
		this.services = services.findAll { !it.startsWith("/") && it.contains(".") }
	}
}
