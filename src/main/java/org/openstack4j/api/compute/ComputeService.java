package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.Extension;

/**
 * Compute (Nova) Operations API
 * 
 * @author Jeremy Unruh
 */
public interface ComputeService extends RestService {

	/**
	 * Flavor Service API
	 *
	 * @return the flavor service
	 */
	FlavorService flavors();
	
	/**
	 * Image Service API
	 *
	 * @return the image service
	 */
	ComputeImageService images();
	
	/**
	 * Server Service API
	 *
	 * @return the server service
	 */
	ServerService servers();
	
	/**
	 * Quota-Set Service API
	 *
	 * @return the quota set service
	 */
	QuotaSetService quotaSets();
	
	/**
	 * @return a list of Extensions that have been added against the Compute service
	 */
	List<? extends Extension> listExtensions();
	
}
