package uni.tartu.utils

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 6:44 PM
 **/

class StringUtils {

	private static final String PARAMETER_INDICATOR = '&'

	private StringUtils() {}

	public static String[] split(final String input, final String delimiter) {
		input.split("\\$delimiter")
	}

	public static String trim(String str) {
		str.replace('\"', '')
	}

	/**
	 * performs initial and final data maintenance.
	 * removes params from last index of separator.
	 * @param str - raw url data
	 * @param separator - optional parameter. Indicates separator in the url.
	 * @return lower case url without parameter values.
	 */
	public static String clean(String str, String separator = null) {
		if (!separator) {
			return str.replaceFirst("/", "").toLowerCase()
		}
		def i = str.indexOf("?")
		if (i != -1) {
			return str.substring(0, i)
		}
		int index = str.indexOf(PARAMETER_INDICATOR)
		if (index != -1) {
			def dot = str.indexOf(separator)
			if (dot == -1) {
				return str
			}
			int lDot = str.lastIndexOf(separator, index)
			if (lDot == -1) {
				return str
			}
			def j = null
			if (index > lDot) {
				j = index
			} else {
				while (index >= 0) {
					index = str.indexOf(PARAMETER_INDICATOR, index + 1)
					if (index != -1 && index > lDot) {
						j = index
						break
					}
				}
			}
			if (j) {
				return str.substring(0, j)
			}
		}
		return str
	}

}
