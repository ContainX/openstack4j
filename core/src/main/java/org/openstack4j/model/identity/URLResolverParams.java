package org.openstack4j.model.identity;

import org.openstack4j.api.types.Facing;
import org.openstack4j.api.types.ServiceType;

/**
 * Dynamic parameters used for URL resolution with Endpoints
 * 
 * @author Jeremy Unruh
 */
public class URLResolverParams {

	public final Access access;
	public final ServiceType type;
	public String region;
	public Facing perspective;
	
	private URLResolverParams(Access access, ServiceType type) {
		this.access = access;
		this.type = (type == null) ? ServiceType.IDENTITY : type;
	}
	
	public static URLResolverParams create(Access access, ServiceType type) {
		return new URLResolverParams(access, type);
	}
	
	public URLResolverParams region(String region) {
		this.region = region;
		return this;
	}
	
	public URLResolverParams perspective(Facing perspective) {
		this.perspective = perspective;
		return this;
	}
	
}
