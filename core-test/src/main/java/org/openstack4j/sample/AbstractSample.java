/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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

	OSClientV3 osc;

	@BeforeClass
	public void initialV3Client() {
		// add override endpoint
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_VOLUME_BACKUP,
				"https://vbs.eu-de.otc.t-systems.com/v2/%(project_id)s");

		osc = OSFactory.builderV3().withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver))
				.endpoint("https://iam.eu-de.otc.t-systems.com/v3")
				.credentials("14783667 OTC00000000001000000680", "eSpace7850",
						Identifier.byId("bb42e2cd2b784ac4bdc350fb660a2bdb"))
				.scopeToProject(Identifier.byId("d4f2557d248e4860829f5fef030b209c")).authenticate();
	}
}
