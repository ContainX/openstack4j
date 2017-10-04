/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.functional.scaling;

import static org.testng.Assert.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.scaling.Disk.DiskType;
import com.huawei.openstack4j.model.scaling.Disk.VolumeType;
import com.huawei.openstack4j.model.scaling.InstanceConfig;
import com.huawei.openstack4j.model.scaling.ScalingConfig;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.openstack.scaling.options.ScalingConfigListOptions;

public class ASConfigTest extends BaseAutoScalingTest {

	ScalingGroup scalingGroup = null;
	String name = randomName();
	String configId;

	ScalingConfig create;

	@BeforeClass
	public void testCreateConfig() {
		// create a config
		configId = this.createConfig(name);
	}

	@AfterClass
	public void testDeleteConfig() {
		osclient.autoScaling().configs().delete(configId);
	}

	@Test
	public void testGetAutoScalingConfig() {
		ScalingConfig get = osclient.autoScaling().configs().get(configId);
		validateConfig(get);
		create = get;
	}

	@Test(dependsOnMethods = { "testGetAutoScalingConfig" })
	public void testListAutoScalingConfig() {
		List<? extends ScalingConfig> all = osclient.autoScaling().configs().list();
		boolean found = false;
		for (ScalingConfig config : all) {
			if (config.getConfigId().equals(configId)) {
				found = true;
				validateConfig(config);
				break;
			}
		}

		Assert.assertTrue(found);

		ScalingConfigListOptions options = ScalingConfigListOptions.create().configName(create.getConfigName());
		List<? extends ScalingConfig> list = osclient.autoScaling().configs().list(options);
		Assert.assertTrue(list.size() == 1);

		ScalingConfig config = list.get(0);
		validateConfig(config);
	}

	@Test
	public void testBatchDeleteAutoScalingConfig() {
		String configId1 = this.createConfig(name + "-1");
		String configId2 = this.createConfig(name + "-2");
		ActionResponse delete = osclient.autoScaling().configs().delete(Lists.newArrayList(configId1, configId2));
		Assert.assertTrue(delete.isSuccess(), delete.getFault());
	}

	/**
	 * @param config
	 */
	public void validateConfig(ScalingConfig config) {
		assertEquals(config.getConfigId(), configId);
		assertEquals(config.getConfigName(), name);

		InstanceConfig instanceConfig = config.getInstanceConfig();
		assertEquals(instanceConfig.getFlavorRef(), getFirstFlavor().getId());
		assertEquals(instanceConfig.getImageRef(), getFirstImage().getId());
		assertEquals(instanceConfig.getKeyName(), getFirstKeypair().getName());
		assertEquals(instanceConfig.getMetadata().size(), 2);
		assertEquals(instanceConfig.getMetadata().get("tag"), "app");
		assertEquals(instanceConfig.getMetadata().get("node"), "sdk-unittest");
		assertEquals(instanceConfig.getDisks().size(), 1);
		assertEquals(instanceConfig.getDisks().get(0).getSize().intValue(), 40);
		assertEquals(instanceConfig.getDisks().get(0).getVolumeType(), VolumeType.SATA);
		assertEquals(instanceConfig.getDisks().get(0).getDiskType(), DiskType.SYS);
	}
}
