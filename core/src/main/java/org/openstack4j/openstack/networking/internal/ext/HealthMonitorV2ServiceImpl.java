package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.HealthMonitorV2Service;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.HealthMonitorV2;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

/**
 * OpenStack (Neutron) lbaas v2 health monitor operations
 * @author emjburns
 */
public class HealthMonitorV2ServiceImpl extends BaseNetworkingServices implements HealthMonitorV2Service{
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends HealthMonitorV2> list(){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends HealthMonitorV2> list(Map<String, String> filteringParams){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2 get(String healthMonitorId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String healthMonitorId){
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2 create(HealthMonitorV2 healthMonitor){
        return null;
    }
}
