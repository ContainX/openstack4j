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
package com.huawei.openstack4j.sample.key.management;

import java.util.List;

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
 * @author QianBiao.NG
 * @date   2017-07-13 14:43:01
 */
@Test(suiteName = "KeyManagement/Key/Sample")
public class KmsTokenSample extends AbstractKmsSample {

	String name = randomName();
	Key key = null;
	static KmsClient kmstokenclient;
	String tokenId="MIIL-QYJKoZIhvcNAQcCoIIL7jCCC+oCAQExDTALBglghkgBZQMEAgEwggpLBgkqhkiG9w0BBwGgggo8BIIKOHsidG9rZW4iOnsiaXNzdWVkX2F0IjoiMjAxNy0xMC0xM1QwMjoxNzo0NS4yMTUwMDBaIiwiZXhwaXJlc19hdCI6IjIwMTctMTAtMTRUMDI6MTc6NDUuMjE1MDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwicHJvamVjdCI6eyJuYW1lIjoic291dGhjaGluYSIsImlkIjoiNjc2NDFmZTY4ODZmNDNmY2I3OGVkYmJmMGFkMGI5OWYiLCJkb21haW4iOnsibmFtZSI6IndlaWh1YTEyMyIsImlkIjoiOTY5ODU0Mjc1OGJjNDIyMDg4YzBjM2VhYmZjMzBkMTIifX0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjE5OTJjMWRmOWFkNjQxMmU5YzgzMzAzMmNkNzBjYThmIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiJlYjNkOGUyMGI5MTI0YmZhOTc0YWE3YzVmMzI5M2FmZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX0dBQ1MifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jZXNfYWd0In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfY29sZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Jkc19teW9wdCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2FzZGFzZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Vjc19ub3JtYWxleGNsdXNpdmUifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9lcndlcndleCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2EifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vYnNfd2FybV9jb2xkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfYWlzX29jcl9mb3JtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfa3ZtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZWNzX3JlY3ljbGViaW4ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90ZXN0MDAyIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfeGlhb3RhaXlhbmcifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vbXMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90YXNzc2cxIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGFzc3NnMiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Rhc3NzZzQifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90YXNzc2c1In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGFzc3NnNiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RyZHMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jbG91ZElNIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZWNzX25vcm1hbF9zMyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkXzA5MTd0ZXN0MiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29ic193b3JtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfRnVuY3Rpb25HcmFwaCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3RhZyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2Vjc19obWVtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfcmRzX2N1c3RvbWVyY2xvdWQifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9ibXMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9zY2NfaHZkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfbHRzIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfc2NjX3NjcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RkbSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2hqdGVzdGNvZGUifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kZHMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF91cXVlcnkifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jbG91ZGNjIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZGZnZGZnIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfY3V4aWFvX2s3MjIxIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfSU0ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9mZ3MifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF80NTYifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kd3NfZmVhdHVyZSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2dhdGVkX2ttcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29wX2dhdGVkX3NjY193YWYifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90bXMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kY3NfY2x1c3RlciJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2hqdGVzdGp1bXAifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9haXNfaW1hZ2VfYW50aXBvcm4ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9zY2NfcHRzIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZWxhc3RpY3NlYXJjaCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Jkcy10cmFuc2ZlciJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2lvdCJ9XSwidXNlciI6eyJkb21haW4iOnsibmFtZSI6IndlaWh1YTEyMyIsImlkIjoiOTY5ODU0Mjc1OGJjNDIyMDg4YzBjM2VhYmZjMzBkMTIifSwiaWQiOiIxNzQwYzY3MGNhYjI0MTA0YjBmMjg2OWJkNTlkMjY3MiIsIm5hbWUiOiJ3ZWlodWExMjMifSwiY2F0YWxvZyI6W119fTGCAYUwggGBAgEBMFwwVzELMAkGA1UEBhMCVVMxDjAMBgNVBAgMBVVuc2V0MQ4wDAYDVQQHDAVVbnNldDEOMAwGA1UECgwFVW5zZXQxGDAWBgNVBAMMD3d3dy5leGFtcGxlLmNvbQIBATALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEAld66ySPkoptHvjyqQwm1DNEyFkg4VC8XBRz6ToWIeq4ep5Pgyp8barCag+0hlhoVTAFbN1EAPe6mQ9dewwRfbDb6YIoAFHFACRdiPxCwBPjKYgpMBP1a+LZ2T4BEBmS++TnQxeifJeYQpV5CO3SC8SwATA3jxUBks51ZsP3bfK28lOHBrG55zqJMZ+yA6zNiOWZlNGzE4dxIOtoLyXPl-+1Ol6PPhiVzHV3mHtpTNcV--1cV7E1AfRB5LPoPL2ibQg1AW6U4dq7vFTwI-OMLw-qY7Rh9cMEjc+Gg-Bm3qMAj1Y1uRLTVd23eltsujpKGmiA4vB6D3Fb9m+ONNrDJCg==";
	
	@BeforeTest
	public static void initialClient() {
		// add override endpoint
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
	 
		endpointResolver.addOverrideEndpoint(ServiceType.KEY_MANAGEMENT,
				"https://kms.cn-north-1.myhwclouds.com/v1.0/%(project_id)s");

		// // TODO remove authentication before push to github
		// String user = "replace-with-your-username";
		// String password = "replace-with-your-password";
		// String projectId = "d4f2557d248e4860829f5fef030b209c";
		// String userDomainId = "bb42e2cd2b784ac4bdc350fb660a2bdb";
		// String authUrl = "https://iam.cn-north-1.myhwclouds.com/v3";
		//

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
	
		KeyCreate create = KeyCreate.builder().alias(name).description("desc").realm("eu-de").build();
		key = kmsclient.withToken(tokenId).keys().create(create);
	}

	/**
	 * after all, we submit a schedule deletion to delete the key in future
	 */
	@AfterClass
	public void cleanup() {
		kmsclient.keys().scheduleDeletion(key.getId(), 7, null);
	}

	@Test(priority = 1)
	public void testGetKey() {
		Key get = kmstokenclient.withToken(tokenId).keys().get(key.getId(), null);
		Assert.assertEquals(get.getAlias(), name);
		Assert.assertEquals(get.getDescription(), "desc");
		Assert.assertEquals(get.getRealm(), "cn-north-1");

		key = get;
	}

	@Test(priority = 2)
	public void testScheduleDeletion() {
		Key deleted = kmstokenclient.withToken(tokenId).keys().scheduleDeletion(key.getId(), 7, null);
		Assert.assertEquals(deleted.getState(), KeyState.ScheduledDeletion);
	}

	@Test(priority = 3)
	public void testCancelDeletion() {
		Key canceled = kmstokenclient.withToken(tokenId).keys().cancelDeletion(key.getId(), null);
		Assert.assertEquals(canceled.getState(), KeyState.Disabled);
	}

	@Test(priority = 4)
	public void testEnableKey() {
		Key enabled = kmstokenclient.withToken(tokenId).keys().enable(key.getId(), null);
		Assert.assertEquals(enabled.getState(), KeyState.Enabled);
	}

	@Test(priority = 5)
	public void testDisableKey() {
		Key disabled =kmstokenclient.withToken(tokenId).keys().disable(key.getId(), null);
		Assert.assertEquals(disabled.getState(), KeyState.Disabled);
	}

	@Test(priority = 6)
	public void testListKeys() {
		KeyListOptions options = KeyListOptions.create().limit(2).keyState(KeyState.Enabled);
		Keys keys = kmstokenclient.withToken(tokenId).keys().list(options);

		boolean contains = keys.get().contains(key.getId());
		while (!contains && keys.getTruncated()) {
			options.marker(keys.getNextMarker());
			keys = kmsclient.keys().list(options);
			contains = keys.get().contains(key.getId());
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

}
