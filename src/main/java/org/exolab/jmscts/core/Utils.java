package org.exolab.jmscts.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

  public static <T> List<T> retryUntilExpectedCount(Duration timeout,
      CallableSupplier<List<T>> operation, int expected) throws Exception {
    List<T> result = operation.get();
    if (result == null || result.size() < expected) {
      List<T> accumulator = result == null ? new ArrayList<>(expected) :
          new ArrayList<>(result);
      result = Utils.retryUntilNotNull(timeout,
          () -> {
            List<T> messages = operation.get();
            if (messages != null) {
              accumulator.addAll(messages);
              if (accumulator.size() == expected) {
                return accumulator;
              }
            }
            return null;
          });
    }
    return result;
  }

  @FunctionalInterface
  public interface CallableSupplier<T> {

    T get() throws Exception;
  }

}
