package org.openstack4j.api.compute.ext;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ext.Hypervisor;
import org.openstack4j.model.compute.ext.HypervisorStatistics;

/**
 * API which supports the "os-hypervisors" extension.  For more details
 * @See http://developer.openstack.org/api-ref-compute-v2-ext.html#ext-os-hypervisors
 * 
 * @author Jeremy Unruh
 */
public interface HypervisorService extends RestService {

	  /**
	   * The Hypervisors for this OpenStack deployment.  
	   * 
	   * NOTE: This is an extension and not all deployments support os-hypervisors
	   * 
	   * @return the available hypervisors in detail
	   */
		List<? extends Hypervisor> list();
		
		/**
		 * The Hypervisor Statistics for this OpenStack Deployment
		 * 
		 * NOTE: This is an extension and not all deployments support os-hypervisors
		 * 
		 * @return the hypervisor statistics
		 */
		HypervisorStatistics statistics();
	
}
