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
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.identity.v3.Domain;

/**
 * Tests the Identity/Keystone API version 3 DomainService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneDomainServiceTests extends AbstractTest {

    private static final String JSON_DOMAINS_CREATE = "/identity/v3/domains_create_response.json";
    private static final String JSON_DOMAINS_UPDATE = "/identity/v3/domains_update_response.json";
    private static final String DOMAIN_NAME = "Domain_CRUD";
    private static String DOMAIN_DESCRIPTION = "Domain used for CRUD tests";
    private static String DOMAIN_DESCRIPTION_UPDATED = "An updated domain used for CRUD tests";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Domain Tests ------------

    // Tests here just address the missing update() spec feature.
    // Find more tests in the respective KeystoneXService.spec in
    // core-integration-test module

    public void crud_domain_test() throws Exception {

        // create a new domain

        Domain domain = Builders.domain()
                .name(DOMAIN_NAME)
                .description(DOMAIN_DESCRIPTION)
                .enabled(true)
                .build();

        respondWith(JSON_DOMAINS_CREATE);

        Domain newDomain = osv3().identity().domains().create(domain);

        assertEquals(newDomain.getName(), DOMAIN_NAME);
        assertEquals(newDomain.getDescription(), DOMAIN_DESCRIPTION);

        String DOMAIN_ID = newDomain.getId();

        // update an existing domain

        respondWith(JSON_DOMAINS_UPDATE);

        Domain updatedDomain = osv3().identity().domains().update(Builders.domain()
                .id(DOMAIN_ID)
                .description(DOMAIN_DESCRIPTION_UPDATED)
                .enabled(true)
                .build());

        assertEquals(updatedDomain.getId(), DOMAIN_ID);
        assertEquals(updatedDomain.getName(), DOMAIN_NAME);
        assertEquals(updatedDomain.getDescription(), DOMAIN_DESCRIPTION_UPDATED);

    }

}
