package uni.tartu

import uni.tartu.configuration.Configuration
import uni.tartu.discovery.Discovery
import uni.tartu.discovery.DiscoveryInitializer
import uni.tartu.utils.ThreadLocalStopWatch

import static uni.tartu.parser.Parser.parse

/**
 * author: lkokhreidze
 * date: 2/18/16
 * time: 7:33 PM
 **/

class Main {
  public static void main(String[] args) {
    def conf = new Configuration()
    runFor("k8g0ta8m6e7gsb2afhq6qm5efh", conf)
    runFor("1d8v0kuc4vcf08n96qa5d2jh73", conf)
  }

  protected static void runFor(String dataset, Configuration conf) {
    ThreadLocalStopWatch.clear()

    def records = parse(new File(Main.class.getResource("/" + dataset + ".csv").toURI()), conf)
    def discovery = new Discovery(DiscoveryInitializer.getInitializerInstance().loadProvider(records, conf))
    def result = discovery.discover()
    println ThreadLocalStopWatch.get().prettyPrint()
    println result
  }
}
