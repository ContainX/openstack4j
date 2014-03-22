package org.openstack4j.openstack.networking.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openstack4j.model.network.IP;

import com.google.common.base.Objects;

/**
 * A Fixed IP Address
 * 
 * @author Jeremy Unruh
 */
public class NeutronIP implements IP {

	private static final long serialVersionUID = 1L;

  @JsonProperty("ip_address")
  private String address;

  @JsonProperty("subnet_id")
  private String subnetId;
	
  public NeutronIP() { }
  
  public NeutronIP(String address, String subnetId) {
  	this.address = address;
  	this.subnetId = subnetId;
  }
  
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIpAddress() {
		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSubnetId() {
		return subnetId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues().add("ipAddress", address).add("subnetId", subnetId).toString();
	}

}
