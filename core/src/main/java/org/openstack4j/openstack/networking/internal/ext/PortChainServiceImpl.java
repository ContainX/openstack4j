package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.PortChainService;
import org.openstack4j.api.networking.ext.PortPairGroupService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortChain;

import org.openstack4j.model.network.ext.PortChainUpdate;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.model.network.ext.PortPairGroupUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortChain;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortChain.PortChains;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPairGroup;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPairGroup.PortPairGroups;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *  OpenStack (Neutron) Port Chain based Operations
 * @author Massimiliano Romano
 *
 */
public class PortChainServiceImpl extends BaseNetworkingServices implements
		PortChainService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PortChain> list() {
		return get(PortChains.class, uri("/sfc/port_chains")).execute().getList();
}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PortChain> list(Map<String, String> filteringParams) {
		Invocation<PortChains> req = get(PortChains.class, uri("/sfc/port_chains"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortChain get(String portChainId) {
		checkNotNull(portChainId);
		return get(NeutronPortChain.class,uri("/sfc/port_chains/%s",portChainId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String portChainId) {
		checkNotNull(portChainId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,uri("/sfc/port_chains/%s",portChainId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortChain create(PortChain portChain) {
		checkNotNull(portChain);
		return post(NeutronPortChain.class,uri("/sfc/port_chains")).entity(portChain).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortChain update(String portChainId, PortChainUpdate portChainUpdate) {
		checkNotNull(portChainId);
		checkNotNull(portChainUpdate);
		return put(NeutronPortChain.class,uri("/sfc/port_chains/%s",portChainId)).entity(portChainUpdate).execute();
	}


}
