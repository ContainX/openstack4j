package org.openstack4j.core.transport;

/**
 * Common String Constants
 * 
 * @author Jeremy Unruh
 */
public interface ClientConstants 
{
	static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";
	static final String HEADER_CONTENT_LANGUAGE = "Content-Language";
	static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	static final String HEADER_ACCEPT = "Accept";
	
	
	static final String CONTENT_TYPE_JSON = "application/json";
	static final String CONTENT_TYPE_STREAM = "application/stream";
	static final String CONTENT_TYPE_OCTECT_STREAM = "application/octet-stream";
	// Paths
	static final String PATH_TENANTS = "/tenants";
	static final String PATH_ROLES = "/roles";
	static final String PATH_EXTENSIONS = "/extensions";
	static final String URI_SEP = "/";

	
}
