package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.PortPairService;
import org.openstack4j.api.networking.ext.VipService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairUpdate;
import org.openstack4j.model.network.ext.Vip;
import org.openstack4j.model.network.ext.VipUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPair;
import org.openstack4j.openstack.networking.domain.ext.NeutronVip;
import org.openstack4j.openstack.networking.domain.ext.NeutronVip.Vips;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPair.PortPairs;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *  OpenStack (Neutron) Lbaas vip based Operations
 * @author Massimiliano Romano
 *
 */
public class PortPairServiceImpl extends BaseNetworkingServices implements
		PortPairService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PortPair> list() {
		return get(PortPairs.class, uri("/sfc/port_pairs")).execute().getList();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PortPair> list(Map<String, String> filteringParams) {
		Invocation<PortPairs> req = get(PortPairs.class, uri("/sfc/port_pairs"));
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
	public PortPair get(String portPairId) {
		checkNotNull(portPairId);
		return get(NeutronPortPair.class,uri("/sfc/port_pairs/%s",portPairId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String portPairId) {
		checkNotNull(portPairId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,uri("/sfc/port_pairs/%s",portPairId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortPair create(PortPair portPair) {
		checkNotNull(portPair);
		return post(NeutronPortPair.class,uri("/sfc/port_pairs")).entity(portPair).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortPair update(String portPairId, PortPairUpdate portPairUpdate) {
		checkNotNull(portPairId);
		checkNotNull(portPairUpdate);
		return put(NeutronPortPair.class,uri("/sfc/port_pairs/%s",portPairId)).entity(portPairUpdate).execute();
	}

}
