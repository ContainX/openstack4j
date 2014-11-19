package org.openstack4j.connectors.okhttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.Config;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.core.transport.UntrustedSSL;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

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
        client = new OkHttpClient();
        
        Config config = request.getConfig();
        if (config.getConnectTimeout() > 0)
            client.setConnectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS);
        
        if (config.getReadTimeout() > 0)
            client.setReadTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS);
        
        if (config.isIgnoreSSLVerification())
        {
            client.setHostnameVerifier(UntrustedSSL.getHostnameVerifier());
            client.setSslSocketFactory(UntrustedSSL.getSSLContext().getSocketFactory());
        }
        
        if (config.getSslContext() != null) 
            client.setSslSocketFactory(config.getSslContext().getSocketFactory());

        if (config.getHostNameVerifier() != null)
            client.setHostnameVerifier(config.getHostNameVerifier());
        
        clientReq = new Request.Builder();
        
        populateHeaders(request);
        populateQueryParams(request);
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
            String content = ObjectMapperSingleton.getContext(request.getEntity().getClass()).writer().writeValueAsString(request.getEntity());
            body = RequestBody.create(MediaType.parse(request.getContentType()), content);
        }
        else if(request.hasJson()) {
            body = RequestBody.create(MediaType.parse(ClientConstants.CONTENT_TYPE_JSON), request.getJson());
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
                }
                catch (UnsupportedEncodingException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        clientReq.url(url.toString());
    }

    private void populateHeaders(HttpRequest<R> request) {

        if (!request.hasHeaders()) return;

        for(Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            clientReq.addHeader(h.getKey(), String.valueOf(h.getValue()));
        }
    }
}
