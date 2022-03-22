package org.exolab.jmscts;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.LoggerFactory;

public class LogUtils {

  public static void configure(String filename) {
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    try (InputStream in = new BufferedInputStream(new FileInputStream(filename))) {
      JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(context);
      context.reset();
      configurator.doConfigure(in);
    } catch (JoranException je) {
      // StatusPrinter will handle this
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    StatusPrinter.printInCaseOfErrorsOrWarnings(context);
  }
}
