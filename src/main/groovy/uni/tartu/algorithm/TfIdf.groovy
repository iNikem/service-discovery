package uni.tartu.algorithm

import groovy.util.logging.Slf4j
import uni.tartu.configuration.Configuration
import uni.tartu.storage.AnalyzedUrlData
import uni.tartu.storage.UrlInfoData
import uni.tartu.utils.ThreadLocalStopWatch

import static uni.tartu.algorithm.MiniMapReduce.Mapper
import static uni.tartu.algorithm.MiniMapReduce.Reducer

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

  public Collection<AnalyzedUrlData> calculate(Map groupedData, long D, Configuration configuration) {
    def analyzedData = thirdIteration.perform(secondIteration.perform(firstIteration.perform(groupedData)))
    calculateTfIdf(analyzedData, D, configuration)
  }

  private Collection<AnalyzedUrlData> calculateTfIdf(Map data, long D, Configuration configuration) {
    log.info("started calculating TF-IDF score")
    ThreadLocalStopWatch.get().start('calculateTfIdf')
    double parameterThreshold = configuration.getParameterThreshold()
    def importanceThreshold = configuration.getImportanceThreshold()
    def wordIdHolder = MiniMapReduce.getUrlIdHolders()
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
      wordIdHolder.get(urlPart).each { UrlInfoData holder ->
        def analyzedUrl = analyzedUrls.get(holder.originalUrl)
        if (analyzedUrl == null) {
          analyzedUrl = new AnalyzedUrlData(id, holder.urlId, tfIdf, holder.originalUrl)
          analyzedUrls.put(holder.originalUrl, analyzedUrl)
        }
        if (tfIdf > parameterThreshold) {
          analyzedUrl.dynamicPartsToReplace.add(urlPart)
        } else {
          analyzedUrl.staticParts.add(urlPart)
        }
      }
    }
    ThreadLocalStopWatch.get().stop()
    if (importanceThreshold > 0) {
      return analyzedUrls.findAll { it.value.score > importanceThreshold }.values()
    }
    analyzedUrls.values()
  }

  public static class Step {

    private Closure mapper
    private Closure reducer

    public Step(Closure mapper, Closure reducer) {
      this.mapper = mapper
      this.reducer = reducer
    }

    Map perform(Map data) {
      Reducer.reduce(Mapper.map(data, mapper), reducer)
    }
  }
}
