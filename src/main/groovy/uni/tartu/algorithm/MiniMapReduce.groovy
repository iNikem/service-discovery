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

	public static final MultiMap multimap = new MultiMap()

	static class Mapper {
		public static def map(Map what, Closure how) {
			what.collect how
			multimap
		}

		public static void put(String key, int value) {
			multimap.put(key, value)
		}
	}

	static class Reducer {
		public static def reduce(Map what, Closure how) {
			what.inject [:], how
		}
	}
}
