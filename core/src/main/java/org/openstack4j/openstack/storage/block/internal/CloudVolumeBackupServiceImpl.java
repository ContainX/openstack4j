/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package org.openstack4j.openstack.storage.block.internal;

import static com.google.common.base.Preconditions.*;

import org.openstack4j.api.storage.CloudVolumeBackupService;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.storage.block.CloudVolumeBackupCreate;
import org.openstack4j.model.storage.block.CloudVolumeBackupJob;
import org.openstack4j.openstack.internal.BaseOpenStackService;
import org.openstack4j.openstack.storage.block.domain.VBSVolumeBackupJob;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:47:47
 */
public class CloudVolumeBackupServiceImpl extends BaseOpenStackService implements CloudVolumeBackupService {

	public CloudVolumeBackupServiceImpl() {
		super(ServiceType.CLOUD_VOLUME_BACKUP);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public CloudVolumeBackupJob create(CloudVolumeBackupCreate cvbc) {
		checkNotNull(cvbc);
		checkNotNull(cvbc.getVolumeId());
		return post(VBSVolumeBackupJob.class, uri("/cloudbackups")).entity(cvbc).execute();
	}

}
