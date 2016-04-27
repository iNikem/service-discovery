package uni.tartu.algorithm

import uni.tartu.storage.AnalyzedUrlData

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

	public List<String> reduce() {
		List<String> reducedUrls = []
		for (AnalyzedUrlData it in this.analyzedUrls) {
			def delimiter = delimiterAnalyzer.getDelimiter(it.accountId)
			def regexToInject = 'PARAMETER_PART'
			def currentStr = it.originalUrl
			if (!it.staticParts.isEmpty() && !it.urlPart.isEmpty()) {
				it.urlPart.each { dynamic ->
					def inj = delimiter + regexToInject + delimiter
					currentStr = replace(dynamic, inj, currentStr, delimiter)
				}
				reducedUrls.add(currentStr)
			}
		}
		reducedUrls.collect().unique()
	}

}
