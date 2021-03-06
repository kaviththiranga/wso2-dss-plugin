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
package org.wso2.dss.server.genarator;

import com.codenvy.api.core.ConflictException;
import com.codenvy.api.core.ForbiddenException;
import com.codenvy.api.core.ServerException;
import com.codenvy.api.project.server.FileEntry;
import com.codenvy.api.project.server.FolderEntry;
import com.codenvy.api.project.server.ProjectGenerator;
import com.codenvy.api.project.shared.dto.NewProject;
import com.codenvy.ide.maven.tools.Model;

import org.wso2.dss.shared.ProjectConstants;

/**
 * @author Evgen Vidolob
 */
public class DSSProjectGenerator implements ProjectGenerator {

    public static final String TAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @Override
    public String getId() {
        return ProjectConstants.GENERATOR_ID;
    }

    @Override
    public String getProjectTypeId() {
        return ProjectConstants.PROJECT_ID;
    }

    @Override
    public void generateProject(FolderEntry baseFolder, NewProject newProjectDescriptor)
            throws ForbiddenException, ConflictException, ServerException {
        Model model = Model.createModel();
        model.setModelVersion("4.0.0");
        model.setGroupId(newProjectDescriptor.getAttributes().get(ProjectConstants.GROUP_ID).get(0));
        model.setArtifactId(newProjectDescriptor.getAttributes().get(ProjectConstants.ARTIFACT_ID).get(0));
        model.setVersion(newProjectDescriptor.getAttributes().get(ProjectConstants.VERSION).get(0));
        FileEntry pomFile = baseFolder.createFile("pom.xml", (byte[])null, "text/xml");

        model.writeTo(pomFile.getVirtualFile());


        FolderEntry dbsFolder = baseFolder.createFolder("dbs");
        dbsFolder.createFile(baseFolder.getName() + ".dbs", TAG.getBytes(), "text/xml+wso2-dbs");
    }
}
