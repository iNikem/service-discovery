package uni.tartu

import org.springframework.util.StopWatch
import spock.lang.Specification
import uni.tartu.configuration.Configuration
import uni.tartu.discovery.Discovery
import uni.tartu.discovery.DiscoveryInitializer
import uni.tartu.utils.ThreadLocalStopWatch

import static uni.tartu.parser.Parser.parse
import static uni.tartu.parser.Parser.readUrls

class MainTest extends Specification {

  def "new normalizer"() {
    setup:
    def normalizer = new UrlNormalizer()
    def records = readUrls(new File(Main.class.getResource('/k8g0ta8m6e7gsb2afhq6qm5efh.csv').toURI()))

    when:
    def normalizedUrls = records.collect {
      normalizer.normalize(it)
    }

    then:
    normalizedUrls.last() == 'connect/PARAMETER_PART/PARAMETER_PART/PARAMETER_PART'
  }

  def "old map-reduce"() {
    setup:
    def conf = new Configuration()
    ThreadLocalStopWatch.clear()

    def records = parse(new File(Main.class.getResource('/k8g0ta8m6e7gsb2afhq6qm5efh.csv').toURI()), conf)

    def globalWatch = new StopWatch()

    when:
    globalWatch.start('total')

    def discovery = new Discovery(DiscoveryInitializer.getInitializerInstance().loadProvider(records, conf))
    def result = discovery.discover()

    globalWatch.stop()
    println globalWatch.prettyPrint()
    println ThreadLocalStopWatch.get().prettyPrint()

    List<String> currentResults = result.urlBasedServiceResults.services.sort()

    then:
    new File('original-results-sorted.txt').readLines() == currentResults
  }
}
