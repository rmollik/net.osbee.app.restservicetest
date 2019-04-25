/**
 * Copyright (c) 2011, 2019 - Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 		Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 */
package net.osbee.app.restservicetest.model.actions;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.extensions.EventUtils;
import org.eclipse.e4.core.services.translation.TranslationService;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MBindingContext;
import org.eclipse.e4.ui.model.application.commands.MBindingTable;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.eclipse.e4.ui.model.application.commands.MKeyBinding;
import org.eclipse.e4.ui.model.application.commands.impl.CommandsFactoryImpl;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MTrimmedWindow;
import org.eclipse.e4.ui.model.application.ui.menu.ItemType;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledToolItem;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBarSeparator;
import org.eclipse.e4.ui.model.application.ui.menu.MToolControl;
import org.eclipse.e4.ui.model.application.ui.menu.impl.MenuFactoryImpl;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.osbp.core.api.persistence.IPersistenceService;
import org.eclipse.osbp.ecview.core.common.context.IViewContext;
import org.eclipse.osbp.eventbroker.EventBrokerMsg;
import org.eclipse.osbp.osgi.hybrid.api.HybridVaadinVaaclipseConnector;
import org.eclipse.osbp.preferences.ProductConfiguration;
import org.eclipse.osbp.runtime.common.annotations.IsDirty;
import org.eclipse.osbp.runtime.common.annotations.IsNew;
import org.eclipse.osbp.runtime.common.annotations.IsPositioned;
import org.eclipse.osbp.runtime.common.annotations.IsValid;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherCommand;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherDataTag;
import org.eclipse.osbp.runtime.common.event.IEventDispatcher;
import org.eclipse.osbp.ui.api.contextfunction.ICommandsProvider;
import org.eclipse.osbp.ui.api.e4.IE4Dialog;
import org.eclipse.osbp.ui.api.e4.IE4Focusable;
import org.eclipse.osbp.ui.api.e4.IE4Table;
import org.eclipse.osbp.ui.api.perspective.IPerspectiveProvider;
import org.eclipse.osbp.ui.api.themes.EnumCssClass;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService.ThemeResourceType;
import org.eclipse.osbp.ui.api.useraccess.AbstractAuthorization.Action;
import org.eclipse.osbp.ui.api.useraccess.AbstractAuthorization.Group;
import org.eclipse.osbp.ui.api.useraccess.IUserAccessService;
import org.eclipse.osbp.utils.vaadin.MDXDialog;
import org.eclipse.osbp.vaaclipse.api.VaadinExecutorService;
import org.eclipse.osbp.xtext.action.ChartActionEnum;
import org.eclipse.osbp.xtext.action.DatainterchangeActionEnum;
import org.eclipse.osbp.xtext.action.DialogActionEnum;
import org.eclipse.osbp.xtext.action.ReportActionEnum;
import org.eclipse.osbp.xtext.action.SelectWorkloadActionEnum;
import org.eclipse.osbp.xtext.action.TableActionEnum;
import org.eclipse.osbp.xtext.action.TaskActionEnum;
import org.eclipse.osbp.xtext.action.UIActionEnum;
import org.eclipse.osbp.xtext.action.WorkflowActionEnum;
import org.eclipse.osbp.xtext.action.common.E4Helper;
import org.eclipse.osbp.xtext.action.common.TaskHelper;
import org.eclipse.osbp.xtext.datainterchange.common.WorkerThreadRunnable;
import org.eclipse.osbp.xtext.datainterchange.common.WorkerThreadRunnable.Parameter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.slf4j.Logger;

@SuppressWarnings("all")
public class DeleteItemAction {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("action");
  
  private static Map<String, Boolean> isGranted = new HashMap();
  
  private MUIElement activePart;
  
  public MUIElement getActivePart() {
    return activePart;
  }
  
  @CanExecute
  public boolean canExecute(final IEclipseContext eclipseContext, final IUserAccessService userAccessService, final IViewContext viewContext) {
    IE4Dialog dialog = eclipseContext.get(IE4Dialog.class);
    if (dialog == null) {
    	return false;
    }
    Object dto = viewContext.getBean(IViewContext.MAIN_BEAN_SLOT);
    if (dto == null) {
    	return false;
    }
    boolean result1 = !(boolean) ContextInjectionFactory.invoke(dialog, IsNew.class, eclipseContext);
    boolean result2 = (boolean) ContextInjectionFactory.invoke(dialog, IsPositioned.class, eclipseContext);
    boolean result3 = true;
    if( result1 && result2 ) {
    	String clazzName = dto.getClass().getName();
    	String grantKey = clazzName + "###DELETEABLE";
    	if( isGranted.containsKey(grantKey)) {
    		result3=isGranted.get(grantKey);
    	} else {
    		result3=userAccessService.isGranted(Group.DTO, Action.DELETEABLE, clazzName);
    		isGranted.put(grantKey,result3);
    	}
    }
    return result1 && result2 && result3;
  }
  
  @Execute
  public void execute(final IEclipseContext eclipseContext, final IEventDispatcher eventDispatcher) {
    log.debug("action execute called for deleteItem");
    String uuid = (String)eclipseContext.get("TOOLBAR_UUID");
    MPerspective perspective = eclipseContext.get(MPerspective.class);
    EventDispatcherEvent evnt = new EventDispatcherEvent(perspective, EventDispatcherCommand.ACTION, uuid, "net.osbee.app.restservicetest.model.actions.deleteItem");
    evnt.addItem(EventDispatcherDataTag.BUTTON_ID, DialogActionEnum.DIALOG_ACTION_DELETE);
    eventDispatcher.sendEvent(evnt);
  }
}
