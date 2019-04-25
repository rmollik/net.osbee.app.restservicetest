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
import java.util.Map;
import java.util.Properties;
import org.eclipse.osbp.blob.service.BlobService;
import org.eclipse.osbp.blob.service.BlobTypingAPI;
import org.eclipse.osbp.core.api.persistence.IPersistenceService;
import org.eclipse.osbp.datainterchange.api.IDataInterchange;
import org.eclipse.osbp.preferences.ProductConfiguration;
import org.eclipse.osbp.runtime.common.event.IEventDispatcher;
import org.eclipse.osbp.ui.api.customfields.IBlobService;
import org.eclipse.osbp.xtext.entitymock.common.ABaseMockEntity;
import org.eclipse.osbp.xtext.entitymock.common.ABaseMockObject;
import org.eclipse.osbp.xtext.entitymock.common.ABaseMockResource;
import org.eclipse.osbp.xtext.entitymock.common.AEntityMockDataGenerator;
import org.eclipse.osbp.xtext.entitymock.common.IEntityMockDataDbFiller;
import org.eclipse.osbp.xtext.entitymock.common.IEntityMockDataGenerator;
import org.eclipse.osbp.xtext.entitymock.common.Iterators.DateIterator;
import org.eclipse.osbp.xtext.entitymock.common.Iterators.IntegerIterator;
import org.eclipse.osbp.xtext.entitymock.common.MockedEntityDto;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = IEntityMockDataGenerator.class, immediate = true)
@SuppressWarnings("all")
public final class restservicetestBlobsMock extends AEntityMockDataGenerator {
  private final static Logger log = LoggerFactory.getLogger("mock");
  
  @Override
  public int getRunWithPriority() {
    return 2;
    
  }
  
  @Override
  public int getDataInterchangeSteps() {
    return 0;
    
  }
  
  @Override
  public int getEntityMockSteps() {
    return 1;
    
  }
  
  public void runDataInterchanges(final IPersistenceService persistenceService, final IDataInterchange dataInterchange, final IEventDispatcher eventDispatcher, final IBlobService blobService, final IEntityMockDataDbFiller entityMockDbFiller) {
    
  }
  
  public Map getMockData(final IEntityMockDataDbFiller entityMockDbFiller, final IPersistenceService persistenceService, final IBlobService blobService) {
    initialize();
    entityMockDbFiller.notifyInitializationStep("generate net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMapping", 1, 0.3);
    net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMapping entitymockedBlobMapping = new net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMapping(this);
    Iterator iteratormockedBlobMapping = entitymockedBlobMapping.getDataRows().iterator();
    List listmockedBlobMapping = new ArrayList<org.eclipse.osbp.blob.dtos.BlobMappingDto>();
    entityMockDbFiller.notifyInitializationStep("generate net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMapping", 0, 0.6);
    while (iteratormockedBlobMapping.hasNext()) {
        listmockedBlobMapping.add(entitymockedBlobMapping.generateEntity(iteratormockedBlobMapping.next()));
    }
    addMockObjects("mockedBlobMapping", org.eclipse.osbp.blob.dtos.BlobMappingDto.class, "blob", listmockedBlobMapping);
    entityMockDbFiller.notifyInitializationStep("generate net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMapping", 0, 0.9);
    entityMockDbFiller.notifyInitializationStep("generate net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMappingBlobs", 0, 0.93);
    List listmockedBlobMappingBlobs = new ArrayList<org.eclipse.osbp.blob.dtos.BlobDto>();
    entityMockDbFiller.notifyInitializationStep("generate net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMappingBlobs", 0, 0.96);
    for (Object listmockedBlobMappingObject : listmockedBlobMapping) {
        if  (listmockedBlobMappingObject instanceof org.eclipse.osbp.blob.dtos.BlobMappingDto) {
            org.eclipse.osbp.blob.dtos.BlobMappingDto blobMappingDto = (org.eclipse.osbp.blob.dtos.BlobMappingDto)listmockedBlobMappingObject;
            try {
                InputStream inputStream = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("/resources/entitymock/"+blobMappingDto.getFileName()));
                if  (inputStream != null) {
                    List<Object> blobs = blobService.createBlobMappingBlobs(inputStream, blobMappingDto.getMimeTypeId());
                    for(Object obj:blobs) {
    	                blobMappingDto.addToBlobsRef((org.eclipse.osbp.blob.dtos.BlobDto)obj);
                        listmockedBlobMappingBlobs.add((org.eclipse.osbp.blob.dtos.BlobDto)obj);
                    }
                }
            }
            catch (Exception e) {
                log.error(e.getLocalizedMessage()+e.getCause());
            }
        }
    }
    addMockObjects("mockedBlobMappingBlobs", org.eclipse.osbp.blob.dtos.BlobDto.class, "blob", listmockedBlobMappingBlobs);
    entityMockDbFiller.notifyInitializationStep("generate net.osbee.app.restservicetest.model.entitymocks.restservicetestBlobsEntityMockedBlobMappingBlobs", 0, 0.99);
    return mockDataClassMap;
  }
}
