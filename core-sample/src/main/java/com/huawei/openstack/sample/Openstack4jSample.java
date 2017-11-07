package com.huawei.openstack.sample;

import com.huawei.openstack4j.api.Builders;
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

		// step 2: setup the authentication credit
		String user = "replace-with-your-username";
		String password = "replace-with-your-password";
		String projectId = "replace-with-your-project-id";
		String userDomainId = "replace-with-your-user-domain-id";
		String authUrl = "https://iam.eu-de.otc.t-systems.com/v3";

		// step 3: initial OpenStack4j Client
		OSFactory.enableHttpLoggingFilter(true);
		// config of the client
		// with language setting is required for RDS(trove&database) service
		Config config = Config.newConfig().withEndpointURLResolver(endpointResolver).withLanguage(LANGUAGE)
				.withSSLVerificationDisabled();
		// initial client
		OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
				.credentials(user, password, Identifier.byId(userDomainId)).scopeToProject(Identifier.byId(projectId))
				.authenticate();

		// use client to visit DNS API
		
		// create a public zone
		Zone creation = Builders.zone().name("sdk.tutorial.com.").description("tutorial").ttl(600)
				.email("admin@tutorial.com").build();
		Zone zone = osclient.dns().zones().create(creation);
		
		// get the created zone
		Zone get = osclient.dns().zones().get(zone.getId());
		
		// delete the created zone
		osclient.dns().zones().delete(get.getId());
	}

}
