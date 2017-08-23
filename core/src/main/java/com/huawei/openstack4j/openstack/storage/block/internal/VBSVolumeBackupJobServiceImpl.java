/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
package com.huawei.openstack4j.openstack.storage.block.internal;

import com.huawei.openstack4j.api.storage.AsyncVolumeBackupJobService;
import com.huawei.openstack4j.model.storage.block.AsyncVolumeBackupJob;
import com.huawei.openstack4j.openstack.common.functions.ReplaceVersionOfURL;
import com.huawei.openstack4j.openstack.storage.block.domain.VBSVolumeBackupJob;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:47:47
 */
public class VBSVolumeBackupJobServiceImpl extends BaseVolumeBackupServices implements AsyncVolumeBackupJobService {

	public VBSVolumeBackupJobServiceImpl() {
		super(ReplaceVersionOfURL.instance("/v1"));
	}

	/* 
	 * {@inheritDoc}
	 */
	@Override
	public AsyncVolumeBackupJob get(String jobId) {
		return get(VBSVolumeBackupJob.class, uri("/jobs/%s", jobId)).execute();
	}

}
