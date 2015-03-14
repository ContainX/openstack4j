/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.openstack4j.model.network.ExternalGateway;

import org.openstack4j.model.network.ExternalGatewayInfo;

/**
 *
 * @author Abdul
 */
public class NeutronExternalGatewayInfo implements ExternalGatewayInfo{
    
    
    private static final long serialVersionUID = 1L;

	@JsonProperty("network_id")
	private String networkId;
		
	public NeutronExternalGatewayInfo() { }
	
	public NeutronExternalGatewayInfo(String networkId) {
	  this.networkId = networkId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNetworkId() {
		return networkId;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				    .add("networkId", networkId).toString();
	}
    
}
