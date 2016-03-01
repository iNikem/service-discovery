package uni.tartu.utils

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 6:44 PM
 **/

class StringUtils {
	private StringUtils() {}

	public static String[] split(final String input, final String delimiter) {
		input.replaceFirst("^$delimiter", "").split(delimiter);
	}
}
