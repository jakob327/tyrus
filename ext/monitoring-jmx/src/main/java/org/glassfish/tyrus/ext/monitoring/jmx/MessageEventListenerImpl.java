/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2014-2016 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.tyrus.ext.monitoring.jmx;

import org.glassfish.tyrus.core.frame.TyrusFrame;
import org.glassfish.tyrus.core.monitoring.MessageEventListener;

/**
 * Determines the type of a received or sent frame.
 *
 * @author Petr Janouch
 */
class MessageEventListenerImpl implements MessageEventListener {

    private final MessageListener messageListener;

    MessageEventListenerImpl(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void onFrameSent(TyrusFrame.FrameType frameType, long payloadLength) {
        if (frameType == TyrusFrame.FrameType.TEXT || frameType == TyrusFrame.FrameType.TEXT_CONTINUATION) {
            messageListener.onTextMessageSent(payloadLength);
        }
        if (frameType == TyrusFrame.FrameType.BINARY || frameType == TyrusFrame.FrameType.BINARY_CONTINUATION) {
            messageListener.onBinaryMessageSent(payloadLength);
        }
        if (frameType == TyrusFrame.FrameType.PING || frameType == TyrusFrame.FrameType.PONG) {
            messageListener.onControlMessageSent(payloadLength);
        }
    }

    @Override
    public void onFrameReceived(TyrusFrame.FrameType frameType, long payloadLength) {
        if (frameType == TyrusFrame.FrameType.TEXT || frameType == TyrusFrame.FrameType.TEXT_CONTINUATION) {
            messageListener.onTextMessageReceived(payloadLength);
        }
        if (frameType == TyrusFrame.FrameType.BINARY || frameType == TyrusFrame.FrameType.BINARY_CONTINUATION) {
            messageListener.onBinaryMessageReceived(payloadLength);
        }
        if (frameType == TyrusFrame.FrameType.PING || frameType == TyrusFrame.FrameType.PONG) {
            messageListener.onControlMessageReceived(payloadLength);
        }
    }
}
