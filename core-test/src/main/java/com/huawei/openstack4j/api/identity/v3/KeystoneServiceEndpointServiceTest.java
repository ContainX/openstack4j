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
package com.huawei.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;

/**
 * Tests the Identity/Keystone API version 3 ServiceEndpointsService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneServiceEndpointServiceTest extends AbstractTest {

    private static final String JSON_SERVICES_GET_BYID = "/identity/v3/services_get_byId.json";
    private static final String JSON_SERVICES_UPDATE = "/identity/v3/services_update_response.json";
    private static final String SERVICE_CRUD_ID = "5439da9864004dd088fce14c1c626a4b";
    private static final String SERVICE_CRUD_DESCRIPTION_UPDATE = "An updated service used for tests";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    public void service_update_Test() throws Exception {

        respondWith(JSON_SERVICES_GET_BYID);

        com.huawei.openstack4j.model.identity.v3.Service service_setToUpdate = osv3().identity().serviceEndpoints()
                .get(SERVICE_CRUD_ID);

        respondWith(JSON_SERVICES_UPDATE);

        com.huawei.openstack4j.model.identity.v3.Service updatedService = osv3().identity().serviceEndpoints()
                .update(service_setToUpdate.toBuilder().description(SERVICE_CRUD_DESCRIPTION_UPDATE).build());

        assertEquals(updatedService.getId(), SERVICE_CRUD_ID);
        assertEquals(updatedService.getDescription(), SERVICE_CRUD_DESCRIPTION_UPDATE);
    }

}
