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
package org.openstack4j.sample;

import org.openstack4j.model.storage.block.CloudVolumeBackupJob;
import org.openstack4j.openstack.storage.block.domain.VBSVolumeBackupCreate;
import org.testng.annotations.Test;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-06 20:40:10
 */
public class VolumeBackupSample extends AbstractSample {

	@Test
	public void testCloudVolumeBackup() {
		VBSVolumeBackupCreate vbc = VBSVolumeBackupCreate.builder().name("qianbiao-ng-os4j-1")
				.volumeId("0a3218ef-7841-45c5-b9a1-5da6e0b70b85").build();

		CloudVolumeBackupJob create = osc.cloudVolumeBackup().create(vbc);
		System.out.println(create);
	}

}
