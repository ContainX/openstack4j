package org.openstack4j.api.networking;

import java.util.List;

import org.openstack4j.common.RestService;

import org.openstack4j.model.network.FloatingIP;


/**
 * Provides Neutron-based FloatingIP services
 * 
 *
 * @author nanderson
 */
public interface FloatingIPService extends RestService {

  /**
   * Returns list of floating IPs 
   *
   * @return List of FloatingIPs or empty 
   */
  List<? extends FloatingIP> list();
  
  /**
   * Gets a FloatingIP by id 
   *
   * @param id the floating-ip identifier
   * @return the FloatingIP
   */
  FloatingIP get(String id);
  
  /**
   * Deletes FloatingIP by id
   *
   * @param id floating-ip identifier id
   */
  void delete(String id);
  
  /**
   * Creates the.
   *
   * @param floatingIp floating ip
   */
  FloatingIP create(FloatingIP floatingIp);
  
  /**
   * Update.
   *
   * @param floatingIp the floating ip
   */
  //FloatingIP update(FloatingIP floatingIp);
  
  
  //FloatingIP associateToPort();
  
  //FloatingIP disassociateFromPort();
  
}
