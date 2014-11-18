package org.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.compute.ComputeFloatingIPService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.FloatingIP;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.common.MapEntity;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP.NovaFloatingIPs;
import org.openstack4j.openstack.compute.domain.NovaFloatingIPPools;
import org.openstack4j.openstack.compute.domain.actions.FloatingIpActions;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;

/**
 * OpenStack Floating-IP API Implementation
 * 
 * @author Nathan Anderson
 */
public class ComputeFloatingIPServiceImpl extends BaseComputeServices implements ComputeFloatingIPService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends FloatingIP> list() {
        return get(NovaFloatingIPs.class, uri("/os-floating-ips")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getPoolNames() {
        return get(NovaFloatingIPPools.class, uri("/os-floating-ip-pools")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FloatingIP allocateIP(String pool) {
        return post(NovaFloatingIP.class, uri("/os-floating-ips")).entity(MapEntity.create("pool", pool)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deallocateIP(String id) {
        checkNotNull(id);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/os-floating-ips/%s", id)).executeWithResponse()
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addFloatingIP(Server server, String fixedIpAddress, String ipAddress) {
        checkNotNull(server);
        checkNotNull(ipAddress);

        return invokeAction(server.getId(), FloatingIpActions.Add.create(ipAddress, fixedIpAddress));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addFloatingIP(Server server, String ipAddress) {
        return addFloatingIP(server, null,  ipAddress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse removeFloatingIP(Server server, String ipAddress) {
        checkNotNull(server);
        checkNotNull(ipAddress);

        return invokeAction(server.getId(), FloatingIpActions.Remove.create(ipAddress));
    }

}
