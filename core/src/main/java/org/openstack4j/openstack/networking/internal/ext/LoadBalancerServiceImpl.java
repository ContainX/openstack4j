package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.ext.HealthMonitorService;
import org.openstack4j.api.networking.ext.LbPoolService;
import org.openstack4j.api.networking.ext.LoadBalancerService;
import org.openstack4j.api.networking.ext.MemberService;
import org.openstack4j.api.networking.ext.VipService;

/**
 * LBaaS Service Implementation
 */
public class LoadBalancerServiceImpl implements LoadBalancerService {

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
        return Apis.get(LbPoolService.class);
    }

}
