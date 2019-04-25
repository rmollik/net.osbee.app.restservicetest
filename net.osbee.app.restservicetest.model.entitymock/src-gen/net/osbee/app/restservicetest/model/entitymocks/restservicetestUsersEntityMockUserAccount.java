package net.osbee.app.restservicetest.model.entitymocks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.eclipse.osbp.blob.service.BlobService;
import org.eclipse.osbp.blob.service.BlobTypingAPI;
import org.eclipse.osbp.preferences.ProductConfiguration;
import org.eclipse.osbp.ui.api.customfields.IBlobService;
import org.eclipse.osbp.xtext.entitymock.common.ABaseMockEntity;
import org.eclipse.osbp.xtext.entitymock.common.ABaseMockObject;
import org.eclipse.osbp.xtext.entitymock.common.ABaseMockResource;
import org.eclipse.osbp.xtext.entitymock.common.AEntityMockDataGenerator;
import org.eclipse.osbp.xtext.entitymock.common.IEntityMockDataDbFiller;
import org.eclipse.osbp.xtext.entitymock.common.Iterators.DateIterator;
import org.eclipse.osbp.xtext.entitymock.common.Iterators.IntegerIterator;
import org.eclipse.osbp.xtext.entitymock.common.MockedEntityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class restservicetestUsersEntityMockUserAccount extends ABaseMockEntity {
  private static Logger log = LoggerFactory.getLogger("mock");
  
  private static ABaseMockResource resourceUserAccounts_resource = new net.osbee.app.restservicetest.model.entitymocks.restservicetestUsersResourceResourceUserAccounts();
  
  public restservicetestUsersEntityMockUserAccount(final AEntityMockDataGenerator mockDataGenerator) {
    super(mockDataGenerator, "authentication");
  }
  
  public final Set getDataRows() {
    return resourceUserAccounts_resource.getDataRows();
  }
  
  public final Object generateEntity(final Object dataRow) {
    reset();
    org.eclipse.osbp.authentication.account.dtos.UserAccountDto internal = new org.eclipse.osbp.authentication.account.dtos.UserAccountDto();
    entity = internal;
    generateData();
    try {
    internal.setUserName(asString(resourceUserAccounts_resource.getAttribute(dataRow.toString(), "userName")));
    internal.setPassword(asString(resourceUserAccounts_resource.getAttribute(dataRow.toString(), "password")));
    internal.setEmail(asString(resourceUserAccounts_resource.getAttribute(dataRow.toString(), "email")));
    internal.setPosition(asString(resourceUserAccounts_resource.getAttribute(dataRow.toString(), "position")));
    internal.setEnabled(asBoolean(resourceUserAccounts_resource.getAttribute(dataRow.toString(), "enabled")));
    internal.setLocaleTag(asString(resourceUserAccounts_resource.getAttribute(dataRow.toString(), "localeTag")));
    internal.setSuperuser(asBoolean(resourceUserAccounts_resource.getAttribute(dataRow.toString(), "isSuperuser")));
    }
    catch (Exception e) {
        log.error(e.getLocalizedMessage()+e.getCause());
    }
    return entity;
  }
  
  protected final void generateDataRow() {
    
  }
}
