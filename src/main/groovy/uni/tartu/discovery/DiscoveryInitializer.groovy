package uni.tartu.discovery

import groovy.util.logging.Slf4j
import uni.tartu.configuration.Configuration
import uni.tartu.discovery.providers.DiscoverUrlServices

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

	static {
		INITIALIZER_INSTANCE = new DiscoveryInitializer()
	}

	private DiscoveryInitializer() {
		log.info("loading discovery providers.")
	}

	public static DiscoveryInitializer getInitializerInstance() {
		return INITIALIZER_INSTANCE
	}

  public static DiscoveryProcessor loadProvider(List<String> records, Configuration configuration) {
    def processor = new DiscoverUrlServices()
    processor.init(records, configuration)
    processor
	}
}
