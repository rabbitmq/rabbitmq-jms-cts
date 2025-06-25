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
 * $Id: BytesMessageVerifier.java,v 1.2 2004/02/03 07:31:04 tanderson Exp $
 */
package org.exolab.jmscts.test.message.util;

import javax.jms.BytesMessage;

import org.exolab.jmscts.core.MethodCache;


/**
 * A helper class for populating and verifying the content of BytesMessage
 * instances.
 *
 * @version     $Revision: 1.2 $ $Date: 2004/02/03 07:31:04 $
 * @author      <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @see         MessagePopulatorVerifier
 */
class BytesMessageVerifier extends MessagePopulatorVerifier {

    /** TODO */
    private static final long serialVersionUID = 1L;

    /**
     * Method cache for BytesMessage
     */
    private static MethodCache _methods = null;

    /**
     * Byte array size
     */
    private static final int BYTE_ARRAY_SIZE = 10;


    /**
     * Construct a new instance. No exceptions are expected to be thrown
     * when invoking methods
     */
    public BytesMessageVerifier() {
    }

    /**
     * Construct an instance with the expected exception thrown when
     * methods are invoked
     *
     * @param exception the expected exception type when methods are invoked
     */
    public BytesMessageVerifier(Class<?> exception) {
        super(exception);
    }

    /**
     * Attempt to populate a BytesMessage instance with data
     *
     * @param message the message to populate
     * @throws Exception for any error
     */
    @Override
    public void populateBytesMessage(BytesMessage message) throws Exception {
        byte[] bytes = populateByteArray(BYTE_ARRAY_SIZE, 0);
        invoke(message, "writeBoolean", Boolean.TRUE);
        invoke(message, "writeByte", Byte.valueOf(Byte.MIN_VALUE));
        invoke(message, "writeByte", Byte.valueOf((byte) 0xFF));
        invoke(message, "writeBytes", bytes);

        Object[] args = {bytes, Integer.valueOf(1), Integer.valueOf(bytes.length - 2)};
        invoke(message, "writeBytes", args);
        invoke(message, "writeShort", Short.valueOf(Short.MIN_VALUE));
        invoke(message, "writeShort", Short.valueOf((short) 0xFFFF));
        invoke(message, "writeChar", Character.valueOf(Character.MIN_VALUE));
        invoke(message, "writeInt", Integer.valueOf(Integer.MIN_VALUE));
        invoke(message, "writeLong", Long.valueOf(Long.MIN_VALUE));
        invoke(message, "writeFloat", Float.valueOf(Float.MIN_VALUE));
        invoke(message, "writeDouble", Double.valueOf(Double.MIN_VALUE));
        invoke(message, "writeUTF", "ABC");

        invoke(message, "writeObject", Boolean.TRUE);
        invoke(message, "writeObject", Byte.valueOf(Byte.MAX_VALUE));
        invoke(message, "writeObject", bytes);
        invoke(message, "writeObject", Short.valueOf(Short.MAX_VALUE));
        invoke(message, "writeObject", Character.valueOf(Character.MAX_VALUE));
        invoke(message, "writeObject", Integer.valueOf(Integer.MAX_VALUE));
        invoke(message, "writeObject", Long.valueOf(Long.MAX_VALUE));
        invoke(message, "writeObject", Float.valueOf(Float.MAX_VALUE));
        invoke(message, "writeObject", Double.valueOf(Double.MAX_VALUE));
        invoke(message, "writeObject", "ABC");
    }

    /**
     * Attempt to verify the content of a BytesMessage populated via the above
     * {@link #populateBytesMessage}.
     *
     * @param message the message to verify
     * @throws Exception for any error
     */
    @Override
    public void verifyBytesMessage(BytesMessage message) throws Exception {
        expect(message, "readBoolean", Boolean.TRUE);
        expect(message, "readByte", Byte.valueOf(Byte.MIN_VALUE));
        expect(message, "readUnsignedByte", Integer.valueOf(0xFF));

        byte[] buffer1 = new byte[BYTE_ARRAY_SIZE];
        expect(message, "readBytes", buffer1, Integer.valueOf(buffer1.length));
        equal(buffer1, populateByteArray(buffer1.length, 0));

        byte[] buffer2 = new byte[BYTE_ARRAY_SIZE - 2];
        Object[] args = {buffer2, Integer.valueOf(buffer2.length)};
        expect(message, "readBytes", args, Integer.valueOf(buffer2.length));
        equal(buffer2, populateByteArray(buffer2.length, 1));

        expect(message, "readShort", Short.valueOf(Short.MIN_VALUE));
        expect(message, "readUnsignedShort", Integer.valueOf(0xFFFF));
        expect(message, "readChar", Character.valueOf(Character.MIN_VALUE));
        expect(message, "readInt", Integer.valueOf(Integer.MIN_VALUE));
        expect(message, "readLong", Long.valueOf(Long.MIN_VALUE));
        expect(message, "readFloat", Float.valueOf(Float.MIN_VALUE));
        expect(message, "readDouble", Double.valueOf(Double.MIN_VALUE));
        expect(message, "readUTF", "ABC");

        expect(message, "readBoolean", Boolean.TRUE);
        expect(message, "readByte", Byte.valueOf(Byte.MAX_VALUE));

        byte[] buffer3 = new byte[BYTE_ARRAY_SIZE];
        expect(message, "readBytes", buffer3, Integer.valueOf(buffer3.length));
        equal(buffer3, populateByteArray(buffer3.length, 0));

        expect(message, "readShort", Short.valueOf(Short.MAX_VALUE));
        expect(message, "readChar", Character.valueOf(Character.MAX_VALUE));
        expect(message, "readInt", Integer.valueOf(Integer.MAX_VALUE));
        expect(message, "readLong", Long.valueOf(Long.MAX_VALUE));
        expect(message, "readFloat", Float.valueOf(Float.MAX_VALUE));
        expect(message, "readDouble", Double.valueOf(Double.MAX_VALUE));
        expect(message, "readUTF", "ABC");
    }

    /**
     * Returns a cache of the <code>BytesMessage</code> methods
     *
     * @return a cache of the <code>BytesMessage</code> methods
     */
    @Override
    protected synchronized MethodCache getMethods() {
        if (_methods == null) {
            _methods = new MethodCache(BytesMessage.class);
        }
        return _methods;
    }

}
