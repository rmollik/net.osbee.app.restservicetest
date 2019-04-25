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
import org.eclipse.e4.ui.workbench.IPresentationEngine;
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
import org.eclipse.osbp.xtext.action.common.IToolbarAction;
import org.eclipse.osbp.xtext.action.common.TaskHelper;
import org.eclipse.osbp.xtext.datainterchange.common.WorkerThreadRunnable;
import org.eclipse.osbp.xtext.datainterchange.common.WorkerThreadRunnable.Parameter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.slf4j.Logger;

@SuppressWarnings("all")
public class DialogToolbar implements IToolbarAction {
  public DialogToolbar() {
    super();
  }
  
  private static Logger log = org.slf4j.LoggerFactory.getLogger("toolbar");
  
  private String uuid;
  
  private MToolBar toolbar;
  
  private String stateLabelUUID;
  
  @Override
  public void createToolbar(final IEclipseContext eclipseContext, final IPresentationEngine renderingEngine, final IThemeResourceService themeResourceService, final String uuid) {
    this.uuid = uuid;
    MenuFactoryImpl factory = MenuFactoryImpl.eINSTANCE;
    MHandledToolItem toolItem = null;
    MToolBarSeparator separator = null;
    
    MApplication application = (MApplication)eclipseContext.get(MApplication.class);
    MPart part = (MPart)eclipseContext.get(MPart.class);
    if(part != null) {
    	toolbar = part.getToolbar();
    	if (toolbar == null) {
    		toolbar = factory.createToolBar();
    		toolbar.setElementId(uuid);
    		part.getContext().set("TOOLBAR_UUID", toolbar.getElementId());
    		toolbar.setToBeRendered(true);
    		part.setToolbar(toolbar);
    		
    		Panel panel = (Panel) part.getWidget();
    		if (panel != null) {		// it is already visible in terms of vaadin
    			//create toolbar area
    			VerticalLayout rootContainer = (VerticalLayout)panel.getContent();
    			CssLayout toolbarArea = new CssLayout();
    			toolbarArea.setStyleName(EnumCssClass.MPARTTOOLBARAREA.toString());
    			toolbarArea.setSizeUndefined();
    			toolbarArea.setWidth("100%");
    			rootContainer.addComponentAsFirst(toolbarArea);
    			
    			//create toolbar
    			Component toolbarWidget = (Component) renderingEngine.createGui(toolbar);
    			((AbstractLayout)toolbarWidget).setSizeUndefined();
    			toolbarWidget.setStyleName(EnumCssClass.MPARTTOOLBAR.toString());
    			toolbarArea.addComponent(toolbarWidget);
    		}
    	}
    	else {
    		toolbar.getChildren().removeIf(c -> c.isToBeRendered());
    	}
    toolItem = factory.createHandledToolItem();
    toolItem.setElementId(UUID.randomUUID().toString());
    toolItem.setType(ItemType.PUSH);
    toolItem.setTooltip("newItem.keybinding.CTRL ALT N");
    toolItem.setIconURI(themeResourceService.getThemeURI("new", ThemeResourceType.ICON));
    toolItem.setEnabled(true);
    toolItem.setToBeRendered(true);
    toolItem.setVisible(true);
    setCommand("newItem", toolItem, application);
    toolbar.getChildren().add(toolItem);
    toolItem = factory.createHandledToolItem();
    toolItem.setElementId(UUID.randomUUID().toString());
    toolItem.setType(ItemType.PUSH);
    toolItem.setTooltip("saveItem.keybinding.CTRL ALT S");
    toolItem.setIconURI(themeResourceService.getThemeURI("save", ThemeResourceType.ICON));
    toolItem.setEnabled(true);
    toolItem.setToBeRendered(true);
    toolItem.setVisible(true);
    setCommand("saveItem", toolItem, application);
    toolbar.getChildren().add(toolItem);
    toolItem = factory.createHandledToolItem();
    toolItem.setElementId(UUID.randomUUID().toString());
    toolItem.setType(ItemType.PUSH);
    toolItem.setTooltip("deleteItem.keybinding.CTRL ALT D");
    toolItem.setIconURI(themeResourceService.getThemeURI("filter_except", ThemeResourceType.ICON));
    toolItem.setEnabled(true);
    toolItem.setToBeRendered(true);
    toolItem.setVisible(true);
    setCommand("deleteItem", toolItem, application);
    toolbar.getChildren().add(toolItem);
    toolItem = factory.createHandledToolItem();
    toolItem.setElementId(UUID.randomUUID().toString());
    toolItem.setType(ItemType.PUSH);
    toolItem.setTooltip("cancelItem.keybinding.CTRL ALT Z");
    toolItem.setIconURI(themeResourceService.getThemeURI("nopic_small", ThemeResourceType.ICON));
    toolItem.setEnabled(true);
    toolItem.setToBeRendered(true);
    toolItem.setVisible(true);
    setCommand("cancelItem", toolItem, application);
    toolbar.getChildren().add(toolItem);
    }
  }
  
  @Override
  public MToolBar getToolBar() {
    return toolbar;
  }
  
  @Override
  public String getStateLabelUUID() {
    return stateLabelUUID;
  }
  
  private void setCommand(final String commandName, final MHandledToolItem toolItem, final MApplication application) {
    for(MCommand command:application.getCommands()) {
    	if(command.getCommandName().equals(commandName)) {
    		toolItem.setCommand(command);
    		break;
    	}
    }
    
  }
}
