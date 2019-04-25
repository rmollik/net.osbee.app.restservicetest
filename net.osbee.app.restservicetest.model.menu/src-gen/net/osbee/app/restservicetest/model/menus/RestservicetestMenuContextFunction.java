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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.TreeDragMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.stream.Collectors;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.osbp.bpm.api.IBPMEngine;
import org.eclipse.osbp.bpm.api.IBlipBPMFunctionProvider;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherCommand;
import org.eclipse.osbp.runtime.common.event.EventDispatcherEvent.EventDispatcherDataTag;
import org.eclipse.osbp.ui.api.contextfunction.IUserMenuProvider;
import org.eclipse.osbp.ui.api.contextfunction.IViewEmbeddedProvider;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService;
import org.eclipse.osbp.ui.api.themes.IThemeResourceService.ThemeResourceType;
import org.eclipse.osbp.ui.api.useraccess.AbstractAuthorization.Action;
import org.eclipse.osbp.ui.api.useraccess.AbstractAuthorization.Group;
import org.eclipse.osbp.ui.api.useraccess.IUserAccessService;
import org.eclipse.osbp.utils.vaadin.bpmn.BpmnWindow;
import org.eclipse.osbp.xtext.menu.common.UserMenuItem;
import org.eclipse.osbp.xtext.menu.common.UserMenuItem.UserMenuItemType;
import org.eclipse.xtext.xbase.lib.Pair;
import org.osgi.service.component.annotations.Component;
import org.vaadin.hene.popupbutton.PopupButton;

@Component(service = IContextFunction.class, property = "service.context.key=Menu")
@SuppressWarnings("all")
public class RestservicetestMenuContextFunction implements IContextFunction {
  @Override
  public Object compute(final IEclipseContext context, final String contextKey) {
    MApplication application = context.get(MApplication.class);
    IEclipseContext appCtx = application.getContext();
    IUserMenuProvider provider = ContextInjectionFactory.make(RestservicetestMenu.class, appCtx);
    appCtx.set(IUserMenuProvider.class, provider);
    return provider;
  }
}
