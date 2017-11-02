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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.common.ServiceVersion;

@Test(suiteName = "Trove/Version/Test")
public class TroveServiceVersionTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(TroveServiceVersionTest.class);

	List<ServiceVersion> versions = null;

	@Test
	public void testListVersion() {
		// list version first
		versions = osclient.trove().versions().list();
		logger.info("versions: {}", versions);
		Assert.assertTrue(versions.size() >= 1);
	}

	@Test(dependsOnMethods = { "testListVersion" })
	public void testGetVersion() {
		// list version first
		String id = versions.get(0).getId();
		ServiceVersion version = osclient.trove().versions().get(id);
		logger.info("version: {}", version);
		Assert.assertEquals(version.getId(), id);
	}

}
