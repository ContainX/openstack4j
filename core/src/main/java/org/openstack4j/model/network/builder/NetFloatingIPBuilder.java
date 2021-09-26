package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.NetFloatingIP;

/**
 * Builder for a FloatingIP model class.
 *
 * @author Nathan Anderson
 */
public interface NetFloatingIPBuilder extends Builder<NetFloatingIPBuilder, NetFloatingIP> {
	
	/**
	 * sets Id of floating network
	 *
	 * @param networkId the network id
	 * @return the floating ip builder
	 */
	NetFloatingIPBuilder floatingNetworkId(String networkId);
  
  /**
   * Port id.
   *
   * @param portId the port id
   * @return the floating ip builder
   */
  NetFloatingIPBuilder portId(String portId);

	/**
	 * floatingIpAddress fip.
	 *
	 * @param floatingIpAddress the floatingIp address
	 * @return the floating ip builder
	 */
  NetFloatingIPBuilder floatingIpAddress(String floatingIpAddress);
	
}
