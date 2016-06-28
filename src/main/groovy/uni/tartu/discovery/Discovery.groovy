package uni.tartu.discovery

import groovy.util.logging.Slf4j
import uni.tartu.storage.ResultSetWithStats
import uni.tartu.utils.ThreadLocalStopWatch

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
    def watch = ThreadLocalStopWatch.get()

    watch.start('tokenize')
    def stepResult = discoveryProvider.tokenize()
    watch.stop()

    stepResult = stepResult.analyze()

    watch.start('reduce')
    def finalResult = stepResult.reduce()
    watch.stop()

    log.info("got intermediate results, building result set. my work here is done!")
    new ResultSetWithStats(finalResult)
  }
}