package uni.tartu.algorithm

import uni.tartu.storage.AnalyzedUrlData
import uni.tartu.storage.RawUrlData
import uni.tartu.storage.WordIdHolderData

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 4:53 PM
 **/

class ScoreAnalyzer {

	private final List<AnalyzedUrlData> analyzedUrls

	ScoreAnalyzer(List<AnalyzedUrlData> analyzedUrls) {
		this.analyzedUrls = analyzedUrls
	}

	public void analyzeUrlScores() {
//		def params = analyzedUrls.findAll { it.score <= 0.001 }
//		def buckets = urlBuckets.groupBy { it.urlId }
//		def param = params.groupBy { it.urlId }
//		def res = ''
	}
}
