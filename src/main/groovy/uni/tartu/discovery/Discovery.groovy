package uni.tartu.discovery

import groovy.util.logging.Slf4j
import uni.tartu.storage.ResultSetWithStats

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:38 PM
 **/

@Slf4j
class Discovery {
	private final DiscoveryProcessor discoveryProvider

	Discovery(DiscoveryProcessor discoveryProvider) {
		this.discoveryProvider = discoveryProvider
	}

	ResultSetWithStats discover() {
		def resultSet = discoveryProvider.tokenize().analyze().reduce()
		log.info("got intermediate results, building result set. my work here is done!")
		new ResultSetWithStats(resultSet)
	}
}