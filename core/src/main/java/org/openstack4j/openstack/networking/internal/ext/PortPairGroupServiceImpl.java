package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.PortPairGroupService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.model.network.ext.PortPairGroupUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPairGroup.PortPairGroups;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPairGroup;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *  OpenStack (Neutron) Port Pair Group based Operations
 * @author Massimiliano Romano
 *
 */
public class PortPairGroupServiceImpl extends BaseNetworkingServices implements
		PortPairGroupService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PortPairGroup> list() {
		return get(PortPairGroups.class, uri("/sfc/port_pair_groups")).execute().getList();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends PortPairGroup> list(Map<String, String> filteringParams) {
		Invocation<PortPairGroups> req = get(PortPairGroups.class, uri("/sfc/port_pair_groups"));
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
	public PortPairGroup get(String portPairGroupId) {
		checkNotNull(portPairGroupId);
		return get(NeutronPortPairGroup.class,uri("/sfc/port_pair_groups/%s",portPairGroupId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String portPairGroupId) {
		checkNotNull(portPairGroupId);
		return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,uri("/sfc/port_pair_groups/%s",portPairGroupId)).executeWithResponse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortPairGroup create(PortPairGroup portPairGroup) {
		checkNotNull(portPairGroup);
		return post(NeutronPortPairGroup.class,uri("/sfc/port_pair_groups")).entity(portPairGroup).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PortPairGroup update(String portPairGroupId, PortPairGroupUpdate portPairGroupUpdate) {
		checkNotNull(portPairGroupId);
		checkNotNull(portPairGroupUpdate);
		return put(NeutronPortPairGroup.class,uri("/sfc/port_pair_groups/%s",portPairGroupId)).entity(portPairGroupUpdate).execute();
	}

}
