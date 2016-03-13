package uni.tartu.storage

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 5:08 PM
 **/

class AnalyzedUrlData {
	public String accountId
	public int urlId
	public List<String> urlPart
	public double score
	public String originalUrl

	@Override
	public String toString() {
		return "$accountId;$originalUrl - ($urlId) params: ${urlPart}".toString();
	}
}
