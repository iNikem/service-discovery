package uni.tartu.algorithm

import uni.tartu.storage.AnalyzedUrlData

import static uni.tartu.algorithm.DelimiterAnalyzer.getInstance
import static uni.tartu.utils.StringUtils.replace
import static uni.tartu.utils.TextDumper.dump

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 4:53 PM
 **/

class RegexGenerator {

	private final List<AnalyzedUrlData> analyzedUrls

	private final DelimiterAnalyzer delimiterAnalyzer = getInstance()

	RegexGenerator(List<AnalyzedUrlData> analyzedUrls) {
		this.analyzedUrls = analyzedUrls
	}

	public void generate() {
		def generatedRegex = []
		for (AnalyzedUrlData it in this.analyzedUrls) {
			def delimiter = delimiterAnalyzer.getDelimiter(it.accountId)
			def regexToInject = '(.*)'
			def currentStr = null
			if (it.urlPart.isEmpty()) {
				generatedRegex.add(it.staticParts.join(delimiter))
				break
			}
			it.urlPart.each { dynamic ->
				if (currentStr) {
					currentStr = replace(dynamic, regexToInject, currentStr)
				} else {
					currentStr = replace(dynamic, regexToInject, it.originalUrl)
				}
			}
			generatedRegex.add(currentStr)
		}
		dump("/Users/lkokhreidze/Desktop", generatedRegex.unique())
	}

}
