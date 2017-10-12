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
package com.huawei.openstack4j.sample.key.management;

import java.util.UUID;

import org.testng.annotations.BeforeTest;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.kms.client.KmsClient;
import com.huawei.openstack4j.kms.client.KmsFactory;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import com.huawei.openstack4j.sample.Retry;

/**
 *
 * @author QianBiao.NG
 * @date 2017-06-10 18:33:12
 */
public class AbstractKmsSample {

	protected static KmsClient kmsclient;

	@BeforeTest
	public static void initialV3Client() {
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

		String user = "weihua123";
		String password = "weihua@12";
		String projectId = "67641fe6886f43fcb78edbbf0ad0b99f";
		String userDomainId = "9698542758bc422088c0c3eabfc30d12";
		String authUrl = "https://192.144.35.205:31943/v3";

		OSFactory.enableHttpLoggingFilter(true);

		kmsclient = (KmsClient) KmsFactory.builder()
				.withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver).withSSLVerificationDisabled())
				.credentials(user, password, userDomainId, projectId, authUrl);
	}

	protected Object retry(Retry retry) {
		Integer retryTimes = 0;
		Integer maxRetryTimes = retry.maxRetryTimes();
		while (retryTimes++ < maxRetryTimes) {
			Object result = retry.run();
			if (result != null) {
				return result;
			}

			try {
				Thread.sleep(retry.delay() * 1000);
			} catch (InterruptedException e) {
			}
		}

		throw new RuntimeException("Retried max times, but no expected result returned");
	}

	protected static String randomName() {
		return "SDK-" + UUID.randomUUID().toString();
	}
}
