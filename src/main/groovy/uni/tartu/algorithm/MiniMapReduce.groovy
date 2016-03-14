package uni.tartu.algorithm

import groovy.transform.CompileStatic
import uni.tartu.storage.UrlInfoData
import uni.tartu.storage.MultiMap

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 7:09 PM
 **/

@CompileStatic
class MiniMapReduce {

	private static final MultiMap wordIdHolder = new MultiMap()
	private static final MultiMap dataHolder = new MultiMap()

	public static void put(String key, Object value) {
		dataHolder.put(key, value)
	}

	public static void putUrlIdHolder(String urlPart, UrlInfoData data) {
		wordIdHolder.put(urlPart, data)
	}

	public static Map getUrlIdHolders() {
		wordIdHolder
	}

	static class Mapper {
		public static def map(Map what, Closure how) {
			dataHolder.clear()
			what.collect how
			dataHolder
		}
	}

	static class Reducer {
		public static def reduce(Map what, Closure how) {
			what.inject [:], how
		}
	}
}
