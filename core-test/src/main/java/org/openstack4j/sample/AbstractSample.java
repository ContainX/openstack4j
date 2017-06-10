/*******************************************************************************
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
