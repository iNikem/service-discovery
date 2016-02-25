package uni.tartu.discovery
/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:38 PM
 **/

class Discovery {
	private final DiscoveryInitializer discoveryInitializer

	Discovery(DiscoveryInitializer discoveryInitializer) {
		this.discoveryInitializer = discoveryInitializer
	}

	void tokenize() {
		discoveryInitializer.processors.each { k, v ->
			println "key: ${k}, size: ${v.size}"
			println "key: ${k}, size: ${v.tokenize()}"
		}
	}
}