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
package org.openstack4j.openstack.database.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.database.domain.DatabaseBackupPolicy;
import org.openstack4j.openstack.database.domain.DatabaseParam;
import org.openstack4j.openstack.database.domain.DatabaseParam.Parameters;
import org.openstack4j.openstack.database.domain.InstanceParamOperationResult;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

/**
 * The implementation of manipulation of database backup
 *
 * @author QianBiao.NG
 * @date   2017-08-03 11:06:34
 */
public class DatabaseBackupService extends BaseDatabaseServices {

	/**
	 * update the backup policy of an instance
	 * 
	 * @param instanceId	database instance id
	 * @return {@link ActionResponse} instance
	 */
	public ActionResponse updateBackupPolicy(String instanceId, DatabaseBackupPolicy policy) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		Preconditions.checkArgument(policy != null, "parameter `policy` should not be null");
		Preconditions.checkArgument(policy.getKeepDay() >= 0, "parameter `policy.keepDay` should be great than zero");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(policy.getStartTime()),
				"parameter `policy.startTime` should be great than zero");
		return putWithResponse(uri("/instances/%s/backups/policy", instanceId)).entity(policy).execute();
	}

	/**
	 * get the backup policy of a database instance
	 * 
	 * @param instanceId	database instance id
	 * @return {@link DatabaseBackupPolicy} instance
	 */
	public DatabaseBackupPolicy getBackupPolicy(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		return get(DatabaseBackupPolicy.class, uri("/instances/%s/backups/policy", instanceId)).execute();
	}

	/**
	 * list all configuration parameters of a specification version	 of datastore
	 * 
	 * @param dataStoreVersionId	datastore version identifier
	 * @return a list of {@link DatabaseParam} instances 
	 */
	public List<DatabaseParam> list(String dataStoreVersionId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(dataStoreVersionId),
				"parameter `dataStoreVersionId` should not be empty");
		return get(Parameters.class, uri("/datastores/versions/%s/parameters", dataStoreVersionId)).execute().getList();
	}

	/**
	 * get the details of a configuration parameter
	 * 
	 * @param dataStoreVersionId	datastore version identifier
	 * @param paramName				datastore configuration parameter name
	 * @return an instance of {@link DatabaseParam}
	 */
	public DatabaseParam get(String dataStoreVersionId, String paramName) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(dataStoreVersionId),
				"parameter `dataStoreVersionId` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(paramName), "parameter `paramName` should not be empty");
		return get(DatabaseParam.class, uri("/datastores/versions/%s/parameters/%s", dataStoreVersionId, paramName))
				.execute();
	}

	/**
	 * config database parameters for an instance
	 * 
	 * @param instanceId	database instance identifier
	 * @param params		database parameters map
	 * @return {@link InstanceParamOperationResult} instance
	 */
	public InstanceParamOperationResult config(String instanceId, Map<String, Object> params) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		Preconditions.checkArgument(params != null && !params.isEmpty(), "parameter `params` should not be empty");

		HashMap<String, Object> entity = Maps.newHashMap();
		entity.put("values", params);
		return put(InstanceParamOperationResult.class, uri("/instances/%s/parameters", instanceId)).entity(entity)
				.execute();
	}

	/**
	 * restore database parameters for an instance
	 * 
	 * @param instanceId	database instance identifier
	 * @return {@link InstanceParamOperationResult} instance
	 */
	public InstanceParamOperationResult restore(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");

		HashMap<String, Object> entity = Maps.newHashMap();
		return put(InstanceParamOperationResult.class, uri("/instances/%s/parameters/default", instanceId))
				.entity(entity).execute();
	}

}
