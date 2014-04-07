package org.openstack4j.api.networking;

import java.util.List;

import org.openstack4j.common.RestService;

import org.openstack4j.model.network.NetFloatingIP;


/**
 * Provides Neutron-based FloatingIP services.
 *
 * @author Nathan Anderson
 */
public interface NetFloatingIPService extends RestService {

  /**
   * Returns list of floating IPs.
   *
   * @return List of NetFloatingIPs or empty
   */
  List<? extends NetFloatingIP> list();
  
  /**
   * Gets a NetFloatingIP by id.
   *
   * @param id the floating-ip identifier
   * @return the NetFloatingIP
   */
  NetFloatingIP get(String id);
  
  
  /**
   * Deletes NetFloatingIP by id.
   *
   * @param id the id
   */
  void delete(String id);
  
  
  /**
   * Creates the.
   *
   * @param floatingIp the floating ip
   * @return the net floating ip
   */
  NetFloatingIP create(NetFloatingIP floatingIp);
  
  
  /**
   * Associate to port.
   *
   * @param floatingIp the floating ip
   * @return the net floating ip
   */
  NetFloatingIP associateToPort(String id, String portId);
  
  
  /**
   * Disassociate from port.
   *
   * @param floatingIp the floating ip
   * @return the net floating ip
   */
  NetFloatingIP disassociateFromPort(String id);
  
}
