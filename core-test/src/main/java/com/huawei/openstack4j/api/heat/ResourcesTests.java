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
package com.huawei.openstack4j.api.heat;

import org.testng.annotations.*;

import com.huawei.openstack4j.api.*;
import com.huawei.openstack4j.model.common.*;
import com.huawei.openstack4j.model.heat.*;

import java.io.*;
import java.util.*;

import static org.testng.Assert.*;

@Test(suiteName="heat/resources", enabled = true)
public class ResourcesTests extends AbstractTest {

    private static final String METADATA="/heat/metadata.json";

    @SuppressWarnings("unchecked")
    public void testGetResourceMetadata() throws IOException {
        respondWith(METADATA);
        Map<String, Object> metadata = osv3().heat().resources().getMetadata("name", "id", "resource");
        assertEquals(metadata.size(), 1);
        Map<String, Object> inner = (Map)metadata.get("metadata");
        assertEquals(inner.size(), 2);
        assertEquals(inner.get("scaling_in_progress"), false);
        Map<String, String> capacity = (Map)inner.get("cooldown");
        assertEquals(capacity.get("2016-10-05T21:13:29.736841"), "ExactCapacity : 4");
    }


    public void testSignal() throws IOException {
        respondWith(200);
        ActionResponse result = osv3().heat().resources().signal("name", "id", "resource");
        assertTrue(result.isSuccess());
    }

    public void testMarkUnhealthy() throws IOException {
        respondWith(200);
        ResourceHealth health = Builders.resourceHealth().
                markUnhealthy(true)
                .resourceStatusReason("it is inevitable, Mr. Anderson")
                .build();
        ActionResponse result = osv3().heat().resources().markUnhealthy("name", "id", "resource", health);
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.ORCHESTRATION;
    }
}
