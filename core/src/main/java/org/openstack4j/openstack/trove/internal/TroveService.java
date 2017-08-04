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
import org.openstack4j.openstack.trove.domain.DatabaseConfig;
import org.openstack4j.openstack.trove.domain.DatabaseParam;

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
	 * @return {@link TroveVersionService} instance
	 */
	public TroveVersionService versions() {
		return Apis.get(TroveVersionService.class);
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
	 * @return {@link TroveDatabaseInstanceService} instance
	 */
	public TroveDatabaseInstanceService instances() {
		return Apis.get(TroveDatabaseInstanceService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of {@link DatabaseConfig}
	 *
	 * @return {@link TroveDatabaseConfigService} instance
	 */
	public TroveDatabaseConfigService configs() {
		return Apis.get(TroveDatabaseConfigService.class);
	}
	
	/**
	 * Service implementation which provides methods for manipulation of {@link DatabaseParam}
	 *
	 * @return {@link TroveDatabaseParamService} instance
	 */
	public TroveDatabaseParamService params() {
		return Apis.get(TroveDatabaseParamService.class);
	}

}
