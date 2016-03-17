package uni.tartu.utils

import static uni.tartu.utils.StringUtils.split

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 6:48 PM
 **/

class CollectionUtils {
	private CollectionUtils() {}

	public static List<String> transform(List<String> urls, String delimiter) {
		urls.collect { split(it, delimiter).collect { """'$it'""" }.join('.') }
	}

	public static void init() {
		initListHelpers()
	}

	private static void initListHelpers() {
		List.metaClass.filter { String[] patterns ->
			delegate.removeAll { entry -> patterns.any { entry.contains(it) } }
			delegate
		}
	}
}
