package uni.tartu.discovery.providers

import uni.tartu.configuration.Configuration
import uni.tartu.discovery.DiscoveryProcessor
import uni.tartu.discovery.DiscoveryProvider
import uni.tartu.discovery.DiscoveryType
import uni.tartu.storage.IntermediateResultSet

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:04 PM
 **/

@DiscoveryProvider
class DiscoverRmiServices implements DiscoveryProcessor {
	private List<String> ignoredServices
	private String currentProcessId

	@Override
	DiscoveryProcessor analyze() {
		println "Hello sir, this is RMI analyzer"
		this
	}

	@Override
	IntermediateResultSet reduce() {
		def resultSet = new IntermediateResultSet(currentProcessId, ignoredServices.size(), [], getType())
		resultSet.setIgnoredServices(ignoredServices)
		resultSet
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
	void init(List<String> services, Configuration configuration) {
		this.ignoredServices = services.findAll {
			def parts = it.split(';')
			if (!currentProcessId) {
				currentProcessId = parts[0]
			}
			!parts[1].startsWith("/") && parts[1].contains(".")
		}
	}
}
