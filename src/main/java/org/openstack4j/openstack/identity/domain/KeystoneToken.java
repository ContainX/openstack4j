package org.openstack4j.openstack.identity.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.identity.TokenV2;

import com.google.common.base.Objects;

public final class KeystoneToken implements TokenV2 {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date expires;
	private KeystoneTenant tenant;
	
	public String getId() {
		return id;
	}
	
	public Date getExpires() {
		return expires;
	}
	
	public Tenant getTenant() {
		return tenant;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				   .add("id", id).add("expires", expires).add("tenant", tenant)
				   .toString();
	}

	@JsonIgnore
	@Override
	public AuthVersion getVersion() {
		return AuthVersion.V2;
	}
	
}
