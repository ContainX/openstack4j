package org.openstack4j.connectors.okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.HttpMethod;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.core.transport.UntrustedSSL;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;
import org.openstack4j.core.transport.internal.HttpLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.ByteStreams;

/**
 * HttpCommand is responsible for executing the actual request driven by the HttpExecutor.
 *
 * @param <R>
 */
public final class HttpCommand<R> {

    private static final Logger LOG = LoggerFactory.getLogger(HttpCommand.class);

    private HttpRequest<R> request;
    private OkHttpClient client;
    private Request.Builder clientReq;
    private int retries;

    private HttpCommand(HttpRequest<R> request) {
        this.request = request;
    }

    /**
     * Creates a new HttpCommand from the given request
     * @param request the request
     * @return the command
     */
    public static <R> HttpCommand<R> create(HttpRequest<R> request) {
        HttpCommand<R> command = new HttpCommand<R>(request);
        command.initialize();
        return command;
    }

    private void initialize() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        Config config = request.getConfig();

        if (config.getProxy() != null) {
            okHttpClientBuilder.proxy(new Proxy(Type.HTTP,
                    new InetSocketAddress(config.getProxy().getRawHost(), config.getProxy().getPort())));
        }

        if (config.getConnectTimeout() > 0)
            okHttpClientBuilder.connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS);

        if (config.getReadTimeout() > 0)
            okHttpClientBuilder.readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS);

        if (config.isIgnoreSSLVerification())
        {
            okHttpClientBuilder.hostnameVerifier(UntrustedSSL.getHostnameVerifier());
            okHttpClientBuilder.sslSocketFactory(UntrustedSSL.getSSLContext().getSocketFactory());
        }

        if (config.getSslContext() != null)
            okHttpClientBuilder.sslSocketFactory(config.getSslContext().getSocketFactory());

        if (config.getHostNameVerifier() != null)
            okHttpClientBuilder.hostnameVerifier(config.getHostNameVerifier());
        if (HttpLoggingFilter.isLoggingEnabled()) {
            okHttpClientBuilder.addInterceptor(new LoggingInterceptor());
        }
        okHttpClientBuilder.connectionPool(getConnectionPool());
        client = okHttpClientBuilder.build();
        clientReq = new Request.Builder();
        populateHeaders(request);
        populateQueryParams(request);
    }

    /**
     * Create ConnectionPool optimized for short lived client with little chance to reuse connections.
     */
    private ConnectionPool getConnectionPool() {
        int maxIdleConnections = 0;
        // OkHttp creates "OkHttp ConnectionPool" thread per every ConnectionPool created to mange its connections. It
        // lives as long as the last connection made through it + its keep alive timeout. By default that it 5 min which
        // makes no sense for openstack4j since the connections or pools are not reused (after HttpCommand completion,
        // at least). Setting strict keepAlive duration here so the connections and threads does not hang around longer
        // than necessary.
        int keepAliveDuration = 500;
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.MILLISECONDS);
    }

    /**
     * Executes the command and returns the Response
     *
     * @return the response
     * @throws Exception
     */
    public Response execute() throws Exception {
        RequestBody body = null;
        if (request.getEntity() != null) {
            if (InputStream.class.isAssignableFrom(request.getEntity().getClass())) {
                byte[] content = ByteStreams.toByteArray((InputStream)request.getEntity());
                body = RequestBody.create(MediaType.parse(request.getContentType()), content);
            } else {
                String content = ObjectMapperSingleton.getContext(request.getEntity().getClass()).writer().writeValueAsString(request.getEntity());
                body = RequestBody.create(MediaType.parse(request.getContentType()), content);
            }
        }
        else if(request.hasJson()) {
            body = RequestBody.create(MediaType.parse(ClientConstants.CONTENT_TYPE_JSON), request.getJson());
        }
        //Added to address https://github.com/square/okhttp/issues/751
        //Set body as empty byte array if request is POST or PUT and body is sent as null
        if((request.getMethod() == HttpMethod.POST || request.getMethod() == HttpMethod.PUT) && body == null){
            body = RequestBody.create(null, Util.EMPTY_BYTE_ARRAY);
        }
        clientReq.method(request.getMethod().name(), body);
        Call call = client.newCall(clientReq.build());
        return call.execute();
    }

    /**
     * @return true if a request entity has been set
     */
    public boolean hasEntity() {
        return request.getEntity() != null;
    }

    /**
     * @return current retry execution count for this command
     */
    public int getRetries() {
        return retries;
    }

    /**
     * @return incremement's the retry count and returns self
     */
    public HttpCommand<R> incrementRetriesAndReturn() {
        initialize();
        retries++;
        return this;
    }

    public HttpRequest<R> getRequest() {
        return request;
    }

    private void populateQueryParams(HttpRequest<R> request)  {

        StringBuilder url = new StringBuilder();
        url.append(new EndpointURIFromRequestFunction().apply(request));

        if (!request.hasQueryParams())
        {
            clientReq.url(url.toString());
            return;
        }

        url.append("?");

        for(Map.Entry<String, List<Object> > entry : request.getQueryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                try
                {
                    url.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(o), "UTF-8"));
                    url.append("&");
                }
                catch (UnsupportedEncodingException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        if (url.charAt(url.length() - 1) == '&') {
            url.deleteCharAt(url.length() - 1);
        }
        clientReq.url(url.toString());
    }

    private void populateHeaders(HttpRequest<R> request) {

        if (!request.hasHeaders()) return;

        for(Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            clientReq.addHeader(h.getKey(), String.valueOf(h.getValue()));
        }
    }

    static class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            System.err.println(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            System.err.println(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }
}