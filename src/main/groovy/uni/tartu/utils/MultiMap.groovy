package uni.tartu.utils

/**
 * author: lkokhreidze
 * date: 2/29/16
 * time: 7:04 PM
 **/

class MultiMap extends HashMap<String, List<Integer>> {
	public void put(String key, Integer number) {
		List<Integer> current = get(key)
		if (!current) {
			current = new ArrayList<Integer>()
			super.put(key, current)
		}
		current.add(number)
	}
}
