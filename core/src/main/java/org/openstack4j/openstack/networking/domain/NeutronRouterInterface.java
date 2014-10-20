package org.openstack4j.openstack.networking.domain;

import org.openstack4j.model.network.RouterInterface;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * An interface data model which is returned during interface association with a router
 * 
 * @author Jeremy Unruh
 */
public class NeutronRouterInterface implements RouterInterface {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("subnet_id")
	private String subnetId;
	
	@JsonProperty("port_id")
	private String portId;
	
	public NeutronRouterInterface() { }
	
	public NeutronRouterInterface(String subnetId, String portId) {
		this.subnetId = subnetId;
		this.portId = portId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
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
	public String getPortId() {
		return portId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("subnetId", subnetId).add("portId", portId).add("tenantId", tenantId).toString();
	}
	
}
