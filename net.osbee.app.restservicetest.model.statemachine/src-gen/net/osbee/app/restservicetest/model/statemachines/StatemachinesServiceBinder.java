package net.osbee.app.restservicetest.model.statemachines;

import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.Compare.Greater;
import com.vaadin.data.util.filter.Compare.GreaterOrEqual;
import com.vaadin.data.util.filter.Compare.Less;
import com.vaadin.data.util.filter.Compare.LessOrEqual;
import com.vaadin.data.util.filter.IsNull;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.Timer;
import jpos.BaseControl;
import jpos.CashDrawer;
import jpos.JposException;
import jpos.LineDisplay;
import jpos.LineDisplayConst;
import jpos.POSPrinter;
import jpos.POSPrinterConst;
import jpos.config.JposEntry.Prop;
import jpos.config.simple.SimpleEntry;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.osbp.abstractstatemachine.AbstractStateMachine;
import org.eclipse.osbp.abstractstatemachine.POSServiceBinder;
import org.eclipse.osbp.abstractstatemachine.TaskEventSource;
import org.eclipse.osbp.ecview.core.extension.model.extension.YSuggestTextFieldEvents;
import org.eclipse.osbp.runtime.common.filter.IDTOService;
import org.eclipse.osbp.runtime.common.i18n.ITranslator;
import org.eclipse.osbp.ui.api.functionlibrary.IFunctionLibraryService;
import org.eclipse.osbp.ui.api.message.MessageEvent;
import org.eclipse.osbp.ui.api.message.MessageEvent.EventType;
import org.eclipse.osbp.ui.api.statemachine.IStateMachine;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService.ThemeResourceType;
import org.joda.time.DateTime;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.useradmin.User;
import org.slf4j.Logger;

/**
 * Copyright (c) 2011, 2019 - Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 		Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 */
@SuppressWarnings("serial")
@Component
public class StatemachinesServiceBinder {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("servicebinder");
  
  private static IFunctionLibraryService functionLibraryService;
  
  public static IFunctionLibraryService getFunctionLibraryService() {
    return functionLibraryService;
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindFunctionLibraryService(final IFunctionLibraryService functionLibraryService) {
    StatemachinesServiceBinder.functionLibraryService = functionLibraryService;
    log.debug("FSM FunctionLibraryServiceService bound");
  }
  
  public synchronized void unbindFunctionLibraryService(final IFunctionLibraryService functionLibraryService) {
    StatemachinesServiceBinder.functionLibraryService = null;
    log.debug("FSM FunctionLibraryServiceService unbound");
  }
}
