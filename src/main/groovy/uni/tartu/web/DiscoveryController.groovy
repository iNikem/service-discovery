package uni.tartu.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import uni.tartu.discovery.DiscoveryRunner

/**
 * author: lkokhreidze
 * date: 3/17/16
 * time: 7:59 PM
 **/

@RestController
@RequestMapping('/api/discovery')
class DiscoveryController {

	@RequestMapping(value = '/services/tree', method = RequestMethod.GET)
	public Map getServiceTree(@RequestParam String id) {
		DiscoveryRunner discovery = new DiscoveryRunner()
		def resultSet = discovery.discover(new File(Thread
			.currentThread()
			.getContextClassLoader()
			.getResource("${id}.csv")
			.toURI()))
		[reductionPercentage      : resultSet.urlBasedServiceResults.reductionPercentage,
		 reducedServicesSize      : resultSet.urlBasedServiceResults.reducedServicesSize,
		 urlServicesSize          : resultSet.urlBasedServiceResults.count,
		 ignoredNoiseSize         : resultSet.ignoredUrlBasedServices.count,
		 ignoredControllerServices: resultSet.ignoredControllerBasedServices.count,
		 graph                    : resultSet.urlBasedServiceResults.graph,
		 total                    : resultSet.totalSize]
	}
}
