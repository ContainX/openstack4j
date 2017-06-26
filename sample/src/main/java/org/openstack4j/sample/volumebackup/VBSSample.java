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
/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.sample.volumebackup;

import java.util.List;

import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.storage.block.CloudVolumeBackupJob;
import org.openstack4j.model.storage.block.VolumeBackup;
import org.openstack4j.model.storage.block.VolumeBackupCreate;
import org.openstack4j.openstack.storage.block.domain.VBSVolumeBackupCreate;
import org.openstack4j.sample.AbstractSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-06 20:40:10
 */
public class VBSSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(VBSSample.class);

	@Test
	public void testVolumeBackupCreate() {
		VBSVolumeBackupCreate vbc = VBSVolumeBackupCreate.builder().name("qianbiao-ng-os4j-1")
				.volumeId("0a3218ef-7841-45c5-b9a1-5da6e0b70b85").build();
		CloudVolumeBackupJob job = osclient.cloudVolumeBackup().create(vbc);
		Assert.assertNotNull(job.getJobId());
	}

	@Test
	public void testVolumeBackupList() {
		VolumeBackupCreate vbc = Builders.volumeBackupCreate().volumeId("0a3218ef-7841-45c5-b9a1-5da6e0b70b85")
				.name("qianbiao-ng-original-1").build();
		osclient.blockStorage().backups().create(vbc);
		List<? extends VolumeBackup> list = osclient.blockStorage().backups().list();
		logger.info("{}", list);
	}

	@Test
	public void testVolumeBackupDelete() {
		ActionResponse delete = osclient.blockStorage().backups().delete("fc335f70-4880-4f03-a408-3d2bc691df8d");
		logger.info("{}", delete);
	}

}
