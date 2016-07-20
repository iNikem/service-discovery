package uni.tartu.parser

import groovy.util.logging.Slf4j
import uni.tartu.configuration.Configuration
import uni.tartu.utils.CollectionUtils

import static uni.tartu.utils.StringUtils.trim

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 8:59 PM
 **/

@Slf4j
class Parser {

  public static List<String> readUrls(File source) {
    source.readLines().collect { trim(it.split(',')[3]) }
  }

  public static List<String> parse(File what, Configuration configuration) {
    CollectionUtils.init(configuration)
    def lines = what.readLines()
    def keys = lines[0].split(',').collect { trim(it) }
    def records = lines[1..-1].collect { line ->
      def i = 0, values = line.split(',', -1)
      keys.inject([:]) { m, k -> m << [(k): trim(values[i++])] }
    }.collect {
      "${it.accountId};${it.serviceName}"
    }

    log.info("Total {} urls read", records.size())
    def polluted = records.polluted() as List<String>
    log.info("Found {} polluted URLs. Current filter: {}", polluted.size(), configuration.getFilters().join(", "))

    def clean = records.clean() as List<String>
    checkValidity(clean, configuration)
    log.info("{} urls read and ready for further processing", clean.size())
    return clean
  }

  public static List<String> parse(String id, List<String> records, Configuration configuration) {
    CollectionUtils.init(configuration)

    log.info("Total {} urls read", records.size())

    def polluted = records.polluted() as List<String>
    log.info("Found {} polluted URLs. Current filter: {}", polluted.size(), configuration.getFilters().join(", "))

    def clean = records.clean().collect { "$id;$it".toString() } as List<String>
    checkValidity(clean, configuration)
    log.info("{} urls read and ready for further processing", clean.size())
    return clean
  }

  private static List<String> checkValidity(def records, Configuration configuration) {
    if (records.size() < configuration.getDocumentSize()) {
      //TODO This class should not have any knowledge about default size
      throw new RuntimeException("records size (after cleaning): ${records.size()} can't be less that documentSize: ${configuration.getDocumentSize()}. " +
          "If you haven't specified discovery.documentSize property than default value of 1000 will be used")
    }
    return records
  }
}
