package uni.tartu.algorithm

import static uni.tartu.algorithm.MiniMapReduce.Mapper
import static uni.tartu.algorithm.MiniMapReduce.Reducer

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 6:33 PM
 **/

class TfIdf {

	private FirstIteration firstIteration
	private SecondIteration secondIteration
	private ThirdIteration thirdIteration

	TfIdf(FirstIteration firstIteration,
			SecondIteration secondIteration,
			ThirdIteration thirdIteration) {
		this.firstIteration = firstIteration
		this.secondIteration = secondIteration
		this.thirdIteration = thirdIteration
	}

	public Map calculate(Map groupedData) {
		def data = thirdIteration.perform(secondIteration.perform(firstIteration.perform(groupedData)))
		calculateTfIdf(data, groupedData.size())
	}

	private static Map calculateTfIdf(Map data, int D) {
		data.collectEntries { k, v ->
			def parts = (v as String).split(";"),
				 n = parts[0] as int,
				 N = parts[1] as int,
				 m = parts[2] as int
			double tfIdf = ((n / N) as double) * Math.log((D / m) as double)
			[(k): tfIdf]
		}
	}

	static class FirstIteration {

		private Closure mapper
		private Closure reducer

		private FirstIteration() {}

		static FirstIteration build(Closure mapper, Closure reducer) {
			def firstIteration = new FirstIteration()
			firstIteration.mapper = mapper
			firstIteration.reducer = reducer
			firstIteration
		}

		Map perform(Map data) {
			Reducer.reduce(Mapper.map(data, mapper), reducer)
		}
	}

	static class SecondIteration {

		private Closure mapper
		private Closure reducer

		private SecondIteration() {}

		static SecondIteration build(Closure mapper, Closure reducer) {
			def secondIteration = new SecondIteration()
			secondIteration.mapper = mapper
			secondIteration.reducer = reducer
			secondIteration
		}

		Map perform(Map data) {
			Reducer.reduce(Mapper.map(data, mapper), reducer)
		}
	}

	static class ThirdIteration {

		private Closure mapper
		private Closure reducer

		private ThirdIteration() {}

		static ThirdIteration build(Closure mapper, Closure reducer) {
			def thirdIteration = new ThirdIteration()
			thirdIteration.mapper = mapper
			thirdIteration.reducer = reducer
			thirdIteration
		}

		Map perform(Map data) {
			Reducer.reduce(Mapper.map(data, mapper), reducer)
		}
	}
}
