package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("os-migrate_volume")
public class CinderVolumeMigration implements ModelEntity {


	private static final long serialVersionUID = 1L;

	@JsonProperty("force_host_copy")
	private boolean forceHostCopy;
	@JsonProperty("host")
	private String hostService;

	public boolean getForceHostCopy() {
		return forceHostCopy;
	}
	public String getHostService() {
		return hostService;
	}
	
	public CinderVolumeMigration(String hostService, boolean forceHostCopy) {
		this.hostService = hostService;
		this.forceHostCopy = forceHostCopy;
	}
	
}
