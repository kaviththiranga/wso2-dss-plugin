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
package org.wso2.dss.client.inject;

import com.codenvy.ide.api.extension.ExtensionGinModule;
import com.codenvy.ide.api.filetypes.FileType;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import org.wso2.dss.client.DSSResources;

/**
 * @author Evgen Vidolob
 */
@ExtensionGinModule
public class DSSGinModule extends AbstractGinModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    @Named("wso2DBSFile")
    protected FileType provideDssFileTYpe(DSSResources resources){
        return new FileType(resources.dssFileIcon(),"text/xml+wso2-dbs", "dbs");
    }


}
