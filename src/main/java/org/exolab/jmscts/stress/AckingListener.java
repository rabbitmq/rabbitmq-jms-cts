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
 * Copyright 2003-2004 (C) Exoffice Technologies Inc. All Rights Reserved.
 *
 * $Id: AckingListener.java,v 1.3 2004/02/03 21:52:08 tanderson Exp $
 */
package org.exolab.jmscts.stress;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.exolab.jmscts.core.CountingListener;


/**
 * Listener which counts and acks each message it receives
 *
 * @version     $Revision: 1.3 $ $Date: 2004/02/03 21:52:08 $
 * @author      <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @see         CountingListener
 */
public class AckingListener extends CountingListener {

    /**
     * The logger
     */
    private static final Logger log =
        LoggerFactory.getLogger(AckingListener.class);


    /**
     * Construct an instance of the listener, with the number of messages
     * expected to be received. When this count is reached, the listener
     * notifies the waiting client (if any)
     *
     * @param expected the number of messages expected
     */
    public AckingListener(int expected) {
        super(expected);
    }

    /**
     * This method is asynchronously invoked by the message consumer when
     * a message becomes available.
     * If the number of expected messages are received, it notifies
     * the waiting client (if any)
     *
     * @param message the received message
     */
    @Override
    public void onMessage(Message message) {
        try {
            message.acknowledge();
        } catch (Exception exception) {
            log.error("Failed to acknowledge message", exception);
        }
        super.onMessage(message);
    }

}
