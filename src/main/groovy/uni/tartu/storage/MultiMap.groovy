package uni.tartu.storage

/**
 * author: lkokhreidze
 * date: 2/29/16
 * time: 7:04 PM
 **/

class MultiMap<T> extends HashMap<String, List<T>> {
	public void put(String key, T value) {
		List<T> current = get(key)
		if (!current) {
			current = new ArrayList<T>()
			super.put(key, current)
		}
		current.add(value)
	}
}
