package net.osbee.app.restservicetest.model.functionlibraries;

import org.eclipse.osbp.bpm.BlipBaseFunctionGroup;
import org.eclipse.osbp.ui.api.functionlibrary.IFunctionLibraryGroup;
import org.eclipse.osbp.ui.api.functionlibrary.IFunctionLibraryPackage;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;

@Component(service = IFunctionLibraryPackage.class)
@SuppressWarnings("all")
public final class FunctionLibraryPackage implements IFunctionLibraryPackage {
}
