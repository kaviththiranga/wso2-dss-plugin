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

import com.codenvy.ide.api.action.Action;
import com.codenvy.ide.api.action.ActionEvent;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.DtoUnmarshallerFactory;
import com.codenvy.ide.rest.Unmarshallable;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.wso2.dss.shared.dto.HelloUser;
import org.wso2.dss.shared.dto.SayHello;

/**
 * @author Evgen Vidolob
 */
@Singleton
public class HelloAction extends Action {

    private final HelloService helloService;
    private final DtoFactory factory;
    private final DtoUnmarshallerFactory unmarshallerFactory;

    @Inject
    public HelloAction(HelloService helloService, DtoFactory factory, DtoUnmarshallerFactory unmarshallerFactory) {
        super("Hello", "Hello");
        this.helloService = helloService;
        this.factory = factory;
        this.unmarshallerFactory = unmarshallerFactory;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        SayHello sayHello = factory.createDto(SayHello.class);
        sayHello.setHelloMessage("AAAAAAAAAAAAAAA");
        Unmarshallable<HelloUser> unmarshaller = unmarshallerFactory.newUnmarshaller(HelloUser.class);
        helloService.sayHello(sayHello, new AsyncRequestCallback<HelloUser>(unmarshaller) {
            @Override
            protected void onSuccess(HelloUser result) {
                Window.alert(result.getHelloMessage());
            }

            @Override
            protected void onFailure(Throwable exception) {
                Window.alert("Error " + exception.getMessage());
            }
        });
    }
}
