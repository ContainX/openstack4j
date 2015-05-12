package org.openstack4j.openstack.identity.domain;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Auth implements ModelEntity {

	private static final long serialVersionUID = 1L;
	public enum Type { CREDENTIALS, TOKEN };
	
	private String tenantId;
	private String tenantName;
	@JsonIgnore
	private transient Type type;
	
	protected Auth(Type type) {
	    this.type = type;
	}
	
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	
	@JsonIgnore
	public Type getType() {
	    return type;
	}
}
