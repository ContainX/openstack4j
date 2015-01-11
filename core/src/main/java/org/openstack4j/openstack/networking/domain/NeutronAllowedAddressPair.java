package org.openstack4j.openstack.networking.domain;

import org.openstack4j.model.network.AllowedAddressPair;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * A Fixed IP Address
 * 
 * @author Jeremy Unruh
 */
public class NeutronAllowedAddressPair implements AllowedAddressPair {

	private static final long serialVersionUID = 1L;

  @JsonProperty("ip_address")
  private String ipAddress;
	
  public NeutronAllowedAddressPair() { }
  
  public NeutronAllowedAddressPair(String address) {
  	this.ipAddress = address;
  }
  
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIpAddress() {
		return ipAddress;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues().add("ipAddress", ipAddress).toString();
	}

}
