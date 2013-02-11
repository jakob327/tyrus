/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012-2013 Oracle and/or its affiliates. All rights reserved.
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
package org.glassfish.tyrus.spi;


import javax.websocket.ClientEndpointConfiguration;

/**
 * The TyrusContainer is the starting point of the provider SPI. The provider must implement this
 * class with a public no args constructor. The new provider can be configured
 * in the web.xml of the web application requesting the new provider by specifying a servlet context
 * initialization parameter of key org.glassfish.websocket.provider.class and value the fully qualified classname
 * of the provider class.
 *
 * @author Danny Coward (danny.coward at oracle.com)
 */
public interface TyrusContainer {
    /**
     * Creates a new embedded HTTP server (if supported) listening to incoming connections at a given root path
     * and port.
     *
     * @param rootPath context root
     * @param port     TCP port
     * @return server that can be started and stopped
     */
    public TyrusServer createServer(String rootPath, int port);

    /**
     * Open client socket - connect to endpoint specified with {@code url} parameter.
     *
     * @param url      address where remote service is deployed.
     * @param cec      endpoint configuration.
     * @param endpoint endpoint instance.
     * @return representation of incoming socket.
     */
    public TyrusClientSocket openClientSocket(String url, ClientEndpointConfiguration cec, SPIEndpoint endpoint);
}
