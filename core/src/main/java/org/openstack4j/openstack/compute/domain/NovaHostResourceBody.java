package org.openstack4j.openstack.compute.domain;

import org.openstack4j.model.compute.HostResource;
import org.openstack4j.model.compute.HostResourceBody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Nova Host Resource Body contains Nova host resource
 * 
 * @author Qin An
 */
public class NovaHostResourceBody implements HostResourceBody {

	public static final long serialVersionUID = 1L;

	@JsonProperty("resource")
	private NovaHostResource resource;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HostResource getHostResource() {
		return resource;
	}
}
