package org.openstack4j.openstack.compute.domain;

import org.openstack4j.model.compute.HostResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Nova Host Resource describes the capacity and capability of a compute host that provides compute service
 * 
 * @author Qin An
 * @author Elina Meier
 */
@JsonRootName("resource")
public class NovaHostResource implements HostResource {

    public static final long serialVersionUID = 1L;

    private int cpu;
	@JsonProperty("disk_gb")
    private int diskGb;
    private String host;
	@JsonProperty("memory_mb")
    private int memoryMb;
    private String project;

    /**
	 * {@inheritDoc}
	 */
	@Override
    public int getCpu() {
        return cpu;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getDiskInGb() {
        return diskGb;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public String getHost() {
        return host;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public int getMemoryInMb() {
        return memoryMb;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    public String getProject() {
        return project;
    }

}
