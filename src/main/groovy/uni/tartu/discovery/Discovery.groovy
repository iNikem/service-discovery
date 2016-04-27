package uni.tartu.discovery

import groovy.util.logging.Slf4j
import uni.tartu.storage.IntermediateResultSet
import uni.tartu.storage.ResultSetWithStats

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:38 PM
 **/

@Slf4j
class Discovery {
	private final Map<DiscoveryType, DiscoveryProcessor> discoveryProviders

	Discovery(Map<DiscoveryType, DiscoveryProcessor> discoveryProviders) {
		this.discoveryProviders = discoveryProviders
	}

	ResultSetWithStats discover() {
		def resultSet = discoveryProviders.collectEntries {
			[(it.key): it.value.group().analyze().reduce()]
		} as Map<DiscoveryType, IntermediateResultSet>
		log.info("got intermediate results, building result set. my work here is done!")
		new ResultSetWithStats(resultSet)
	}
}