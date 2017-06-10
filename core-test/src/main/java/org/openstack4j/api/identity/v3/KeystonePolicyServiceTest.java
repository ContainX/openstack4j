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
import org.openstack4j.model.identity.v3.Policy;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 PolicyService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystonePolicyServiceTest extends AbstractTest {

    private static final String JSON_POLICIES_GET_BYID = "/identity/v3/policies_get_byId.json";
    private static final String JSON_POLICIES_UPDATE = "/identity/v3/policies_update_response.json";

    private static final String POLICY_ID = "13c92821e4c4476a878d3aae7444f52f";
    private static final String POLICY_BLOB_UPDATE = "{'admin' : 'role:non-admin-user'}";
    private static final String POLICY_TYPE = "application/json";
    private static final String POLICY_PROJECT_ID = "123ac695d4db400a9001b91bb3b8aa46";
    private static final String POLICY_USER_ID = "aa9f25defa6d4cafb48466df83106065";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Policy Tests ------------

    // The following tests are to verify the update() method of the
    // PolicyService using HTTP PATCH, which is not supported by betamax.
    // Find more tests in KeystonePolicyServiceSpec in core-integration-test
    // module.

    public void policy_update_Test() throws Exception {

        respondWith(JSON_POLICIES_GET_BYID);

        Policy policy_setToUpdate = osv3().identity().policies().get(POLICY_ID);

        respondWith(JSON_POLICIES_UPDATE);

        Policy updatedPolicy = osv3().identity().policies()
                .update(policy_setToUpdate.toBuilder().blob(POLICY_BLOB_UPDATE).build());

        assertEquals(updatedPolicy.getId(), POLICY_ID);
        assertEquals(updatedPolicy.getBlob(), POLICY_BLOB_UPDATE);
        assertEquals(updatedPolicy.getProjectId(), POLICY_PROJECT_ID);
        assertEquals(updatedPolicy.getUserId(), POLICY_USER_ID);
        assertEquals(updatedPolicy.getType(), POLICY_TYPE);

    }

}
