/**
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "Exolab" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of Exoffice Technologies.  For written permission,
 *    please contact tma@netspace.net.au.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Exoffice Technologies. Exolab is a registered
 *    trademark of Exoffice Technologies.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (https://castor.exolab.org).
 *
 * THIS SOFTWARE IS PROVIDED BY EXOFFICE TECHNOLOGIES AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * EXOFFICE TECHNOLOGIES OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2001-2004 (C) Exoffice Technologies Inc. All Rights Reserved.
 *
 */
package org.exolab.jmscts.test.selector;

import java.util.HashMap;

import junit.framework.Test;

import org.exolab.jmscts.core.TestCreator;


/**
 * This class tests selectors containing the IN operator.
 *
 * @author Steve Powell
 * @see AbstractSelectorTestCase
 * @jmscts.message Message
 */
public class InOperatorTest extends AbstractSelectorTestCase {

    /**
     * Message properties
     */
    private static final HashMap<String, String> PROPERTIES = new HashMap<String, String>();

    /**
     * Create an instance of this class for a specific test case, testing
     * against all delivery types
     *
     * @param name the name of test case
     */
    public InOperatorTest(String name) {
        super(name);
    }

    /**
     * Sets up the test suite
     *
     * @return an instance of this class that may be run by
     * {@link org.exolab.jmscts.core.JMSTestRunner}
     */
    public static Test suite() {
        return TestCreator.createSendReceiveTest(InOperatorTest.class);
    }

    /**
     * Verifies that the selector <code>Country in ('France')</code> selects
     * all messages, for the property Country = 'France'
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testIn1() throws Exception {
        checkSelector("Country in ('France')", true, PROPERTIES);
    }

    /**
     * Verifies that the selector <code>Country not in ('France')</code> selects
     * no messages, for the property Country = 'France'
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testIn2() throws Exception {
        checkSelector("Country not in ('France')", false, PROPERTIES);
    }

    /**
     * Verifies that the selector <code>Country in ('UK', 'US', 'France')</code> selects
     * all messages, for the property Country = 'France'
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testIn3() throws Exception {
        checkSelector("Country in ('UK', 'US', 'France')", true, PROPERTIES);
    }

    /**
     * Verifies that the selector <code>Country in ('UK', 'US', 'Peru')</code> selects
     * no messages, for the property Country = 'France'
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testIn4() throws Exception {
        checkSelector("Country in ('UK', 'US', 'Peru')", false, PROPERTIES);
    }

    /**
     * Verifies that the selector <code>Country in ('FRANCE')</code> selects
     * no messages, for the property Country = 'France'
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testIn5() throws Exception {
        checkSelector("Country in ('FRANCE')", false, PROPERTIES);
    }

    /**
     * Verifies that the selector <code>Country in ('France')</code> selects
     * no messages when Country is unknown
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testIn6() throws Exception {
        checkSelector("Country in ('France')", false);
    }

    /**
     * Verifies that the selector <code>age in (21, 22, 23)</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalid1() throws Exception {
        checkInvalidSelector("age in (21, 22, 23)");
    }

    /**
     * Verifies that the selector <code>Country in 'France', 'UK'</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalid2() throws Exception {
        checkInvalidSelector("Country in 'France', 'UK'");
    }

    /**
     * Verifies that the selector <code>Country in ('France', 'UK'</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalid3() throws Exception {
        checkInvalidSelector("Country in ('France', 'UK'");
    }

    /**
     * Verifies that the selector <code>in Country ('France', 'UK')</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalid4() throws Exception {
        checkInvalidSelector("in Country ('France', 'UK')");
    }

    /**
     * Verifies that the selector <code>Country in ('France' 'UK')</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalid5() throws Exception {
        checkInvalidSelector("Country in ('France' 'UK')");
    }

    /**
     * Verifies that the selector <code>Country in (France)</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalid6() throws Exception {
        checkInvalidSelector("Country in (France)");
    }

    /**
     * Verifies that the selector <code>Country in ()</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.operator.in
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalid7() throws Exception {
        checkInvalidSelector("Country in ()");
    }

    static {
        PROPERTIES.put("Country", "France");
    }
}
