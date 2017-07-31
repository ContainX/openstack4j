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

/**
 * Trove (Relation Database) Operations API implementation
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:25:44
 */
public class TroveService extends BaseTroveServices {

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
	 * @return {@link TroveDatastoreService} instance
	 */
	public TroveDatastoreService datastores() {
		return Apis.get(TroveDatastoreService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of databases
	 *
	 * @return {@link TroveDatabaseService} instance
	 */
	public TroveDatabaseService databases() {
		return Apis.get(TroveDatabaseService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of database users
	 *
	 * @return {@link TroveDBUserService} instance
	 */
	public TroveDBUserService databaseUsers() {
		return Apis.get(TroveDBUserService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of database instance flavors
	 *
	 * @return {@link TroveDBUserService} instance
	 */
	public TroveInstanceFlavorService flavors() {
		return Apis.get(TroveInstanceFlavorService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of database instances
	 *
	 * @return {@link TroveDBInstanceService} instance
	 */
	public TroveDBInstanceService instances() {
		return Apis.get(TroveDBInstanceService.class);
	}

}
