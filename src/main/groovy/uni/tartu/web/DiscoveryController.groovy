package uni.tartu.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import uni.tartu.discovery.Discovery
import uni.tartu.discovery.DiscoveryInitializer
import uni.tartu.discovery.DiscoveryRunner
import uni.tartu.storage.ResultSetWithStats

import static uni.tartu.parser.Parser.parse

/**
 * author: lkokhreidze
 * date: 3/17/16
 * time: 7:59 PM
 **/

@RestController
@RequestMapping('/api/discovery')
class DiscoveryController {

	//FIXME ResultSetWithStats can't be resolved as response atm. fix it
	@RequestMapping(value = '/services/tree', method = RequestMethod.GET)
	public ResultSetWithStats getServiceTree(@RequestParam String id) {
		DiscoveryRunner discovery = new DiscoveryRunner()
		def resultSet = discovery.discover(new File(Thread
			.currentThread()
			.getContextClassLoader()
			.getResource("${id}.csv")
			.toURI()))
		resultSet
	}
}
