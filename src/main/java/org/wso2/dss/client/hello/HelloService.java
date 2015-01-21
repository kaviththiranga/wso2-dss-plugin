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
package org.wso2.dss.client.hello;

import com.codenvy.ide.api.app.AppContext;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import org.wso2.dss.shared.dto.HelloUser;
import org.wso2.dss.shared.dto.SayHello;

/**
 * @author Evgen Vidolob
 */
@Singleton
public class HelloService {

    private final String restContext;
    private final AppContext context;
    private final AsyncRequestFactory factory;

    @Inject
    public HelloService(@Named("restContext") String restContext, AppContext context, AsyncRequestFactory factory) {
        this.restContext = restContext;
        this.context = context;
        this.factory = factory;
    }

    public void sayHello(SayHello sayHello, AsyncRequestCallback<HelloUser> callback) {
        String url = restContext + "/hello/" + context.getWorkspace().getId() + "/world";
        factory.createPostRequest(url,sayHello).send(callback);
    }
}
