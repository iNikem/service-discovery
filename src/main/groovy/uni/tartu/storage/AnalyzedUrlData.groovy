package uni.tartu.storage

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 5:08 PM
 **/

class AnalyzedUrlData {
	public String id
	public String urlPart
	public double score

	@Override
	public String toString() {
		return "$id;$urlPart - $score".toString();
	}
}
