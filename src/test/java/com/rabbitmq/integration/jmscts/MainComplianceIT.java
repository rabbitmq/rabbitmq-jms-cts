package com.rabbitmq.integration.jmscts;

import java.io.File;

import junit.framework.TestCase;

import org.exolab.jmscts.test.ComplianceTestSuite;

/**
 * Runs the jmscts test suite as a single (integration) test (using maven/failsafe).
 */
public class MainComplianceIT extends TestCase {

    static {
        if (System.getProperty("basedir")==null) {
            System.setProperty("basedir",".");
        }
        System.setProperty("jmscts.home", System.getProperty("basedir"));
    }

    public void testAll() throws Exception {
        if (System.getProperty("rabbit.jms.terminationTimeout") == null) {
            System.setProperty("rabbit.jms.terminationTimeout", "2000");
        }
        String basedir = System.getProperty("basedir");
        ComplianceTestSuite.main(new String[] { "-output",
                                                new File(basedir, "target/jmscts-report").getAbsolutePath(),
                                                "-filter",
                                                new File(basedir, "config/filter.xml").getAbsolutePath() });
    }

    public void testSelector() throws Exception {
        if (System.getProperty("rabbit.jms.terminationTimeout") == null) {
            System.setProperty("rabbit.jms.terminationTimeout", "2000");
        }
        String basedir = System.getProperty("basedir");
        ComplianceTestSuite.main(new String[] { "-output",
                                                new File(basedir, "target/jmscts-selector-report").getAbsolutePath(),
                                                "-filter",
                                                new File(basedir, "config/selector-filter.xml").getAbsolutePath() });
    }

}
