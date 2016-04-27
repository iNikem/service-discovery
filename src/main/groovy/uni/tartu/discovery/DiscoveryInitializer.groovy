package uni.tartu.discovery

import groovy.util.logging.Slf4j
import uni.tartu.configuration.Configuration
import uni.tartu.utils.ReflectionUtils

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 8:52 PM
 *
 * Singleton class for loading discovery providers
 **/

@Slf4j
public class DiscoveryInitializer {
	private static DiscoveryInitializer INITIALIZER_INSTANCE
	private Map<DiscoveryType, DiscoveryProcessor> discoveryProviders = [:]
	private final static String PACKAGE_BASE = "uni.tartu.discovery.providers"
	private final Configuration configuration

	static {
		INITIALIZER_INSTANCE = new DiscoveryInitializer()
	}

	private DiscoveryInitializer() {
		log.info("loading discovery providers.")
		this.configuration = new Configuration()
	}

	public static DiscoveryInitializer getInitializerInstance() {
		return INITIALIZER_INSTANCE
	}

	public Map<DiscoveryType, DiscoveryProcessor> loadProviders(List<String> records) {
		ReflectionUtils.scan(PACKAGE_BASE, DiscoveryProvider.class).each { DiscoveryProcessor processor ->
			processor.init(records, configuration)
			discoveryProviders[(processor.getType())] = processor
		}
		discoveryProviders
	}
}
