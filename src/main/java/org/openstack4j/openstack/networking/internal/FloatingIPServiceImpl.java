package org.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.FloatingIPService;
import org.openstack4j.model.network.FloatingIP;
import org.openstack4j.openstack.networking.domain.NeutronFloatingIP;
import org.openstack4j.openstack.networking.domain.NeutronFloatingIP.FloatingIPs;

/**
 * FloatingIPService implementation that provides Neutron Floating-IP based Service Operations.
 *
 * @author Nathan Anderson
 */
public class FloatingIPServiceImpl extends BaseNetworkingServices implements FloatingIPService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends FloatingIP> list() {
		return get(FloatingIPs.class, uri("/floatingips")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FloatingIP get(String id) {
		checkNotNull(id);
		return get(NeutronFloatingIP.class, uri("/floatingips/%s", id)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String routerId) {
		checkNotNull(routerId);
		delete(Void.class, uri("/routers/%s", routerId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FloatingIP create(FloatingIP floatingIp) {
		checkNotNull(floatingIp);
		//checkNotNull(floatingIp.getPortId());
		return post(NeutronFloatingIP.class, uri("/floatingip")).entity(floatingIp).execute(); 
	}

	/**
	 * {@inheritDoc}
	 */
	/*
	@Override
	public FloatingIP update(FloatingIP floatingIp) {
		checkNotNull(floatingIp);
		checkNotNull(floatingIp.getId());
		
		
		return put(NeutronFloatingIP.class, uri("/floatingip/%s", floatingIp.getId()))
				     .entity(NeutronRouter.builder().)
				     .execute();
	} */

}
