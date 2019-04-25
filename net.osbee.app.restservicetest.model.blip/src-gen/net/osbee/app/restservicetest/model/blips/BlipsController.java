/**
 * Copyright (c) 2011, 2019 - Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 		Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 */
package net.osbee.app.restservicetest.model.blips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.osbp.bpm.AbstractBPMServiceTask;
import org.eclipse.osbp.bpm.AbstractBlipBPMFunctionProvider;
import org.eclipse.osbp.bpm.AbstractBlipBPMItem;
import org.eclipse.osbp.bpm.AbstractBlipBPMUserTask;
import org.eclipse.osbp.bpm.BPMCallActivity;
import org.eclipse.osbp.bpm.BPMEndEvent;
import org.eclipse.osbp.bpm.BPMScriptTask;
import org.eclipse.osbp.bpm.BPMSplitGateway;
import org.eclipse.osbp.bpm.BPMSplitGateway.GatewayMode;
import org.eclipse.osbp.bpm.BlipBPMOutgoing;
import org.eclipse.osbp.bpm.BlipBPMStartInfo;
import org.eclipse.osbp.bpm.api.IBPMEngine;
import org.eclipse.osbp.bpm.api.IBlipBPMFunctionProvider;
import org.eclipse.osbp.bpm.api.IBlipBPMWorkloadModifiableItem;
import org.eclipse.osbp.bpm.api.ServiceExecutionMode;
import org.eclipse.osbp.bpm.api.ServiceImplementation;
import org.eclipse.osbp.dsl.common.datatypes.IDto;
import org.eclipse.osbp.preferences.ProductConfiguration;
import org.eclipse.osbp.ui.api.useraccess.IBlipProcessPermissions;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = IBlipBPMFunctionProvider.class)
@SuppressWarnings("all")
public class BlipsController extends AbstractBlipBPMFunctionProvider {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("blip");
  
  @Activate
  protected void activate(final ComponentContext context) {
    
  }
}
