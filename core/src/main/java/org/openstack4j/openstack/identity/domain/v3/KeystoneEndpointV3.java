package org.openstack4j.openstack.identity.domain.v3;

import java.net.URL;

import org.openstack4j.api.types.Facing;
import org.openstack4j.model.identity.v3.EndpointV3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Represents a Version 3 Endpoint
 *
 * @author Jeremy Unruh
 */
public class KeystoneEndpointV3 implements EndpointV3 {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	private String id;
	@JsonProperty("interface")
	private Facing iface;
	@JsonProperty("region")
	private String region;
	@JsonProperty("url")
	private URL url;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Facing getInterface() {
		return iface;
	}

	@Override
	public String getRegion() {
		return region;
	}

	@Override
	public URL getURL() {
		return url;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(getClass()).omitNullValues()
				    .add("id", id).add("interface", iface).add("region", region).add("url", url).toString();
	}

}
