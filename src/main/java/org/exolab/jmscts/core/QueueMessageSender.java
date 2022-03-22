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
 * $Id: QueueMessageSender.java,v 1.4 2005/06/16 06:31:11 tanderson Exp $
 */
package org.exolab.jmscts.core;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueSender;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class for sending messages using a {@link javax.jms.QueueSender}
 *
 * @version     $Revision: 1.4 $ $Date: 2005/06/16 06:31:11 $
 * @author      <a href="mailto:tma@netspace.net.au">Tim Anderson</a>
 * @see         AbstractMessageSender
 * @see         TopicMessageSender
 */
class QueueMessageSender extends AbstractMessageSender {

    /**
     * The logger
     */
    private static final Logger log =
        LoggerFactory.getLogger(QueueMessageSender.class);


    /**
     * Construct an instance of with the sender to send messages with,
     * using default values for delivery mode, priority, and time-to-live
     *
     * @param sender the topic sender to send messages with
     */
    public QueueMessageSender(QueueSender sender) {
        super(sender, new MessagingBehaviour());
    }

    /**
     * Construct an instance with the sender to send messages with,
     * using values for delivery mode, priority, and time-to-live from the
     * supplied messaging behaviour
     *
     * @param sender the sender to send messages with
     * @param behaviour the messaging behaviour
     */
    public QueueMessageSender(QueueSender sender,
                              MessagingBehaviour behaviour) {
        super(sender, behaviour);
    }

    /**
     * Send messages to a destination, invoking a message populator prior
     * to each message being sent
     *
     * @param message the message to send
     * @param count the number of times to send the message
     * @param populator the message populator, or null, if no population is
     * required
     * @param timeToLive the message time-to-live
     * @throws JMSException if any of the JMS operations fail
     */
    @Override
    public void send(Message message, int count, MessagePopulator populator,
                     long timeToLive)
        throws JMSException {

        MessagingBehaviour behaviour = getBehaviour();
        int deliveryMode = behaviour.getDeliveryMode();
        int priority = behaviour.getPriority();

        QueueSender sender = (QueueSender) getProducer();
        final MessageListener listener = getMessageListener();

        for (int i = 0; i < count; ++i) {
            if (populator != null) {
                try {
                    populator.populate(message);
                } catch (Exception exception) {
                    JMSException error = new JMSException(
                        exception.getMessage());
                    error.setLinkedException(exception);
                    throw error;
                }
            }
            if (log.isDebugEnabled()) {
                Class<?> type = MessageTypes.getType(message);
                log.debug("Sending message " + "[message=" + type.getName()
                          + ", queue=" + getDestination()
                          + ", deliveryMode=" + deliveryMode
                          + ", priority=" + priority
                          + ", timeToLive=" + timeToLive + "]");
            }
            sender.send(message, deliveryMode, priority, timeToLive);
            if (listener != null) {
                listener.onMessage(message);
            }
        }
    }

}
