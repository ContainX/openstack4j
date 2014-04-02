package org.openstack4j.openstack.networking.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openstack4j.model.network.FloatingIP;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.base.Objects;

/**
 * An OpenStack Neutron Floating IP Model
 * 
 * @author nanderson
 */
@JsonRootName("floatingips")
public class NeutronFloatingIP implements FloatingIP {
  
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
  @Override
  public void setId(String id) {
    this.id = id;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setRouterId(String routerId) {
    this.routerId = routerId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFloatingNetworkId(String floatingNetworkId) {
    this.floatingNetworkId = floatingNetworkId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFloatingIpAddress(String floatingIpAddress) {
    this.floatingIpAddress = floatingIpAddress;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFixedIpAddress(String fixedIpAddress) {
    this.fixedIpAddress = fixedIpAddress;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPortId(String portId) {
    this.portId = portId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return Objects.toStringHelper(this).omitNullValues()
            .add("id", id).add("routerId", routerId).add("tenantId", tenantId).add("floatingNetworkId", floatingNetworkId)
            .add("floatingIpAddress", floatingIpAddress).add("fixedIpAddress", fixedIpAddress).add("portId", portId)
            .addValue("\n")
            .toString();
  }
  
  public static class FloatingIPs extends ListResult<NeutronFloatingIP> {
    
    private static final long serialVersionUID = 1L;

    @JsonProperty("floatingips")
    private List<NeutronFloatingIP> floatingIps;
    
    @Override
    protected List<NeutronFloatingIP> value() {
      return floatingIps;
    }
  }
  
}
