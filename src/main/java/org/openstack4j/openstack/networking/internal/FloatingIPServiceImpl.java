package org.openstack4j.openstack.networking.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.networking.NetFloatingIPService;
import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.openstack.networking.domain.NeutronFloatingIP;
import org.openstack4j.openstack.networking.domain.NeutronFloatingIP.FloatingIPs;

/**
 * FloatingIPService implementation that provides Neutron Floating-IP based Service Operations.
 *
 * @author Nathan Anderson
 */
public class FloatingIPServiceImpl extends BaseNetworkingServices implements NetFloatingIPService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends NetFloatingIP> list() {
		return get(FloatingIPs.class, uri("/floatingips")).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NetFloatingIP get(String id) {
		checkNotNull(id);
		return get(NeutronFloatingIP.class, uri("/floatingips/%s", id)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String id) {
		checkNotNull(id);
		delete(Void.class, uri("/floatingips/%s", id)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NetFloatingIP create(NetFloatingIP floatingIp) {
		checkNotNull(floatingIp);
		checkNotNull(floatingIp.getFloatingNetworkId());
		checkNotNull(floatingIp.getPortId());
		return post(NeutronFloatingIP.class, uri("/floatingips")).entity(floatingIp).execute(); 
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public NetFloatingIP associateToPort(String id, String portId) {
    checkNotNull(id);
    checkNotNull(portId);
    String inner = String.format("{ \"port_id\":\"%s\" }", portId);
    String json = String.format("{ \"%s\": %s }", "floatingip", inner);
    return put(NeutronFloatingIP.class, uri("/floatingips/%s",id)).json(json).execute();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public NetFloatingIP disassociateFromPort(String id) {
    checkNotNull(id);
    String json = String.format("{ \"%s\": %s }", "floatingip", "{ \"port_id\":null }");
    return put(NeutronFloatingIP.class, uri("/floatingips/%s",id)).json(json).execute();
  }
}
