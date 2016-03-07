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
	 * TODO: need to pass separator as parameter
	 * performs data maintenance.
	 * removes params from last index of separator.
	 * @param str - raw url data
	 * @return lower case url without parameter values.
	 */
	public static String clean(String str) {
		str = str.replaceFirst("/", "").toLowerCase()
		def i = str.indexOf("?")
		if (i != -1) {
			return str.substring(0, i)
		}
		int index = str.indexOf(PARAMETER_INDICATOR)
		if (index != -1) {
			def dot = str.indexOf(".")
			if (dot == -1) {
				return str
			}
			int lDot = str.lastIndexOf(".", index);
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
						break;
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
