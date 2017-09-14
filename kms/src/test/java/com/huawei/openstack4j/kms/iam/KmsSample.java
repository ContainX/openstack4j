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
package com.huawei.openstack4j.kms.iam;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.kms.AbstractSample;
import com.huawei.openstack4j.kms.openstack.constants.KeyState;
import com.huawei.openstack4j.kms.openstack.domain.Key;
import com.huawei.openstack4j.kms.openstack.domain.Key.Keys;
import com.huawei.openstack4j.kms.openstack.options.KeyListOptions;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.ResourceType;


/**
 *
 * @author QianBiao.NG
 * @date   2017-07-13 14:43:01
 */
@Test(suiteName = "KeyManagement/Key/Sample")
public class KmsSample extends AbstractSample {

	String name = randomName();
	Key key = null;

	/**
	 * create a key for test
	 */
	@BeforeClass
	public void prepare() {
//		KeyCreate create = KeyCreate.builder().alias(name).description("desc").realm("eu-de").build();
//		key = kmsclient.keys().create(create);
	}

	/**
	 * after all, we submit a schedule deletion to delete the key in future
	 */
	@AfterClass
	public void cleanup() {
//		kmsclient.keys().scheduleDeletion(key.getId(), 7, null);
	}

	@Test(priority = 1)
	public void testGetKey() {
		Key get = kmsclient.keys().get(key.getId(), null);
		Assert.assertEquals(get.getAlias(), name);
		Assert.assertEquals(get.getDescription(), "desc");
		Assert.assertEquals(get.getRealm(), "cn-north-1");

		key = get;
	}

	@Test(priority = 2)
	public void testScheduleDeletion() {
		Key deleted = kmsclient.keys().scheduleDeletion(key.getId(), 7, null);
		Assert.assertEquals(deleted.getState(), KeyState.ScheduledDeletion);
	}

	@Test(priority = 3)
	public void testCancelDeletion() {
		Key canceled = kmsclient.keys().cancelDeletion(key.getId(), null);
		Assert.assertEquals(canceled.getState(), KeyState.Disabled);
	}

	@Test(priority = 4)
	public void testEnableKey() {
		Key enabled = kmsclient.keys().enable(key.getId(), null);
		Assert.assertEquals(enabled.getState(), KeyState.Enabled);
	}

	@Test(priority = 5)
	public void testDisableKey() {
		Key disabled =kmsclient.keys().disable(key.getId(), null);
		Assert.assertEquals(disabled.getState(), KeyState.Disabled);
	}

	@Test(priority = 6)
	public void testListKeys() {
		KeyListOptions options = KeyListOptions.create().limit(20).keyState(KeyState.Enabled);
		Keys keys = kmsclient.keys().list(options);

//		boolean contains = keys.get().contains(key.getId());
//		while (!contains && keys.getTruncated()) {
//			options.marker(keys.getNextMarker());
//			keys = kmsclient.keys().list(options);
//			contains = keys.get().contains(key.getId());
//		}
//
//		Assert.assertTrue(contains);
	}

	@Test(priority = 7)
	public void testGetAmount() {
		Integer keyCreatedAmount = kmsclient.keys().getKeyCreatedAmount();
		Assert.assertTrue(keyCreatedAmount > 0);
	}

	@Test(priority = 8)
	public void testListQuota() {
		List<Quota> quotas = kmsclient.keys().quotas();
		Assert.assertTrue(quotas.size() > 0);
		boolean found = false;
		for (Quota quota : quotas) {
			if (quota.getType().equals(ResourceType.CMK)) {
				Assert.assertTrue(quota.getUsed() > 0);
				found = true;
			}
		}

		Assert.assertTrue(found);
	}

}
