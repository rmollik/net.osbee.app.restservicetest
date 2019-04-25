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
import org.eclipse.osbp.user.User;
import org.eclipse.osbp.xtext.datamart.common.DatamartDtoMapper;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedAxis;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedMember;
import org.eclipse.osbp.xtext.datamart.common.olap.DerivedPosition;
import org.eclipse.osbp.xtext.datamart.common.sql.SqlCellSet;
import org.olap4j.Axis;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

@SuppressWarnings("serial")
public class DatamartsTestClass {
}
