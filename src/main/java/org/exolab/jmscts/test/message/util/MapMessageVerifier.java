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
 * $Id: MapMessageVerifier.java,v 1.2 2004/02/03 07:31:04 tanderson Exp $
 */
package org.exolab.jmscts.test.message.util;

import javax.jms.MapMessage;
import javax.jms.Message;

import org.exolab.jmscts.core.MethodCache;


/**
 * A helper class for populating and verifying the content of MapMessage
 * instances.
 *
 * @version     $Revision: 1.2 $ $Date: 2004/02/03 07:31:04 $
 * @author      <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @see         MessagePopulatorVerifier
 */
class MapMessageVerifier extends MessagePopulatorVerifier {

    /** TODO */
    private static final long serialVersionUID = 1L;

    /**
     * Method cache for MapMessage
     */
    private static MethodCache _methods = null;

    /**
     * Seed value used for value names
     */
    private int _seed = 0;

    /**
     * Byte array size
     */
    private static final int BYTE_ARRAY_SIZE = 10;


    /**
     * Construct a new instance. No exceptions are expected to be thrown
     * when invoking methods
     */
    public MapMessageVerifier() {
    }

    /**
     * Construct an instance with the expected exception thrown when
     * methods are invoked
     *
     * @param exception the expected exception type when methods are invoked
     */
    public MapMessageVerifier(Class<?> exception) {
        super(exception);
    }

    /**
     * Attempt to populate a MapMessage instance with data
     *
     * @param message the message to populate
     * @throws Exception for any error
     */
    @Override
    public void populateMapMessage(MapMessage message) throws Exception {
        byte[] bytes = new byte[BYTE_ARRAY_SIZE];
        _seed = 0;
        set(message, "setBoolean", Boolean.TRUE);
        set(message, "setByte", Byte.valueOf(Byte.MIN_VALUE));
        set(message, "setBytes", bytes);

        Object[] args = {bytes, Integer.valueOf(1), Integer.valueOf(bytes.length - 2)};
        set(message, "setBytes", args);
        set(message, "setChar", Character.valueOf(Character.MIN_VALUE));
        set(message, "setDouble", Double.valueOf(Double.MIN_VALUE));
        set(message, "setFloat", Float.valueOf(Float.MIN_VALUE));
        set(message, "setInt", Integer.valueOf(Integer.MIN_VALUE));
        set(message, "setLong", Long.valueOf(Long.MIN_VALUE));
        set(message, "setShort", Short.valueOf(Short.MIN_VALUE));
        set(message, "setString", "ABC");

        set(message, "setObject", Boolean.TRUE);
        set(message, "setObject", Byte.valueOf(Byte.MAX_VALUE));
        set(message, "setObject", bytes);
        set(message, "setObject", Character.valueOf(Character.MAX_VALUE));
        set(message, "setObject", Double.valueOf(Double.MAX_VALUE));
        set(message, "setObject", Float.valueOf(Float.MAX_VALUE));
        set(message, "setObject", Integer.valueOf(Integer.MAX_VALUE));
        set(message, "setObject", Long.valueOf(Long.MAX_VALUE));
        set(message, "setObject", Short.valueOf(Short.MAX_VALUE));
        set(message, "setObject", "ABC");
    }

    /**
     * Attempt to verify the content of a MapMessage populated via the above
     * {@link #populateMapMessage}.
     *
     * @param message the message to verify
     * @throws Exception for any error
     */
    @Override
    public void verifyMapMessage(MapMessage message) throws Exception {
        _seed = 0;
        get(message, "getBoolean", Boolean.TRUE);
        get(message, "getByte", Byte.valueOf(Byte.MIN_VALUE));
        get(message, "getBytes", new byte[BYTE_ARRAY_SIZE]);

        get(message, "getBytes", new byte[BYTE_ARRAY_SIZE - 2]);
        get(message, "getChar", Character.valueOf(Character.MIN_VALUE));
        get(message, "getDouble", Double.valueOf(Double.MIN_VALUE));
        get(message, "getFloat", Float.valueOf(Float.MIN_VALUE));
        get(message, "getInt", Integer.valueOf(Integer.MIN_VALUE));
        get(message, "getLong", Long.valueOf(Long.MIN_VALUE));
        get(message, "getShort", Short.valueOf(Short.MIN_VALUE));
        get(message, "getString", "ABC");

        get(message, "getObject", Boolean.TRUE);
        get(message, "getObject", Byte.valueOf(Byte.MAX_VALUE));
        get(message, "getObject", new byte[BYTE_ARRAY_SIZE]);
        get(message, "getObject", Character.valueOf(Character.MAX_VALUE));
        get(message, "getObject", Double.valueOf(Double.MAX_VALUE));
        get(message, "getObject", Float.valueOf(Float.MAX_VALUE));
        get(message, "getObject", Integer.valueOf(Integer.MAX_VALUE));
        get(message, "getObject", Long.valueOf(Long.MAX_VALUE));
        get(message, "getObject", Short.valueOf(Short.MAX_VALUE));
        get(message, "getObject", "ABC");
    }

    /**
     * Set a message property
     *
     * @param message the message
     * @param method the method to invoke
     * @param value the value to set
     * @throws Exception for any error
     */
    protected void set(Message message, String method, Object value)
        throws Exception {
        Object[] args = {"name" + ++_seed, value};
        invoke(message, method, args);
    }

    /**
     * Set a message array property
     *
     * @param message the message
     * @param method the method to invoke
     * @param values the values to set
     * @throws Exception for any error
     */
    protected void set(Message message, String method, Object[] values)
        throws Exception {
        Object[] args = new Object[values.length + 1];
        args[0] = "name" + ++_seed;
        System.arraycopy(values, 0, args, 1, values.length);
        invoke(message, method, args);
    }

    /**
     * Get a message property, verifying it against the expected value
     *
     * @param message the message
     * @param method the method to invoke
     * @param expected the expected value
     * @throws Exception for any error
     */
    protected void get(Message message, String method, Object expected)
        throws Exception {
        Object[] args = {"name" + ++_seed};
        expect(message, method, args, expected);
    }

    /**
     * Get a message array property, verifying it against the expected value
     *
     * @param message the message
     * @param method the method to invoke
     * @param values the array to buffer the property value
     * @param expected the expected value
     * @throws Exception for any error
     */
    protected void get(Message message, String method, Object[] values,
                       Object expected) throws Exception {
        Object[] args = new Object[values.length + 1];
        args[0] = "name" + ++_seed;
        System.arraycopy(values, 0, args, 1, values.length);
        expect(message, method, args, expected);
    }

    /**
     * Returns a cache of the <code>MapMessage</code> methods
     *
     * @return a cache of the <code>MapMessage</code> methods
     */
    @Override
    protected synchronized MethodCache getMethods() {
        if (_methods == null) {
            _methods = new MethodCache(MapMessage.class);
        }
        return _methods;
    }

}
