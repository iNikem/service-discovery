package uni.tartu.algorithm

import uni.tartu.storage.AnalyzedUrlData
import uni.tartu.storage.RawUrlData

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 4:53 PM
 **/

class ScoreAnalyzer {

	private final List<RawUrlData> urlBuckets
	private final List<AnalyzedUrlData> analyzedUrls

	ScoreAnalyzer(List<RawUrlData> urlBuckets, List<AnalyzedUrlData> analyzedUrls) {
		this.urlBuckets = urlBuckets
		this.analyzedUrls = analyzedUrls
	}
}
