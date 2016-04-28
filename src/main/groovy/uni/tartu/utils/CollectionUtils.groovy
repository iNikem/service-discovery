package uni.tartu.utils

import uni.tartu.configuration.Configuration

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

	public static void init(Configuration configuration) {
		initListHelpers(configuration)
	}

	private static void initListHelpers(Configuration configuration) {
		def filters = configuration.getFilters()
		List.metaClass.clean {
			delegate.removeAll { String entry -> filters.any { entry.contains(it) } }
			delegate
		}

		List.metaClass.polluted {
			delegate.findAll { String entry -> filters.any { entry.contains(it) } }
		}
	}
}
