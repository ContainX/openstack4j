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
package com.huawei.openstack4j.functional.key.management;

import java.util.UUID;

import org.testng.annotations.BeforeTest;

import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.kms.client.KmsClient;
import com.huawei.openstack4j.kms.client.KmsFactory;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

/**
 *
 * @author Super Stone
 * 
 * @date 2017-11-2 18:33:12
 */
public class AbstractKmsSample {

	protected static KmsClient kmsclient;

	@BeforeTest
	public static void initialV3Client() {
		// add override endpoint
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		endpointResolver.addOverrideEndpoint(ServiceType.KEY_MANAGEMENT,
				"https://kms.cn-north-1.myhwclouds.com/v1.0/%(project_id)s");

		String user = "replace-with-your-username";
		String password = "replace-with-your-password";
		String projectId = "67641fe6886f43fcb78edbbf0ad0b99f";
		String userDomainId = "9698542758bc422088c0c3eabfc30d12";
		String authUrl = "https://endpoint-of-iam/v3";

		OSFactory.enableHttpLoggingFilter(true);

		kmsclient = (KmsClient) KmsFactory.builder()
				.withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver).withSSLVerificationDisabled())
				.credentials(user, password, userDomainId, projectId, authUrl);
	}


	protected static String randomName() {
		return "SDK-" + UUID.randomUUID().toString();
	}
}
