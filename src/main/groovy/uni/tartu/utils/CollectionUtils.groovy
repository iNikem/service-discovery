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
		urls.collect { split(it, delimiter).collect { """'${it.replace("'", "")}'""" }.join('.') }
	}

	public static void init() {
		initListHelpers()
	}

	private static void initListHelpers() {
		List.metaClass.clean {
			delegate.removeAll { String entry -> ['.html', '$', '.php', '.js', '.txt', '.css', '.jtp', '.ico', '.gif', '.text', '.pdf'].any { entry.contains(it) } }
			delegate
		}

		List.metaClass.polluted {
			delegate.findAll { String entry -> ['.html', '$', '.php', '.js', '.txt', '.css', '.jtp', '.ico', '.gif', '.text', '.pdf'].any { entry.contains(it) } }
		}
	}
}
