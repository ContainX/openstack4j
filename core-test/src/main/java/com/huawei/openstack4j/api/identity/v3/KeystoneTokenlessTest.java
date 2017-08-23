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

import okhttp3.mockwebserver.RecordedRequest;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.OSClient;
import com.huawei.openstack4j.core.transport.ClientConstants;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.openstack.OSFactory;

import static org.testng.Assert.assertEquals;

/**
 * Tests the Identity/Keystone API version 3 Keystone tokenless
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneTokenlessTest extends AbstractTest {

    private static final String JSON_USERS = "/identity/v3/users.json";
    private static final String DOMAIN_ID = "default";

    /**
     * check headers whether right from request
     *
     * @throws Exception
     */
    public void pass_headers_Test() throws Exception {

        respondWith(JSON_USERS);

        OSClient.OSClientV3 osClient = OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .authenticate();
        osClient.identity().users().list();

        RecordedRequest request = server.takeRequest();
        assertEquals(request.getHeader(ClientConstants.HEADER_X_DOMAIN_ID), DOMAIN_ID);
    }

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }
}
