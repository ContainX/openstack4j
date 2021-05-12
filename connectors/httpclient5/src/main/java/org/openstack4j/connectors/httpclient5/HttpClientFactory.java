package org.openstack4j.connectors.httpclient5;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.sync.CloseableHttpClient;
import org.apache.hc.client5.http.impl.sync.HttpClientBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.core5.http.HttpHost;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.UntrustedSSL;
import org.openstack4j.core.transport.internal.HttpExecutor;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

/**
 * Creates the initial HttpClient and keeps it as a singleton to preserve pooling strategies within the Http Client
 *
 * @author Jeremy Unruh
 * @author Gleb Schukin
 */
public class HttpClientFactory {

    public static final HttpClientFactory INSTANCE = new HttpClientFactory();
    private static final String USER_AGENT = "OpenStack4j-Agent";
    private static final Logger LOG = LoggerFactory.getLogger(HttpExecutor.class);

    private CloseableHttpClient client;
    private static HttpClientConfigInterceptor INTERCEPTOR;

    /**
     * Creates or Returns an existing HttpClient
     *
     * @param config the configuration
     * @return CloseableHttpClient
     */
    CloseableHttpClient getClient(Config config) {
        if (client == null) {
            synchronized(this) {
                if (client == null) {
                    client = buildClient(config);
                }
            }
        }
        return client;
    }

    /**
     * Registers a HttpClientConfigInterceptor that is invoked prior to a new HttpClient being created.
     *
     * @param interceptor the http config interceptor
     */
    public static void registerInterceptor(HttpClientConfigInterceptor interceptor) {
        INTERCEPTOR = interceptor;
    }

    private CloseableHttpClient buildClient(Config config) {
        HttpClientBuilder cb = HttpClientBuilder.create().setUserAgent(USER_AGENT);

        if (config.getProxy() != null) {
            HttpHost proxy = HttpHost.create(config.getProxy().getHost() + ":" + config.getProxy().getPort());
            cb.setProxy(proxy);
        }

        if (config.isIgnoreSSLVerification()) {
            cb.setSSLContext(UntrustedSSL.getSSLContext());
            cb.setSSLHostnameVerifier(new NoopHostnameVerifier());
        }

        if (config.getSslContext() != null)
            cb.setSSLContext(config.getSslContext());

        if (config.getMaxConnections() > 0)
            cb.setMaxConnTotal(config.getMaxConnections());

        if (config.getMaxConnectionsPerRoute() > 0)
            cb.setMaxConnPerRoute(config.getMaxConnectionsPerRoute());

        RequestConfig.Builder rcb = RequestConfig.custom();

        if (config.getConnectTimeout() > 0)
            rcb.setConnectTimeout(config.getConnectTimeout());

        if (config.getReadTimeout() > 0)
            rcb.setSocketTimeout(config.getReadTimeout());

        if (INTERCEPTOR != null)
            INTERCEPTOR.onClientCreate(cb, rcb, config);

        return cb.setDefaultRequestConfig(rcb.build()).build();
    }
}
