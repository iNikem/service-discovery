package uni.tartu.utils

/**
 * author: lkokhreidze
 * date: 2/29/16
 * time: 7:04 PM
 **/

class MultiMap extends HashMap<String, List<Object>> {
	public void put(String key, Object value) {
		List<Object> current = get(key)
		if (!current) {
			current = new ArrayList<Object>()
			super.put(key, current)
		}
		current.add(value)
	}
}
