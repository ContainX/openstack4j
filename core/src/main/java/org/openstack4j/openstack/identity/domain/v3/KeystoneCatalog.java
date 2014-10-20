package org.openstack4j.openstack.identity.domain.v3;

import java.util.List;

import org.openstack4j.model.identity.v3.Catalog;
import org.openstack4j.model.identity.v3.EndpointV3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Represents an OpenStack Identity Version 3 Catalog.  A catalog describes a service as well as the available 
 * Endpoint's for the service
 * 
 * @author Jeremy Unruh
 */
public class KeystoneCatalog implements Catalog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@JsonProperty
	private String id;
	@JsonProperty
	private String type;
	@JsonProperty
	private List<KeystoneEndpointV3> endpoints;
	
	
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
	public String getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends EndpointV3> getEndpoints() {
		return endpoints;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(getClass()).omitNullValues()
				     .add("id", id).add("type", type).add("endpoints", endpoints).toString();
	}
	
}
