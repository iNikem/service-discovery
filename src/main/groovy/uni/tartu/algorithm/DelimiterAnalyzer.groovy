package uni.tartu.algorithm

import groovy.util.logging.Slf4j
import uni.tartu.storage.RawUrlData

import static uni.tartu.utils.StringUtils.split

/**
 * author: lkokhreidze
 * date: 3/3/16
 * time: 4:58 PM
 **/

@Slf4j
class DelimiterAnalyzer {

	private static DelimiterAnalyzer ANALYZER_INSTANCE

	static {
		ANALYZER_INSTANCE = new DelimiterAnalyzer()
	}

	private static final float DELIMITER_CONFIDENCE_THRESHOLD = 90.0
	private final Map<String, String> analyzedDelimiters = new LinkedHashMap<String, String>()
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

	public String getDelimiter(String id = null) {
		def delimiter
		if (id) {
			delimiter = analyzedDelimiters[getKey(id)]
		} else if (analyzedDelimiters.size() == 1) {
			delimiter = analyzedDelimiters.values().getAt(0)
		}
		if (delimiter == null) {
			return knownDelimiters.slash
		}
		return delimiter
	}

	public void build(List<RawUrlData> services) {
		initialGrouping = services
			.groupBy { "${it.id}__${it.collectionId}".toString() }
			.collectEntries { k, v -> [(k): v.collect { "${it.rawUrl}__${it.urlId}" }] }
		analyze()
	}

	/*
	 * Idea of this method is to scan all services per account, and find mostly used delimiter with 90% confidence.
	 */

	@SuppressWarnings("GroovyAssignabilityCheck")
	private void analyze() {
		log.info("started to analyze delimiter per account")
		initialGrouping.groupBy {
			k, v -> k.split('__')[0]
		}.collectEntries { k, v ->
			[(k): v.values().flatten()]
		}.inject([:]) { map, k, List<String> v ->
			k = getKey(k);
			map << [(k): knownDelimiters.collectEntries { key, delimiter ->
				[(key): (v.count { it.contains(delimiter) } * 100) / v.size()]
			}]
		}.each { k, Map v ->
			def delimiter = null
			v.findAll { it.value > DELIMITER_CONFIDENCE_THRESHOLD }.each { delimiter = knownDelimiters[it.key] }
			analyzedDelimiters.put(k, delimiter)
		}
		analyzedDelimiters.each { k, v ->
			log.info("for account: {} got delimiter: {}", k, v)
		}
	}

	private static String getKey(String id) {
		if (id.contains("__")) {
			return split(id, "__")[0]
		}
		id
	}
}
