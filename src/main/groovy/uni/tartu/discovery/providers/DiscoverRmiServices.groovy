package uni.tartu.discovery.providers

import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType
import uni.tartu.storage.ResultSetWithStats

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
	DiscoveryProcessor analyze() {
		println "Hello sir, this is RMI analyzer"
		this
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.RMI_DISCOVERY
	}

	@Override
	List<ResultSetWithStats> toTree() {
		return null
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
