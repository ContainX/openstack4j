package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.NetFloatingIPService;
import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.networking.PortService;
import org.openstack4j.api.networking.RouterService;
import org.openstack4j.api.networking.SecurityGroupRuleService;
import org.openstack4j.api.networking.SecurityGroupService;
import org.openstack4j.api.networking.SubnetService;
import org.openstack4j.api.networking.ext.HealthMonitorService;
import org.openstack4j.api.networking.ext.LbPoolService;
import org.openstack4j.api.networking.ext.MemberService;
import org.openstack4j.api.networking.ext.NetQuotaService;
import org.openstack4j.api.networking.ext.VipService;

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
    public MemberService member(){
    	return Apis.get(MemberService.class);
    }
    /**
     * {@inheritDoc}
     */
	@Override
	public VipService vip() {
		return Apis.get(VipService.class);
	}
	
    /**
     * {@inheritDoc}
     */
	@Override
	public HealthMonitorService healthMonitor() {
		return Apis.get(HealthMonitorService.class);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public LbPoolService lbPool() {
		// TODO Auto-generated method stub
		return Apis.get(LbPoolService.class);
	}

	
}
