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
	String tokenId="MIIL0AYJKoZIhvcNAQcCoIILwTCCC70CAQExDTALBglghkgBZQMEAgEwggoeBgkqhkiG9w0BBwGgggoPBIIKC3sidG9rZW4iOnsiaXNzdWVkX2F0IjoiMjAxNy0wOS0xNVQwOToxNzozOC4xNzIwMDBaIiwiZXhwaXJlc19hdCI6IjIwMTctMDktMTZUMDk6MTc6MzguMTcyMDAwWiIsIm1ldGhvZHMiOlsicGFzc3dvcmQiXSwicHJvamVjdCI6eyJuYW1lIjoic291dGhjaGluYSIsImlkIjoiNjc2NDFmZTY4ODZmNDNmY2I3OGVkYmJmMGFkMGI5OWYiLCJkb21haW4iOnsibmFtZSI6IndlaWh1YTEyMyIsImlkIjoiOTY5ODU0Mjc1OGJjNDIyMDg4YzBjM2VhYmZjMzBkMTIifX0sInJvbGVzIjpbeyJuYW1lIjoidGVfYWRtaW4iLCJpZCI6IjE5OTJjMWRmOWFkNjQxMmU5YzgzMzAzMmNkNzBjYThmIn0seyJuYW1lIjoidGVfYWdlbmN5IiwiaWQiOiJlYjNkOGUyMGI5MTI0YmZhOTc0YWE3YzVmMzI5M2FmZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX0dBQ1MifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jb2xkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfcmRzX215b3B0In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfaXRlc3QifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9hc2Rhc2QifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9lcndlcndleCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2EifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vYnNfd2FybV9jb2xkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfYWlzX29jcl9mb3JtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfa3ZtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfY2xvdWRpZGUifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90ZXN0MDAyIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfeGlhb3RhaXlhbmcifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90YXNzc2cxIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGFzc3NnMiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Rhc3NzZzQifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90YXNzc2c1In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGFzc3NnNiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RyZHMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jbG91ZElNIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGVzdDIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF90ZXN0MyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkXzA5MTd0ZXN0MiJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX29ic193b3JtIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfdGFnIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZWNzX2htZW0ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9zcWxzZXJ2ZXIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF93ZWJzY2FuIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfcmRzX2N1c3RvbWVyY2xvdWQifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9yZHNfcGcifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9zY2NfaHZkIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfbHRzIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfc2NjX3NjcyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2RkbSJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2hqdGVzdGNvZGUifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9kZHMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF91cXVlcnkifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9jbG91ZGNjIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZGZnZGZnIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfY3V4aWFvX2s3MjIxIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfSU0ifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9lY3NfZ3B1X2NhIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfNDU2In0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZHdzX2ZlYXR1cmUifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9yZHNfY3VzdG9tZXJjbG91ZCJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Jkc19zcWxzZXJ2ZXIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9nYXRlZF9rbXMifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9vcF9nYXRlZF9zY2Nfd2FmIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfZGNzX2NsdXN0ZXIifSx7ImlkIjoiMCIsIm5hbWUiOiJvcF9nYXRlZF9oanRlc3RqdW1wIn0seyJpZCI6IjAiLCJuYW1lIjoib3BfZ2F0ZWRfb3BfZ2F0ZWRfc2NjX3B0cyJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX3Jkcy10cmFuc2ZlciJ9LHsiaWQiOiIwIiwibmFtZSI6Im9wX2dhdGVkX2lvdCJ9XSwidXNlciI6eyJkb21haW4iOnsibmFtZSI6IndlaWh1YTEyMyIsImlkIjoiOTY5ODU0Mjc1OGJjNDIyMDg4YzBjM2VhYmZjMzBkMTIifSwiaWQiOiIxNzQwYzY3MGNhYjI0MTA0YjBmMjg2OWJkNTlkMjY3MiIsIm5hbWUiOiJ3ZWlodWExMjMifSwiY2F0YWxvZyI6W119fTGCAYUwggGBAgEBMFwwVzELMAkGA1UEBhMCVVMxDjAMBgNVBAgMBVVuc2V0MQ4wDAYDVQQHDAVVbnNldDEOMAwGA1UECgwFVW5zZXQxGDAWBgNVBAMMD3d3dy5leGFtcGxlLmNvbQIBATALBglghkgBZQMEAgEwDQYJKoZIhvcNAQEBBQAEggEABM2K85IzeOhs8HXqvguTJep9e2ihigmNTqcZRQW40L94FR7zqE2d8RdmGTaKPzy0KCx34Y3p9ZbOp-LjgrW+lApX0TfYE1CjawGnGlN5CBrIFuhGZhKrNDl+5rX7pFAO25bw64bhqfzKSIHMgv6nbyJyqacbKpiM7amtiUUBTWfqZ-rozxT9CqSSy74HwUHhs5KwEl6d22thrHe9tlN0L6uAKhYj0EgJSV4HHEmZNxWkRvznu0kkLUJlzeao6MHaZyV4rLL6byCWA3gJNHhIaUGl4DI03eVG3SMvvdkY+x0vnXsJLaHxejk0latIuUfS2iPW2bjxeycPTmlCWqugsg==";

	
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
