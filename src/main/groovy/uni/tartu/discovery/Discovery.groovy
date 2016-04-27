package uni.tartu.discovery

import uni.tartu.storage.IntermediateResultSet
import uni.tartu.storage.ResultSetWithStats

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:38 PM
 **/

class Discovery {
	private final Map<DiscoveryType, DiscoveryProcessor> discoveryProviders

	Discovery(Map<DiscoveryType, DiscoveryProcessor> discoveryProviders) {
		this.discoveryProviders = discoveryProviders
	}

	ResultSetWithStats discover() {
		def resultSet = discoveryProviders.collectEntries {
			[(it.key): it.value.group().analyze().reduce()]
		} as Map<DiscoveryType, IntermediateResultSet>
		new ResultSetWithStats(resultSet)
	}
}