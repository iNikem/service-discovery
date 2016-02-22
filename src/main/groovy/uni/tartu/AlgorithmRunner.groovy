package uni.tartu

import uni.tartu.discovery.Discovery
import uni.tartu.discovery.DiscoveryInitializer
import uni.tartu.util.Helpers

import static uni.tartu.parser.Parser.parse

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:33 PM
 **/

class AlgorithmRunner {
	public static void main(String[] args) {
		Helpers.init()
		def discovery = new Discovery(new DiscoveryInitializer(parse {
			new File(AlgorithmRunner
				.class
				.getClassLoader()
				.getResource("test-data-1.csv")
				.toURI())
		}).loadProviders())
		discovery.tokenize()
	}
}
