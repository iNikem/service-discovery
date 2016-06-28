package uni.tartu.utils

import org.springframework.util.StopWatch;

class ThreadLocalStopWatch {

  private static ThreadLocal<StopWatch> holder = ThreadLocal.withInitial({ new StopWatch() });

  public static StopWatch get() {
    return holder.get()
  }

  public static clear() {
    holder.remove()
  }
}
