package org.openstack4j.connectors.jersey2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.client.HttpUrlConnectorProvider.ConnectionFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.openstack4j.api.exceptions.ConnectionException;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.core.transport.UntrustedSSL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.ContextResolver;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * A factory for creating a rest Client which is mapped to Jackson for JSON processing.
 *
 * @author Jeremy Unruh
 */
class ClientFactory {

    private static final CustomContextResolver RESOLVER = new CustomContextResolver();
    private static LoadingCache<Config, Client> CACHE = CacheBuilder.newBuilder()
                                                                    .expireAfterAccess(20, TimeUnit.MINUTES)
                                                                    .build(new CacheLoader<Config, Client>() {
                                                                        @Override
                                                                        public Client load(Config config) throws Exception {
                                                                            return buildClientFromConfig(config);
                                                                        } });

    /**
     * Creates or Returns a Client
     *
     * @param config the configuration to use for the given client
     * @return the client
     */
    static Client create(Config config) {
        try {
            return CACHE.get(config);
        } catch (ExecutionException e) {
            throw new ConnectionException("Issue creating Jersey 2 Client: " + e.getMessage(), 0, e);
        }
    }

    private static Client buildClientFromConfig(Config config) {
        ClientConfig clientConfig = new ClientConfig();

        if (config.getProxy() != null) {
            addProxy(clientConfig, config);
        }

        ClientBuilder cb = ClientBuilder.newBuilder()
                            .withConfig(clientConfig)
                            .property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, "true")
                            .register(JacksonFeature.class)
                            .register(RESOLVER)
                            .register(new RequestFilter());

        if (config.getSslContext() != null)
            cb.sslContext(config.getSslContext());
        else if (config.isIgnoreSSLVerification())
            cb.sslContext(UntrustedSSL.getSSLContext());

        if (config.getHostNameVerifier() != null)
            cb.hostnameVerifier(config.getHostNameVerifier());
        else if (config.isIgnoreSSLVerification())
            cb.hostnameVerifier(UntrustedSSL.getHostnameVerifier());

        if (config.getReadTimeout() > 0)
            cb.property(ClientProperties.READ_TIMEOUT, config.getReadTimeout());

        if (config.getConnectTimeout() > 0)
            cb.property(ClientProperties.CONNECT_TIMEOUT, config.getConnectTimeout());

        return cb.build();
    }

    private static void addProxy(ClientConfig cc, Config config) {
        if (config.getProxy() != null) {
            HttpUrlConnectorProvider cp = new HttpUrlConnectorProvider();
            cc.connectorProvider(cp);
            final Proxy proxy = new Proxy(Type.HTTP,
                    new InetSocketAddress(config.getProxy().getRawHost(), config.getProxy().getPort()));

            cp.connectionFactory(new ConnectionFactory() {

                @Override
                public HttpURLConnection getConnection(URL url) throws IOException {
                    return (HttpURLConnection) url.openConnection(proxy);
                }});
        }
    }

    private static final class RequestFilter implements ClientRequestFilter {

        /**
         * {@inheritDoc}
         */
        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            requestContext.getHeaders().remove(ClientConstants.HEADER_CONTENT_LANGUAGE);
            requestContext.getHeaders().remove(ClientConstants.HEADER_CONTENT_ENCODING);
        }
    }

    private static final class CustomContextResolver implements ContextResolver<ObjectMapper> {

        /**
         * {@inheritDoc}
         */
        @Override
        public ObjectMapper getContext(Class<?> type) {
            return ObjectMapperSingleton.getContext(type);
        }

    }
}
