package uni.tartu.storage

/**
 * author: lkokhreidze
 * date: 3/11/16
 * time: 7:15 PM
 **/

class UrlInfoData {
	public String urlPart
	public int urlId
	public String originalUrl

	UrlInfoData(String urlPart, int urlId, String originalUrl) {
		this.urlPart = urlPart
		this.urlId = urlId
		this.originalUrl = originalUrl
	}
}
