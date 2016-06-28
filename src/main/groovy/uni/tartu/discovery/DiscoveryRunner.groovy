package uni.tartu.discovery

import groovy.util.logging.Slf4j
import java.security.SecureRandom
import uni.tartu.configuration.Configuration
import uni.tartu.storage.ResultSetWithStats

import static uni.tartu.parser.Parser.parse

/**
 * author: lkokhreidze
 * date: 4/27/16
 * time: 11:11 PM
 **/

@Slf4j
class DiscoveryRunner {
	private final SecureRandom random = new SecureRandom();

	public ResultSetWithStats discover(List<String> records) {
		log.info("started cleaning original data")
		def identifier = new BigInteger(130, random).toString(32)
		Configuration configuration = new Configuration()
		def cleanedData = parse(identifier, records, configuration)
		return doProcess(cleanedData, configuration)
	}

	public static ResultSetWithStats discover(File csvFile) {
		if (!csvFile.exists()) {
			throw new IOException("csv file doesn't exist!")
		}
		Configuration configuration = new Configuration()
		log.info("started cleaning original csv file")
		def cleanedData = parse(csvFile, configuration)
		return doProcess(cleanedData, configuration)
	}

	private static ResultSetWithStats doProcess(List<String> data, Configuration configuration) {
		try {
			log.info("started service discovery process. go now and pray!")
			def initializer = DiscoveryInitializer.getInitializerInstance()
			def discovery = new Discovery(initializer.loadProvider(data, configuration))
			return discovery.discover()
		} catch (Exception e) {
			log.error("failed to analyze", e)
			return null
		}
	}
}
