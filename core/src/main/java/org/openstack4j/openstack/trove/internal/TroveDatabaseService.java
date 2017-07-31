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
package org.openstack4j.openstack.trove.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.trove.domain.TroveDatabase;
import org.openstack4j.openstack.trove.domain.TroveDatabase.Databases;

/**
 * The implementation of manipulation of {@link TroveDatabase}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:41:17
 */
public class TroveDatabaseService extends BaseTroveServices {

	/**
	 * Gets the database specified by ID
	 * @param instanceId
	 * @return the database or null if not found
	 */
	public List<TroveDatabase> list(String instanceId) {
		return get(Databases.class, uri("/instances/%s/databases", instanceId)).execute().getList();
	}

	/**
	 * Create a new database
	 * @param id
	 * @param databases
	 * @return the action response
	 */
	public ActionResponse create(String instanceId, Databases databases) {
		checkNotNull(instanceId);
		checkNotNull(databases);
		return post(ActionResponse.class, uri("/instances/%s/databases", instanceId)).entity(databases).execute();
	}

	/**
	 * Deletes the database
	 * @param instanceId
	 * @param name
	 * @return the action response
	 */
	public ActionResponse delete(String instanceId, String dbName) {
		checkNotNull(instanceId);
		checkNotNull(dbName);
		return deleteWithResponse(uri("/instances/%s/databases/%s", instanceId, dbName)).execute();
	}

}
