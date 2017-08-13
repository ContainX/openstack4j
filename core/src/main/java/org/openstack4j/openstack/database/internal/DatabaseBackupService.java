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

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.database.domain.DatabaseBackup;
import org.openstack4j.openstack.database.domain.DatabaseBackup.Backups;
import org.openstack4j.openstack.database.domain.DatabaseBackupCreate;
import org.openstack4j.openstack.database.domain.DatabaseBackupCreateResponse;
import org.openstack4j.openstack.database.domain.DatabaseBackupPolicy;
import org.openstack4j.openstack.database.domain.DatabaseInstance;
import org.openstack4j.openstack.database.domain.DatabaseInstanceRestore;
import org.openstack4j.openstack.database.domain.DatabaseRestorePoint;
import org.openstack4j.openstack.trove.domain.ExtendParam;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

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
	 * get the backup policy of a database instance
	 * 
	 * @param creation	model represent the attributes of database backup creation
	 * @return {@link DatabaseBackupCreateResponse} instance
	 */
	public DatabaseBackupCreateResponse create(DatabaseBackupCreate creation) {
		return post(DatabaseBackupCreateResponse.class, "/backups").entity(creation).execute();
	}

	/**
	 * list all backup snapshots
	 * 
	 * @return list of {@link DatabaseBackup} instances
	 */
	public List<DatabaseBackup> list() {
		return get(Backups.class, "/backups").execute().getList();
	}

	/**
	 * delete backup snapshot
	 * 
	 * @return {@link ActionResponse} instance
	 */
	public ActionResponse delete(String backupId) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(backupId), "parameter `backupId` should not be empty");
		return deleteWithResponse("/backups/", backupId).execute();
	}

	/**
	 * restore to an exist database instance
	 * 
	 * @param restoreToInstanceId	the database instance-id to be restored
	 * @param restorePoint			the restore point of source database
	 * @return	the identifier list of asynchronous restore job 
	 */
	public List<String> restoreToExistInstance(String restoreToInstanceId, DatabaseRestorePoint restorePoint) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(restoreToInstanceId),
				"parameter `restoreToInstanceId` should not be empty");
		Preconditions.checkArgument(restorePoint != null, "parameter `restorePoint` should not be empty");
		Preconditions.checkArgument(
				!Strings.isNullOrEmpty(restorePoint.getBackupRef()) || restorePoint.getRestoreTime() != null,
				"parameter `restorePoint.backupRef` and `restorePoint.restoreTime` should not both be null");
		// reset unused field
		restorePoint = restorePoint.toBuilder().sourceInstanceId(null).build();
		ExtendParam execute = post(ExtendParam.class, uri("/instances/%s/action", restoreToInstanceId))
				.entity(restorePoint).execute();

		List<String> jobIds = Lists.newArrayList();
		for (IdResourceEntity job : execute.getJobs()) {
			jobIds.add(job.getId());
		}
		return jobIds;
	}

	/**
	 * restore backup snapshot to a new instance
	 * 
	 * @param restore model represent the attributes of restore
	 * @return
	 */
	public DatabaseInstance restoreToNewInstance(DatabaseInstanceRestore restore) {
		Preconditions.checkArgument(restore != null, "parameter `restore` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(restore.getName()),
				"parameter `restore.name` should not be empty");
		Preconditions.checkArgument(!Strings.isNullOrEmpty(restore.getFlavorRef()),
				"parameter `restore.flavorRef` should not be empty");
		Preconditions.checkArgument(restore.getVolume() != null, "parameter `restore.volume` should not be null");
		Preconditions.checkArgument(restore.getVolume().getSize() != null,
				"parameter `restore.volume.size` should not be null");

		Preconditions.checkArgument(restore.getRestorePoint() != null,
				"parameter `restore.restorePoint` should not be null");
		Preconditions.checkArgument(
				!Strings.isNullOrEmpty(restore.getRestorePoint().getBackupRef())
						|| restore.getRestorePoint().getRestoreTime() != null,
				"parameter `restore.restorePoint.backupRef` and `restore.restorePoint.restoreTime` should not both be null");
		return post(DatabaseInstance.class, "/instances").entity(restore).execute();
	}

}
