/*******************************************************************************
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

import org.openstack4j.api.Apis;
import org.openstack4j.api.trove.DatabaseService;
import org.openstack4j.api.trove.DatastoreService;
import org.openstack4j.api.trove.InstanceFlavorService;
import org.openstack4j.api.trove.UserService;

/**
 * Trove (Relation Database) Operations API implementation
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:25:44
 */
public class TroveServiceImpl extends BaseTroveServices {

	/**
	 * Service implementation which provides methods for manipulation of version
	 *
	 * @return {@link VersionServiceImpl} instance
	 */
	public VersionServiceImpl versions() {
		return Apis.get(VersionServiceImpl.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of datastores
	 *
	 * @return DatastoreService
	 */
	public DatastoreService datastores() {
		return Apis.get(DatastoreService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of databases
	 *
	 * @return DatabaseService
	 */
	public DatabaseService databases() {
		return Apis.get(DatabaseService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of database users
	 *
	 * @return UserService
	 */
	public UserService databaseUsers() {
		return Apis.get(UserService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of database instance flavors
	 *
	 * @return DBInstanceFlavorService
	 */
	public InstanceFlavorService flavors() {
		return Apis.get(InstanceFlavorService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of database instances
	 *
	 * @return InstanceService
	 */
	public DBInstanceServiceImpl instances() {
		return Apis.get(DBInstanceServiceImpl.class);
	}

}
