package uni.tartu.algorithm

import groovy.util.logging.Slf4j
import uni.tartu.configuration.Configuration
import uni.tartu.storage.AnalyzedUrlData
import uni.tartu.storage.UrlInfoData

import static uni.tartu.algorithm.MiniMapReduce.Mapper
import static uni.tartu.algorithm.MiniMapReduce.Reducer
import static uni.tartu.algorithm.MiniMapReduce.getUrlIdHolders

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 6:33 PM
 **/

@Slf4j
class TfIdf {
  private final Map<String, AnalyzedUrlData> analyzedUrls = new HashMap<>()
  private Step firstIteration
  private Step secondIteration
  private Step thirdIteration

  TfIdf(Step firstIteration, Step secondIteration, Step thirdIteration) {
    this.firstIteration = firstIteration
    this.secondIteration = secondIteration
    this.thirdIteration = thirdIteration
  }

  public Map<String, AnalyzedUrlData> calculate(Map groupedData, long D, Configuration configuration) {
    def analyzedData = thirdIteration.perform(secondIteration.perform(firstIteration.perform(groupedData)))
    calculateTfIdf(analyzedData, D, configuration)
  }

  private Map<String, AnalyzedUrlData> calculateTfIdf(Map data, long D, Configuration configuration) {
    log.info("started calculating TF-IDF score")
    def parameterThreshold = configuration.getParameterThreshold()
    def importanceThreshold = configuration.getImportanceThreshold()
    def wordIdHolder = getUrlIdHolders()
    data.each { k, v ->
      def parts = (v as String).split(";"),
          n = parts[0] as int,
          N = parts[1] as int,
          m = parts[2] as int
      double tfIdf = ((n / N) as double) * Math.log((D / m) as double)
      if (D < m) {
        log.warn("something shitty happening, should't get negative log")
      }
      def ids = (k as String).split(";")
      def urlPart = ids[0]
      def id = ids[1]
      wordIdHolder.get(urlPart).each {
        def holder = it as UrlInfoData
        def urlId = holder.urlId
        if (analyzedUrls.containsKey(holder.originalUrl) && tfIdf > parameterThreshold) {
          analyzedUrls.get(holder.originalUrl).dynamicPartsToReplace.add(urlPart)
        } else if (!analyzedUrls.containsKey(holder.originalUrl) && tfIdf <= parameterThreshold) {
          def analyzedUrl = new AnalyzedUrlData(accountId: id, score: tfIdf, urlId: urlId, originalUrl: holder.originalUrl)
          analyzedUrl.staticParts = [urlPart]
          analyzedUrls.put(holder.originalUrl, analyzedUrl)
        }
        if (analyzedUrls.containsKey(holder.originalUrl) && tfIdf <= parameterThreshold) {
          analyzedUrls.get(holder.originalUrl).staticParts.add(urlPart)
        } else if (!analyzedUrls.containsKey(holder.originalUrl) && tfIdf > parameterThreshold) {
          def analyzedUrl = new AnalyzedUrlData(accountId: id, score: tfIdf, urlId: urlId, originalUrl: holder.originalUrl)
          analyzedUrl.dynamicPartsToReplace = [urlPart]
          analyzedUrls.put(holder.originalUrl, analyzedUrl)
        }
      }
    }
    if (importanceThreshold > 0) {
      return analyzedUrls.findAll { it.value.score > importanceThreshold }
    }
    analyzedUrls
  }

  public static class Step {

    private Closure mapper
    private Closure reducer

    private Step(Closure mapper, Closure reducer) {
      this.mapper = mapper
      this.reducer = reducer
    }

    Map perform(Map data) {
      Reducer.reduce(Mapper.map(data, mapper), reducer)
    }
  }
}
