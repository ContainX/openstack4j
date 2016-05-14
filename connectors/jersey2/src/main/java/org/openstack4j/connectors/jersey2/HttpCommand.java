package org.openstack4j.connectors.jersey2;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.RequestEntityProcessing;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.internal.HttpLoggingFilter;

/**
 * HttpCommand is responsible for executing the actual request driven by the HttpExecutor. 
 * 
 * @param <R>
 */
public final class HttpCommand<R> {

    private HttpRequest<R> request;
    private Entity<?> entity;
    private Invocation.Builder invocation;
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
        Client client = ClientFactory.create(request.getConfig());
        //try to set unsupported HTTP method. In our case used for PATCH.
        if( request.getMethod().name()=="PATCH" )
            client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
        
        WebTarget target = client.target(request.getEndpoint()).path(request.getPath());
        
        if (HttpLoggingFilter.isLoggingEnabled())
            target.register(new LoggingFilter(Logger.getLogger("os"), 10000));

        target = populateQueryParams(target, request);
        invocation = target.request(MediaType.APPLICATION_JSON);
        
        populateHeaders(invocation,  request);

        entity = (request.getEntity() == null) ? null : Entity.entity(request.getEntity(), request.getContentType());
    }

    /**
     * Executes the command and returns the Response
     * 
     * @return the response
     */
    public Response execute() {
        Response response = null;

        if (hasEntity()) {
            if (isInputStreamEntity())
            {
                // Issue #20 - Out of Memory in Jersey for large streams
                invocation.property(ClientProperties.CHUNKED_ENCODING_SIZE, 1024);
                invocation.property(ClientProperties.REQUEST_ENTITY_PROCESSING, RequestEntityProcessing.CHUNKED);
            }
            response = invocation.method(request.getMethod().name(), getEntity());
        }
        else if(request.hasJson()) {
            response = invocation.method(request.getMethod().name(), Entity.entity(request.getJson(), ClientConstants.CONTENT_TYPE_JSON));
        }
        else
        {
            response = invocation.method(request.getMethod().name());
        }
        
        return response;
    }
    
    private boolean isInputStreamEntity() {
        return (hasEntity() && InputStream.class.isAssignableFrom(entity.getEntity().getClass()));
    }

    /**
     * @return the request entity or null
     */
    public Entity<?> getEntity() {
        return entity;
    }

    /**
     * @return true if a request entity has been set
     */
    public boolean hasEntity() {
        return entity != null;
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

    private WebTarget populateQueryParams(WebTarget target, HttpRequest<R> request) {

        if (!request.hasQueryParams()) return target;

        for(Map.Entry<String, List<Object> > entry : request.getQueryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                target = target.queryParam(entry.getKey(), o);
            }
        }
        return target;
    }

    private void populateHeaders(Invocation.Builder invocation, HttpRequest<R> request) {

        if (!request.hasHeaders()) return;

        for(Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            invocation.header(h.getKey(), h.getValue());
        }
    }
}
