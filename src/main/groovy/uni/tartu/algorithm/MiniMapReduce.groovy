package uni.tartu.algorithm

import groovy.transform.CompileStatic
import uni.tartu.utils.MultiMap

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 7:09 PM
 **/

@CompileStatic
class MiniMapReduce {

	private static final MultiMap multimap = new MultiMap()

	public static void put(String key, Object value) {
		multimap.put(key, value)
	}

	static class Mapper {
		public static def map(Map what, Closure how) {
			multimap.clear()
			what.collect how
			multimap
		}
	}

	static class Reducer {
		public static def reduce(Map what, Closure how) {
			what.inject [:], how
		}
	}
}
