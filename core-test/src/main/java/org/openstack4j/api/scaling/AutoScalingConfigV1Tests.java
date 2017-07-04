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
package org.openstack4j.api.scaling;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.Disk;
import org.openstack4j.model.scaling.InstanceConfig;
import org.openstack4j.model.scaling.ScalingConfig;
import org.openstack4j.model.scaling.ScalingConfigCreate;
import org.openstack4j.model.scaling.Disk.DiskType;
import org.openstack4j.model.scaling.Disk.VolumeType;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingConfigCreate;
import org.openstack4j.openstack.scaling.options.ScalingConfigListOptions;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;

@Test(suiteName = "AutoScaling/AutoScalingConfigV1", enabled = true)
public class AutoScalingConfigV1Tests extends AbstractTest {
	private static final String JSON_SCALING_CONFIG_LIST = "/scaling/as_scaling_config_list.json";
	private static final String JSON_SCALING_CONFIG_LIST2 = "/scaling/as_scaling_config_list2.json";
	private static final String JSON_SCALING_CONFIG = "/scaling/as_scaling_config.json";
	private static final String JSON_SCALING_CONFIG_CREATE = "/scaling/as_scaling_config_create.json";

	public void testCreateAutoScalingConfig() throws IOException {
		respondWith(JSON_SCALING_CONFIG_CREATE);
		Map<String, String> metaData = Maps.newHashMap();
		metaData.put("key1", "val1");
		metaData.put("key2", "val2");
		Disk disk = Disk.builder().size(40).volumeType(VolumeType.SATA).diskType(DiskType.SYS).build();
		InstanceConfig instanceConfig = InstanceConfig.builder().flavorRef("computev1-1")
				.imageRef("cb6ad86a-f69e-4a36-b65b-1038b19e15d3").disks(Lists.newArrayList(disk))
				.keyName("KeyPair-28ice").metadata(metaData).build();
		ScalingConfigCreate config = ASAutoScalingConfigCreate.builder().configName("test-config-name")
				.instanceConfig(instanceConfig).build();

		ScalingConfigCreate result = osv3().autoScaling().configs().create(config);
		assertEquals(result.getConfigId(), "7345948e-8511-491b-bf82-36303348fa3d");
	}

	public void testListAutoScalingConfig() throws IOException {
		respondWith(JSON_SCALING_CONFIG_LIST);
		List<? extends ScalingConfig> all = osv3().autoScaling().configs().list();
		assertEquals(all.size(), 13);

		respondWith(JSON_SCALING_CONFIG_LIST2);
		ScalingConfigListOptions options = ScalingConfigListOptions.create().configName("test-config-name");
		List<? extends ScalingConfig> list = osv3().autoScaling().configs().list(options);
		assertEquals(list.size(), 8);
		for (ScalingConfig config : list) {
			assertEquals(config.getConfigName(), "test-config-name");
		}
	}

	public void testGetAutoScalingConfig() throws IOException {
		respondWith(JSON_SCALING_CONFIG);
		String configId = "a9800cc4-3f59-43b1-8a95-090f967a55c3";
		ScalingConfigCreate config = osv3().autoScaling().configs().get(configId);
		assertEquals(config.getConfigId(), configId);
	}

	public void testDeleteAutoScalingConfig() {
		respondWith(204);
		ActionResponse resp = osv3().autoScaling().configs().delete("7345948e-8511-491b-bf82-36303348fa3d");
		assertTrue(resp.isSuccess(), resp.getFault());
		respondWith(204);
		ActionResponse resp2 = osv3().autoScaling().configs()
				.delete(Lists.newArrayList("7345948e-8511-491b-bf82-36303348fa3d"));
		assertTrue(resp2.isSuccess(), resp2.getFault());
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}
}
