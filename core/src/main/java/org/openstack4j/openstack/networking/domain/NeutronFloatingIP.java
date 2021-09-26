package org.openstack4j.openstack.networking.domain;

import java.util.List;

import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.model.network.builder.NetFloatingIPBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

/**
 * An OpenStack Neutron Floating IP Model.
 *
 * @author Nathan Anderson
 */
@JsonRootName("floatingip")
public class NeutronFloatingIP implements NetFloatingIP {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
  private String id;

  @JsonProperty("router_id")
  private String routerId;

  @JsonProperty("tenant_id")
  private String tenantId;

  @JsonProperty("floating_network_id")
  private String floatingNetworkId;

  @JsonProperty("floating_ip_address")
  private String floatingIpAddress;

  @JsonProperty("fixed_ip_address")
  private String fixedIpAddress;

  @JsonProperty("port_id")
  private String portId;

  private String status;

  /**
   * {@inheritDoc}
   */
  @Override
  public NetFloatingIPBuilder toBuilder() {
    return new FloatingIPConcreteBuilder(this);
  }

  /**
   * Builder.
   *
   * @return the net floating ip builder
   */
  public static NetFloatingIPBuilder builder() {
    return new FloatingIPConcreteBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    return this.id;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getRouterId() {
    return this.routerId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTenantId() {
    return this.tenantId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getFloatingNetworkId() {
    return this.floatingNetworkId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getFloatingIpAddress() {
    return this.floatingIpAddress;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getFixedIpAddress() {
    return this.fixedIpAddress;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPortId() {
    return this.portId;
  }

  /**
   * {@inheritDoc}
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   */
  public void setRouterId(String routerId) {
    this.routerId = routerId;
  }

  /**
   * {@inheritDoc}
   */
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  /**
   * {@inheritDoc}
   */
  public void setFloatingNetworkId(String floatingNetworkId) {
    this.floatingNetworkId = floatingNetworkId;
  }

  /**
   * {@inheritDoc}
   */
  public void setFloatingIpAddress(String floatingIpAddress) {
    this.floatingIpAddress = floatingIpAddress;
  }

  /**
   * {@inheritDoc}
   */
  public void setFixedIpAddress(String fixedIpAddress) {
    this.fixedIpAddress = fixedIpAddress;
  }

  /**
   * {@inheritDoc}
   */
  public void setPortId(String portId) {
    this.portId = portId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getStatus() {
	return status;
  }

  /**
   * {@inheritDoc}
   */
  public void setStatus(String status) {
	this.status = status;
  }

/**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).omitNullValues()
            .add("id", id).add("routerId", routerId).add("tenantId", tenantId).add("floatingNetworkId", floatingNetworkId)
            .add("floatingIpAddress", floatingIpAddress).add("fixedIpAddress", fixedIpAddress).add("portId", portId).add("status", status)
            .addValue("\n")
            .toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, routerId, tenantId, floatingNetworkId,
            floatingIpAddress, fixedIpAddress, portId, status);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof NeutronFloatingIP) {
      NeutronFloatingIP that = (NeutronFloatingIP) obj;
      if (java.util.Objects.equals(id, that.id) &&
              java.util.Objects.equals(routerId, that.routerId) &&
              java.util.Objects.equals(tenantId, that.tenantId) &&
              java.util.Objects.equals(floatingNetworkId, that.floatingNetworkId) &&
              java.util.Objects.equals(floatingIpAddress, that.floatingIpAddress) &&
              java.util.Objects.equals(fixedIpAddress, that.fixedIpAddress) &&
              java.util.Objects.equals(portId, that.portId) &&
              java.util.Objects.equals(status, that.status)) {
        return true;
      }
    }
    return false;
  }

  /**
   * The Class FloatingIPs.
   *
   *
   * @author Nathan Anderson
   */
  public static class FloatingIPs extends ListResult<NeutronFloatingIP> {

    private static final long serialVersionUID = 1L;

    @JsonProperty("floatingips")
    private List<NeutronFloatingIP> floatingIps;

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<NeutronFloatingIP> value() {
      return floatingIps;
    }
  }

  /**
   * The Class FloatingIPConcreteBuilder.
   *
   *
   * @author Nathan Anderson
   */
  public static class FloatingIPConcreteBuilder implements NetFloatingIPBuilder {

    private NeutronFloatingIP f = null;

    /**
     * Instantiates a new floating ip concrete builder.
     */
    public FloatingIPConcreteBuilder() {
      f = new NeutronFloatingIP();
    }

    /**
     * Instantiates a new floating ip concrete builder.
     *
     * @param in the in
     */
    public FloatingIPConcreteBuilder(NetFloatingIP in) {
      f = (NeutronFloatingIP) in;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIP build() {
      return f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIPBuilder from(NetFloatingIP in) {
      f = (NeutronFloatingIP) in;
      return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIPBuilder floatingNetworkId(String networkId) {
      f.floatingNetworkId = networkId;
      return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetFloatingIPBuilder portId(String portId) {
      f.portId = portId;
      return this;
    }

    @Override
    public NetFloatingIPBuilder floatingIpAddress(String floatingIpAddress) {
      f.floatingIpAddress = floatingIpAddress;
      return this;
    }
  }


}
