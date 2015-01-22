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

import com.codenvy.ide.api.action.ActionManager;
import com.codenvy.ide.api.action.DefaultActionGroup;
import com.codenvy.ide.api.action.IdeActions;
import com.codenvy.ide.api.editor.EditorRegistry;
import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.filetypes.FileType;
import com.codenvy.ide.api.filetypes.FileTypeRegistry;
import com.codenvy.ide.api.projecttype.wizard.ProjectTypeWizardRegistry;
import com.codenvy.ide.api.projecttype.wizard.ProjectWizard;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import org.wso2.dss.client.editor.DBSMultiEditorProvider;
import org.wso2.dss.client.editor.graphical.DBSEditorProvider;
import org.wso2.dss.client.editor.graphical.MessagesRouter;
import org.wso2.dss.client.hello.HelloAction;
import org.wso2.dss.client.wizard.DSSConfigurationPresenter;
import org.wso2.dss.shared.ProjectConstants;

@Singleton
@Extension(title = "DSS extension", description = "DSS extension", version = "1.0.0")
public class DSSExtension {

    @Inject
    public DSSExtension(ProjectWizard projectWizard,
                        Provider<DSSConfigurationPresenter> wizardPage,
                        ProjectTypeWizardRegistry registry,
                        FileTypeRegistry fileTypeRegistry,
                        @Named("wso2DBSFile")FileType dssFileType,
                        DBSMultiEditorProvider editorProvider,
                        EditorRegistry editorRegistry) {

        projectWizard.addPage(wizardPage);

        registry.addWizard(ProjectConstants.PROJECT_ID, projectWizard);

        fileTypeRegistry.registerFileType(dssFileType);
        editorRegistry.registerDefaultEditor(dssFileType, editorProvider);
    }

    @Inject
    protected void initializeRouter(MessagesRouter router){

    }

    @Inject
    public void initializeActions(HelloAction helloAction, ActionManager actionManager){
        actionManager.registerAction("helloAction", helloAction);

        DefaultActionGroup fileMenuGroup = (DefaultActionGroup)actionManager.getAction(IdeActions.GROUP_FILE);
        fileMenuGroup.add(helloAction);
    }
}
