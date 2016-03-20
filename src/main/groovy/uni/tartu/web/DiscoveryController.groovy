package uni.tartu.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import uni.tartu.discovery.Discovery
import uni.tartu.discovery.DiscoveryInitializer

import static uni.tartu.parser.Parser.parse

/**
 * author: lkokhreidze
 * date: 3/17/16
 * time: 7:59 PM
 **/

@RestController
@RequestMapping('/api/discovery')
class DiscoveryController {

	@RequestMapping(value = '/services/tree', method = RequestMethod.GET)
	public List<Map> getServiceTree() {
		def discovery = new Discovery(DiscoveryInitializer.getInitializerInstance().loadProviders(parse {
			new File(Thread
				.currentThread()
				.getContextClassLoader()
				.getResource('test-data-1.csv')
				.toURI())
		}))
		discovery.discover()
	}
}
