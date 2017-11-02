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
package com.huawei.openstack4j.functional.trove;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.trove.domain.InstanceFlavor;

@Test(suiteName = "Trove/Flavor/Test")
public class DatabaseInstanceFlavorTest extends AbstractTest {

	List<InstanceFlavor> flavors = null;

	@Test
	public void testListInstanceFlavors() {
		flavors = osclient.trove().flavors().list();
		Assert.assertTrue(flavors.size() > 0);
	}

	@Test(dependsOnMethods = { "testListInstanceFlavors" })
	public void testGetInstanceFlavors() {
		InstanceFlavor flavor = flavors.get(0);
		InstanceFlavor get = osclient.trove().flavors().get(flavor.getStrId());
		Assert.assertEquals(flavor.getId(), get.getId());
		Assert.assertEquals(flavor.getName(), get.getName());
		Assert.assertEquals(flavor.getStrId(), get.getStrId());
		Assert.assertEquals(flavor.getRam().intValue() / 1024, get.getRam().intValue());
	}
}
