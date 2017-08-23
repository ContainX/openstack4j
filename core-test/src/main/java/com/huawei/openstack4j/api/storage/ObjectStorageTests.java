/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.storage;

import static com.huawei.openstack4j.model.storage.object.SwiftHeaders.*;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.storage.object.SwiftContainer;
import com.huawei.openstack4j.model.storage.object.SwiftObject;

@Test(suiteName="Object Storage Tests")
public class ObjectStorageTests extends AbstractTest {

    private static final String JSON_CONTAINERS = "/storage/containers.json";
    private static final String NAME_BOOK = "Book";
    private static final String NAME_YEAR = "Year";
    
    @Override
    protected Service service() {
        return Service.OBJECT_STORAGE;
    }

    public void containerListingTest() throws Exception {
        respondWith(JSON_CONTAINERS);
        
        List<? extends SwiftContainer> containers = osv3().objectStorage().containers().list();
        assertEquals(2, containers.size());
        assertEquals(containers.get(0).getTotalSize(), 100);
        assertEquals(containers.get(0).getName(), "Test");
        assertEquals(containers.get(1).getName(), "marktwain");
    }
    
    public void containerMetadataTest() throws Exception {
        respondWith(generateContainerMetadataMap(), 204);
        
        Map<String, String> metadata = osv3().objectStorage().containers().getMetadata("Test");
        assertNotNull(metadata);
        assertEquals(metadata.get(NAME_YEAR), "2000");
        assertEquals(metadata.get(NAME_BOOK), "TestBook");
    }
    
    private Map<String, String> generateContainerMetadataMap() {
        Map<String, String> metadata = Maps.newHashMap();
        metadata.put(CONTAINER_METADATA_PREFIX+NAME_BOOK, "TestBook");
        metadata.put(CONTAINER_METADATA_PREFIX+NAME_YEAR, "2000");
        return metadata;
    }

    public void objectRetrievalTest() throws Exception {
        Map<String, String> headers = Maps.newHashMap();
        headers.put(CONTENT_LENGTH, "15");
        headers.put(CONTENT_TYPE, "application/json");
        headers.put(ETAG, "12345678901234567890");
        respondWith(headers, 200, "[\"hello world\"]");

        SwiftObject object = osv3().objectStorage().objects().get("test-container", "test-file");
        assertEquals(object.getContainerName(), "test-container");
        assertEquals(object.getName(), "test-file");
        assertEquals(object.getSizeInBytes(), 15);
        assertEquals(object.getMimeType(), "application/json");
        assertEquals(object.getETag(), "12345678901234567890");
    }
}
