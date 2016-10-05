package org.openstack4j.core.transport;

/**
 * Common String Constants
 *
 * @author Jeremy Unruh
 */
public final class ClientConstants {

    public static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";
    public static final String HEADER_X_SUBJECT_TOKEN = "X-Subject-Token";
    public static final String HEADER_CONTENT_LANGUAGE = "Content-Language";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_OS4J_AUTH = "OS4J-Auth-Command";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String USER_AGENT = "OpenStack4j / OpenStack Client";

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_STREAM = "application/stream";
    public static final String CONTENT_TYPE_DIRECTORY = "application/directory";
    public static final String CONTENT_TYPE_OCTECT_STREAM = "application/octet-stream";
    public static final String CONTENT_TYPE_TEXT = "text/plain";
    public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
    public static final String CONTENT_TYPE_IMAGE_V2_PATCH = "application/openstack-images-v2.1-json-patch";
    
    public static final String X_OPENSTACK_REQUEST_ID = "x-openstack-request-id";
	public static final String X_COMPUTE_REQUEST_ID = "X-Compute-Request-Id";


    // Paths
    public static final String URI_SEP = "/";
    public static final String PATH_PROJECTS = "/projects";
    public static final String PATH_ROLES = "/roles";
    public static final String PATH_USERS = "/users";
    public static final String PATH_SERVICES = "/services";
    public static final String PATH_DOMAINS = "/domains";
    public static final String PATH_ENDPOINTS = "/endpoints";
    public static final String PATH_EXTENSIONS = "/extensions";
    public static final String PATH_GROUPS = "/groups";
    public static final String PATH_POLICIES = "/policies";
    public static final String PATH_REGIONS = "/regions";
    public static final String PATH_CREDENTIALS = "/credentials";
    public static final String PATH_TOKENS = "/auth/tokens";
    public static final String PATH_PROJECT_SCOPES = "auth/projects";
    public static final String PATH_DOMAIN_SCOPES = "auth/domains";
    public static final String PATH_SERVICE_CATALOGS = "auth/catalog";
    public static final String PATH_TENANTS = "/tenants";

}
