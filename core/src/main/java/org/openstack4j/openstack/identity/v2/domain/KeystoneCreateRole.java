package org.openstack4j.openstack.identity.v2.domain;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("role")
public class KeystoneCreateRole implements ModelEntity {
	
	private static final long serialVersionUID = 1L;
	private String name;

	public KeystoneCreateRole() {
	}
	
	public KeystoneCreateRole(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
