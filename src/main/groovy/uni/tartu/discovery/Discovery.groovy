package uni.tartu.discovery

import groovy.util.logging.Slf4j
import org.springframework.util.StopWatch
import uni.tartu.storage.ResultSetWithStats

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:38 PM
 **/

@Slf4j
class Discovery {
  private final DiscoveryProcessor discoveryProvider

  Discovery(DiscoveryProcessor discoveryProvider) {
    this.discoveryProvider = discoveryProvider
  }

  ResultSetWithStats discover() {
    def watch = new StopWatch()

    watch.start('tokenize')
    def stepResult = discoveryProvider.tokenize()
    watch.stop()

    watch.start('analyze')
    stepResult = stepResult.analyze()
    watch.stop()

    watch.start('reduce')
    def finalResult = stepResult.reduce()
    watch.stop()

    println watch.prettyPrint()

    log.info("got intermediate results, building result set. my work here is done!")
    new ResultSetWithStats(finalResult)
  }
}