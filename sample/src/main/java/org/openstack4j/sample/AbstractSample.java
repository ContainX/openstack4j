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
package org.openstack4j.sample;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-10 18:33:12
 */
public class AbstractSample {

	protected OSClientV3 osclient;

	@BeforeClass
	public void initialV3Client() {
		// add override endpoint
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		endpointResolver.addOverrideEndpoint(ServiceType.VOLUME_BACKUP,
				"https://vbs.eu-de.otc.t-systems.com/v2/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.AUTO_SCALING,
				"https://as.eu-de.otc.t-systems.com/autoscaling-api/v1/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_EYE,
				"https://ces.eu-de.otc.t-systems.com/V1.0/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.LOAD_BALANCER,
				"https://elb.eu-de.otc.t-systems.com/v1.0/%(project_id)s");
		// endpointResolver.addOverrideEndpoint(ServiceType.DNS,
		// "https://dns.eu-de.otc.t-systems.com/v2/%(project_id)s");

		String user = "replace-with-your-username";
		String password = "replace-with-your-password";
		String projectId = "d4f2557d248e4860829f5fef030b209c";
		String userDomainId = "bb42e2cd2b784ac4bdc350fb660a2bdb";
		String authUrl = "https://iam.eu-de.otc.t-systems.com/v3";
		// TODO remove authentication before push to github
		osclient = OSFactory.builderV3()
				.withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver))
				.endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();
	}
}
