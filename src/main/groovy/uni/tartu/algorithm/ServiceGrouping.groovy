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

	ServiceGrouping(int size, int configurationSize) {
		this.maxRange = getMaxRange(size, configurationSize)
		this.minRange = 1
	}

	public int generateGroupingId() {
		minRange + (int) (Math.random() * ((maxRange - 1) + 1))
	}

	private static int getMaxRange(int size, int configurationSize) {
		if (configurationSize > 0) {
			def maxRange = size / configurationSize as int
			log.info("generated maxRange with value: {} for defined maxGroupSize in propeties: {}", maxRange, configurationSize)
			return maxRange
		} else {
			def maxRange = size / 1000 as int
			log.info("generated maxRange with value: {} for data size: {}", maxRange, size)
			return maxRange
		}
	}
}
