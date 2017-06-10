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

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.trove.Database;
import org.openstack4j.openstack.trove.builder.TroveBuilders;
import org.openstack4j.openstack.trove.domain.TroveDatabase;
import org.openstack4j.openstack.trove.domain.TroveDatabase.Databases;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by sumit gandhi on 8/22/2016.
 */

@Test(suiteName="trove/databases")
public class DBDatabaseServiceImplTest extends AbstractTest{

    private static final String TROVE_DATABASES = "/trove/databases.json";

    @Override
    protected Service service() {
        return Service.DATABASE;
    }

    @Test
    public void testListDatabases() throws Exception{
        String databaseInstanceId = "54c91755526e44b9808385a263db4aa6";
        respondWith(TROVE_DATABASES);
        List<? extends Database> databases = osv2().trove().databaseService().list(databaseInstanceId);
        assertEquals(5, databases.size());
        Preconditions.checkNotNull(databases.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Database from List : "+ databases.get(0));
    }

    @Test
    public void testCreateDatabase() throws Exception{
        String databaseInstanceId = "54c91755526e44b9808385a263db4aa6";
        respondWith(200);
        TroveBuilders troveBuilders = new TroveBuilders();
        TroveDatabase database = (TroveDatabase) troveBuilders.databaseCreate().name("exampledb").build();
        Databases troveDatabases = new Databases();
        List<TroveDatabase> troveDatabaseList = new ArrayList<>();
        troveDatabaseList.add(database);
        troveDatabases.setTroveDatabaseList(troveDatabaseList);
        ActionResponse result = osv2().trove().databaseService().create(databaseInstanceId, troveDatabases);
        assertTrue(result.isSuccess());
    }

    @Test
    public void testDeleteDatabase() {
        String databaseInstanceId = "54c91755526e44b9808385a263db4aa6";
        respondWith(200);
        String dbName = "exampledb";
        ActionResponse result = osv2().trove().databaseService().delete(databaseInstanceId, dbName);
        assertTrue(result.isSuccess());
    }

}
