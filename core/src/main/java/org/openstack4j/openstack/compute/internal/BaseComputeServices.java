package org.openstack4j.openstack.compute.internal;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.internal.BaseOpenStackService;

/**
 * Base class for Computer / Nova services
 * 
 * @author Jeremy Unruh
 */
public class BaseComputeServices extends BaseOpenStackService {

	protected BaseComputeServices() {
		super(ServiceType.COMPUTE);
	}
	
}
