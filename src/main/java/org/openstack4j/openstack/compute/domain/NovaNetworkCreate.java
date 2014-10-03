package org.openstack4j.openstack.compute.domain;

import org.openstack4j.model.compute.NetworkCreate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NovaNetworkCreate implements NetworkCreate {

	private static final long serialVersionUID = 1L;

	@JsonProperty("uuid")
	private String id;
	@JsonProperty("fixed_ip")
	private String fixedIp;
	
	private String port;
	
	public NovaNetworkCreate() { }
	
	public void setId(String id) {
		this.id = id;
	}

	public void setFixedIp(String fixedIp) {
		this.fixedIp = fixedIp;
	}

	public void setPort(String port) {
		this.port = port;
	}		

	public NovaNetworkCreate(String id, String fixedIp) {
		this.id = id;
		this.fixedIp = fixedIp;
	}

	public NovaNetworkCreate(String id, String fixedIp, String port) {
		this.id = id;
		this.fixedIp = fixedIp;
		this.port = port;
	}		
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getFixedIp() {
		return fixedIp;
	}

	public String getPort() {
		return port;
	}		

}
