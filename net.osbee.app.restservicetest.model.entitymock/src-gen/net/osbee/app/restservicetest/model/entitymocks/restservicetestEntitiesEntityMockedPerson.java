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
public class restservicetestEntitiesEntityMockedPerson extends ABaseMockEntity {
  private static Logger log = LoggerFactory.getLogger("mock");
  
  public restservicetestEntitiesEntityMockedPerson(final AEntityMockDataGenerator mockDataGenerator) {
    super(mockDataGenerator, "businessdata");
  }
  
  private static ABaseMockObject personData_template = new net.osbee.app.restservicetest.model.entitymocks.restservicetestEntitiesObjectPersonObject();
  
  protected final void generateDataRow() {
    generateAttribute("personData", personData_template);
    generateAttribute("firstName", "personData.firstName");
    generateAttribute("lastName", "personData.lastName");
    generateAttribute("birthdate", "personData.birthDate");
    
  }
  
  public final Object generateEntity(final Object dataRow) {
    reset();
    net.osbee.app.restservicetest.model.dtos.PersonDto internal = new net.osbee.app.restservicetest.model.dtos.PersonDto();
    entity = internal;
    generateData();
    mockDataGenerator.addDtoMockData(internal, getMockData());
    try {
    	internal.setFirstName(asString(getMockData().get("firstName")));
    	internal.setLastName(asString(getMockData().get("lastName")));
    	internal.setBirthdate(asDate(getMockData().get("birthdate")));
    }
    catch (Exception e) {
        log.error(e.getLocalizedMessage()+e.getCause());
    }
    return entity;
  }
}
