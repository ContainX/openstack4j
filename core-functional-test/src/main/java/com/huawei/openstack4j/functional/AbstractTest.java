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
package com.huawei.openstack4j.functional;

import java.util.List;
import java.util.UUID;

import org.testng.annotations.BeforeTest;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.compute.Flavor;
import com.huawei.openstack4j.model.compute.Image;
import com.huawei.openstack4j.model.compute.Keypair;
import com.huawei.openstack4j.model.network.Router;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-10 18:33:12
 */
public class AbstractTest {

	protected static OSClientV3 osclient;
	private Router router;
	private Flavor flavor;
	private Image image;
	private Keypair keypair;

	@BeforeTest
	public static void initialV3Client() {
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
		endpointResolver.addOverrideEndpoint(ServiceType.MAP_REDUCE,
				"https://mrs.eu-de.otc.t-systems.com/v1.1/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.KEY_MANAGEMENT,
				"https://kms.eu-de.otc.t-systems.com/v1.0/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_TRACE,
				"https://cts.eu-de.otc.t-systems.com/v1.0/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.ANTI_DDOS,
				"https://antiddos.eu-de.otc.t-systems.com/v1/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.Notification,
				"https://smn.eu-de.otc.t-systems.com/v2/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.MessageQueue,
				"https://dms.eu-de.otc.t-systems.com/v1.0/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.MAAS,
				"https://maas.eu-de.otc.t-systems.com/v1/%(project_id)s/objectstorage");
		endpointResolver.addOverrideEndpoint(ServiceType.DATABASE, "https://rds.eu-de.otc.t-systems.com");

		// TODO remove authentication before push to github
		String user = "replace-with-your-username";
		String password = "replace-with-your-password";
		String projectId = "d4f2557d248e4860829f5fef030b209c";
		String userDomainId = "bb42e2cd2b784ac4bdc350fb660a2bdb";
		String authUrl = "https://iam.eu-de.otc.t-systems.com/v3";

		OSFactory.enableHttpLoggingFilter(true);
		// with language setting is required for RDS(trove&database) service
		Config config = Config.newConfig().withEndpointURLResolver(endpointResolver).withLanguage("zh-cn")
				.withSSLVerificationDisabled();
		osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();
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

	/**
	 * get first router(VPC) for testing
	 * 
	 * @return
	 */
	protected Router getFirstRouter() {
		if (router == null) {
			router = osclient.networking().router().list().get(0);
		}
		return router;
	}

	protected Flavor getFirstFlavor() {
		if (flavor == null) {
			List<? extends Flavor> flavors = osclient.compute().flavors().list();
			if (flavors == null || flavors.size() == 0) {
				throw new RuntimeException("no flavor available for testing");
			}
			flavor = flavors.get(0);
		}
		return flavor;
	}

	protected Image getFirstImage() {
		if (image == null) {
			List<? extends Image> images = osclient.compute().images().list();
			if (images == null || images.size() == 0) {
				throw new RuntimeException("no image available for testing");
			}
			image = images.get(0);
		}
		return image;
	}

	protected Keypair getFirstKeypair() {
		if (keypair == null) {
			List<? extends Keypair> list = osclient.compute().keypairs().list();
			if (list == null || list.size() == 0) {
				keypair = osclient.compute().keypairs().create("sdk-unittest", null);
			} else {
				keypair = list.get(0);
			}
		}
		return keypair;
	}
}
