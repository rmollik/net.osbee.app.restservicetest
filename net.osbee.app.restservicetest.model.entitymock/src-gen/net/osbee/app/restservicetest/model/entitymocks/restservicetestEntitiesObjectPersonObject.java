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
public class restservicetestEntitiesObjectPersonObject extends ABaseMockObject {
  private static Logger log = LoggerFactory.getLogger("mock");
  
  private static ABaseMockResource sex_resource = new net.osbee.app.restservicetest.model.entitymocks.restservicetestEntitiesResourceGenderResources();
  
  private static Map firstName_items = new HashMap<String, Object[]>() {{
    put("MALE", new Object[] {"Andreas","Armin","Ernst","Hans","Hubert","Jens","Johan","Michael","Norbert","Oliver","Thorsten","Ulrich"});
    put("FEMALE", new Object[] {"Andrea","Evelin","Jutta","Maria"});
    }};
  
  private static Object[] lastName_items = {"Abbott","Collins","Hammond","Maynard","Schultz","Swanson","Watson","Zimmerman"};
  
  protected final void generateDataRow() {
    generateAttribute("sex", sex_resource.getDataRows().toArray());
    generateAttribute("firstName", "sex", firstName_items);
    generateAttribute("lastName", lastName_items);
    generateDateAttribute("birthDate", -50, -20);
    
  }
}
