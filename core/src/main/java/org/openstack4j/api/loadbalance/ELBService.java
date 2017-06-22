package org.openstack4j.api.loadbalance;

import org.openstack4j.common.RestService;

public interface ELBService extends RestService {
	ELBLoadBalancerService loadBalancers();
	
	AsyncJobService jobs();
}
