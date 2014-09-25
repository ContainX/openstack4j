package org.openstack4j.api.heat;

import org.openstack4j.common.RestService;

/**
 * This interface containts all available HeatServices
 * @author Matthias Reisser
 *
 */
public interface HeatService extends RestService{
	
	/**
	 * returns the service implementation which provides methods for manipulation of stacks
	 * @return service
	 */
	StackService stacks();
	
	TemplateService templates();
	
	EventsService events();
	
	ResourcesService resources();
}
