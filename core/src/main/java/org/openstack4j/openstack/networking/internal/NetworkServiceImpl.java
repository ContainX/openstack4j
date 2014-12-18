package org.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.NetworkService;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.NetworkUpdate;
import org.openstack4j.openstack.networking.domain.NeutronNetwork;
import org.openstack4j.openstack.networking.domain.NeutronNetwork.Networks;

/**
 * OpenStack (Neutron) Network based Operations
 * 
 * @author Jeremy Unruh
 */
public class NetworkServiceImpl extends BaseNetworkingServices implements NetworkService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Network> list() {
		return get(Networks.class, uri("/networks")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String networkId) {
		checkNotNull(networkId);
		return deleteWithResponse(uri("/networks/%s", networkId)).execute();
	}

	@Override
	public Network get(String networkId) {
		checkNotNull(networkId);
		return get(NeutronNetwork.class, uri("/networks/%s", networkId)).execute();
	}

	@Override
	public Network create(Network network) {
		checkNotNull(network);
		return post(NeutronNetwork.class, uri("/networks")).entity(network).execute();
	}

    @Override
    public Network update(String networkId, NetworkUpdate network) {
        checkNotNull(networkId);
        checkNotNull(network, "network");
        return put(NeutronNetwork.class, uri("/networks/%s", networkId)).entity(network).execute();
    }

}
