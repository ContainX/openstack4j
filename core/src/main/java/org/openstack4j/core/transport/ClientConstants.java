package org.openstack4j.core.transport;

/**
 * Common String Constants
 * 
 * @author Jeremy Unruh
 */
public final class ClientConstants 
{
    
	public static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";
	public static final String HEADER_X_SUBJECT_TOKEN = "X-Subject-Token";
	public static final String HEADER_CONTENT_LANGUAGE = "Content-Language";
	public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	public static final String HEADER_OS4J_AUTH = "OS4J-Auth-Command";
	public static final String HEADER_ACCEPT = "Accept";
	public static final String HEADER_USER_AGENT = "User-Agent";
	public static final String USER_AGENT = "OpenStack4j / OpenStack Client";
	
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_STREAM = "application/stream";
	public static final String CONTENT_TYPE_DIRECTORY = "application/directory";
	public static final String CONTENT_TYPE_OCTECT_STREAM = "application/octet-stream";
	// Paths
	public static final String PATH_TENANTS = "/tenants";
	public static final String PATH_ROLES = "/roles";
	public static final String PATH_EXTENSIONS = "/extensions";
	public static final String URI_SEP = "/";

	
}
