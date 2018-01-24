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

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.google.common.collect.Maps;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.storage.block.Volume;
import com.huawei.openstack4j.model.storage.block.Volume.Status;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "volume/create/new")
public class VolumeCreateTests extends AbstractTest {

	@Override
	protected Service service() {
		return Service.BLOCK_STORAGE;
	}

	@Test
	public void testCreateVolume() throws Exception {

		respondWith("/storage/v2/cinder_volume_create_with_scheduler_hints_response.json");

		HashMap<String, String> meta = Maps.newHashMap();
		meta.put("key1", "value1");
		Volume volume = Builders.volume().name("sdk-test").imageRef("image-ref").zone("en-us")
				.description("sdk-unittest").volumeType("SATA").metadata(meta).snapshot("snapshot-id").size(40).build();

		Map<String, Object> schedulerHints = Maps.newHashMap();
		schedulerHints.put("same_host",
				Lists.newArrayList("a0cf03a5-d921-4877-bb5c-86d26cf818e1", "8c19174f-4220-44f0-824a-cd1eeef10287"));
		Volume created = osv3().blockStorage().volumes().create(volume, schedulerHints);

		RecordedRequest request = server.takeRequest();
		assertTrue(request.getPath().equals("/v1/project-id/volumes"));
		assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		String expectBody = this.getResource("/storage/v2/cinder_volume_create_with_scheduler_hints_request.json");
		Assert.assertEquals(requestBody, expectBody);
		
		Assert.assertEquals(created.getZone(), "xxx");
		Assert.assertEquals(created.bootable(), false);
		Assert.assertEquals(created.getDescription(), "create for api test");
		Assert.assertEquals(created.getId(), "8dd7c486-8e9f-49fe-bceb-26aa7e312b66");
		Assert.assertEquals(created.getCreated().getTime(), 1464144312463L);
		Assert.assertEquals(created.encrypted(), false);
		Assert.assertEquals(created.getMetaData().get("volume_owner"), "openapi");
		Assert.assertEquals(created.getName(), "openapi_vol01");
		Assert.assertEquals(created.getSize(), 40);
		Assert.assertEquals(created.getStatus(), Status.CREATING);
		Assert.assertEquals(created.getVolumeType(), "SATA");
		
	}

}
