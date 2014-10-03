package org.openstack4j.core.transport.internal;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.openstack4j.core.transport.ClientConstants;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * A factory for creating a rest Client which is mapped to Jackson for JSON processing.
 * 
 * @author Jeremy Unruh
 */
class ClientFactory {

    private static final CustomContextResolver RESOLVER = new CustomContextResolver();
    private static Client clientStrict;
    private static Client client;


    /**
     * Creates or Returns a Client
     *
     * @return the client
     */
    static Client create(boolean useNonStrictSSL) {

        if (useNonStrictSSL)
            return getNonStrictSSLClient();

        if (clientStrict == null) {
            clientStrict = ClientBuilder.newBuilder()
                    .register(JacksonFeature.class)
                    .register(RESOLVER)
                    .register(new RequestFilter())
                    .build();
        }
        return clientStrict;
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

    private static Client getNonStrictSSLClient() {
        if (client == null) 
        {
            ClientBuilder cb = ClientBuilder.newBuilder()
                    .register(JacksonFeature.class)
                    .register(RESOLVER)
                    .register(new RequestFilter());

            try
            {
                TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                } };
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(null, trustAllCerts, new SecureRandom());

                cb.sslContext(context);
                cb.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession session) {
                        return true;
                    } });
            }
            catch (Throwable t) {
                t.printStackTrace();
            }

            client = cb.build();
        }

        return client;
    }

    private static final class CustomContextResolver implements ContextResolver<ObjectMapper> {

        ObjectMapper mapper;
        ObjectMapper rootMapper;

        private CustomContextResolver() {

            mapper = new ObjectMapper();

            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            rootMapper = new ObjectMapper();
            rootMapper.setSerializationInclusion(Include.NON_NULL);
            rootMapper.enable(SerializationFeature.INDENT_OUTPUT);
            rootMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
            rootMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            rootMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            rootMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ObjectMapper getContext(Class<?> type) {
            return type.getAnnotation(JsonRootName.class) == null ? mapper : rootMapper;
        }

    }

}
