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
package com.huawei.openstack4j.api.murano.v1;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.murano.v1.domain.AppCatalogSession;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author nmakhotkin
 */
@Test(suiteName="Murano/AppCatalog", enabled = true)
public class SessionTests extends AbstractTest {
    private static final String SESSION_JSON = "/murano/v1/session.json";
    private static final String envId = "e1c1b5a0b3284f188c5d91ab18bf0451";

    public void testGetSession() throws IOException {
        respondWith(SESSION_JSON);
        String id = "b8f4006064d24c10a33d9ed68e554f0f";
        AppCatalogSession ses = osv3().murano().sessions().get(envId, id);
        assertNotNull(ses);
        assertNotNull(ses.getId());
        assertEquals(ses.getId(), id);
    }

    public void testConfigureSession() throws IOException {
        respondWith(SESSION_JSON);
        AppCatalogSession session = osv3().murano().sessions().configure(envId);
        assertNotNull(session);
        assertEquals(envId, session.getEnvId());
    }

    public void testDeleteSession() throws IOException {
        respondWith(200);
        String id = "b8f4006064d24c10a33d9ed68e554f0f";
        ActionResponse delete = osv3().murano().sessions().delete(envId, id);
        assertTrue(delete.isSuccess());
    }

    public void testDeploySession() throws IOException {
        respondWith(200);
        String id = "b8f4006064d24c10a33d9ed68e554f0f";
        ActionResponse deploy = osv3().murano().sessions().deploy(envId, id);
        assertTrue(deploy.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.APP_CATALOG;
    }
}
