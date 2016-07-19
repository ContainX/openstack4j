package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.LoadBalancerV2Service;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.LoadBalancerV2;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

/**
 * Openstack (Neutron) lbaas v2 load balancer operations
 * @author emjburns
 */
public class LoadBalancerV2ServiceImpl extends BaseNetworkingServices implements LoadBalancerV2Service{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LoadBalancerV2> list(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends LoadBalancerV2> list(Map<String, String> filteringParams){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2 get(String loadbalancerId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String loadbalancerId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2 create(LoadBalancerV2 loadbalancer){
        return null;
    }
}
