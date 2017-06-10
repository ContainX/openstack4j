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
package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.identity.v3.Credential;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 CredentialService
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneCredentialServiceTests extends AbstractTest {

    private static final String JSON_CREDENTIALS_UPDATE = "/identity/v3/credentials_update_response.json";
    private static final String CREDENTIAL_CRUD_ID = "3d3367228f9c7665266604462ec60029bcd83ad89614021a80b2eb879c572510";
    private static final String CREDENTIAL_CRUD_BLOB_UPDATE = "{\"access\":\"181920\",\"secret\":\"updatedSecretKey\"}";
    private static final String CREDENTIAL_CRUD_USER_ID = "aa9f25defa6d4cafb48466df83106065";
    private static final String CREDENTIAL_CRUD_PROJECT_ID = "123ac695d4db400a9001b91bb3b8aa46";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Credential Tests ------------

    // The following tests are to verify the update() method of the
    // PolicyService using HTTP PATCH, which is not supported by betamax.
    // Find more tests in KeystonePolicyServiceSpec in core-integration-test
    // module.

    public void credentials_update_Test() throws Exception {

        respondWith(JSON_CREDENTIALS_UPDATE);

        Credential updatedCredential = osv3().identity().credentials().update(Builders.credential()
                .id(CREDENTIAL_CRUD_ID)
                .blob(CREDENTIAL_CRUD_BLOB_UPDATE)
                .build());

        assertEquals(updatedCredential.getId(), CREDENTIAL_CRUD_ID);
        assertEquals(updatedCredential.getBlob(), CREDENTIAL_CRUD_BLOB_UPDATE);
        assertEquals(updatedCredential.getUserId(), CREDENTIAL_CRUD_USER_ID);
        assertEquals(updatedCredential.getProjectId(), CREDENTIAL_CRUD_PROJECT_ID);

    }

}
