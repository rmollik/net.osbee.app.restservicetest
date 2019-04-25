/**
 * Copyright (c) 2011, 2019 - Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * 		Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 */
package net.osbee.app.restservicetest.model.datamarts;

import com.vaadin.ui.Notification;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.eclipse.osbp.bpm.api.BPMTaskSummary;
import org.eclipse.osbp.bpm.api.IBPMEngine;
import org.eclipse.osbp.bpm.api.IBPMTaskClient;
import org.eclipse.osbp.core.api.persistence.IPersistenceService;
import org.eclipse.osbp.dsl.common.datatypes.IDto;
import org.eclipse.osbp.preferences.ProductConfiguration;
import org.eclipse.osbp.runtime.common.event.IDualData;
import org.eclipse.osbp.runtime.common.historized.UUIDHist;
import org.eclipse.osbp.runtime.common.i18n.ITranslator;
import org.eclipse.osbp.ui.api.datamart.DatamartData;
import org.eclipse.osbp.ui.api.datamart.DatamartFilter;
import org.eclipse.osbp.ui.api.datamart.IDataMart;
import org.eclipse.osbp.ui.api.datamart.IDataMart.EType;
import org.eclipse.osbp.ui.api.date.SimpleDateFormatter;
import org.eclipse.osbp.ui.api.metadata.IDSLMetadataService;
import org.eclipse.osbp.ui.api.useraccess.IUserAccessService;
import org.eclipse.osbp.user.User;
import org.eclipse.osbp.xtext.datamart.common.DatamartDtoMapper;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedAxis;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedMember;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedPosition;
import org.eclipse.osbp.xtext.datamart.common.sql.SqlCellSet;
import org.olap4j.Axis;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;

@SuppressWarnings("serial")
@Component
public class DatamartsServiceBinder {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("servicebinder");
  
  private static IPersistenceService persistenceService;
  
  private static IBPMEngine bpmEngine;
  
  private static IUserAccessService userAccessService;
  
  private static IDSLMetadataService dslMetadataService;
  
  private static IBPMTaskClient taskClient;
  
  public static IPersistenceService getPersistenceService() {
    return persistenceService;
  }
  
  public static IBPMEngine getBpmEngine() {
    return bpmEngine;
  }
  
  public static IUserAccessService getUserAccessService() {
    return userAccessService;
  }
  
  public static IDSLMetadataService getDSLMetadataService() {
    return dslMetadataService;
  }
  
  public static IBPMTaskClient getTaskClient() {
    return taskClient;
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindPersistenceMethod(final IPersistenceService persistenceService) {
    DatamartsServiceBinder.persistenceService = persistenceService;
    log.debug("Datamart PersistenceService bound");
  }
  
  public synchronized void unbindPersistenceMethod(final IPersistenceService persistenceService) {
    DatamartsServiceBinder.persistenceService = null;
    log.debug("Datamart PersistenceService unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
  public synchronized void bindBPMMethod(final IBPMEngine bpmEngine) {
    DatamartsServiceBinder.bpmEngine = bpmEngine;
    log.debug("Datamart BPMEngine bound");
  }
  
  public synchronized void unbindBPMMethod(final IBPMEngine bpmEngine) {
    DatamartsServiceBinder.bpmEngine = null;
    log.debug("Datamart BPMEngine unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindUserAccessMethod(final IUserAccessService userAccessService) {
    DatamartsServiceBinder.userAccessService = userAccessService;
    log.debug("Datamart UserAccessService bound");
  }
  
  public synchronized void unbindUserAccessMethod(final IUserAccessService userAccessService) {
    DatamartsServiceBinder.userAccessService = null;
    log.debug("Datamart UserAccessService unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindDSLMetadataService(final IDSLMetadataService dslMetadataService) {
    DatamartsServiceBinder.dslMetadataService = dslMetadataService;
    log.debug("Datamart DSLMetadataService bound");
  }
  
  public synchronized void unbindDSLMetadataService(final IDSLMetadataService dslMetadataService) {
    DatamartsServiceBinder.dslMetadataService = null;
    log.debug("Datamart DSLMetadataService unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.OPTIONAL, policy = ReferencePolicy.DYNAMIC)
  public synchronized void bindTaskClient(final IBPMTaskClient taskClient) {
    DatamartsServiceBinder.taskClient = taskClient;
    log.debug("Datamart BPMTaskClient bound");
  }
  
  public synchronized void unbindTaskClient(final IBPMTaskClient taskClient) {
    DatamartsServiceBinder.taskClient = null;
    log.debug("Datamart BPMTaskClient unbound");
  }
}
