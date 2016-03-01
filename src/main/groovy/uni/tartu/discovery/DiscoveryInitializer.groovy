package uni.tartu.discovery

import uni.tartu.utils.ReflectionUtils

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:52 PM
 *
 * Singleton class for loading discovery providers
 **/

public class DiscoveryInitializer {
	private static DiscoveryInitializer instance = new DiscoveryInitializer();
	private Map<DiscoveryType, DiscoveryProcessor> discoveryProviders = [:]
	private final static String PACKAGE_BASE = "uni.tartu.discovery.providers"

	private DiscoveryInitializer() {}

	public static DiscoveryInitializer getInstance() {
		return instance;
	}

	public static DiscoveryInitializer loadProviders(List<String> records) {
		ReflectionUtils.scan(PACKAGE_BASE, DiscoveryProvider.class).each { DiscoveryProcessor processor ->
			processor.init(records)
			instance.discoveryProviders[(processor.getType())] = processor
		}
		instance
	}

	public Map<DiscoveryType, DiscoveryProcessor> getProcessors() {
		discoveryProviders
	}

	public DiscoveryProcessor getProcessor(DiscoveryType type) {
		discoveryProviders.get(type)
	}
}
