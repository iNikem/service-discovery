package uni.tartu.discovery.providers

import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType;

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:12 PM
 **/

@DiscoveryProvider
public class DiscoverUrlServices implements DiscoveryProcessor {
	public List<String> services

	@Override
	int getSize() {
		this.services.size()
	}

	@Override
	void tokenize() {
		//URL_DISCOVERY specific algorithm
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
