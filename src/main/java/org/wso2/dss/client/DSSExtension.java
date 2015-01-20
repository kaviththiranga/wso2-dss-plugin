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
package org.wso2.dss.client;

/**
 * @author Evgen Vidolob
 */

import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.projecttype.wizard.ProjectTypeWizardRegistry;
import com.codenvy.ide.api.projecttype.wizard.ProjectWizard;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import org.wso2.dss.client.wizard.DSSConfigurationPresenter;
import org.wso2.dss.shared.ProjectConstants;

@Singleton
@Extension(title = "DSS extension", description = "DSS extension", version = "1.0.0")
public class DSSExtension {

    @Inject
    public DSSExtension(ProjectWizard projectWizard,
                        Provider<DSSConfigurationPresenter> wizardPage,
                        ProjectTypeWizardRegistry registry) {

        projectWizard.addPage(wizardPage);

        registry.addWizard(ProjectConstants.PROJECT_ID, projectWizard);
    }
}
