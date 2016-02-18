package uni.tartu.parser

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 8:59 PM
 **/

class Parser {
	public static def parse(File file) {
		def lines = file.readLines()
		def keys = lines[0].split(',').collect { trim(it) }
		lines[1..-1].collect { line ->
			def i = 0, values = line.split(',', -1)
			keys.inject([:]) { map, key ->
				map << [(key): trim(values[i++])]
			}
		}
	}

	private static def trim(String str) {
		str.replace("\"", '')
	}
}
