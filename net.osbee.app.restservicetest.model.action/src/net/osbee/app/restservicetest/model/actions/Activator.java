/**
 * Copyright (c) 2011, 2019 - Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html *
 * Contributors: 
 *		Loetz GmbH&Co.KG, 69115 Heidelberg, Germany
 */


package net.osbee.app.restservicetest.model.actions;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator implements BundleActivator  {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}
	
	public void start(BundleContext bundleContext) throws Exception {
 		Activator.context = bundleContext;
	}

	public void stop(BundleContext context) throws Exception {
            Activator.context = null;
	}

}
