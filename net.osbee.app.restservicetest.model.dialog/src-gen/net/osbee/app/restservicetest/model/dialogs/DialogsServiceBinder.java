package net.osbee.app.restservicetest.model.dialogs;

import com.vaadin.ui.ComponentContainer;
import java.util.HashMap;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.osbp.blob.component.BlobConverter;
import org.eclipse.osbp.core.api.persistence.IPersistenceService;
import org.eclipse.osbp.dsl.dto.lib.impl.DtoServiceAccess;
import org.eclipse.osbp.ecview.core.common.context.IConfiguration;
import org.eclipse.osbp.ecview.core.common.context.IContext;
import org.eclipse.osbp.ecview.core.common.context.IViewContext;
import org.eclipse.osbp.ecview.core.common.model.core.YView;
import org.eclipse.osbp.eventbroker.EventBrokerMsg;
import org.eclipse.osbp.runtime.common.i18n.II18nService;
import org.eclipse.osbp.runtime.web.ecview.presentation.vaadin.VaadinRenderer;
import org.eclipse.osbp.ui.api.contextfunction.IViewEmbeddedProvider;
import org.eclipse.osbp.ui.api.metadata.IDSLMetadataService;
import org.eclipse.osbp.ui.api.report.IReportProvider;
import org.eclipse.osbp.ui.api.statemachine.IDataProvider;
import org.eclipse.osbp.ui.api.statemachine.IStateMachine;
import org.eclipse.osbp.ui.api.statemachine.IStateMachineParticipant;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService;
import org.eclipse.osbp.ui.api.useraccess.IUserAccessService;
import org.eclipse.osbp.xtext.dialogdsl.DialogDSLPackage;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Component
public class DialogsServiceBinder {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("servicebinder");
  
  private static IPersistenceService persistenceService;
  
  private static IThemeResourceService themeResourceService;
  
  private static IUserAccessService userAccessService;
  
  private static IDSLMetadataService dslMetadataService;
  
  private static IReportProvider reportProvider;
  
  public static IPersistenceService getPersistenceService() {
    return persistenceService;
  }
  
  public static IThemeResourceService getThemeResourceService() {
    return themeResourceService;
  }
  
  public static IUserAccessService getUserAccessService() {
    return userAccessService;
  }
  
  public static IDSLMetadataService getDSLMetadataService() {
    return dslMetadataService;
  }
  
  public static IReportProvider getReportProvider() {
    return reportProvider;
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindPersistenceService(final IPersistenceService persistenceService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.persistenceService = persistenceService;
    log.debug("MobileDialog PersistenceService bound");
  }
  
  public synchronized void unbindPersistenceService(final IPersistenceService persistenceService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.persistenceService = null;
    log.debug("MobileDialog PersistenceService unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindThemeResourceService(final IThemeResourceService themeResourceService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.themeResourceService = themeResourceService;
    log.debug("MobileDialog ThemeResourceService bound");
  }
  
  public synchronized void unbindThemeResourceService(final IThemeResourceService themeResourceService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.themeResourceService = null;
    log.debug("MobileDialog ThemeResourceService unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindUserAccessService(final IUserAccessService userAccessService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.userAccessService = userAccessService;
    log.debug("MobileDialog UserAccessService bound");
  }
  
  public synchronized void unbindUserAccessService(final IUserAccessService userAccessService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.userAccessService = null;
    log.debug("MobileDialog UserAccessService unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindDSLMetadataService(final IDSLMetadataService dslMetadataService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.dslMetadataService = dslMetadataService;
    log.debug("MobileDialog DSLMetadataService bound");
  }
  
  public synchronized void unbindDSLMetadataService(final IDSLMetadataService dslMetadataService) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.dslMetadataService = null;
    log.debug("MobileDialog DSLMetadataService unbound");
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindReportProvider(final IReportProvider reportProvider) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.reportProvider = reportProvider;
    log.debug("MobileDialog ReportProvider bound");
  }
  
  public synchronized void unbindReportProvider(final IReportProvider reportProvider) {
    net.osbee.app.restservicetest.model.dialogs.DialogsServiceBinder.reportProvider = null;
    log.debug("MobileDialog ReportProvider unbound");
  }
}
