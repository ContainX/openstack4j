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
package com.huawei.openstack4j.functional.key.management;

import java.util.List;
import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.kms.client.KmsClient;
import com.huawei.openstack4j.kms.client.KmsFactory;
import com.huawei.openstack4j.kms.openstack.constants.KeyState;
import com.huawei.openstack4j.kms.openstack.domain.Key;
import com.huawei.openstack4j.kms.openstack.domain.Key.Keys;
import com.huawei.openstack4j.kms.openstack.domain.KeyCreate;
import com.huawei.openstack4j.kms.openstack.options.KeyListOptions;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.common.Quota.ResourceType;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

/**
 *
 * @author Super Stone
 * 
 * @date 2017-11-2 18:33:12
 */
@Test(suiteName = "KeyManagement/Key/Sample")
public class KmsTokenSample {

	String name = randomName();
	String keyId = null;
	boolean deleteKey = false;
	static KmsClient kmstokenclient;
	private String tokenId = "MIIJpgYJKoZIhvcNAQcCoIIJlzCCCZMCAQExDTALBglghkgBZQMEAgEwggf0BgkqhkiG9w0BBwGgggflBIIH4XsidG9rZW4iOnsiaXNzdWVkX2F0IjoiMjAxNy0xMS0wMlQxMToyNzowMi44ODgwMDBaIiwiZXhwaXJlc19hdCI6IjIwMTctMTEtMDNUMTE6Mjc6MDIuODg4MDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwicHJvamVjdCI6eyJuYW1lIjoic291dGhjaGluYSIsImlkIjoiNjc2NDFmZTY4ODZmNDNmY2I3OGVkYmJmMGFkMGI5OWYiLCJkb21haW4iOnsibmFtZSI6IndlaWh1YTEyMyIsImlkIjoiOTY5ODU0Mjc1OGJjNDIyMDg4YzBjM2VhYmZjMzBkMTIifX0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjE5OTJjMWRmOWFkNjQxMmU5YzgzMzAzMmNkNzBjYThmIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiJlYjNkOGUyMGI5MTI0YmZhOTc0YWE3YzVmMzI5M2FmZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19vY3JfdmF0X2ludm9pY2UifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9zY2NfaHZkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfR0FDUyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Nlc19hZ3QifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jb2xkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfcmRzX215b3B0In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfc2NjX3NjcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RkbSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RkcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Vjc19ub3JtYWxleGNsdXNpdmUifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jbG91ZGNjIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb2JzX3dhcm1fY29sZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2t2bSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX0lNIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZWNzX3JlY3ljbGViaW4ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9MVFMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9mZ3MifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vbXMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kd3NfZmVhdHVyZSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RyZHMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jbG91ZElNIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZWNzX25vcm1hbF9zMyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Rlc3RnYzAwMSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2dhdGVkX2ttcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19pbWFnZV9yZWNhcHR1cmVfZGV0ZWN0In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfc2NjX3dhZiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29ic193b3JtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfRnVuY3Rpb25HcmFwaCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3RhZyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2NjIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZGNzX2NsdXN0ZXIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9lY3NfaG1lbSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Fpc19pbWFnZV9hbnRpcG9ybiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3NjY19wdHMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9lbGFzdGljc2VhcmNoIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfYWlzX2ltYWdlX3RhZ2dpbmcifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9yZHMtdHJhbnNmZXIifV0sInVzZXIiOnsiZG9tYWluIjp7Im5hbWUiOiJ3ZWlodWExMjMiLCJpZCI6Ijk2OTg1NDI3NThiYzQyMjA4OGMwYzNlYWJmYzMwZDEyIn0sImlkIjoiMTc0MGM2NzBjYWIyNDEwNGIwZjI4NjliZDU5ZDI2NzIiLCJuYW1lIjoid2VpaHVhMTIzIn0sImNhdGFsb2ciOltdfX0xggGFMIIBgQIBATBcMFcxCzAJBgNVBAYTAlVTMQ4wDAYDVQQIDAVVbnNldDEOMAwGA1UEBwwFVW5zZXQxDjAMBgNVBAoMBVVuc2V0MRgwFgYDVQQDDA93d3cuZXhhbXBsZS5jb20CAQEwCwYJYIZIAWUDBAIBMA0GCSqGSIb3DQEBAQUABIIBAF8qq88B2edTpPcnpTUTlimbscVey-OTJpRFmBm+kRvelMFGCDx7CDdgLVdFBLj2WOHE4K1pfCaiqDngp7G3UysrrB4n4b4ui5S2doRYx-EzwGiQSvW7ro-AQZbM2kxsLxDoen8qJi1mNfUFHwl296GyJMH6Metlgj8FVU8WuPP8Cg9QqxwXMhG55WCVeEzTqURIhHH65eACmk9vjrLlfzQuMaiE+Fk0jcVoyhBPWuU7SQeg8c2I2YG80Gh9NQ2Q13yKTWqqk5pnXEFUWK6Wh3BVTursSJ8yKwD1nJH9ZTff7C46gtZKc0QmBwIkZYgjrmRs4y4tU6EUtDRkV54j6iA=";
	@BeforeTest
	public static void initialClient() {
		// add override endpoint
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();

		endpointResolver.addOverrideEndpoint(ServiceType.KEY_MANAGEMENT,
				"https://kms.cn-north-1.myhwclouds.com/v1.0/%(project_id)s");

		String projectId = "67641fe6886f43fcb78edbbf0ad0b99f";
		OSFactory.enableHttpLoggingFilter(true);

		kmstokenclient = (KmsClient) KmsFactory.builder()
				.withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver).withSSLVerificationDisabled())
				.withProjectId(projectId);
	}

	/**
	 * create a key for test
	 */
	@BeforeClass
	public void prepare() {

		List<String> keys = kmstokenclient.withToken(tokenId).keys().list(null).get();
		if (keys != null && keys.size() > 0) {
			for (String keyId : keys) {
				Key key = kmstokenclient.withToken(tokenId).keys().get(keyId, null);
				if (key.getAlias().startsWith("SDK")) {
					this.keyId = key.getId();
					break;
				}
			}
		}
		if (null == keyId) {
			KeyCreate create = KeyCreate.builder().alias(name).description("desc").build();
			keyId = kmstokenclient.withToken(tokenId).keys().create(create).getId();
		}
	}

	/**
	 * after all, we submit a schedule deletion to delete the key in future
	 */
	@AfterClass
	public void cleanup() {
		if (deleteKey) {
			kmstokenclient.withToken(tokenId).keys().scheduleDeletion(keyId, 7, null);
		}
	}

	@Test(priority = 1)
	public void testGetKey() {
		Key get = kmstokenclient.withToken(tokenId).keys().get(keyId, null);
		Assert.assertEquals(get.getAlias(), name);
		Assert.assertEquals(get.getDescription(), "desc");
		Assert.assertEquals(get.getRealm(), "cn-north-1");
	}

	@Test(priority = 2)
	public void testScheduleDeletion() {
		Key deleted = kmstokenclient.withToken(tokenId).keys().scheduleDeletion(keyId, 7, null);
		Assert.assertEquals(deleted.getState(), KeyState.ScheduledDeletion);
	}

	@Test(priority = 3)
	public void testCancelDeletion() {
		Key canceled = kmstokenclient.withToken(tokenId).keys().cancelDeletion(keyId, null);
		Assert.assertEquals(canceled.getState(), KeyState.Disabled);
	}

	@Test(priority = 4)
	public void testEnableKey() {
		Key enabled = kmstokenclient.withToken(tokenId).keys().enable(keyId, null);
		Assert.assertEquals(enabled.getState(), KeyState.Enabled);
	}

	@Test(priority = 5)
	public void testDisableKey() {
		Key disabled = kmstokenclient.withToken(tokenId).keys().disable(keyId, null);
		Assert.assertEquals(disabled.getState(), KeyState.Disabled);
	}

	@Test(priority = 6)
	public void testListKeys() {
		KeyListOptions options = KeyListOptions.create().limit(2).keyState(KeyState.Enabled);
		Keys keys = kmstokenclient.withToken(tokenId).keys().list(options);

		boolean contains = keys.get().contains(keyId);
		while (!contains && keys.getTruncated()) {
			options.marker(keys.getNextMarker());
			keys = kmstokenclient.keys().list(options);
			contains = keys.get().contains(keyId);
		}

		Assert.assertTrue(contains);
	}

	@Test(priority = 7)
	public void testGetAmount() {
		Integer keyCreatedAmount = kmstokenclient.withToken(tokenId).keys().getKeyCreatedAmount();
		Assert.assertTrue(keyCreatedAmount > 0);
	}

	@Test(priority = 8)
	public void testListQuota() {
		List<Quota> quotas = kmstokenclient.withToken(tokenId).keys().quotas();
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

	protected static String randomName() {
		return "SDK-" + UUID.randomUUID().toString();
	}

}
