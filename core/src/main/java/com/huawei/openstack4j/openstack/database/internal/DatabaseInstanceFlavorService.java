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
package com.huawei.openstack4j.openstack.database.internal;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor.Flavors;

/**
 * The implementation of manipulation of {@link InstanceFlavor}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:13:41
 */
public class DatabaseInstanceFlavorService extends BaseDatabaseServices {

	/**
	 * list all the available database instance flavors of a particular database in region
	 * 
	 * @param databaseId	a particular database id (Database is a specify version of datastore)
	 * @param region		the cloud region 
	 * @return
	 */
	public List<InstanceFlavor> list(String databaseId, String region) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(databaseId), "parameter `databaseId` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(region), "parameter `region` should not be empty");
		return get(Flavors.class, uri("/flavors")).param("dbId", databaseId).param("region", region).execute()
				.getList();
	}

	/**
	 * Get the instance flavor specified by ID
	 * @param flavorId 	the flavor identifier
	 * @return the flavor or null if not found
	 */
	public InstanceFlavor get(String flavorId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(flavorId), "parameter `flavorId` should not be empty");
		return get(InstanceFlavor.class, uri("/flavors/%s", flavorId)).execute();
	}

}
