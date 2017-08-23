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
package com.huawei.openstack4j.api.storage;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.storage.block.ext.Service.State;
import com.huawei.openstack4j.model.storage.block.ext.Service.Status;

/**
 * Test cases for Block Storage Services function
 * 
 * @author Taemin
 */

@Test(suiteName = "BlockStorageService")
public class ServiceTests extends AbstractTest {

	private static final String JSON_SERVICES = "/storage/ext/services.json";

	public void serviceListingTest() throws Exception {
		respondWith(JSON_SERVICES);

		List<? extends com.huawei.openstack4j.model.storage.block.ext.Service> services = osv3().blockStorage().services()
				.list();

		com.huawei.openstack4j.model.storage.block.ext.Service s = services.get(0);
		assertEquals("cinder-scheduler", s.getBinary());
		assertEquals("host1", s.getHost());
		assertEquals(Status.ENABLED, s.getStatus());
		assertEquals(State.UP, s.getState());        
	}

	@Override
	protected Service service() {
		return Service.BLOCK_STORAGE;
	}

}