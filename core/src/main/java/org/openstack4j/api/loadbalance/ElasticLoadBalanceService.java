package org.openstack4j.api.loadbalance;

import org.openstack4j.common.RestService;

public interface ElasticLoadBalanceService extends RestService {
	ElasticLoadBalancerService loadBalancers();
	
	ElasticAsyncJobService jobs();
}
