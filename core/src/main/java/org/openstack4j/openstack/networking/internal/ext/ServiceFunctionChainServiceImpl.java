package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.Apis;
import org.openstack4j.api.networking.ext.FlowClassifierService;
import org.openstack4j.api.networking.ext.PortChainService;
import org.openstack4j.api.networking.ext.PortPairGroupService;
import org.openstack4j.api.networking.ext.PortPairService;
import org.openstack4j.api.networking.ext.ServiceFunctionChainService;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * {@inheritDoc}
 */
public class ServiceFunctionChainServiceImpl extends BaseNetworkingServices implements ServiceFunctionChainService {

    /**
     * {@inheritDoc}
     */
    @Override
    public FlowClassifierService flowclassifiers() {
        return Apis.get(FlowClassifierService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortPairService portpairs() {
        return Apis.get(PortPairService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortPairGroupService portpairgroups() {
        return Apis.get(PortPairGroupService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortChainService portchains() {
        return Apis.get(PortChainService.class);
    }
}
