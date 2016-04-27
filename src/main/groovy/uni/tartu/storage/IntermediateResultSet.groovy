package uni.tartu.storage

import uni.tartu.discovery.DiscoveryType

/**
 * author: lkokhreidze
 * date: 4/27/16
 * time: 12:13 PM
 **/

class IntermediateResultSet {
	final String processId
	final List<String> generatedServices
	final int originalSize
	final DiscoveryType discoveryType

	List<String> ignoredServices

	IntermediateResultSet(String processId, int originalSize, List<String> generatedServices, DiscoveryType discoveryType) {
		this.processId = processId
		this.originalSize = originalSize
		this.generatedServices = generatedServices
		this.discoveryType = discoveryType
	}

	public void setIgnoredServices(List<String> ignored) {
		this.ignoredServices = ignored
	}
}
