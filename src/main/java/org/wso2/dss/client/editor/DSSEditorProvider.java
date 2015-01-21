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
package org.wso2.dss.client.editor;

import com.codenvy.ide.api.editor.EditorPartPresenter;
import com.codenvy.ide.api.editor.EditorProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.annotation.Nonnull;

/**
 * @author Evgen Vidolob
 */
@Singleton
public class DSSEditorProvider implements EditorProvider {

    @Inject
    public DSSEditorProvider() {
    }

    @Override
    public String getId() {
        return "wso2dssEditor";
    }

    @Override
    public String getDescription() {
        return "Editor for dss file";
    }

    @Nonnull
    @Override
    public EditorPartPresenter getEditor() {
        return null;
    }
}
