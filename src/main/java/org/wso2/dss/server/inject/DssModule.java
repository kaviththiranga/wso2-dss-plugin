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
 * Created by Evgen on 1/20/15.
 */
package org.wso2.dss.server.inject;

import com.codenvy.inject.DynaModule;
import com.google.inject.AbstractModule;

import org.wso2.dss.server.project.type.DssProjectTypeDescriptionExtension;
import org.wso2.dss.server.project.type.DssProjectTypeExtension;

/**
 * @author Evgen Vidolob
 */
@DynaModule
public class DssModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DssProjectTypeExtension.class);
        bind(DssProjectTypeDescriptionExtension.class);
    }
}
