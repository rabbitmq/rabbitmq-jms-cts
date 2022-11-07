package org.exolab.jmscts.core;

import java.time.Duration;

public abstract class Utils {

  private Utils() {
  }

  public static <T> T retryUntilNotNull(
      Duration timeout, CallableSupplier<T> condition)
      throws Exception {
    T result = condition.get();
    if (result != null) {
      return result;
    }
    int waitTime = 100;
    int waitedTime = 0;
    long timeoutInMs = timeout.getSeconds();
    while (waitedTime <= timeoutInMs) {
      Thread.sleep(waitTime);
      waitedTime += waitTime;
      try {
        result = condition.get();
        if (condition != null) {
          return result;
        }
      } catch (Exception e) {
      }
    }
    return null;
  }

  @FunctionalInterface
  public interface CallableSupplier<T> {

    T get() throws Exception;
  }
}
