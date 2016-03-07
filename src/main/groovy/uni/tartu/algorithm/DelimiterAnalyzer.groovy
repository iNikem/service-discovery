package uni.tartu.algorithm

/**
 * author: lkokhreidze
 * date: 3/3/16
 * time: 4:58 PM
 **/

class DelimiterAnalyzer {
	private final List<String> services

	private final def knownDelimiters = [dot  : '.',
													 slash: '/']

	public DelimiterAnalyzer(List<String> services) {
		this.services = services
	}

	/*
	 * Idea of this method is to scan all services. find first appearance of delimiter candidate ['/', '.', '-']
	 */

	public List tokenize() {
		def result = knownDelimiters.collectEntries { k, v ->
			[(k): this.services.count { it.contains(v) }]
		}.each { k, v ->

		}
		def percentage = (result * 100) / this.services.size()
		def a = ''
		[]
	}
}
