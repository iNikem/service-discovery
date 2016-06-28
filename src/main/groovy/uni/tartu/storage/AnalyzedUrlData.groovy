package uni.tartu.storage

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 5:08 PM
 **/

class AnalyzedUrlData {
	public String accountId
	public int urlId
	public List<String> dynamicPartsToReplace = []
	public List<String> staticParts = []
	public double score
	public String originalUrl

	AnalyzedUrlData(String accountId, int urlId, double score, String originalUrl) {
		this.accountId = accountId
		this.urlId = urlId
		this.score = score
		this.originalUrl = originalUrl
	}

	@Override
	public String toString() {
		return "$accountId;$originalUrl - ($urlId) params: ${dynamicPartsToReplace} static parts: ${staticParts}".toString();
	}
}
