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
public class restservicetestBlobsResourceResourceBlobMapping extends ABaseMockResource {
  public restservicetestBlobsResourceResourceBlobMapping() {
    setAttributes("id","fileName","mimeTypeId","uniqueName");
    addDataRow("B002","dacf6830-d36b-422d-9cc5-d981f27c49c1","Boss.jpg","1","Boss.jpg");
    addDataRow("B006","bf26332f-f7e7-4fc6-9889-8e524579eb8a","Employee10.jpg","1","Employee10.jpg");
    addDataRow("B007","e2f7acf2-d826-4e8c-9853-6c532871910c","Employee10.jpg","1","Employee10.jpg");
    addDataRow("B008","fb089725-1999-4a98-b074-9b3d101237a3","Employee11.jpg","1","Employee11.jpg");
    addDataRow("B009","f00e8c41-8fe5-4a4f-9c29-a36ace86eafa","Employee11.jpg","1","Employee11.jpg");
    addDataRow("B010","b37d71b5-dba4-4f3d-9f50-c7bf57527d86","Employee12.jpg","1","Employee12.jpg");
    addDataRow("B011","f1f38c51-a808-4f28-b556-03194a37e652","Employee13.jpg","1","Employee13.jpg");
    addDataRow("B012","c8ecc8e4-200b-4baa-9042-865d2554d043","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B013","681e3720-4897-48c7-bad2-309f40dec41f","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B014","c9f10f25-cc5b-4a47-b057-0494f747540e","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B015","970c6257-9441-4f3f-aeea-4d5c31f83acf","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B016","be444617-bd02-4fa4-8fed-620846945e28","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B017","28c58175-2fc2-473c-8cb1-fdcfa9450c5a","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B018","310b7ba2-8829-41d1-b964-00bc9437b821","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B019","058381b1-178c-4f6e-b67e-14cc2237c758","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B020","716e411e-fd7f-4620-b13f-a42b76f4ee55","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B021","839c1b0d-0e5e-4068-a06a-2f94b5347e78","Employee14.jpg","1","Employee14.jpg");
    addDataRow("B022","4d2e2844-7be3-4870-b09c-e95e466565e9","Employee15.jpg","1","Employee15.jpg");
    addDataRow("B023","668a9519-f68d-49a2-8955-6132a5869f25","Employee17.jpg","1","Employee17.jpg");
    addDataRow("B024","1a074db7-5e6c-4022-a054-17a959678229","Employee17.jpg","1","Employee17.jpg");
    addDataRow("B025","ce1f20d4-b962-46df-acb7-5558561800b2","Employee18.jpg","1","Employee18.jpg");
    addDataRow("B026","07d29d9d-45b6-454a-90ef-2b8ca3d91680","Employee18.jpg","1","Employee18.jpg");
    addDataRow("B027","448fbbd2-1bc8-43b0-9f22-069473d8732a","Employee18.jpg","1","Employee18.jpg");
    addDataRow("B028","b01c3e47-8226-43ce-88e2-6e93b3991f06","Employee2.jpg","1","Employee2.jpg");
    addDataRow("B029","10f02cce-da4c-4dd5-9e92-7e1a270e4f82","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B030","4e9d30ef-471f-45fa-af43-6d1b976f3a5e","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B031","db4a4f1f-41b3-47e4-b9f5-3902895d963c","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B032","c1c369ed-b17b-4d39-862e-bb3e963d83be","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B033","ecb815b4-fd53-481d-b30e-c104c7ea124c","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B034","5cb9a480-f223-44ff-b97d-f4b7892e5997","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B035","70b7b1d6-9828-4205-9ac5-c0548f03e739","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B036","e24a0980-9724-494f-afba-a1c0b7cb64b8","Employee20.jpg","1","Employee20.jpg");
    addDataRow("B037","1b818f60-c262-4a1c-8193-89cd711eba5f","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B038","8188ed35-d440-4731-907a-fac9bb0fbb51","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B039","e626403d-eb9a-4d74-8a5a-6146042b873d","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B040","275c9508-982d-4915-a118-636774b2f905","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B041","bbd38e4f-0cf4-4127-9414-ed175f03e016","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B042","03505615-a5d8-484e-a50a-3daa8aa97351","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B043","61c342b2-9c87-4c28-b893-52e1e2ba03e6","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B044","8ba871b4-bc07-4608-a0fb-7aa47620525b","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B045","e757b698-a114-4741-a03b-3c1c4fb9863e","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B046","0c2f260e-d95f-4f98-9361-043de551765b","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B047","092ef09d-8b8e-4339-83b2-d3ac45d3e694","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B048","fda54b78-95e5-4d49-9585-c4b8bfbbf3d1","Employee21.jpg","1","Employee21.jpg");
    addDataRow("B049","c6d42a81-a798-420b-90de-9fd0f80f5515","Employee23.jpg","1","Employee23.jpg");
    addDataRow("B050","d3316c68-324c-4b88-8e8c-a2d34b5758f8","Employee25.jpg","1","Employee25.jpg");
    addDataRow("B051","25a67895-1a7d-47e7-99c3-82d87f2533ec","Employee26.jpg","1","ElisabethMossPhoto");
    addDataRow("B052","96f5fdd0-6e94-4ac9-b8d1-8771196bee38","Employee26.jpg","1","Employee26.jpg");
    addDataRow("B053","cbfd9791-5398-4cbc-9e96-24e634bf71c0","Employee26.jpg","1","Employee26.jpg");
    addDataRow("B054","f5411150-fb59-4daa-8bc5-1992d45af6f5","Employee3.jpg","1","Employee3.jpg");
    addDataRow("B055","976a9cd2-1859-47e3-9f2c-83e0f9e2f0ae","Employee3.jpg","1","Employee3.jpg");
    addDataRow("B056","5ce14973-c5eb-45be-827e-94a88a26f9f8","Employee4.jpg","1","Employee4.jpg");
    addDataRow("B057","7b1411b0-4c02-438f-b53d-d81e8791366a","Employee5.jpg","1","Employee5.jpg");
    addDataRow("B058","287a5fda-33e3-4bf1-a505-137529cfc357","Employee6.jpg","1","Employee6.jpg");
    addDataRow("B059","c86b3f0b-c418-4b22-9a1f-e84335257365","Employee6.jpg","1","Employee6.jpg");
    addDataRow("B060","0dc1064b-c09d-400c-9159-1909798cd9f8","Employee6.jpg","1","Employee6.jpg");
    addDataRow("B061","3d068f5c-5f83-42f3-a597-94158939630c","Employee7.jpg","1","Employee7.jpg");
    addDataRow("B062","0051e81f-c2c9-432a-b0d9-0cf065dfee49","Employee7.jpg","1","Employee7.jpg");
    addDataRow("B063","8a5f20be-77bc-4e57-a5ea-5bdf37a948c4","Employee8.jpg","1","Employee8.jpg");
    addDataRow("B064","180949f4-5a54-4a72-b0e7-77665afd4baf","Employee9.jpg","1","Employee9.jpg");
    addDataRow("B065","803c55a5-9bbb-402c-9d15-a2fde19f369d","Employee9.jpg","1","Employee9.jpg");
    addDataRow("B066","7bb631b1-86eb-4acd-88af-24052e28a672","Employee9.jpg","1","Employee9.jpg");
    addDataRow("B067","502a0692-5bd0-4bc0-aec9-161ba02cd6f6","Employee9.jpg","1","Employee9.jpg");
    addDataRow("B068","9dede9e9-97f7-4c62-871d-c01fa6a60deb","Employee9.jpg","1","Employee9.jpg");
    addDataRow("B069","125f6872-61ae-492e-9722-fb395c5f7810","Employee9.jpg","1","Employee9.jpg");
    addDataRow("F001","18b56d9d-0052-4b9f-ac9f-385846c9c18d","Employee16.jpg","1","Employee16.jpg");
    addDataRow("F002","a8ba9355-ecbf-4e16-8058-4fa1c1d1f591","Employee11.jpg","1","Employee11.jpg");
    addDataRow("F003","3a5003d8-c75c-441a-a630-9094631cbdff","Employee10.jpg","1","Employee10.jpg");
    addDataRow("F004","beea7482-3fa6-4c9e-a06a-277ca9a9cdc0","Employee14.jpg","1","Employee14.jpg");
    addDataRow("F005","c49ef549-6cb6-4b67-9d66-d89ad4df7e42","Boss.jpg","1","Boss.jpg");
    addDataRow("F006","dec4340a-e3f8-4414-90c9-3474ae150ed1","Employee23.jpg","1","Employee23.jpg");
    addDataRow("F007","c390a031-9b80-47c3-bc01-a66412f8813f","Employee9.jpg","1","Employee9.jpg");
    addDataRow("F008","bb90a031-9b80-47c3-bc01-a66412f8813f","Employee21.jpg","1","Employee21.jpg");
    
  }
}