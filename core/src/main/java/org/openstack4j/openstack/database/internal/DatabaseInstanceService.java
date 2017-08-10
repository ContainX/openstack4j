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

import java.util.HashMap;
import java.util.List;

import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.database.domain.DatabaseInstance;
import org.openstack4j.openstack.database.domain.DatabaseInstance.DatabaseInstances;
import org.openstack4j.openstack.database.domain.DatabaseInstanceCreate;
import org.openstack4j.openstack.database.domain.Volume;
import org.openstack4j.openstack.trove.domain.ExtendParam;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The implementation of manipulation of {@link DatabaseInstance}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:13:41
 */
public class DatabaseInstanceService extends BaseDatabaseServices {

	/**
	 * list all database instances
	 * 
	 * @return a list of {@link DatabaseInstance} instances
	 */
	public List<DatabaseInstance> list() {
		return get(DatabaseInstances.class, uri("/instances")).execute().getList();
	}

	/**
	 * get the detail of database instance
	 * 
	 * @param instanceId	database instance identifier
	 * @return {@link DatabaseInstance} instance
	 */
	public DatabaseInstance get(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		DatabaseInstance instance = get(DatabaseInstance.class, uri("/instances/%s", instanceId)).execute();
		return instance;
	}

	/**
	 * create a new database instance (new instance or read only(replica) instance)
	 * 
	 * @param creation	a model represent the attributes of database instance creation
	 * @return {@link DatabaseInstance} instance
	 */
	public DatabaseInstance create(DatabaseInstanceCreate creation) {
		return post(DatabaseInstance.class, uri("/instances")).entity(creation).execute();
	}

	/**
	 * delete a database instance
	 * @param instanceId	database instance identifier
	 * @return				asynchronous job id of the database deletion job
	 */
	public String delete(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		HashMap<Object, Object> entity = Maps.newHashMap();
		entity.put("keepLastManualBackup", "0");
		Job execute = delete(Job.class, uri("/instances/%s", instanceId)).entity(entity).execute();
		return execute.getId();
	}

	/**
	 * restart database instance asynchronously
	 * 
	 * @param instanceId 	database instance identifier
	 * @return	asynchronous job identifier list 
	 */
	public List<String> restart(String instanceId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		HashMap<Object, Object> entity = Maps.newHashMap();
		entity.put("restart", Maps.newHashMap());

		List<String> jobIds = Lists.newArrayList();
		ExtendParam execute = post(ExtendParam.class, uri("/instances/%s/action", instanceId)).entity(entity).execute();
		for (IdResourceEntity job : execute.getJobs()) {
			jobIds.add(job.getId());
		}
		return jobIds;
	}

	/**
	 * Resize the flavor of database instance asynchronously
	 * 
	 * @param instanceId 	database instance identifier
	 * @param flavorRef 	the instance flavor identifier
	 * @return	asynchronous job identifier list 
	 */
	public List<String> resize(String instanceId, String flavorRef) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(flavorRef), "parameter `flavorRef` should not be empty");

		ResizeFlavorRequest entity = new ResizeFlavorRequest();
		entity.setFlavorRef(flavorRef);
		ResizeInstanceResponse execute = post(ResizeInstanceResponse.class, uri("/instances/%s/action", instanceId))
				.entity(entity).execute();
		return execute.getJobIds();
	}

	/**
	 * Resize the flavor of database instance asynchronously
	 * 
	 * @param instanceId 	database instance identifier
	 * @param volumeSize 	the size of the volume
	 * @return	asynchronous job identifier list 
	 */
	public List<String> resize(String instanceId, int volumeSize) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(instanceId), "parameter `instanceId` should not be empty");
		Preconditions.checkArgument(volumeSize >= 110 && volumeSize <= 2000,
				"parameter `volumeSize` shoule between 110 and 2000");

		ResizeVolumeRequest entity = new ResizeVolumeRequest();
		entity.setVolume(Volume.builder().size(volumeSize).build());

		List<String> jobIds = Lists.newArrayList();
		ExtendParam execute = post(ExtendParam.class, uri("/instances/%s/action", instanceId)).entity(entity).execute();
		for (IdResourceEntity job : execute.getJobs()) {
			jobIds.add(job.getId());
		}
		return jobIds;
	}

	static class Job {

		@JsonProperty("jobId")
		String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

	@JsonRootName("resize")
	class ResizeVolumeRequest {

		@JsonProperty("volume")
		Volume volume;

		public Volume getVolume() {
			return volume;
		}

		public void setVolume(Volume volume) {
			this.volume = volume;
		}

	}
	
	@JsonRootName("resize")
	class ResizeFlavorRequest {

		@JsonProperty("flavorRef")
		String flavorRef;

		public String getFlavorRef() {
			return flavorRef;
		}

		public void setFlavorRef(String flavorRef) {
			this.flavorRef = flavorRef;
		}
	}

	static class ResizeInstanceResponse {
		@JsonProperty("jobId")
		List<String> jobIds;

		public List<String> getJobIds() {
			return jobIds;
		}

		public void setJobIds(List<String> jobIds) {
			this.jobIds = jobIds;
		}
	}
	
	
}
