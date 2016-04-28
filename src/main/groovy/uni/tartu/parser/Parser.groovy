package uni.tartu.parser

import uni.tartu.configuration.Configuration
import uni.tartu.utils.CollectionUtils

import static uni.tartu.utils.StringUtils.trim

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 8:59 PM
 **/

class Parser {

	private static final List<String> pollutedUrls = []

	public static List<String> parse(File what, Configuration configuration) {
		pollutedUrls.clear()
		CollectionUtils.init(configuration)
		def lines = what.readLines()
		def keys = lines[0].split(',').collect { trim(it) }
		def records = lines[1..-1].collect { line ->
			def i = 0, values = line.split(',', -1)
			keys.inject([:]) { m, k -> m << [(k): trim(values[i++])] }
		}.collect {
			"${it.accountId};${it.serviceName}"
		}
		def polluted = records.polluted() as List<String>
		def clean = records.clean() as List<String>
		pollutedUrls.addAll(polluted)
		checkValidity(clean, configuration)
	}

	public static List<String> parse(String id, List<String> records, Configuration configuration) {
		pollutedUrls.clear()
		CollectionUtils.init(configuration)
		pollutedUrls.addAll(records.polluted() as List<String>)
		checkValidity(records.clean().collect { "$id;$it".toString() }, configuration)
	}

	public static getPollutedUrls() {
		pollutedUrls
	}

	private static List<String> checkValidity(def records, Configuration configuration) {
		if (records.size() < configuration.getMaxGroupSize()) {
			throw new RuntimeException("records size (after cleaning): ${records.size()} can't be less that maxGroupingSize: ${configuration.getMaxGroupSize()}. " +
				"If tou haven't specified discovery.maxGroupSize property than default value of 1000 will be used")
		}
		return records
	}
}
