package org.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.PortService;
import org.openstack4j.model.network.Port;
import org.openstack4j.openstack.networking.domain.NeutronPort;
import org.openstack4j.openstack.networking.domain.NeutronPort.Ports;

/**
 * OpenStack (Neutron) Port based Operations Implementation
 * 
 * @author Jeremy Unruh
 */
public class PortServiceImpl extends BaseNetworkingServices implements PortService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Port> list() {
		return get(Ports.class, uri("/ports")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Port get(String portId) {
		checkNotNull(portId);
		return get(NeutronPort.class, uri("/ports/%s", portId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String portId) {
		checkNotNull(portId);
		delete(Void.class, uri("/ports/%s", portId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Port create(Port port) {
		checkNotNull(port);
		return post(NeutronPort.class, uri("/ports")).entity(port).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Port update(Port port) {
		checkNotNull(port);
		checkNotNull(port.getId());
		return put(NeutronPort.class, uri("/ports")).entity(port).execute();
	}

}
