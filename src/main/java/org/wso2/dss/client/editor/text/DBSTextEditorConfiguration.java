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

import com.codenvy.ide.collections.Collections;
import com.codenvy.ide.collections.StringMap;
import com.codenvy.ide.jseditor.client.codeassist.CodeAssistProcessor;
import com.codenvy.ide.jseditor.client.editorconfig.DefaultTextEditorConfiguration;

/**
 * @author Evgen Vidolob
 */
public class DBSTextEditorConfiguration extends DefaultTextEditorConfiguration {

    @Override
    public StringMap<CodeAssistProcessor> getContentAssistantProcessors() {
        StringMap<CodeAssistProcessor> map = Collections.createStringMap();
        map.put("__dftl_partition_content_type", new DBSCodeAssistantProcessor());
        return  map;
    }
}
