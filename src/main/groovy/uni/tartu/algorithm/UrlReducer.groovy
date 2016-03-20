package uni.tartu.algorithm

import uni.tartu.storage.AnalyzedUrlData
import uni.tartu.storage.MultiMap

import static uni.tartu.utils.StringUtils.replace

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 4:53 PM
 **/

class UrlReducer {

	private final List<AnalyzedUrlData> analyzedUrls

	private final DelimiterAnalyzer delimiterAnalyzer = DelimiterAnalyzer.getInstance()

	UrlReducer(List<AnalyzedUrlData> analyzedUrls) {
		this.analyzedUrls = analyzedUrls
	}

	public Map reduce() {
		def reducedUrls = new MultiMap()
		for (AnalyzedUrlData it in this.analyzedUrls) {
			def delimiter = delimiterAnalyzer.getDelimiter(it.accountId)
			def regexToInject = 'PARAMETER_PART'
			def currentStr = null
			if (it.urlPart.isEmpty()) {
				reducedUrls.put(it.accountId, it.staticParts.join(delimiter))
				break
			}
			it.urlPart.each { dynamic ->
				def inj = regexToInject
				if (currentStr) {
					currentStr = replace(dynamic, inj, currentStr, delimiter)
				} else {
					currentStr = replace(dynamic, inj, it.originalUrl, delimiter)
				}
			}
			reducedUrls.put(it.accountId, currentStr)
		}

		Map<String, List<String>> unique = [:]
		reducedUrls.each { String k, v ->
			unique.put(k, v.unique() as List<String>)
		}
		unique
	}

}
