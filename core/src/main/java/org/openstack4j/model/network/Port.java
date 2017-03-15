package org.openstack4j.model.network;

import java.util.List;
import java.util.Set;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.PortBuilder;

/**
 * A Network (Neutron) Port
 * 
 * @author Jeremy Unruh
 */
public interface Port extends Resource, Buildable<PortBuilder> {

	 /**
   * @return the current state of the port
   */
  State getState();

  /**
   * @return the administrative state of port. If false, port does not forward packets.
   */
  boolean isAdminStateUp();

  /**
   * @return the id of the network where this port is associated with
   */
  String getNetworkId();

  /**
   * @return the id of the device (e.g. server) using this port.
   */
  String getDeviceId();

  /**
   * @return the entity (e.g.: DHCP Agent) using this port.
   */
  String getDeviceOwner();

  /**
   * @return the set of fixed IPs this port has been assigned
   */
  Set<? extends IP> getFixedIps();
  
  Set<? extends AllowedAddressPair> getAllowedAddressPairs();

  /**
   * @return the MacAddress of this port
   */
  String getMacAddress();
  
  /**
   * @return the security group identifiers assigned to this port
   */
  List<String> getSecurityGroups();

  /**
   * @return The port security status. A valid value is enabled (true) or disabled (false).
   */
  Boolean isPortSecurityEnabled();

  /**
   * @return The type of vNIC which this port should be attached to. This is used to determine which mechanism driver(s) to be used to bind the port. The valid values are normal, macvtap, direct, baremetal, and direct-physical. What type of vNIC is actually available depends on deployments.
   */
  String  getVnicType();
	
}
