package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.HealthMonitorV2Service;
import org.openstack4j.api.networking.ext.LbPoolV2Service;
import org.openstack4j.api.networking.ext.LbaasV2Service;
import org.openstack4j.api.networking.ext.ListenerService;
import org.openstack4j.api.networking.ext.LoadBalancerV2Service;
import org.openstack4j.api.networking.ext.MemberV2Service;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * OpenStack (Neutron) lbaas v2 lb pool operations
 * @author emjburns
 */
public class LbPoolV2ServiceImpl extends BaseNetworkingServices implements LbaasV2Service{
    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerV2Service loadbalancerV2(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2Service healthMonitorV2(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbPoolV2Service lbPoolV2(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerService listener(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberV2Service memberV2(){
        return null;
    }
}
