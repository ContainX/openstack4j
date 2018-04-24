package org.openstack4j.connectors.resteasy.executors;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.UntrustedSSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Default Apache HttpClient based Executor
 *
 * @author Jeremy Unruh
 */
public class ApacheHttpClientEngine extends ApacheHttpClient4Engine {

    ApacheHttpClientEngine(HttpClient client) {
        super(client);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheHttpClientEngine.class);

    public static ApacheHttpClientEngine create(Config config) {

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();

        if (config.getReadTimeout() > 0) {
            requestConfigBuilder.setConnectionRequestTimeout(config.getReadTimeout());
        }

        if (config.getConnectTimeout() > 0) {
            requestConfigBuilder.setConnectTimeout(config.getConnectTimeout());
        }

        /*HttpClient client*/

        HttpClientBuilder httpClientBuilder = HttpClients.custom();

        if (config.isIgnoreSSLVerification()) {
            httpClientBuilder.setSSLContext(UntrustedSSL.getSSLContext());
            httpClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier());
        }

        if (config.getSslContext() != null) {
            httpClientBuilder.setSSLContext(config.getSslContext());
        }

        if (config.getHostNameVerifier() != null) {
            httpClientBuilder.setSSLHostnameVerifier(config.getHostNameVerifier());
        }

        if (config.getProxy() != null) {
            try {
                URL url = new URL(config.getProxy().getHost());
                HttpHost proxy = new HttpHost(url.getHost(), config.getProxy().getPort(), url.getProtocol());
                requestConfigBuilder.setProxy(proxy);
            } catch (MalformedURLException e) {
                LOGGER.error("Invalid proxy Url :%s",config.getProxy(),e);
            }
        }

        httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());
        httpClientBuilder.setRedirectStrategy(new DefaultRedirectStrategy() {
            @Override
            protected boolean isRedirectable(String method)
            {
                return true;
            }
        });

        HttpClient client = httpClientBuilder.build();
        return new ApacheHttpClientEngine(client);
    }

}
