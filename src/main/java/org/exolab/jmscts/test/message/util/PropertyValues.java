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
 * $Id: PropertyValues.java,v 1.2 2004/02/03 07:31:04 tanderson Exp $
 */
package org.exolab.jmscts.test.message.util;


/**
 * Helper interface providing a default set of property values to test with
 *
 * @author <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @version $Revision: 1.2 $
 */
public interface PropertyValues {

    /**
     * Boolean values to test against
     */
    Boolean[] BOOLEANS = {Boolean.TRUE, Boolean.FALSE};

    /**
     * Byte values to test against
     */
    Byte[] BYTES = {Byte.valueOf(Byte.MIN_VALUE), Byte.valueOf(Byte.MAX_VALUE)};

    /**
     * Short values to test against
     */
    Short[] SHORTS = {Short.valueOf(Short.MIN_VALUE), Short.valueOf(Short.MAX_VALUE)};

    /**
     * Int values to test against
     */
    Integer[] INTS = {Integer.valueOf(Integer.MIN_VALUE),
                      Integer.valueOf(Integer.MAX_VALUE)};

    /**
     * Long values to test against
     */
    Long[] LONGS = {Long.valueOf(Long.MIN_VALUE), Long.valueOf(Long.MAX_VALUE)};

    /**
     * Float values to test against
     */
    Float[] FLOATS = {
        Float.valueOf(Float.MIN_VALUE), Float.valueOf(Float.MAX_VALUE)
//        Float.valueOf(Float.NaN),
//        Float.valueOf(Float.NEGATIVE_INFINITY),
//        Float.valueOf(Float.POSITIVE_INFINITY)
        };

    /**
     * Double values to test against
     */
    Double[] DOUBLES = {
        Double.valueOf(Double.MIN_VALUE), Double.valueOf(Double.MAX_VALUE)
//        Double.valueOf(Double.NaN),
//        Double.valueOf(Double.NEGATIVE_INFINITY),
//        Double.valueOf(Double.POSITIVE_INFINITY)
        };

    /**
     * String values to test against
     */
    String[] STRINGS = {"a", "b", "aaa", "bbb", "A", "B"};

    /**
     * All of the values to test against
     */
    Object[][] ALL_VALUES = {
        BOOLEANS, BYTES, SHORTS, INTS, LONGS, FLOATS, DOUBLES, STRINGS};

}
