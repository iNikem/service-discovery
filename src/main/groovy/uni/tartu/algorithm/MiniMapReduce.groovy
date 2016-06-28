package uni.tartu.algorithm

import groovy.transform.CompileStatic
import uni.tartu.storage.MultiMap
import uni.tartu.storage.UrlInfoData
import uni.tartu.utils.ThreadLocalStopWatch

/**
 * author: lkokhreidze
 * date: 2/25/16
 * time: 7:09 PM
 **/

@CompileStatic
class MiniMapReduce {

  private static final MultiMap wordIdHolder = new MultiMap<String>()
  private static final MultiMap dataHolder = new MultiMap<Object>()

  public static void put(String key, Object value) {
    dataHolder.put(key, value)
  }

  public static void putUrlIdHolder(UrlInfoData data) {
    wordIdHolder.put(data.urlPart, data)
  }

  public static Map<String, List<UrlInfoData>> getUrlIdHolders() {
    wordIdHolder
  }

  static class Mapper {
    public static def map(Map what, Closure how) {
      ThreadLocalStopWatch.get().start('mapper-' + how.toString())
      dataHolder.clear()
      what.collect how
      ThreadLocalStopWatch.get().stop()
      dataHolder
    }
  }

  static class Reducer {
    public static def reduce(Map what, Closure how) {
      ThreadLocalStopWatch.get().start('reducer-' + how.toString())
      def result = what.inject [:], how
      ThreadLocalStopWatch.get().stop()
      result
    }
  }
}
