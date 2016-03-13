package uni.tartu.algorithm

import uni.tartu.storage.RawUrlData

import static uni.tartu.utils.StringUtils.split

/**
 * author: lkokhreidze
 * date: 3/3/16
 * time: 4:58 PM
 **/

class DelimiterAnalyzer {

	private static DelimiterAnalyzer ANALYZER_INSTANCE = new DelimiterAnalyzer()

	private static final float DELIMITER_CONFIDENCE_THRESHOLD = 90.0
	private final Map<String, String> analyzedDelimiters = [:]
	private Map<?, ?> initialGrouping
	private final def knownDelimiters = [dot  : '.',
													 slash: '/']

	private DelimiterAnalyzer() {}

	public static DelimiterAnalyzer getInstance() {
		return ANALYZER_INSTANCE
	}

	public def getInitialGroups() {
		initialGrouping as Map<String, List<String>>
	}

	public String getDelimiter(String id) {
		def d = analyzedDelimiters[(id).split("_")[0]]
		d
	}

	public void build(List<RawUrlData> services) {
		initialGrouping = services
			.groupBy { "${it.id}__${it.urlId}".toString() }
			.collectEntries { k, v -> [(k): v.collect { it.rawUrl }] }
		analyze()
	}

	/*
	 * Idea of this method is to scan all services per account, and find mostly used delimiter with 90% confidence.
	 */

	@SuppressWarnings("GroovyAssignabilityCheck")
	private void analyze() {
		initialGrouping.inject([:]) { map, k, List<String> v ->
			k = (k as String).split("__")[0]
			map << [(k): knownDelimiters.collectEntries { key, delimiter ->
				[(key): (v.count { it.contains(delimiter) } * 100) / v.size()]
			}]
		}.each { k, Map v ->
			def delimiter = null
			v.findAll { it.value > DELIMITER_CONFIDENCE_THRESHOLD }.each { delimiter = knownDelimiters[it.key] }
			analyzedDelimiters.put(k, delimiter)
		}
	}
}
