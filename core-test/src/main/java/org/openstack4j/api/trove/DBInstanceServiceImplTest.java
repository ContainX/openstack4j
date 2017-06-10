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
package org.openstack4j.api.trove;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.trove.Instance;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


@Test(suiteName="trove/instances")
public class DBInstanceServiceImplTest extends AbstractTest{

    private static final String TROVE_INSTANCES = "/trove/instances.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListInstances() throws Exception{
        respondWith(TROVE_INSTANCES);
        List<? extends Instance> instances = osv2().trove().instanceService().list();
        assertEquals(1, instances.size());
        Instance instance = instances.get(0);
        assertEquals(instance.getFlavor().getId(), "1");
        assertEquals(instance.getHostname(), "e09ad9a3f73309469cf1f43d11e79549caf9acf2.troveexampledb.com");
        assertEquals(instance.getId(), "44b277eb-39be-4921-be31-3d61b43651d7");
        assertEquals(instance.getName(), "json_rack_instance");
        assertEquals(instance.getDatastoreType(), "mysql");
        assertEquals(instance.getDatastoreVersion(), "5.5");
    }

}
