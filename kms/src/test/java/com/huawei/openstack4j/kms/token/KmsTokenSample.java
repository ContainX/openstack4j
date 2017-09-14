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
package com.huawei.openstack4j.kms.token;

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
public class KmsTokenSample extends AbstractSample {

	String name = randomName();
	Key key = null;
	String tokenId="MIIM3AYJKoZIhvcNAQcCoIIMzTCCDMkCAQExDTALBglghkgBZQMEAgEwggsqBgkqhkiG9w0BBwGgggsbBIILF3sidG9rZW4iOnsiaXNzdWVkX2F0IjoiMjAxNy0wOS0wN1QwMTo1MjoxNi42MjIwMDBaIiwiZXhwaXJlc19hdCI6IjIwMTctMDktMDhUMDE6NTI6MTYuNjIyMDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwicHJvamVjdCI6eyJuYW1lIjoic291dGhjaGluYSIsImlkIjoiNjc2NDFmZTY4ODZmNDNmY2I3OGVkYmJmMGFkMGI5OWYiLCJkb21haW4iOnsibmFtZSI6IndlaWh1YTEyMyIsImlkIjoiOTY5ODU0Mjc1OGJjNDIyMDg4YzBjM2VhYmZjMzBkMTIifX0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjE5OTJjMWRmOWFkNjQxMmU5YzgzMzAzMmNkNzBjYThmIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiJlYjNkOGUyMGI5MTI0YmZhOTc0YWE3YzVmMzI5M2FmZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19kZWZvZyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19zdXBlcl9yZXNvbHV0aW9uIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfR0FDUyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19wYXRoX3Byb2dyYW0ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jb2xkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfYXNkYXNkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZXJ3ZXJ3ZXgifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kd3NfZWNyYSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2NzYnMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9hIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb2JzX3dhcm1fY29sZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19vY3JfZm9ybSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2t2bSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19iaW5fcGFja2luZyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Nsb3VkaWRlIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGVzdDAwMiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3hpYW90YWl5YW5nIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGFzc3NnMSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Rhc3NzZzIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90ZXN0MDA3In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGFzc3NnNCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Rhc3NzZzUifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90YXNzc2c2In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZHJkcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Nsb3VkSU0ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF8wOTE3dGVzdDIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vYnNfd29ybSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3RhZyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Vjc19obWVtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfc3Fsc2VydmVyIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfd2Vic2NhbiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Jkc19jdXN0b21lcmNsb3VkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfcmRzX3BnIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfc2NjX2h2ZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2x0cyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3NjY19zY3MifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kZG0ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9oanRlc3Rjb2RlIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZGRzIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdXF1ZXJ5In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfY2xvdWRjYyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RmZ2RmZyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2N1eGlhb19rNzIyMSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX0lNIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZWNzX2dwdV9jYSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2hzcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc190aW1lX2Fub21hbHkifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF80NTYifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kd3NfZmVhdHVyZSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3Jkc19jdXN0b21lcmNsb3VkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfcmRzX3NxbHNlcnZlciJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2dhdGVkX2ttcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3NjY193YWYifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9haXNfZGFya19lbmhhbmNlIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfaGp0ZXN0anVtcCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Nvc3QyZnJlZSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3NjY19wdHMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9yZHMtdHJhbnNmZXIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9pb3QifV0sInVzZXIiOnsiZG9tYWluIjp7Im5hbWUiOiJ3ZWlodWExMjMiLCJpZCI6Ijk2OTg1NDI3NThiYzQyMjA4OGMwYzNlYWJmYzMwZDEyIn0sImlkIjoiMTc0MGM2NzBjYWIyNDEwNGIwZjI4NjliZDU5ZDI2NzIiLCJuYW1lIjoid2VpaHVhMTIzIn0sImNhdGFsb2ciOltdfX0xggGFMIIBgQIBATBcMFcxCzAJBgNVBAYTAlVTMQ4wDAYDVQQIDAVVbnNldDEOMAwGA1UEBwwFVW5zZXQxDjAMBgNVBAoMBVVuc2V0MRgwFgYDVQQDDA93d3cuZXhhbXBsZS5jb20CAQEwCwYJYIZIAWUDBAIBMA0GCSqGSIb3DQEBAQUABIIBAFjl5SYJsTTFZaDCHdR4F-sdODKeY53k7+jW26IVSRaaimeKvZi4NyzeBs47azXhvjHogBQ8Aym2bLz5KUuHbN-c4tk3ofZzeMImqAHvqtmAD1d2sUKJ0uGlmOlrwdIGlkfOXdB8MSNTM4vs1-IZ5Dr9h8u7InrhvJI0L43ClyY3Q00GWQqyddTvhljgoYNUMYdh+Lsdf2MAmNQZem+HXbHwEktqwJHp6lYc0E69+T6ffiCaLFxIh8G3icE7spT7VgNkfzqTSHzYPgqRZaGy4i-ku-oIeVZIkKMziEWlyPbX57In3rsh3IYr9yT9xZzUqWqI8BXeTb3E2eSRPcMkav8=";

	/**
	 * create a key for test
	 */
	@BeforeClass
	public void prepare() {
//		KeyCreate create = KeyCreate.builder().alias(name).description("desc").realm("eu-de").build();
//		key = kmsclient.withToken(tokenId).keys().create(create);
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
		Key get = kmsclient1.keys().get(key.getId(), null);
		Assert.assertEquals(get.getAlias(), name);
		Assert.assertEquals(get.getDescription(), "desc");
		Assert.assertEquals(get.getRealm(), "cn-north-1");

		key = get;
	}

	@Test(priority = 2)
	public void testScheduleDeletion() {
		Key deleted = kmsclient1.keys().scheduleDeletion(key.getId(), 7, null);
		Assert.assertEquals(deleted.getState(), KeyState.ScheduledDeletion);
	}

	@Test(priority = 3)
	public void testCancelDeletion() {
		Key canceled = kmsclient1.keys().cancelDeletion(key.getId(), null);
		Assert.assertEquals(canceled.getState(), KeyState.Disabled);
	}

	@Test(priority = 4)
	public void testEnableKey() {
		Key enabled = kmsclient1.keys().enable(key.getId(), null);
		Assert.assertEquals(enabled.getState(), KeyState.Enabled);
	}

	@Test(priority = 5)
	public void testDisableKey() {
		Key disabled =kmsclient1.keys().disable(key.getId(), null);
		Assert.assertEquals(disabled.getState(), KeyState.Disabled);
	}

	@Test(priority = 6)
	public void testListKeys() {
		KeyListOptions options = KeyListOptions.create().limit(2).keyState(KeyState.Enabled);
		Keys keys = kmsclient1.withToken(tokenId).keys().list(options);
		keys.getTruncated();
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
		Integer keyCreatedAmount = kmsclient1.keys().getKeyCreatedAmount();
		Assert.assertTrue(keyCreatedAmount > 0);
	}

	@Test(priority = 8)
	public void testListQuota() {
		List<Quota> quotas = kmsclient1.keys().quotas();
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
