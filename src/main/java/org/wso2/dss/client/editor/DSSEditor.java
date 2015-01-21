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

import com.codenvy.ide.api.editor.AbstractEditorPresenter;
import com.codenvy.ide.api.editor.EditorInput;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Evgen Vidolob
 */
public class DSSEditor extends AbstractEditorPresenter {
    @Override
    protected void initializeEditor() {
//        input.getFile()
    }

    @Override
    public void doSave() {

    }

    @Override
    public void doSave(AsyncCallback<EditorInput> callback) {

    }

    @Override
    public void doSaveAs() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void close(boolean save) {

    }

    @Nonnull
    @Override
    public String getTitle() {
        return null;
    }

    @Nullable
    @Override
    public ImageResource getTitleImage() {
        return null;
    }

    @Nullable
    @Override
    public String getTitleToolTip() {
        return null;
    }

    @Override
    public void go(AcceptsOneWidget container) {
        Frame editorFrame = new Frame("/somewar/editor.html");
        container.setWidget(editorFrame);
    }
}
