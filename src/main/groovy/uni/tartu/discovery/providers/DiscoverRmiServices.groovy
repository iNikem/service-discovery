package uni.tartu.discovery.providers

import groovy.util.logging.Slf4j
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

@Slf4j
@DiscoveryProvider
class DiscoverRmiServices implements DiscoveryProcessor {
	private List<String> ignoredServices
	private String currentProcessId

	@Override
	DiscoveryProcessor analyze() {
		log.info("started analyzing phase for controller based services")
		this
	}

	@Override
	IntermediateResultSet reduce() {
		log.info("started reduction phase for controller based services")
		def resultSet = new IntermediateResultSet(currentProcessId, ignoredServices.size(), [], getType())
		resultSet.setIgnoredServices(ignoredServices)
		resultSet
	}

	@Override
	DiscoveryType getType() {
		DiscoveryType.RMI_DISCOVERY
	}

	@Override
	DiscoveryProcessor tokenize() {
		log.info("started grouping phase for controller based services")
		this
	}

	@Override
	void init(List<String> services, Configuration configuration) {
		log.info("started initialization phase for controller based services")
		this.ignoredServices = services.findAll {
			def parts = it.split(';')
			if (!currentProcessId) {
				currentProcessId = parts[0]
			}
			!parts[1].startsWith("/") && parts[1].contains(".")
		}
	}
}
