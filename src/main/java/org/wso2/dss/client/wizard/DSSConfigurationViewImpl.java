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
package org.wso2.dss.client.wizard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Evgen Vidolob
 */
public class DSSConfigurationViewImpl extends Composite implements DSSConfigurationView {
    interface DSSConfigurationViewImplUiBinder
            extends UiBinder<Widget, DSSConfigurationViewImpl> {

    }

    private static DSSConfigurationViewImplUiBinder ourUiBinder = GWT.create(DSSConfigurationViewImplUiBinder.class);

    private ActionDelegate delegate;

    @Inject
    public DSSConfigurationViewImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setDelegate(ActionDelegate delegate) {

        this.delegate = delegate;
    }
}