package uni.tartu.algorithm

import groovy.util.logging.Slf4j

/**
 * author: lkokhreidze
 * date: 4/27/16
 * time: 3:08 PM
 **/

@Slf4j
class ServiceGrouping {
	private final int maxRange
	private final int minRange

	ServiceGrouping(int size) {
		this.maxRange = getMaxRange(size)
		this.minRange = 1
	}

	public int generateGroupingId() {
		minRange + (int) (Math.random() * ((maxRange - 1) + 1))
	}

	private static int getMaxRange(int size) {
		def maxRange = size / 1000 as int
		log.info("generated maxRange with value: {} for data size: {}", maxRange, size)
		maxRange
	}
}
