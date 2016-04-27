package uni.tartu.discovery

import groovy.util.logging.Slf4j
import uni.tartu.storage.ResultSetWithStats

import java.security.SecureRandom

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
		def cleanedData = parse(identifier, records)
		return doProcess(cleanedData)
	}

	public ResultSetWithStats discover(File csvFile) {
		if (!csvFile.exists()) {
			throw new IOException("csv file doesn't exist!")
		}
		log.info("started cleaning original csv file")
		def cleanedData = parse { csvFile }
		return doProcess(cleanedData)
	}

	private static ResultSetWithStats doProcess(List<String> data) {
		try {
			log.info("started service discovery process. go now and pray!")
			def initializer = DiscoveryInitializer.getInitializerInstance()
			def discovery = new Discovery(initializer.loadProviders(data))
			return discovery.discover()
		} catch (Exception e) {
			log.error("failed to analyze", e)
			return null
		}
	}
}
