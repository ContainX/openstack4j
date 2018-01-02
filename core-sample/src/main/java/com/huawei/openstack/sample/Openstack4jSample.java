package com.huawei.openstack.sample;

import java.util.List;

import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.dns.v2.Zone;
import com.huawei.openstack4j.openstack.OSFactory;
import com.huawei.openstack4j.openstack.identity.internal.OverridableEndpointURLResolver;

/**
 *
 * @author QianBiao.NG
 * @date   2017-10-04 21:41:29
 */
public class Openstack4jSample {

	private static final String LANGUAGE = "zh-cn";

	public static void main(String[] args) {

		                                                                                                 
		// step 1: add cloud service override endpoint
		OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		// "example" in the endpoint stands for "Region.Cloud"
		endpointResolver.addOverrideEndpoint(ServiceType.DNS, "https://dns.example.com");
		
		// ========================================================================================== // 
		// those services's endpoint will be auto detected from V3 authentication token               // 
		//                                                                                            // 
		//       Nova         ->   ECS                                                                // 
		//       Cinder       ->   EVS                                                                // 
		//       Neutron      ->   VPC                                                                // 
		//       Keystone     ->   IAM                                                                // 
		//       Glance       ->   IMS                                                                // 
		//       Heat         ->   RTS                                                                // 
		//                                                                                            // 
		// so, we do not need to setup the endpoint override for them.                                // 
		// ========================================================================================== // 

		// endpoint override for the other service
		// "example" in the endpoint stands for "Region.Cloud"
		/*
		 * endpointResolver.addOverrideEndpoint(ServiceType.VOLUME_BACKUP,
		 * "https://vbs.example.com/v2/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.AUTO_SCALING,
		 * "https://as.example.com/autoscaling-api/v1/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_EYE,
		 * "https://ces.example.com/V1.0/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.LOAD_BALANCER,
		 * "https://elb.example.com/v1.0/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.MAP_REDUCE,
		 * "https://mrs.example.com/v1.1/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.KEY_MANAGEMENT,
		 * "https://kms.example.com/v1.0/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_TRACE,
		 * "https://cts.example.com/v1.0/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.ANTI_DDOS,
		 * "https://antiddos.example.com/v1/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.Notification,
		 * "https://smn.example.com/v2/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.MessageQueue,
		 * "https://dms.example.com/v1.0/%(project_id)s");
		 * endpointResolver.addOverrideEndpoint(ServiceType.MAAS,
		 * "https://maas.example.com/v1/%(project_id)s/objectstorage");
		 * endpointResolver.addOverrideEndpoint(ServiceType.DATABASE, "https://rds.example.com");
		 */

		// step 2: setup the authentication credit
		String user = "replace-with-your-username";
		String password = "replace-with-your-password";
		String projectId = "replace-with-your-project-id";
		String userDomainId = "replace-with-your-user-domain-id";
		// "example" in the endpoint stands for "Region.Cloud"
		String authUrl = "https://iam.example.com/v3";

		// step 3: initial OpenStack4j Client
		OSFactory.enableHttpLoggingFilter(true);
		// config of the client
		// with language setting is required for RDS(trove&database) service
		// withSSLVerificationDisabled is required if the SSL certification of the cloud service is illegal
		Config config = Config.newConfig().withEndpointURLResolver(endpointResolver).withLanguage(LANGUAGE)
				.withSSLVerificationDisabled();

		// initial client
		OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToDomain(Identifier.byId(userDomainId))
				.scopeToProject(Identifier.byId(projectId)).authenticate();

		// Use client to visit DNS list zone API
		List<? extends Zone> list = osclient.dns().zones().list();
		System.out.println(list);
	}

}
