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
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.murano.v1.domain.Application;
import com.huawei.openstack4j.model.murano.v1.domain.Environment;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author nmakhotkin
 */
@Test(suiteName="Murano/AppCatalog", enabled = true)
public class EnvironmentTests extends AbstractTest {
    private static final String ENVIRONMENTS_JSON = "/murano/v1/environments.json";
    private static final String ENVIRONMENT_JSON = "/murano/v1/environment.json";
    private static final String ENVIRONMENT_RENAME_JSON = "/murano/v1/environment-rename.json";

    public void testListEnvironments() throws IOException {
        respondWith(ENVIRONMENTS_JSON);
        List<? extends Environment> list = osv3().murano().environments().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), "e1c1b5a0b3284f188c5d91ab18bf0451");
        assertEquals(list.get(0).getName(), "some_env");
    }

    public void testGetEnvironment() throws IOException {
        respondWith(ENVIRONMENT_JSON);
        String id = "721f76f9a9d64ebcacc76189cb8978a9";
        Environment env = osv3().murano().environments().get(id);
        assertNotNull(env);
        assertNotNull(env.getId());
        assertEquals(env.getId(), id);
    }

    public void testGetEnvironmentCheckServices() throws IOException {
        respondWith(ENVIRONMENT_JSON);
        String id = "721f76f9a9d64ebcacc76189cb8978a9";
        Environment env = osv3().murano().environments().get(id);
        assertNotNull(env);

        List<? extends Application> services = env.getServices();

        assertEquals(services.size(), 2);
        assertEquals(services.get(0).getService().getId(), "2614f193-f13e-42b1-af7e-01729bb5af75");
    }

    public void testCreateEnvironment() throws IOException {
        respondWith(ENVIRONMENT_JSON);
        String id = "721f76f9a9d64ebcacc76189cb8978a9";
        String name = "test";
        Environment env = Builders.environment().name(name).build();
        env = osv3().murano().environments().create(env);
        assertNotNull(env);
        assertEquals(env.getId(), id);
        assertEquals(env.getName(), name);
    }

    public void testDeleteEnvironment() throws IOException {
        respondWith(204);
        ActionResponse delete = osv3().murano().environments().delete("721f76f9a9d64ebcacc76189cb8978a9");
        assertTrue(delete.isSuccess());
    }

    public void testRenameEnvironment() throws IOException {
        respondWith(ENVIRONMENT_RENAME_JSON);
        String name = "renamed-test";
        String envId = "721f76f9a9d64ebcacc76189cb8978a9";
        Environment env = osv3().murano().environments().rename(envId, name);
        assertEquals(env.getName(), name);
        assertEquals(env.getId(), envId);
    }

    @Override
    protected Service service() {
        return Service.APP_CATALOG;
    }
}
