package org.openstack4j.connectors.httpclient;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openstack4j.api.exceptions.ConnectionException;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.core.transport.UntrustedSSL;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;

import com.google.common.io.ByteStreams;

/**
 * HttpCommand is responsible for executing the actual request driven by the HttpExecutor. 
 * 
 * @param <R>
 */
public final class HttpCommand<R> {

    private static final String USER_AGENT = "OpenStack4j-Agent";

    private HttpRequest<R> request;
    private CloseableHttpClient client;
    HttpUriRequest clientReq;
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
        URI url = null;
        try
        {
            url = populateQueryParams(request);
        }
        catch (URISyntaxException e) {
            throw new ConnectionException(e.getMessage(),e.getIndex(), e);
        }

        HttpClientBuilder cb = HttpClientBuilder.create().setUserAgent(USER_AGENT);
        final Config config = request.getConfig();

        if (config.isIgnoreSSLVerification())
        {
            cb.setSslcontext(UntrustedSSL.getSSLContext());
            cb.setHostnameVerifier(new AllowAllHostnameVerifier());
        }
        
        if (config.getSslContext() != null)
            cb.setSslcontext(config.getSslContext());

        RequestConfig.Builder rcb = RequestConfig.custom();
        if (config.getConnectTimeout() > 0)
            rcb.setConnectTimeout(config.getConnectTimeout());
        
        if (config.getReadTimeout() > 0)
            rcb.setSocketTimeout(config.getReadTimeout());
        
        client = cb.setDefaultRequestConfig(rcb.build()).build();
        
        switch (request.getMethod()) {
        case POST:
            clientReq = new HttpPost(url);
            break;
        case PUT:
            clientReq = new HttpPut(url);
            break;
        case DELETE:
            clientReq = new HttpDelete(url);
            break;
        case HEAD:
            clientReq = new HttpHead(url);
            break;
        case GET:
        default:
            clientReq = new HttpGet(url);
            break;
        }
        populateHeaders(request);
    }

    /**
     * Executes the command and returns the Response
     * 
     * @return the response
     * @throws Exception 
     */
    public CloseableHttpResponse execute() throws Exception {

        EntityBuilder builder = null;

        if (request.getEntity() != null) {
            builder = EntityBuilder.create();
            
            if (InputStream.class.isAssignableFrom(request.getEntity().getClass())) 
            {
                builder
                	.setContentType(ContentType.create(request.getContentType()))
                	.setBinary(ByteStreams.toByteArray((InputStream)request.getEntity()));
            }
            else
            {
                builder
                	.setContentType(ContentType.create(request.getContentType(),"UTF-8"))
                    .setText(ObjectMapperSingleton.getContext(request.getEntity().getClass()).writer().writeValueAsString(request.getEntity()))
                    .setContentEncoding("UTF-8");
            }
        }
        else if(request.hasJson()) {
            builder = EntityBuilder.create()
                    .setContentType(ContentType.APPLICATION_JSON)
                    .setText(request.getJson())
                    .setContentEncoding("UTF-8");
        }
        if (builder != null && clientReq instanceof HttpEntityEnclosingRequestBase)
            ((HttpEntityEnclosingRequestBase)clientReq).setEntity(builder.build());

        return client.execute(clientReq);
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

    private URI populateQueryParams(HttpRequest<R> request) throws URISyntaxException {

        URIBuilder uri = new URIBuilder(new EndpointURIFromRequestFunction().apply(request));
        
        if (!request.hasQueryParams()) 
            return uri.build();

        for(Map.Entry<String, List<Object> > entry : request.getQueryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                uri.addParameter(entry.getKey(), String.valueOf(o));
            }
        }
        return uri.build();
    }

    private void populateHeaders(HttpRequest<R> request) {

        if (!request.hasHeaders()) return;

        for(Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            clientReq.addHeader(h.getKey(), String.valueOf(h.getValue()));
        }
    }
}
