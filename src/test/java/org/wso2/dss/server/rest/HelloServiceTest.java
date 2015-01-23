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

package org.wso2.dss.server.rest;

import com.codenvy.dto.server.DtoFactory;

import org.everrest.core.impl.ContainerResponse;
import org.everrest.core.impl.EverrestProcessor;
import org.everrest.core.impl.ProviderBinder;
import org.everrest.core.impl.ResourceBinderImpl;
import org.everrest.core.tools.DependencySupplierImpl;
import org.everrest.core.tools.ResourceLauncher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.wso2.dss.shared.dto.HelloUser;
import org.wso2.dss.shared.dto.SayHello;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HelloServiceTest {

    private static final String BASE_URI = "http://localhost";

    private ResourceLauncher launcher;

    @Before
    public void setUp() throws Exception {
        DependencySupplierImpl dependencies = new DependencySupplierImpl();

        // TODO if you need to add some injection instance you have to execute
        // dependencies.addComponent(SomeClass.class, instanceClass);

        ResourceBinderImpl resources = new ResourceBinderImpl();
        resources.addResource(HelloService.class, null);

        EverrestProcessor processor = new EverrestProcessor(resources, ProviderBinder.getInstance(), dependencies);

        launcher = new ResourceLauncher(processor);
    }

    private ContainerResponse sendRequest(String method, String path, byte[] data) throws Exception {
        Map<String, List<String>> headers = new HashMap<>(1);
        headers.put("Content-Type", Arrays.asList(MediaType.APPLICATION_JSON));

        return launcher.service(method, "/hello/workspaceId/" + path, BASE_URI, headers, data, null);
    }

    @Test
    public void helloUserShouldBeReturned() throws Exception {
        String message = "hello";

        DtoFactory dtoFactory = DtoFactory.getInstance();
        SayHello sayHello = dtoFactory.createDto(SayHello.class);
        sayHello.setHelloMessage(message);

        ContainerResponse response = sendRequest("POST", "world", dtoFactory.toJson(sayHello).getBytes());

        assertThat(response.getStatus(), is(200));
        HelloUser entity = (HelloUser)response.getEntity();

        assertThat(entity.getHelloMessage(), is(message + " from server"));

    }

}