package org.openstack4j.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.client.IOSClientBuilder;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.internal.HttpLoggingFilter;
import org.openstack4j.model.identity.Access;
import org.openstack4j.openstack.client.OSClientBuilder;
import org.openstack4j.openstack.identity.domain.KeystoneAccess;
import org.openstack4j.openstack.identity.internal.DefaultEndpointURLResolver;
import org.openstack4j.openstack.internal.OSClientSession;
import org.openstack4j.openstack.logging.internal.FallbackLoggerFactorySupplier;

/**
 * A Factory which sets up the APIs to be used a previously non-expired authorization or new authorization.
 * 
 * @author Jeremy Unruh
 *
 */
public abstract class OSFactory<T extends OSFactory<T>> {

	private OSFactory() { }

	/**
	 * Skips Authentication and created the API around a previously cached Access object.  This can be useful in multi-threaded environments
	 * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours
	 * 
	 * @param access an authorized access entity which is to be used to create the API
	 * @return the OSCLient
	 */
	public static OSClient clientFromAccess(Access access) {
		return OSClientSession.createSession((KeystoneAccess) access);
	}
	
	/**
     * Skips Authentication and created the API around a previously cached Access object.  This can be useful in multi-threaded environments
     * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours
     * 
     * @param access an authorized access entity which is to be used to create the API
     * @param config OpenStack4j configuration options
     * @return the OSCLient
     */
    public static OSClient clientFromAccess(Access access, Config config) {
        return OSClientSession.createSession((KeystoneAccess) access, null, config);
    }
    
    /**
     * Globally enables or disables verbose HTTP Request and Response logging useful for debugging
     * @param enabled true to enable, false to enable
     */
    public static void enableHttpLoggingFilter(boolean enabled) {
        System.getProperties().setProperty(HttpLoggingFilter.class.getName(), String.valueOf(enabled));
    }
    
    /**
     * As of 2.0 of OpenStack4j we have removed a workaround for endpoints that have the admin URL configured wrong by substituting the 
     * host from the original Keystone connection.  This is not up to specification so we have removed this functionality by default.
     * 
     * As a result it has broken some users with development based environments.  Turn this on to go back to original behaviour.
     * 
     * @param enabled
     */
    public static void enableLegacyEndpointHandling(boolean enabled) {
        DefaultEndpointURLResolver.enableLegacyEndpointHandling(enabled);
    }
    
    /**
     * Unless a logger plugin is declared the default logger is console.  By invoking this method the default
     * console is replaced by the JDK based Logger
     */
    public static void useJDKLogger() {
        FallbackLoggerFactorySupplier.getInstance().useJDKLogger();
    }

	/**
	 * Creates builder for OpenStack V2 based authentication
	 * @return V2 Authentication builder
	 */
	public static IOSClientBuilder.V2 builder() {
		return new OSClientBuilder.ClientV2();
	}
	
	   /**
     * Creates builder for OpenStack V3 based authentication
     * @return V3 Authentication builder
     */
	public static IOSClientBuilder.V3 builderV3() {
		return new OSClientBuilder.ClientV3();
	}
}
