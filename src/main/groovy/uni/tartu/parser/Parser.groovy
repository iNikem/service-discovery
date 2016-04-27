package uni.tartu.parser

import uni.tartu.utils.CollectionUtils

import static uni.tartu.utils.StringUtils.trim

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 8:59 PM
 **/

class Parser {

	private static final List<String> pollutedUrls = []

	public static List<String> parse(Closure<File> what) {
		pollutedUrls.clear()
		CollectionUtils.init()
		def lines = what().readLines()
		def keys = lines[0].split(',').collect { trim(it) }
		def records = lines[1..-1].collect { line ->
			def i = 0, values = line.split(',', -1)
			keys.inject([:]) { m, k -> m << [(k): trim(values[i++])] }
		}.collect {
			"${it.accountId};${it.serviceName}"
		}
		checkValidity(records)
		def polluted = records.polluted() as List<String>
		def clean = records.clean() as List<String>
		pollutedUrls.addAll(polluted)
		clean
	}

	public static List<String> parse(String id, List<String> records) {
		checkValidity(records)
		pollutedUrls.clear()
		CollectionUtils.init()
		pollutedUrls.addAll(records.polluted() as List<String>)
		records.clean().collect { "$id;$it".toString() }
	}

	public static getPollutedUrls() {
		pollutedUrls
	}

	private static def checkValidity(def records) {
		if (records.size() < 1000) {
			throw new RuntimeException("I can't work with the data less than a 1000 services")
		}
	}
}
