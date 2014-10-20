package org.openstack4j.openstack.identity.domain;

import org.openstack4j.model.ModelEntity;

public abstract class Auth implements ModelEntity {

	private static final long serialVersionUID = 1L;
	
	private String tenantId;
	private String tenantName;
	
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
	
	
	
}
