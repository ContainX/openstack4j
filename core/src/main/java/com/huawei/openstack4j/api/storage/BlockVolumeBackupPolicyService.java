/*******************************************************************************
 * 	Copyright 2017 HuaWei and OTC tld                                      
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
package com.huawei.openstack4j.api.storage;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicy;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyResource.VolumeBackupPolicyResourceActionResult;
import com.huawei.openstack4j.model.storage.block.options.BakcupTaskListOptions;

/**
 * 
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:56:05
 */
public interface BlockVolumeBackupPolicyService extends RestService {

	/**
	 * create a new volume backup policy
	 * 
	 * @return
	 */
	VolumeBackupPolicy create(VolumeBackupPolicy policy);

	/**
	 * list all volume backup policies
	 * 
	 * @return
	 */
	List<? extends VolumeBackupPolicy> list();

	/**
	 * 
	 * update an exists volume backup policy
	 * 
	 * @param updated
	 * @return
	 */
	VolumeBackupPolicy update(VolumeBackupPolicy updated);

	/**
	 * 
	 * delete a volume backup policy
	 * 
	 * @param backupPolicyId
	 * @return
	 */
	ActionResponse delete(String backupPolicyId);
	
	
	/**
	 * 
	 * execute a volume backup policy immediately
	 * 
	 * @param backupPolicyId
	 * @return
	 */
	ActionResponse execute(String backupPolicyId);
	
	
	/**
	 * 
	 * enable a volume backup policy
	 * 
	 * @param backupPolicyId
	 * @return
	 */
	VolumeBackupPolicy enable(String backupPolicyId);
	
	/**
	 * 
	 * disable a volume backup policy
	 * 
	 * @param backupPolicyId
	 * @return
	 */
	VolumeBackupPolicy disable(String backupPolicyId);
	
	
	/**
	 * 
	 * link resources (Volume) to the backup policy
	 * 
	 * @param resourceIds resource id list
	 * @return
	 */
	VolumeBackupPolicyResourceActionResult linkResources(String backupPolicyId, List<String> resourceIds);
	
	/**
	 * unlink resources of the backup policy
	 * 
	 * @param resourceIds
	 * @return
	 */
	VolumeBackupPolicyResourceActionResult unlinkResources(String backupPolicyId, List<String> resourceIds);
	
	
	/**
	 * 
	 * list backup tasks belongs to the policy
	 * 
	 * @param policyId tasks of the policy
	 * @param options list filter option
	 * @return
	 */
	List<? extends VolumeBackupPolicyBackupTask> tasks(String policyId, BakcupTaskListOptions options);
	

}
