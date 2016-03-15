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

	public static String[] getKey(String str) {
		int i = str.lastIndexOf("__")
		if (i == -1) {
			return []
		}
		def res = [str.substring(0, i), str.substring(i + 2)]
		res
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

	public static String replace(String oldStr, String newStr, String input) {
		StringBuilder sb = new StringBuilder();
		int i;
		int prev = 0;
		while ((i = input.indexOf(oldStr, prev)) >= 0) {
			sb.append(input.substring(prev, i)).append(newStr);
			prev = i + oldStr.length();
		}
		sb.append(input.substring(prev));
		return sb.toString();
	}

}
