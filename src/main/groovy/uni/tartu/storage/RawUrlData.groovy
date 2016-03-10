package uni.tartu.storage

/**
 * author: lkokhreidze
 * date: 3/10/16
 * time: 4:56 PM
 **/

class RawUrlData {

	public String id
	public String rawUrl

	@Override
	public String toString() {
		return "$id;$rawUrl".toString()
	}
}
