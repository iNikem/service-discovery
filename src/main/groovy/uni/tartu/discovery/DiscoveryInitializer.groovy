package uni.tartu.discovery

import org.reflections.Reflections

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:52 PM
 **/

public class DiscoveryInitializer {

	private final List<String> services
	private final Map<DiscoveryType, DiscoveryProcessor> discoveryProvider = [:]
	private final static String DISCOVERY_PROVIDER_PACKAGE_BASE = "uni.tartu.discovery.providers"

	public DiscoveryInitializer(List<Map> records) {
		def services = records.collect { (it).serviceName }
		services.filter '.html', '$'
		this.services = services
	}

	public Map<DiscoveryType, DiscoveryProcessor> loadProviders() {
		new Reflections(DISCOVERY_PROVIDER_PACKAGE_BASE).getTypesAnnotatedWith(DiscoveryProvider.class).each {
			DiscoveryProcessor discoveryProcessor = (DiscoveryProcessor) it.newInstance()
			discoveryProcessor.init(services)
			discoveryProvider[(discoveryProcessor.getType())] = discoveryProcessor
		}
		discoveryProvider
	}

}
