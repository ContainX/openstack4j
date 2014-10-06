package org.openstack4j.openstack.image.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import com.google.common.base.Function;

/**
 * Base Image Operations Implementation is responsible for insuring the proper endpoint is used for all extending operation APIs
 * 
 * @author Jeremy Unruh
 */
public class BaseImageServices extends BaseOpenStackService {

	protected BaseImageServices() {
		super(ServiceType.IMAGE, EndpointFunction.instance);
	}
	
	/**
	 * We support the V1.1 OpenStack API for Glance so we need to insure we access that endpoint.  When we add support for V2 this can simply insure we
	 * are accessing the proper version endpoint
	 */
	private static class EndpointFunction implements Function<String, String> {

		static final EndpointFunction instance = new EndpointFunction();
		
		@Override
		public String apply(String input) {
			if (input != null)
			{
			  if (input.contains("/v2"))
				  return input.replace("/v2", "/v1");
			  else if (!input.contains("/v"))
			  	return input.concat("/v1");
			}
			return input;
		}
	}
	
}
