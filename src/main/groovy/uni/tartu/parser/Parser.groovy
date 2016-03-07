package uni.tartu.parser

import uni.tartu.utils.CollectionUtils

import static uni.tartu.utils.StringUtils.trim

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 8:59 PM
 **/

class Parser {
	public static List<String> parse(Closure<File> what) {
		CollectionUtils.init()
		def lines = what().readLines()
		def keys = lines[0].split(',').collect { trim(it) }
		lines[1..-1].collect { line ->
			def i = 0, values = line.split(',', -1)
			keys.inject([:]) { m, k -> m << [(k): trim(values[i++])] }
		}.collect { "${it.accountId};${it.serviceName}" }.filter '.html', '$'
	}
}
