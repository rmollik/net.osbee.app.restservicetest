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
public class restservicetestBlobsEntityMockedBlobMapping extends ABaseMockEntity {
  private static Logger log = LoggerFactory.getLogger("mock");
  
  private static ABaseMockResource resourceBlobMapping_resource = new net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsResourceResourceBlobMapping();
  
  public restservicetestBlobsEntityMockedBlobMapping(final AEntityMockDataGenerator mockDataGenerator) {
    super(mockDataGenerator, "blob");
  }
  
  public final Set getDataRows() {
    return resourceBlobMapping_resource.getDataRows();
  }
  
  public final Object generateEntity(final Object dataRow) {
    reset();
    org.eclipse.osbp.blob.dtos.BlobMappingDto internal = new org.eclipse.osbp.blob.dtos.BlobMappingDto();
    entity = internal;
    generateData();
    try {
    internal.setId(asString(resourceBlobMapping_resource.getAttribute(dataRow.toString(), "id")));
    internal.setUniqueName(asString(resourceBlobMapping_resource.getAttribute(dataRow.toString(), "uniqueName")));
    internal.setFileName(asString(resourceBlobMapping_resource.getAttribute(dataRow.toString(), "fileName")));
    internal.setMimeTypeId(asInt(resourceBlobMapping_resource.getAttribute(dataRow.toString(), "mimeTypeId")));
    }
    catch (Exception e) {
        log.error(e.getLocalizedMessage()+e.getCause());
    }
    return entity;
  }
  
  protected final void generateDataRow() {
    
  }
}
