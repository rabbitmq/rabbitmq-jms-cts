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
 *    (http://www.exolab.org/).
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
 * $Id: AndOperatorTest.java,v 1.4 2004/02/03 21:52:11 tanderson Exp $
 */
package org.exolab.jmscts.test.selector;

import junit.framework.Test;

import org.exolab.jmscts.core.TestCreator;


/**
 * This class tests selectors containing the OR operator.
 *
 * @author Steve Powell
 * @see AbstractSelectorTestCase
 * @jmscts.message Message
 */
public class OrOperatorTest extends AbstractSelectorTestCase {

    /**
     * Create an instance of this class for a specific test case, testing
     * against all delivery types
     *
     * @param name the name of test case
     */
    public OrOperatorTest(String name) {
        super(name);
    }

    /**
     * Sets up the test suite
     *
     * @return an instance of this class that may be run by
     * {@link org.exolab.jmscts.core.JMSTestRunner}
     */
    public static Test suite() {
        return TestCreator.createSendReceiveTest(OrOperatorTest.class);
    }

    /**
     * Verifies that the selector <code>true or true</code> selects
     * all messages
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testOr1() throws Exception {
        checkSelector("true or true", true);
    }

    /**
     * Verifies that the selector <code>true or false</code> selects
     * all messages
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testOr2() throws Exception {
        checkSelector("true or false", true);
    }

    /**
     * Verifies that the selector <code>false or true</code> selects
     * all messages
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testOr3() throws Exception {
        checkSelector("false or true", true);
    }

    /**
     * Verifies that the selector <code>false or false</code> selects
     * no messages
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @throws Exception for any error
     */
    public void testOr4() throws Exception {
        checkSelector("false or false", false);
    }

    /**
     * Verifies that the selector <code>true or true</code> selects
     * all messages
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @jmscts.requirement selector.reservedwords.case
     * @throws Exception for any error
     */
    public void testOrCase() throws Exception {
        checkSelector("true or true", true);
    }

    /**
     * Verifies that the selector <code>true or dummy</code> selects
     * all messages, for the unset property 'dummy'
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @jmscts.requirement selector.values.null
     * @throws Exception for any error
     */
    public void testUnsetProperty1() throws Exception {
        checkSelector("true or dummy", true);
    }

    /**
     * Verifies that the selector <code>false or dummy</code> selects
     * no messages, for the unset property 'dummy'
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @jmscts.requirement selector.values.null
     * @throws Exception for any error
     */
    public void testUnsetProperty2() throws Exception {
        checkSelector("false or dummy", false);
    }

    /**
     * Verifies that the selector <code>dummy or true</code> selects
     * all messages, for the unset property 'dummy'
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @jmscts.requirement selector.values.null
     * @throws Exception for any error
     */
    public void testUnsetProperty3() throws Exception {
        checkSelector("dummy or true", true);
    }

    /**
     * Verifies that the selector <code>dummy or false</code> selects
     * no messages, for the unset property 'dummy'
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @jmscts.requirement selector.values.null
     * @throws Exception for any error
     */
    public void testUnsetProperty4() throws Exception {
        checkSelector("dummy or false", false);
    }

    /**
     * Verifies that the selector <code>dummy or dummy</code> selects
     * no messages, for the unset property 'dummy'
     *
     * @jmscts.requirement selector.operator.or
     * @jmscts.requirement selector.expression
     * @jmscts.requirement selector.values.null
     * @throws Exception for any error
     */
    public void testUnsetProperty5() throws Exception {
        checkSelector("dummy or dummy", false);
    }

    /**
     * Verifies that the selector <code>or</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalidOr1() throws Exception {
        checkInvalidSelector("or");
    }

    /**
     * Verifies that the selector <code>true and</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalidOr2() throws Exception {
        checkInvalidSelector("true or");
    }

    /**
     * Verifies that the selector <code>false and</code> throws
     * InvalidSelectorException
     *
     * @jmscts.requirement selector.validation
     * @throws Exception for any error
     */
    public void testInvalidOr3() throws Exception {
        checkInvalidSelector("or true");
    }

}
