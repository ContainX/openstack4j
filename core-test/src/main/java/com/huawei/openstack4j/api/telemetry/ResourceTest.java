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
package com.huawei.openstack4j.api.telemetry;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.telemetry.Resource;

@Test(suiteName = "Resource Tests")
public class ResourceTest extends AbstractTest {
    private static final String JSON_RESOURCES = "/telemetry/resources.json";
    private static final String JSON_RESOURCE = "/telemetry/resource.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listResourcesTest() throws IOException {
        respondWith(JSON_RESOURCES);

        List<? extends Resource> resourcess = osv2().telemetry().resources().list();
        assertEquals(resourcess.size(), 2);

        Resource resource = resourcess.get(0);
        assertEquals(resource.getId(), "02748368-2b4a-4b70-ac13-b6c5fd8ed415");
        assertNotNull(resource.getMeataData());
    }

    @Test
    public void getSampleTest() throws IOException {
        respondWith(JSON_RESOURCE);
        Resource resource = osv2().telemetry().resources().get("1e93a890-3732-11e6-a491-005056ac9b87");
        assertEquals(resource.getId(), "02748368-2b4a-4b70-ac13-b6c5fd8ed415");
        assertNotNull(resource.getMeataData());
    }
}
