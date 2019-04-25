package net.osbee.app.restservicetest.model.tables;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
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
import com.vaadin.ui.VerticalLayout;
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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.bpmn2.Task;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.EventUtils;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.IPresentationEngine;
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
import org.eclipse.osbp.infogrid.api.IGridSourceFacade;
import org.eclipse.osbp.infogrid.vaaclipse.SingleInfoGridComponent;
import org.eclipse.osbp.osgi.hybrid.api.AbstractHybridVaaclipseView;
import org.eclipse.osbp.preferences.ProductConfiguration;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherCommand;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherDataTag;
import org.eclipse.osbp.runtime.common.event.IDualData;
import org.eclipse.osbp.runtime.common.event.IEventDispatcher;
import org.eclipse.osbp.runtime.common.event.SelectionStore;
import org.eclipse.osbp.runtime.common.filter.IDTOService;
import org.eclipse.osbp.runtime.common.types.ITypeProviderService;
import org.eclipse.osbp.runtime.web.vaadin.common.data.IBeanSearchServiceFactory;
import org.eclipse.osbp.ui.api.contextfunction.IViewEmbeddedProvider;
import org.eclipse.osbp.ui.api.customfields.IBlobService;
import org.eclipse.osbp.ui.api.datamart.DatamartFilter;
import org.eclipse.osbp.ui.api.datamart.DatamartPrimary;
import org.eclipse.osbp.ui.api.datamart.IDatamartFilterGenerator.FilterChangeListener;
import org.eclipse.osbp.ui.api.e4.IE4Table;
import org.eclipse.osbp.ui.api.layout.IViewLayoutManager;
import org.eclipse.osbp.ui.api.metadata.IDSLMetadataService;
import org.eclipse.osbp.ui.api.perspective.IPerspectiveProvider;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService.ThemeResourceType;
import org.eclipse.osbp.ui.api.user.IUser;
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
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedCellSet;
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
import org.eclipse.osbp.xtext.table.common.PropertyLookup;
import org.eclipse.osbp.xtext.table.common.TableFilterDecorator;
import org.eclipse.osbp.xtext.table.common.TableFilterGenerator;
import org.eclipse.osbp.xtext.table.common.export.CsvExport;
import org.eclipse.osbp.xtext.table.common.export.ExcelExport;
import org.eclipse.osbp.xtext.table.common.export.PdfExport;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.vaadin.hene.popupbutton.PopupButton;

@SuppressWarnings("all")
public class PersonsGrid extends AbstractHybridVaaclipseView implements IUser.UserLocaleListener, IEventDispatcher.Receiver, IE4Table {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("tables");
  
  private boolean initDone = false;
  
  private VerticalLayout parent;
  
  @Inject
  private IEclipseContext eclipseContext;
  
  @Inject
  private IUser user;
  
  @Inject
  private IEventDispatcher eventDispatcher;
  
  private ArrayList<Integer> coordinateSystem;
  
  private IViewLayoutManager layoutManager;
  
  @Inject
  private IDSLMetadataService dslMetadataService;
  
  @Inject
  private IBlobService blobService;
  
  @Inject
  private IGridSourceFacade gridSourceService;
  
  @Inject
  private IECViewSessionHelper ecviewPropsProvider;
  
  @Inject
  private ITypeProviderService bundleSpaceTypeProvider;
  
  private SingleInfoGridComponent grid;
  
  private Component dataComponent;
  
  private boolean mustRefresh = false;
  
  private HashMap<String, PropertyLookup> attributeLookupMap;
  
  private HashMap<Component, ArrayList<ArrayList<Integer>>> tabSheets;
  
  private CellSetIndexedContainer dataSourceContainer;
  
  private OperativeDtoContainer operativeDtoContainer;
  
  private ArrayList<CellSetFilterTable> tables;
  
  @Inject
  private IPresentationEngine renderingEngine;
  
  @Inject
  private IThemeResourceService themeResourceService;
  
  private TableFilterDecorator tableFilterDecorator;
  
  @Inject
  public PersonsGrid(final VerticalLayout parent, final IEclipseContext context, final MApplication app) {
    super(parent,context,app);
    
  }
  
  public ArrayList<Integer> getCoordinateSystem() {
    return this.coordinateSystem;
  }
  
  public DerivedCellSet getCellSet(final boolean limited) {
    operativeDtoContainer = null;
    DerivedCellSet cellSet = null;
    if	(cellSet != null) {
    	operativeDtoContainer = cellSet.getOperativeDtoContainer();
    }
    return cellSet;
    
  }
  
  @Focus
  public void setFocus() {
    if(grid != null) {
    	grid.focus();
    }
  }
  
  @PostConstruct
  public void activate() {
    super.initView();
    user.addUserLocaleListener(this);
    eventDispatcher.addEventReceiver(this);
    
  }
  
  @PreDestroy
  public void deactivate() {
    user.removeUserLocaleListener(this);
    eventDispatcher.removeEventReceiver(this);
    super.destroyView();
  }
  
  public void createView(final VerticalLayout parent) {
    getContext().set(IE4Table.class, this);
    tables = new ArrayList<CellSetFilterTable>();
    tableFilterDecorator = new TableFilterDecorator(dslMetadataService, user.getLocale());
    // the timeout to begin the filter process after the last key pressed
    tableFilterDecorator.setTextChangeTimeout(500);
    parent.setPrimaryStyleName("osbp");
    parent.setId("parent");
    parent.setSizeFull();
    layoutManager = new ViewLayoutManager();
    layoutManager.init(parent);
    this.parent = parent;
    coordinateSystem = new ArrayList<Integer>();
    attributeLookupMap = new HashMap<>();
    tabSheets = new HashMap<>();
    initDone = true;
    
  }
  
  public void createComponents() {
    // generate a new result component
    // remove any previous component
    if	(dataComponent != null) {
    	layoutManager.getDataArea().removeComponent(dataComponent);
    	dataComponent = null;
    }
    dataComponent = createDtoGrid();
    dataComponent.setSizeFull();
    dataComponent.setId("dataComponent");
    layoutManager.getDataArea().addComponent(dataComponent);
    layoutManager.getDataArea().setExpandRatio(dataComponent, 1);
    
  }
  
  public void setEnableManualInput(final boolean manualInput) {
    
  }
  
  public void setInput(final Object dto) {
    
  }
  
  private boolean getSelectById() {
    return false;
  }
  
  public Component createDtoGrid() {
    IEclipseContext childContext = eclipseContext.createChild();
    childContext.set(Locale.class, user.getLocale());
    childContext.set("gridSourceId", "net.osbee.app.restservicetest.model.tables.PersonsGrid");
    LinkedHashMap<String, EventDispatcherEvent> selectionEvntList = new LinkedHashMap<>();
    MPerspective perspective = eclipseContext.get(MPerspective.class);
    EventDispatcherEvent evnt = new EventDispatcherEvent(perspective, EventDispatcherCommand.SELECT, "net.osbee.app.restservicetest.model.entities.Person.id", "net.osbee.app.restservicetest.model.tables.Persons");
    selectionEvntList.put("net.osbee.app.restservicetest.model.entities.Person.id", evnt);
    childContext.set("selectionEvnt", selectionEvntList);
    if ("net.osbee.app.restservicetest.model.dtos.PersonDto".equals(getTaskOperativeDtoFqn())) {
    	childContext.set("inputdata", getTaskInitialOperativeDtos());
    }
    grid = ContextInjectionFactory.make(SingleInfoGridComponent.class, childContext);
    return grid;
  }
  
  @Override
  public void localeChanged(final Locale locale) {
    if(initDone) {
    	tableFilterDecorator.setLocale(locale);
    	for(CellSetFilterTable table:tables) {
    		table.setLocale(locale);
    	}
    	for(String key:attributeLookupMap.keySet()) {
    		attributeLookupMap.get(key).setLocale(locale);
    	}
    	if(dataSourceContainer != null) {
    		dataSourceContainer.setLocale(locale);
    	}
    	layoutManager.setLabelValue(dslMetadataService.translate(locale.toLanguageTag(), "Persons"));
    }
    
  }
  
  @Override
  public void receiveEvent(final EventDispatcherEvent event) {
    switch(event.getCommand()) {
    	case SAVE:
    	case DELETE:
    	case REFRESH:
    		if(!event.getSender().equals("net.osbee.app.restservicetest.model.tables.Persons")) {
    			if(event.getTopic().equals("net.osbee.app.restservicetest.model.entities.Person")){
    				// TODO: GridRefresh
    				grid.refreshData();
    			}
    		}
    		break;
    }
    
  }
}
