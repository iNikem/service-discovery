package uni.tartu.utils

/**
 * author: lkokhreidze
 * date: 2/22/16
 * time: 6:48 PM
 **/

class CollectionUtils {
	private CollectionUtils() {}

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
