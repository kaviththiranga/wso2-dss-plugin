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
 * Created by Evgen on 1/23/15.
 */
package org.wso2.dss.client.editor.text;

import elemental.dom.Element;

import com.codenvy.ide.api.icon.Icon;
import com.codenvy.ide.jseditor.client.codeassist.Completion;
import com.codenvy.ide.jseditor.client.codeassist.CompletionProposal;
import com.codenvy.ide.jseditor.client.document.EmbeddedDocument;
import com.codenvy.ide.jseditor.client.text.LinearRange;

/**
 * @author Evgen Vidolob
 */
public class DBSCompletionProposal implements CompletionProposal {

    private final String text;

    private final int offset;


    public DBSCompletionProposal(String text, int offset) {
        this.text = text;
        this.offset = offset;
    }

    @Override
    public Element getAdditionalProposalInfo() {
        return null;
    }

    @Override
    public String getDisplayString() {
        return text;
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public void getCompletion(CompletionCallback callback) {
        callback.onCompletion(new Completion() {
            @Override
            public void apply(EmbeddedDocument document) {
                document.replace(offset, 0, text);
            }

            @Override
            public LinearRange getSelection(EmbeddedDocument document) {
                return null;
            }
        });
    }
}
