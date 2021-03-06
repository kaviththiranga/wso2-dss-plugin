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
package org.wso2.dss.client.editor.graphical;

import elemental.html.IFrameElement;

import com.codenvy.ide.api.editor.AbstractEditorPresenter;
import com.codenvy.ide.api.editor.EditorInput;
import com.codenvy.ide.util.loging.Log;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Frame;

import org.vectomatic.dom.svg.ui.SVGResource;
import org.wso2.dss.client.editor.IframeEditor;
import org.wso2.dss.client.editor.graphical.messages.GetContentMessage;
import org.wso2.dss.client.editor.graphical.messages.SendContentMessage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Evgen Vidolob
 */
public class DBSEditor extends AbstractEditorPresenter implements IframeEditor {

    private Frame editorFrame;
    private AsyncCallback<String> callback;
    private boolean needToSave= true;

    public DBSEditor() {
        editorFrame = new Frame("/testeditor/index.html");
        editorFrame.addLoadHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                iframeLoaded();
            }
        });
        editorFrame.setSize("100%", "100%");
    }

    @Override
    protected void initializeEditor() {
//        input.getFile().getContent();

    }

    private void iframeLoaded() {
        input.getFile().getContent(new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                Log.error(getClass(), caught);
            }

            @Override
            public void onSuccess(String result) {
                sendContentToIframe(result);
                firePropertyChange(PROP_INPUT);
            }
        });
    }

    private void sendContentToIframe(String result) {
        SendContentMessage message = SendContentMessage.make();
        message.setContent(result);
        message.setFilePath(input.getFile().getPath());
        IFrameElement frameElement = (IFrameElement)editorFrame.getElement();
        frameElement.getContentWindow().postMessage(message, "*");
    }

    @Override
    public void doSave() {
        doSave(new AsyncCallback<EditorInput>() {
            @Override
            public void onSuccess(final EditorInput result) {
                // do nothing
            }

            @Override
            public void onFailure(final Throwable caught) {
                // do nothing
            }
        });
    }

    @Override
    public void doSave(AsyncCallback<EditorInput> callback) {
        sendGetContentMessage();
    }

    private void sendGetContentMessage() {
        GetContentMessage getContentMessage = GetContentMessage.make();
        getContentMessage.setFilePath(input.getFile().getPath());
        IFrameElement frameElement = (IFrameElement)editorFrame.getElement();
        frameElement.getContentWindow().postMessage(getContentMessage,"*");
    }

    @Override
    public void onClose(@Nonnull AsyncCallback<Void> callback) {
        handleClose();
        callback.onSuccess(null);
    }

    @Override
    public void doSaveAs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void activate() {

    }

    @Override
    public void close(boolean save) {

    }

    @Nullable
    @Override
    public SVGResource getTitleSVGImage() {
        return input.getSVGResource();
    }

    @Nonnull
    @Override
    public String getTitle() {
        if (isDirty()) {
            return "*" + input.getName();
        } else {
            return input.getName();
        }
    }

    @Nullable
    @Override
    public ImageResource getTitleImage() {
        return input.getImageResource();
    }

    @Nullable
    @Override
    public String getTitleToolTip() {
        return null;
    }

    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(editorFrame);
    }

    public void saveContent(String content) {
        if(needToSave) {
            input.getFile().updateContent(content, new AsyncCallback<Void>() {
                @Override
                public void onFailure(Throwable caught) {
                    Log.error(getClass(), caught);
                }

                @Override
                public void onSuccess(Void result) {
                    updateDirtyState(false);
                }
            });
        } else{
            needToSave = true;
            if(callback != null){
                callback.onSuccess(content);
            }
        }
    }

    public void setDirtyState(boolean dirty) {
        updateDirtyState(dirty);
    }

    public void serialize(String content){
        sendContentToIframe(content);
    }

    public void deserialize(AsyncCallback<String> callback){
        this.callback = callback;
        this.needToSave = false;
        sendGetContentMessage();
    }
}
