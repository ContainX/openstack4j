package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import com.google.common.base.Function;

/**
 * Base Networking Operations Implementation is responsible for insuring the proper endpoint is used for all extending operation APIs
 * 
 * @author Jeremy Unruh
 */
public class BaseNetworkingServices extends BaseOpenStackService {

	protected BaseNetworkingServices() {
		super(ServiceType.NETWORK, EndpointFunction.instance);
	}

	/**
	 * Due to a glitch in the Access returned from some installations the Nova endpoint will not contain the version.  This allows us to inject it into the URL if it isn't defined
	 */
	private static class EndpointFunction implements Function<String, String> {

		static final EndpointFunction instance = new EndpointFunction();
		
		@Override
		public String apply(String input) {
			if (input == null || input.contains("/v"))
				return input;
			return input.concat(input.endsWith("/") ? "v2.0" : "/v2.0");
		}
	}
	
}
