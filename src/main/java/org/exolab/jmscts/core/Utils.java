package org.exolab.jmscts.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Utils {

  private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

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
    if (expected != 0 && (result == null || result.size() < expected)) {
      LOGGER.warn("Expected result is {} message(s) but got {} on the first try, retrying...",
          expected,
          result == null ? "null" : result.size());
      List<T> accumulator = result == null ? new ArrayList<>(expected) :
          new ArrayList<>(result);
      long start = System.nanoTime();
      Utils.retryUntilNotNull(timeout,
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
      result = accumulator;
      LOGGER.warn("Retry returns with {} message(s), expected {}, time spent retrying {} ms",
          result.size(), expected, Duration.ofNanos(System.nanoTime() - start).toMillis());
      if (result.size() != expected) {
        LOGGER.warn("Dumping stack trace...", new Exception("Stack trace"));
      }
    }
    return result;
  }

  @FunctionalInterface
  public interface CallableSupplier<T> {

    T get() throws Exception;
  }

}
