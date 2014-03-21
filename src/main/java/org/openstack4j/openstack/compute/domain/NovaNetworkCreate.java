package org.openstack4j.openstack.compute.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openstack4j.model.compute.NetworkCreate;

public class NovaNetworkCreate implements NetworkCreate {

	private static final long serialVersionUID = 1L;

	@JsonProperty("uuid")
	private String id;
	@JsonProperty("fixed_ip")
	private String fixedIp;
	
	public NovaNetworkCreate() { }
	
	public void setId(String id) {
		this.id = id;
	}

	public void setFixedIp(String fixedIp) {
		this.fixedIp = fixedIp;
	}

	public NovaNetworkCreate(String id, String fixedIp) {
		this.id = id;
		this.fixedIp = fixedIp;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getFixedIp() {
		return fixedIp;
	}

}
