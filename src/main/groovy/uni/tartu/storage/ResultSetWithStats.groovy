package uni.tartu.storage

import groovy.transform.ToString
import groovy.util.logging.Slf4j
import uni.tartu.algorithm.DelimiterAnalyzer
import uni.tartu.algorithm.tree.TreeBuilder
import uni.tartu.parser.Parser

import static uni.tartu.utils.CollectionUtils.transform

/**
 * author: lkokhreidze
 * date: 3/24/16
 * time: 4:33 PM
 **/

@Slf4j
@ToString
class ResultSetWithStats {

  final PollutedServiceResults ignoredUrlBasedServices
  final UrlBasedServiceResults urlBasedServiceResults

  final int totalSize

  ResultSetWithStats(IntermediateResultSet calculatedResult) {
    def urlBasedResults = calculatedResult
    def pollutedUrls = Parser.pollutedUrls
    this.ignoredUrlBasedServices = new PollutedServiceResults(pollutedUrls.size(), pollutedUrls)
    this.urlBasedServiceResults = new UrlBasedServiceResults(urlBasedResults.originalSize, urlBasedResults.generatedServices)
    this.totalSize = this.ignoredUrlBasedServices.count + this.urlBasedServiceResults.count
  }

  @ToString
  static class PollutedServiceResults {
    final List<String> services
    final int count
    final DiscoveryStatus status

    PollutedServiceResults(int count, List<String> services) {
      this.count = count
      this.services = services
      this.status = DiscoveryStatus.IGNORED
    }
  }

  @ToString
  static class UrlBasedServiceResults {

    final List<String> services
    final int count
    final DiscoveryStatus status

    UrlBasedServiceResults(int originalSize, List<String> generatedServices) {
      this.services = generatedServices
      this.count = originalSize
      this.status = DiscoveryStatus.ANALYZED
    }

    def getGraph() {
      log.info("started building graph from reduced URLs")
      if (!services) {
        return null
      }
      def treeBuilder = new TreeBuilder()
      String delimiter = DelimiterAnalyzer.getInstance().getDelimiter()
      def graph = treeBuilder.transform(transform(services, delimiter))
      log.info("finished with graph building")
      graph
    }

    public String getReductionPercentage() {
      if (getReducedServicesSize() == 0) {
        return "0.0%"
      }
      double d = 100 - ((getReducedServicesSize() * 100) / count)
      "${d.trunc(2)}%".toString()
    }

    public int getReducedServicesSize() {
      services.size()
    }
  }
}
