package org.openstack4j.model.network;

import org.openstack4j.model.ModelEntity;

/**
 * The Interface FloatingIP.
 * 
 * <p>Copyright (c) 2014 by Dorado Software, Inc. All Rights Reserved.
 *
 * @author nanderson
 */
public interface FloatingIP extends ModelEntity {

  /**
   * Gets the id.
   *
   * @return the id
   */
  public abstract String getId();

  /**
   * Gets the router id.
   *
   * @return the router id
   */
  public abstract String getRouterId();

  /**
   * Gets the tenant id.
   *
   * @return the tenant id
   */
  public abstract String getTenantId();

  /**
   * Gets the floating network id.
   *
   * @return the floating network id
   */
  public abstract String getFloatingNetworkId();

  /**
   * Gets the floating ip address.
   *
   * @return the floating ip address
   */
  public abstract String getFloatingIpAddress();

  /**
   * Gets the fixed ip address.
   *
   * @return the fixed ip address
   */
  public abstract String getFixedIpAddress();

  /**
   * Gets the port id.
   *
   * @return the port id
   */
  public abstract String getPortId();

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public abstract void setId(String id);

  /**
   * Sets the router id.
   *
   * @param routerId the new router id
   */
  public abstract void setRouterId(String routerId);

  /**
   * Sets the tenant id.
   *
   * @param tenantId the new tenant id
   */
  public abstract void setTenantId(String tenantId);

  /**
   * Sets the floating network id.
   *
   * @param floatingNetworkId the new floating network id
   */
  public abstract void setFloatingNetworkId(String floatingNetworkId);

  /**
   * Sets the floating ip address.
   *
   * @param floatingIpAddress the new floating ip address
   */
  public abstract void setFloatingIpAddress(String floatingIpAddress);

  /**
   * Sets the fixed ip address.
   *
   * @param fixedIpAddress the new fixed ip address
   */
  public abstract void setFixedIpAddress(String fixedIpAddress);

  /**
   * Sets the port id.
   *
   * @param portId the new port id
   */
  public abstract void setPortId(String portId);

}