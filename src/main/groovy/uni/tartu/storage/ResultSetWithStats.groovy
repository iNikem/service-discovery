package uni.tartu.storage

import uni.tartu.algorithm.DelimiterAnalyzer
import uni.tartu.algorithm.tree.TreeBuilder
import uni.tartu.discovery.DiscoveryType
import uni.tartu.parser.Parser

import static uni.tartu.utils.CollectionUtils.transform

/**
 * author: lkokhreidze
 * date: 3/24/16
 * time: 4:33 PM
 **/

class ResultSetWithStats {

	final ControllerBasedServiceResults ignoredControllerBasedServices
	final PollutedServiceResults ignoredUrlBasedServices
	final UrlBasedServiceResults urlBasedServiceResults

	final int totalSize

	ResultSetWithStats(Map<DiscoveryType, IntermediateResultSet> calculatedResult) {
		def controllerBasedResults = calculatedResult[DiscoveryType.RMI_DISCOVERY]
		def urlBasedResults = calculatedResult[DiscoveryType.URL_DISCOVERY]
		this.ignoredControllerBasedServices = new ControllerBasedServiceResults(controllerBasedResults.originalSize, controllerBasedResults.getIgnoredServices())
		def pollutedUrls = Parser.pollutedUrls
		this.ignoredUrlBasedServices = new PollutedServiceResults(pollutedUrls.size(), pollutedUrls)
		this.urlBasedServiceResults = new UrlBasedServiceResults(urlBasedResults.originalSize, urlBasedResults.generatedServices)
		this.totalSize = this.ignoredUrlBasedServices.count + this.ignoredControllerBasedServices.count + this.urlBasedServiceResults.count
	}


	static class ControllerBasedServiceResults {
		final List<String> services
		final int count
		final DiscoveryStatus status

		ControllerBasedServiceResults(int count, List<String> services) {
			this.services = services
			this.count = count
			this.status = DiscoveryStatus.IGNORED
		}
	}

	static class PollutedServiceResults {
		final List<String> services
		final int count
		final DiscoveryStatus status

		PollutedServiceResults(int count, List<String> services) {
			this.count = count
			this.services = services
			this.status = DiscoveryStatus.IGNORED
		}
	}

	static class UrlBasedServiceResults {

		final List<String> services
		final int count
		final DiscoveryStatus status

		UrlBasedServiceResults(int originalSize, List<String> generatedServices) {
			this.services = generatedServices
			this.count = originalSize
			this.status = DiscoveryStatus.ANALYZED
		}

		def getGraph() {
			if (!services) {
				return null
			}
			def treeBuilder = new TreeBuilder()
			String delimiter = DelimiterAnalyzer.getInstance().getDelimiter()
			treeBuilder.transform(transform(services, delimiter))
		}

		public String getReductionPercentage() {
			if (getReducedServicesSize() == 0) {
				return "0.0%"
			}
			double d = 100 - ((getReducedServicesSize() * 100) / count)
			"${d.trunc(2)}%".toString()
		}

		public int getReducedServicesSize() {
			services.size()
		}
	}
}
