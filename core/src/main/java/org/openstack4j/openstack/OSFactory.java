package org.openstack4j.openstack;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.client.CloudProvider;
import org.openstack4j.api.client.IOSClientBuilder;
import org.openstack4j.api.types.Facing;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.internal.HttpLoggingFilter;
import org.openstack4j.model.identity.Token;
import org.openstack4j.openstack.client.OSClientBuilder;
import org.openstack4j.openstack.internal.OSClientSession;
import org.openstack4j.openstack.logging.internal.FallbackLoggerFactorySupplier;

/**
 * A Factory which sets up the APIs to be used a previously non-expired authorization or new authorization.
 *
 * @author Jeremy Unruh
 */
public abstract class OSFactory<T extends OSFactory<T>> {

	private OSFactory() { }

	/**
	 * Skips Authentication and created the API around a previously cached Token object.  This can be useful in multi-threaded environments
	 * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours.
	 *
	 * @param token an authorized token entity which is to be used to create the API
	 * @return the OSClient
	 */
	public static OSClient clientFromToken(Token token) {
		return OSClientSession.createSession(token);
	}

	/**
     * Skips Authentication and created the API around a previously cached Token object.  This can be useful in multi-threaded environments
     * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours
     *
     * @param token an authorized token entity which is to be used to create the API
     * @param perspective the current endpoint perspective to use
     * @return the OSClient
     */
    public static OSClient clientFromToken(Token token, Facing perspective) {
        return OSClientSession.createSession(token, perspective, null, null);
    }

	/**
     * Skips Authentication and created the API around a previously cached Token object.  This can be useful in multi-threaded environments
     * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours
     *
     * @param token an authorized token entity which is to be used to create the API
     * @param config OpenStack4j configuration options
     * @return the OSClient
     */
    public static OSClient clientFromToken(Token token, Config config) {
        return OSClientSession.createSession(token, null, null, config);
    }

    /**
     * Skips Authentication and created the API around a previously cached Token object.  This can be useful in multi-threaded environments
     * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours
     *
     * @param token an authorized token entity which is to be used to create the API
     * @param perspective the current endpoint perspective to use
     * @param config OpenStack4j configuration options
     * @return the OSClient
     */
    public static OSClient clientFromToken(Token token, Facing perspective, Config config) {
        return OSClientSession.createSession(token, perspective, null, config);
    }

    /**
     * Skips Authentication and created the API around a previously cached Token object.  This can be useful in multi-threaded environments
     * or scenarios where a client should not be re-authenticated due to a token lasting 24 hours
     *
     * @param token an authorized token entity which is to be used to create the API
     * @param perspective the current endpoint perspective to use
     * @param provider the cloud provider
     * @param config OpenStack4j configuration options
     * @return the OSClient
     */
    public static OSClient clientFromToken(Token token, Facing perspective, CloudProvider provider, Config config) {
        return OSClientSession.createSession(token, perspective, provider, config);
    }

    /**
     * Globally enables or disables verbose HTTP Request and Response logging useful for debugging
     * @param enabled true to enable, false to enable
     */
    public static void enableHttpLoggingFilter(boolean enabled) {
        System.getProperties().setProperty(HttpLoggingFilter.class.getName(), String.valueOf(enabled));
    }

    /**
     * Unless a logger plugin is declared the default logger is console.  By invoking this method the default
     * console is replaced by the JDK based Logger
     */
    public static void useJDKLogger() {
        FallbackLoggerFactorySupplier.getInstance().useJDKLogger();
    }

	/**
     * Creates builder for OpenStack V3 based authentication
     * @return V3 Authentication builder
     */
	public static IOSClientBuilder builder() {
		return new OSClientBuilder.Client();
	}
}
