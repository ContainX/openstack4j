package org.openstack4j.openstack.image.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.RemoveVersionFromURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import com.google.common.base.Function;

/**
 * Base Image Operations Implementation is responsible for insuring the proper endpoint is used for all extending operation APIs
 * 
 * @author Jeremy Unruh
 */
public class BaseImageServices extends BaseOpenStackService {

	protected BaseImageServices() {
		super(ServiceType.IMAGE, EndpointFunction.INSTANCE);
	}
	
	/**
	 * We support the V1.1 OpenStack API for Glance so we need to insure we access that endpoint.  When we add support for V2 this can simply insure we
	 * are accessing the proper version endpoint
	 */
	private static class EndpointFunction implements Function<String, String> {

		static final EndpointFunction INSTANCE = new EndpointFunction();

		@Override
		public String apply(String input) {
			if (input != null)
			{
			  String url = RemoveVersionFromURL.INSTANCE.apply(input);
			  return url.concat("/v1");
			}
			return input;
		}
	}
}
