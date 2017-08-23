/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC                                       
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
package com.huawei.openstack4j.openstack.trove.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfig;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigCreate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigUpdate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfig.Configs;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance.DatabaseInstances;

/**
 * The implementation of manipulation of {@link DatabaseConfig}
 *
 * @author QianBiao.NG
 * @date   2017-08-03 11:06:34
 */
public class TroveDatabaseConfigService extends BaseTroveServices {

	/**
	 * create a new database configuration
	 * 
	 * @param creation 		model represent attributes of the configuration to be created
	 * @return {@link DatabaseConfig} instance been created
	 */
	public DatabaseConfig create(DatabaseConfigCreate creation) {
		checkNotNull(creation, "parameter `creation` should not be null");
		checkNotNull(creation.getDatastore(), "parameter `creation.datastore` should not be null");
		checkArgument(!creation.getValues().isEmpty() && creation.getValues().size() > 0,
				"parameter `creation.values` should not be null");

		checkArgument(!Strings.isNullOrEmpty(creation.getName()), "parameter `creation.name` should not be empty");
		checkArgument(!Strings.isNullOrEmpty(creation.getDatastore().getVersion()),
				"parameter `creation.datastore.version` should not be empty");
		checkArgument(creation.getDatastore().getType() != null,
				"parameter `creation.datastore.type` should not be empty");

		return post(DatabaseConfig.class, "/configurations").entity(creation).execute();
	}

	/**
	 * update an existing database configuration
	 * @param update 	model represent attributes of the configuration to be updated
	 * @return  {@link DatabaseConfig} instance been updated
	 */
	public ActionResponse update(DatabaseConfigUpdate update) {
		checkNotNull(update, "parameter `update` should not be null");
		checkArgument(!Strings.isNullOrEmpty(update.getId()), "parameter `update.id` should not be empty");
		return put(ActionResponse.class, "/configurations/", update.getId()).entity(update).execute();
	}

	/**
	 * add parameters to a database configuration identified by a specified ID.
	 * 
	 * @param configurationId	database configuration identifier
	 * @param params			parameters to add, key is parameter name, value is parameter value
	 * @return {@linkplain ActionResponse} instance
	 */
	public ActionResponse updateParams(String configurationId, Map<String, String> params) {
		checkArgument(!Strings.isNullOrEmpty(configurationId), "parameter `configurationId` should not be empty");
		checkArgument(params != null && !params.isEmpty(), "parameter `customerParams` should not be empty");
		DatabaseConfigUpdate build = DatabaseConfigUpdate.builder().values(params).build();
		return patch(ActionResponse.class, "/configurations/", configurationId).entity(build).execute();
	}

	/**
	 * list all configurations
	 * 
	 * @return a list of {@link DatabaseConfig} instances 
	 */
	public List<DatabaseConfig> list() {
		return get(Configs.class, "/configurations").execute().getList();
	}

	/**
	 * 
	 * get the details of a configuration
	 * 
	 * @param configurationId	database configuration identifier
	 * @return an instance of {@link DatabaseConfig}
	 */
	public DatabaseConfig get(String configurationId) {
		checkArgument(!Strings.isNullOrEmpty(configurationId), "parameter `configurationId` should not be empty");
		return get(DatabaseConfig.class, "/configurations/", configurationId).execute();
	}

	/**
	 * delete a database configuration
	 * 
	 * @param configurationId database configuration identifier
	 * @return {@link ActionResponse} instance
	 */
	public ActionResponse delete(String configurationId) {
		checkArgument(!Strings.isNullOrEmpty(configurationId), "parameter `configurationId` should not be empty");
		return deleteWithResponse("/configurations/", configurationId).execute();
	}

	/**
	 * 
	 * get the details of a configuration
	 * 
	 * @param configurationId	database configuration identifier
	 * @return an instance of {@link DatabaseInstance} with attribute id and name set
	 */
	public List<DatabaseInstance> listDatabaseInstances(String configurationId) {
		checkArgument(!Strings.isNullOrEmpty(configurationId), "parameter `configurationId` should not be empty");
		return get(DatabaseInstances.class, "/configurations/", configurationId, "/instances").execute().getList();
	}

}
