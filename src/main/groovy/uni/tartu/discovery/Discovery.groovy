package uni.tartu.discovery
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

	void discover() {
		this.discoveryProviders.each { k, v ->
			def result = v.group().analyze()
			println "Got result from: $k, with size: ${result.size()}"
		}
	}
}