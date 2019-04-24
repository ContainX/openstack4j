package org.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.openstack4j.api.networking.TrunkService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.SubPort;
import org.openstack4j.model.network.Trunk;
import org.openstack4j.openstack.networking.domain.AbstractNeutronTrunk.Trunks;
import org.openstack4j.openstack.networking.domain.NeutronSubPort;
import org.openstack4j.openstack.networking.domain.NeutronSubPort.SubPorts;
import org.openstack4j.openstack.networking.domain.NeutronSubPortCreate;
import org.openstack4j.openstack.networking.domain.NeutronSubPortCreate.NeutronSubPortDelete.NeutronSubPortsDelete;
import org.openstack4j.openstack.networking.domain.NeutronTrunk;
import org.openstack4j.openstack.networking.domain.NeutronTrunkCreate;
import org.openstack4j.openstack.networking.domain.NeutronTrunkSubport;
import org.openstack4j.openstack.networking.domain.NeutronTrunkUpdate;

/**
 * OpenStack Network Trunk operations implementation
 *
 * @author Kashyap Jha
 */
public class TrunkServiceImpl extends BaseNetworkingServices implements TrunkService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Trunk> list() {
        return get(Trunks.class, uri("/trunks")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trunk createTrunk(Trunk trunk) {
        checkNotNull(trunk);
        return post(NeutronTrunk.class, uri("/trunks")).entity(NeutronTrunkCreate.fromTrunk(trunk)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deleteTrunk(String trunkId) {
        checkNotNull(trunkId);
        return deleteWithResponse(uri("/trunks/%s", trunkId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trunk updateTrunk(Trunk trunk, String trunkId) {
        checkNotNull(trunk);
        checkNotNull(trunkId);
        return put(NeutronTrunk.class, uri("/trunks/%s", trunkId)).entity(NeutronTrunkUpdate.update(trunk)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trunk addSubPort(String trunkId, SubPort subPort) {
        checkNotNull(subPort);
        checkNotNull(trunkId);
        List<SubPort> al = new ArrayList<>();
        al.add(subPort);
        return put(NeutronTrunkSubport.class, uri("/trunks/%s/add_subports", trunkId))
                .entity(NeutronSubPortCreate.NeutronSubPortsCreate.fromSubPorts(al)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trunk removeSubPort(String trunkId, String portId) {
        checkNotNull(trunkId);
        checkNotNull(portId);
        List<String> al = new ArrayList<>();
        al.add(portId);
        return put(NeutronTrunkSubport.class, uri("/trunks/%s/remove_subports", trunkId))
                .entity(NeutronSubPortsDelete.delete(al)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NeutronSubPort> listSubPorts(String trunkId) {
        checkNotNull(trunkId);
        return get(SubPorts.class, uri("/trunks/%s/get_subports", trunkId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trunk get(String trunkId) {
        checkNotNull(trunkId);
        return get(NeutronTrunk.class, uri("/trunks/%s", trunkId)).execute();
    }

}
