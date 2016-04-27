package uni.tartu.algorithm

import uni.tartu.configuration.Configuration
import uni.tartu.storage.AnalyzedUrlData
import uni.tartu.storage.UrlInfoData

import static uni.tartu.algorithm.MiniMapReduce.*

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 6:33 PM
 **/

class TfIdf {
	private final Map<String, AnalyzedUrlData> analyzedUrls = new HashMap<>()
	private FirstIteration firstIteration
	private SecondIteration secondIteration
	private ThirdIteration thirdIteration

	TfIdf(FirstIteration firstIteration, SecondIteration secondIteration, ThirdIteration thirdIteration) {
		this.firstIteration = firstIteration
		this.secondIteration = secondIteration
		this.thirdIteration = thirdIteration
	}

	public Map<String, AnalyzedUrlData> calculate(Map groupedData, long D, Configuration configuration) {
		def analyzedData = thirdIteration.perform(secondIteration.perform(firstIteration.perform(groupedData)))
		calculateTfIdf(analyzedData, D, configuration)
	}

	private Map<String, AnalyzedUrlData> calculateTfIdf(Map data, long D, Configuration configuration) {
		def parameterThreshold = configuration.getParameterThreshold()
		def importanceThreshold = configuration.getImportanceThreshold()

		System.out.println(parameterThreshold)
		System.out.println(importanceThreshold)
		def wordIdHolder = getUrlIdHolders()
		data.each { k, v ->
			def parts = (v as String).split(";"),
				 n = parts[0] as int,
				 N = parts[1] as int,
				 m = parts[2] as int
			double tfIdf = ((n / N) as double) * Math.log((D / m) as double)
			def ids = (k as String).split(";")
			def id = ids[1]
			def urlPart = ids[0]
			wordIdHolder.get(urlPart).each {
				def holder = it as UrlInfoData
				def urlId = holder.urlId
				if (analyzedUrls.containsKey(holder.originalUrl) && tfIdf <= parameterThreshold) {
					analyzedUrls.get(holder.originalUrl).urlPart.add(urlPart)
				} else if (!analyzedUrls.containsKey(holder.originalUrl) && tfIdf <= parameterThreshold) {
					def analyzedUrl = new AnalyzedUrlData(accountId: id, score: tfIdf, urlId: urlId, originalUrl: holder.originalUrl)
					analyzedUrl.urlPart = [urlPart]
					analyzedUrls.put(holder.originalUrl, analyzedUrl)
				}
				if (analyzedUrls.containsKey(holder.originalUrl) && tfIdf > parameterThreshold) {
					analyzedUrls.get(holder.originalUrl).staticParts.add(urlPart)
				} else if (!analyzedUrls.containsKey(holder.originalUrl) && tfIdf > parameterThreshold) {
					def analyzedUrl = new AnalyzedUrlData(accountId: id, score: tfIdf, urlId: urlId, originalUrl: holder.originalUrl)
					analyzedUrl.staticParts = [urlPart]
					analyzedUrls.put(holder.originalUrl, analyzedUrl)
				}
			}
		}
		if (importanceThreshold > 0) {
			return analyzedUrls.findAll { it.value.score > importanceThreshold }
		}
		analyzedUrls
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
