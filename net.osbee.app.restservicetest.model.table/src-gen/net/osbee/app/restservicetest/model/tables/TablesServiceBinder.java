package net.osbee.app.restservicetest.model.tables;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.Align;
import com.vaadin.ui.CustomTable.ColumnCollapseEvent;
import com.vaadin.ui.CustomTable.ColumnCollapseListener;
import com.vaadin.ui.CustomTable.ColumnReorderEvent;
import com.vaadin.ui.CustomTable.ColumnReorderListener;
import com.vaadin.ui.CustomTable.ColumnResizeEvent;
import com.vaadin.ui.CustomTable.ColumnResizeListener;
import com.vaadin.ui.CustomTable.RowHeaderMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.UI;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.eclipse.bpmn2.Task;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.di.extensions.EventUtils;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.osbp.bpm.api.BPMOperation;
import org.eclipse.osbp.bpm.api.BPMStatus;
import org.eclipse.osbp.bpm.api.BPMTaskEventType;
import org.eclipse.osbp.bpm.api.BPMTaskSummary;
import org.eclipse.osbp.bpm.api.BPMTaskUserEvent;
import org.eclipse.osbp.bpm.api.IBlipBPMConstants;
import org.eclipse.osbp.bpm.api.IBlipBPMFunctionProvider;
import org.eclipse.osbp.bpm.api.IBlipBPMStartInfo;
import org.eclipse.osbp.bpm.api.IBlipBPMWorkloadModifiableItem;
import org.eclipse.osbp.dsl.common.datatypes.IDto;
import org.eclipse.osbp.dsl.dto.lib.impl.DtoServiceAccess;
import org.eclipse.osbp.eventbroker.EventBrokerMsg;
import org.eclipse.osbp.preferences.ProductConfiguration;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherCommand;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherDataTag;
import org.eclipse.osbp.runtime.common.event.IDualData;
import org.eclipse.osbp.runtime.common.event.SelectionStore;
import org.eclipse.osbp.runtime.common.filter.IDTOService;
import org.eclipse.osbp.runtime.web.vaadin.common.data.IBeanSearchServiceFactory;
import org.eclipse.osbp.ui.api.contextfunction.IViewEmbeddedProvider;
import org.eclipse.osbp.ui.api.customfields.IBlobService;
import org.eclipse.osbp.ui.api.datamart.DatamartFilter;
import org.eclipse.osbp.ui.api.datamart.DatamartPrimary;
import org.eclipse.osbp.ui.api.datamart.IDatamartFilterGenerator.FilterChangeListener;
import org.eclipse.osbp.ui.api.e4.IE4Table;
import org.eclipse.osbp.ui.api.perspective.IPerspectiveProvider;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService.ThemeResourceType;
import org.eclipse.osbp.ui.api.useraccess.IUserAccessService;
import org.eclipse.osbp.utils.constants.ExtendedDate;
import org.eclipse.osbp.utils.vaadin.SelectUserWindow;
import org.eclipse.osbp.utils.vaadin.ViewLayoutManager;
import org.eclipse.osbp.vaaclipse.common.ecview.api.IECViewSessionHelper;
import org.eclipse.osbp.xtext.action.SelectWorkloadActionEnum;
import org.eclipse.osbp.xtext.action.TableActionEnum;
import org.eclipse.osbp.xtext.blip.BlipItem;
import org.eclipse.osbp.xtext.datamart.common.AEntityDatamart;
import org.eclipse.osbp.xtext.datamart.common.DatamartFilterGenerator;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedAxis;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedHierarchy;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedLevel;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedMember;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedPosition;
import org.eclipse.osbp.xtext.datamart.common.sql.OperativeDtoContainer;
import org.eclipse.osbp.xtext.table.common.BeanFilterTable;
import org.eclipse.osbp.xtext.table.common.CellSetFilterTable;
import org.eclipse.osbp.xtext.table.common.CellSetIndexedContainer;
import org.eclipse.osbp.xtext.table.common.CellSetPagedFilterTable;
import org.eclipse.osbp.xtext.table.common.CheckboxSelectionCellSetFilterTable;
import org.eclipse.osbp.xtext.table.common.TableFilterDecorator;
import org.eclipse.osbp.xtext.table.common.TableFilterGenerator;
import org.eclipse.osbp.xtext.table.common.export.CsvExport;
import org.eclipse.osbp.xtext.table.common.export.ExcelExport;
import org.eclipse.osbp.xtext.table.common.export.PdfExport;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.vaadin.hene.popupbutton.PopupButton;

@SuppressWarnings("serial")
@Component
public class TablesServiceBinder {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("servicebinder");
  
  private static IUserAccessService userAccessService;
  
  public static IUserAccessService getUserAccessService() {
    return userAccessService;
  }
  
  @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
  public synchronized void bindUserAccessMethod(final IUserAccessService userAccessService) {
    TablesServiceBinder.userAccessService = userAccessService;
    log.debug("Datamart UserAccessService bound");
  }
  
  public synchronized void unbindUserAccessMethod(final IUserAccessService userAccessService) {
    TablesServiceBinder.userAccessService = null;
    log.debug("Datamart UserAccessService unbound");
  }
}
