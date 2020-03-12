package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.AvailabilityZoneService;
import org.openstack4j.api.networking.NetFloatingIPService;
import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.networking.PortService;
import org.openstack4j.api.networking.RouterService;
import org.openstack4j.api.networking.SecurityGroupRuleService;
import org.openstack4j.api.networking.SecurityGroupService;
import org.openstack4j.api.networking.SubnetService;
import org.openstack4j.api.networking.ext.AgentService;
import org.openstack4j.api.networking.ext.FirewallAsService;
import org.openstack4j.api.networking.ext.LbaasV2Service;
import org.openstack4j.api.networking.ext.LoadBalancerService;
import org.openstack4j.api.networking.ext.NetQuotaService;

/**
 * OpenStack Networking Operations API
 * 
 * @author Jeremy Unruh
 */
public class NetworkingServiceImpl implements NetworkingService {

    /**
     * {@inheritDoc}
     */
    @Override
    public NetworkService network() {
        return Apis.get(NetworkService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubnetService subnet() {
        return Apis.get(SubnetService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortService port() {
        return Apis.get(PortService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RouterService router() {
        return Apis.get(RouterService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIPService floatingip() {
        return Apis.get(NetFloatingIPService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroupService securitygroup() {
        return Apis.get(SecurityGroupService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecurityGroupRuleService securityrule() {
        return Apis.get(SecurityGroupRuleService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQuotaService quotas() {
        return Apis.get(NetQuotaService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadBalancerService loadbalancers() {
        return Apis.get(LoadBalancerService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LbaasV2Service lbaasV2(){
        return Apis.get(LbaasV2Service.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FirewallAsService firewalls() {
        return Apis.get(FirewallAsService.class);
    }	

    /**
     * {@inheritDoc}
     */
    @Override
    public AgentService agent() {
        return Apis.get(AgentService.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public AvailabilityZoneService availabilityzone() {
        return Apis.get(AvailabilityZoneService.class);
    }	

}
