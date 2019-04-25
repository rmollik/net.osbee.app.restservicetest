package net.osbee.app.restservicetest.model.menus;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.osbp.bpm.api.IBPMEngine;
import org.eclipse.osbp.bpm.api.IBlipBPMFunctionProvider;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherCommand;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherDataTag;
import org.eclipse.osbp.runtime.common.event.IEventDispatcher;
import org.eclipse.osbp.ui.api.contextfunction.IUserMenuProvider;
import org.eclipse.osbp.ui.api.contextfunction.IVaadinDialogProvider;
import org.eclipse.osbp.ui.api.contextfunction.IViewEmbeddedProvider;
import org.eclipse.osbp.ui.api.menu.IMenuItemHandler;
import org.eclipse.osbp.ui.api.metadata.IDSLMetadataService;
import org.eclipse.osbp.ui.api.perspective.IPerspectiveProvider;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService.ThemeResourceType;
import org.eclipse.osbp.ui.api.user.IUser;
import org.eclipse.osbp.ui.api.useraccess.AbstractAuthorization.Action;
import org.eclipse.osbp.ui.api.useraccess.AbstractAuthorization.Group;
import org.eclipse.osbp.ui.api.useraccess.IUserAccessService;
import org.eclipse.osbp.utils.vaadin.bpmn.BpmnWindow;
import org.eclipse.osbp.vaaclipse.api.VaadinExecutorService;
import org.eclipse.osbp.xtext.menu.common.UserMenuItem;
import org.eclipse.osbp.xtext.menu.common.UserMenuItem.UserMenuItemType;
import org.eclipse.xtext.xbase.lib.Pair;
import org.slf4j.Logger;
import org.vaadin.hene.popupbutton.PopupButton;

@SuppressWarnings("all")
public class RestservicetestMenu implements IUserMenuProvider, IUser.UserLocaleListener, PopupButton.PopupVisibilityListener {
  private static Logger log = org.slf4j.LoggerFactory.getLogger("menu");
  
  @Inject
  private IThemeResourceService themeResourceService;
  
  @Inject
  private IUserAccessService userAccessService;
  
  @Inject
  private IDSLMetadataService dslMetadataService;
  
  @Inject
  private IEventDispatcher eventDispatcher;
  
  @Inject
  private IPerspectiveProvider perspectiveProvider;
  
  @Inject
  private IUser user;
  
  private Locale locale;
  
  @Inject
  @Optional
  private IBPMEngine bpmEngine;
  
  private HashMap<Accordion, Component> selectedMap;
  
  private VerticalLayout menuComponent;
  
  private String reloadModelText;
  
  private String designerText;
  
  private String designmodeText;
  
  private String undoText;
  
  private String redoText;
  
  private String newText;
  
  private String changeText;
  
  private String exportText;
  
  private String downloadText;
  
  @Inject
  private IEclipseContext context;
  
  @Inject
  @Optional
  @Named("ReloadModelHandler")
  private IMenuItemHandler reloadModelHandler;
  
  @Inject
  @Optional
  @Named("DesignModeHandler")
  private IMenuItemHandler designModeHandler;
  
  @Inject
  @Optional
  @Named("DesignerUndoHandler")
  private IMenuItemHandler designerUndoHandler;
  
  @Inject
  @Optional
  @Named("DesignerRedoHandler")
  private IMenuItemHandler designerRedoHandler;
  
  @Inject
  @Optional
  @Named("NewPerspectiveHandler")
  private IMenuItemHandler newPerspectiveHandler;
  
  @Inject
  @Optional
  @Named("ChangePerspectiveHandler")
  private IMenuItemHandler changePerspectiveHandler;
  
  @Inject
  @Optional
  @Named("ExportPerspectiveHandler")
  private IMenuItemHandler exportPerspectiveHandler;
  
  @Inject
  @Optional
  @Named("DownloadPerspectiveHandler")
  private IMenuItemHandler downloadPerspectiveHandler;
  
  private HashMap<TabSheet.Tab, Pair<String, String>> tabs;
  
  private HashMap<UserMenuItem, String> items;
  
  @Inject
  @Named("UserFilter")
  private IVaadinDialogProvider userFilter;
  
  @Inject
  @Named("KeyBindingDialog")
  private IVaadinDialogProvider keyBinding;
  
  @Inject
  @Named("SystemSettings")
  private IVaadinDialogProvider systemSettings;
  
  @Inject
  @Named("ReportPrinterDialog")
  private IVaadinDialogProvider reportPrinter;
  
  @Inject
  private VaadinExecutorService executorService;
  
  private HashMap<MenuBar.MenuItem, Runnable> enabledUpdaters = new HashMap<>();
  
  private HashMap<IMenuItemHandler, MenuBar.MenuItem> menuItems = new HashMap<>();
  
  private BpmnWindow bpmnWindow;
  
  @Inject
  @Named("UserAccount")
  private IViewEmbeddedProvider userAccount;
  
  @Inject
  public RestservicetestMenu() {
    
  }
  
  @PostConstruct
  public void init() {
    bpmnWindow = new BpmnWindow(bpmEngine, context);
    tabs = new HashMap<>();
    items = new HashMap<>();
    user.addUserLocaleListener(this);
    locale = user.getLocale();
    menuComponent = createMenu();
    localeChanged(locale);
  }
  
  public VerticalLayout createMenu() {
    selectedMap = new HashMap<Accordion,Component>();
    VerticalLayout tabRoot = new VerticalLayout();
    tabRoot.setSizeFull();
    ItemDescriptionGenerator generator = new ItemDescriptionGenerator() {                             
    	public String generateDescription(Component source, Object itemId, Object propertyId) {
    	    return ((UserMenuItem)itemId).getI18nDescription();
    	}
    };
    MPerspective perspective = context.get(MPerspective.class);
    ValueChangeListener valueChangeListener = new ValueChangeListener() {
    	@Override
    	public void valueChange(ValueChangeEvent event) {
    		UserMenuItem item = (UserMenuItem) ((Tree)event.getProperty()).getValue();
    		if(item != null) {
    			if(item.getType() == UserMenuItemType.PROCESS) {
    				bpmEngine.registerProcess(item.getCallId());
    				if(bpmEngine.processHasErrors()) {
    					String errorMessage = bpmEngine.getKnowledgeBuilderErrors().stream().map(e->e.toString()).collect(Collectors.joining("\n"));
    					Notification.show(errorMessage, Type.ERROR_MESSAGE);
    				} else {
    					bpmnWindow.showBpmn(item.getCallId());
    				}
    			} 
    			else if(item.getType() == UserMenuItemType.PERSPECTIVE) {
    				bpmnWindow.closeBpmn();
    				log.debug("start perspective "+item.getCallId());
    				perspectiveProvider.openPerspective(item.getCallId());
    			}
    			((Tree)event.getProperty()).select(null);
    		}
    		EventDispatcherEvent evnt = new EventDispatcherEvent(perspective, EventDispatcherCommand.CLOSE, "UserMenu", "net.osbee.app.restservicetest.model.menus");
    		eventDispatcher.sendEvent(evnt);
    	}
    };
    Accordion accordion = new Accordion();
    tabRoot.addComponent(accordion);
    accordion.setWidth("600px");
    accordion.addStyleName("os-accordion-level0");
    accordion.addSelectedTabChangeListener(new SelectedTabChangeListener() {
    	@Override
    	public void selectedTabChange(SelectedTabChangeEvent event) {
    		setIcon(accordion, false);
    		selectedMap.put(accordion, accordion.getSelectedTab());
    		setIcon(accordion, true);
    		Component component = accordion.getSelectedTab();
    		if(component instanceof AbstractOrderedLayout) {
    			if(((AbstractOrderedLayout)component).getData() instanceof IViewEmbeddedProvider) {
    				IViewEmbeddedProvider view = ((IViewEmbeddedProvider)((AbstractOrderedLayout)component).getData());
    				view.createComponents();
    				// eventDispatcher
    				EventDispatcherEvent evnt = new EventDispatcherEvent(perspective, EventDispatcherCommand.SELECT, userAccessService.getUser().getClass().getCanonicalName(), "Menu");
    				evnt.addItem(EventDispatcherDataTag.DTO, userAccessService.getUser());
    				eventDispatcher.sendEvent(evnt);
    			}
    		}
    	}
    });
    
    if(dslMetadataService.isMenuCategoryAuthorized("net.osbee.app.restservicetest.model.menus.Menu", userAccessService)) {
    	VerticalLayout tabMenu = new VerticalLayout();
    	Tree categoryMenu = new Tree();
    	if(dslMetadataService.isOsbee()) {
    		categoryMenu.setDragMode(TreeDragMode.NODE);
    	}
    	categoryMenu.addValueChangeListener(valueChangeListener);
    	categoryMenu.setItemDescriptionGenerator(generator);
    	tabMenu.addComponent(categoryMenu);
    	categoryMenu.setWidth("600px");
    	categoryMenu.addStyleName("os-menutree-level0");
    	accordion.addTab(tabMenu, "Menu", themeResourceService.getThemeResource("collapse_minus_16", ThemeResourceType.ICON));
    	tabs.put(accordion.getTab(tabMenu), new Pair("Menu",""));
    	if(dslMetadataService.isMenuTreeAuthorized("net.osbee.app.restservicetest.model.menus.Menu.Masterdata", userAccessService)) {
    		UserMenuItem Masterdata = new UserMenuItem(dslMetadataService, "Masterdata", "Masterdata", UserMenuItemType.NONE, "", null);
    		items.put(Masterdata, "Masterdata");
    		categoryMenu.addItem(Masterdata);
    		categoryMenu.setChildrenAllowed(Masterdata, true);
    		if(dslMetadataService.isPerspectiveAuthorized("net.osbee.app.restservicetest.model.perspectives.restservicetest", userAccessService)) {
    			UserMenuItem Masterdatarestservicetest = new UserMenuItem(dslMetadataService, "restservicetest", 
    			"net.osbee.app.restservicetest.model.perspectives.restservicetest"
    			, UserMenuItemType.PERSPECTIVE, "", "");
    			items.put(Masterdatarestservicetest, "restservicetest");
    			categoryMenu.addItem(Masterdatarestservicetest);
    			categoryMenu.setParent(Masterdatarestservicetest, Masterdata);
    			categoryMenu.setChildrenAllowed(Masterdatarestservicetest, false);
    		}
    		
    	}
    	if(dslMetadataService.isMenuTreeAuthorized("net.osbee.app.restservicetest.model.menus.Menu.Administration", userAccessService)) {
    		UserMenuItem Administration = new UserMenuItem(dslMetadataService, "Administration", "Administration", UserMenuItemType.NONE, "", null);
    		items.put(Administration, "Administration");
    		categoryMenu.addItem(Administration);
    		categoryMenu.setChildrenAllowed(Administration, true);
    		if(dslMetadataService.isPerspectiveAuthorized("org.eclipse.osbp.authentication.account.perspectives.UserAdministration", userAccessService)) {
    			UserMenuItem AdministrationuserAdministration = new UserMenuItem(dslMetadataService, "userAdministration", 
    			"org.eclipse.osbp.authentication.account.perspectives.UserAdministration"
    			, UserMenuItemType.PERSPECTIVE, "", "employee");
    			items.put(AdministrationuserAdministration, "userAdministration");
    			categoryMenu.addItem(AdministrationuserAdministration);
    			categoryMenu.setParent(AdministrationuserAdministration, Administration);
    			categoryMenu.setChildrenAllowed(AdministrationuserAdministration, false);
    			categoryMenu.setItemIcon(AdministrationuserAdministration, themeResourceService.getThemeResource("employee", ThemeResourceType.ICON));
    		}
    		
    	}
    }
    if(dslMetadataService.isMenuCategoryAuthorized("net.osbee.app.restservicetest.model.menus.Profile", userAccessService)) {
    	VerticalLayout tabProfile = new VerticalLayout();
    	Tree categoryProfile = new Tree();
    	if(dslMetadataService.isOsbee()) {
    		categoryProfile.setDragMode(TreeDragMode.NODE);
    	}
    	categoryProfile.addValueChangeListener(valueChangeListener);
    	categoryProfile.setItemDescriptionGenerator(generator);
    	tabProfile.addComponent(categoryProfile);
    	categoryProfile.setWidth("600px");
    	categoryProfile.addStyleName("os-menutree-level0");
    	accordion.addTab(tabProfile, "Profile", themeResourceService.getThemeResource("collapse_minus_16", ThemeResourceType.ICON));
    	tabs.put(accordion.getTab(tabProfile), new Pair("Profile",""));
    	userAccount.createView(tabProfile);
    	tabProfile.setData(userAccount);
    }
    return tabRoot;
    
  }
  
  public void setIcon(final Accordion accordion, final boolean open) {
    Tab tab = accordion.getTab(selectedMap.get(accordion));
    if (tab != null) {
    	if(open) {
    		tab.setIcon(themeResourceService.getThemeResource("expand_plus_16", ThemeResourceType.ICON));
    	} else {
    		tab.setIcon(themeResourceService.getThemeResource("collapse_minus_16", ThemeResourceType.ICON));
    	}
    }
    
  }
  
  @Override
  public VerticalLayout getMenu(final PopupButton popup) {
    popup.addPopupVisibilityListener(this);
    return menuComponent;
    
  }
  
  @Override
  public void localeChanged(final Locale locale) {
    this.locale = locale;
    for(TabSheet.Tab tab: tabs.keySet()) {
    	tab.setCaption(dslMetadataService.translate(locale.toLanguageTag(),tabs.get(tab).getKey()));
    	tab.setDescription(dslMetadataService.translate(locale.toLanguageTag(),tabs.get(tab).getValue()));
    }
    for(UserMenuItem item: items.keySet()) {
    	item.setLocale(locale);
    }
    reloadModelText = dslMetadataService.translate(locale.toLanguageTag(),"reloadmodel");
    designerText = dslMetadataService.translate(locale.toLanguageTag(),"designer");
    designmodeText = dslMetadataService.translate(locale.toLanguageTag(),"designermode");
    undoText = dslMetadataService.translate(locale.toLanguageTag(),"undo");
    redoText = dslMetadataService.translate(locale.toLanguageTag(),"redo");
    newText = dslMetadataService.translate(locale.toLanguageTag(),"new");
    changeText = dslMetadataService.translate(locale.toLanguageTag(),"change");
    exportText = dslMetadataService.translate(locale.toLanguageTag(),"export");
    downloadText = dslMetadataService.translate(locale.toLanguageTag(),"download");
    
  }
  
  @Override
  public void popupVisibilityChange(final PopupButton.PopupVisibilityEvent event) {
    if(event.isPopupVisible()) {
    	registerEnablementUpdaters();
    } else {
    	unregisterEnablementUpdaters();
    }
  }
  
  public void registerEnablementUpdaters() {
    for(IMenuItemHandler handler:menuItems.keySet()) {
    	if (!enabledUpdaters.containsKey(menuItems.get(handler))) {
    		Runnable runnable = new Runnable() {
    	
    			@Override
    			public void run() {
    				boolean can = handler.canExecute();
    				if(menuItems.get(handler).isEnabled() != can) {
    					menuItems.get(handler).setEnabled(can);
    				}
    			}
    		};
    		this.enabledUpdaters.put(menuItems.get(handler), runnable);
    		executorService.invokeLaterAlways(runnable);
    	}
    }
  }
  
  public void unregisterEnablementUpdaters() {
    for(IMenuItemHandler handler:menuItems.keySet()) {
    	Runnable runnable = enabledUpdaters.remove(menuItems.get(handler));
    	if (runnable != null) {
    		executorService.removeAlwaysRunnable(runnable);
    	}
    }
  }
}
