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

import com.codenvy.ide.jseditor.client.codeassist.CodeAssistCallback;
import com.codenvy.ide.jseditor.client.codeassist.CodeAssistProcessor;
import com.codenvy.ide.jseditor.client.codeassist.CompletionProposal;
import com.codenvy.ide.jseditor.client.texteditor.TextEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgen Vidolob
 */
public class DBSCodeAssistantProcessor implements CodeAssistProcessor {


    @Override
    public void computeCompletionProposals(TextEditor editor, int offset, CodeAssistCallback callback) {
        List<CompletionProposal> proposals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            proposals.add(new DBSCompletionProposal("test:" + i, offset));
        }
        callback.proposalComputed(proposals);
    }

    @Override
    public String getErrorMessage() {
        return null;
    }
}
