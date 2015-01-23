/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/

/**
 * Created by Evgen on 1/21/15.
 */
package org.wso2.dss.server.rest;

import com.codenvy.dto.server.DtoFactory;
import com.google.inject.Inject;

import org.apache.commons.codec.language.bm.Languages;
import org.wso2.dss.shared.dto.HelloUser;
import org.wso2.dss.shared.dto.SayHello;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Evgen Vidolob
 */
@Path("/hello/{ws-id}")
public class HelloService {

    @PathParam("ws-id")
    private String wsId;

    @Path("world")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    @POST
    public HelloUser sayHello(SayHello sayHello){
        HelloUser helloUser = DtoFactory.getInstance().createDto(HelloUser.class);
        helloUser.setHelloMessage(sayHello.getHelloMessage() + " from server");
        return helloUser;
    }
}
