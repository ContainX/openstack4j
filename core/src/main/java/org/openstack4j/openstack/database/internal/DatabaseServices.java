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
package org.openstack4j.openstack.database.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.openstack.trove.domain.DatabaseParam;

/**
 * Relation Database Operations API implementation
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:25:44
 */
public class DatabaseServices extends BaseDatabaseServices {

	/**
	 * Service implementation which provides methods for manipulation of version
	 *
	 * @return {@link DatabaseServiceVersionService} instance
	 */
	public DatabaseServiceVersionService versions() {
		return Apis.get(DatabaseServiceVersionService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of datastores
	 *
	 * @return {@link DatastoreService} instance
	 */
	public DatastoreService datastores() {
		return Apis.get(DatastoreService.class);
	}


	/**
	 * Service implementation which provides methods for manipulation of database instance flavors
	 *
	 * @return {@link TroveDBUserService} instance
	 */
	public DatabaseInstanceFlavorService flavors() {
		return Apis.get(DatabaseInstanceFlavorService.class);
	}

	/**
	 * Service implementation which provides methods for manipulation of database instances
	 *
	 * @return {@link DatabaseInstanceService} instance
	 */
	public DatabaseInstanceService instances() {
		return Apis.get(DatabaseInstanceService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of {@link DatabaseParam}
	 *
	 * @return {@link DatabaseParamService} instance
	 */
	public DatabaseParamService params() {
		return Apis.get(DatabaseParamService.class);
	}

}
