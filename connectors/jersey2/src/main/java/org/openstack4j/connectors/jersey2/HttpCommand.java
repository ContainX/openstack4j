package org.openstack4j.connectors.jersey2;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.filter.LoggingFilter;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpMethod;
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
        Client client = ClientFactory.create(request.useNonStrictSSLClient());
        WebTarget target = client.target(request.getEndpoint()).path(request.getPath());

        if (Boolean.getBoolean(HttpLoggingFilter.class.getName()))
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
            response = invocation.method(request.getMethod().name(), getEntity());
        }
        else if(HttpMethod.PUT == request.getMethod() || request.hasJson()) {
            response = invocation.method(request.getMethod().name(), Entity.entity(request.getJson(), ClientConstants.CONTENT_TYPE_JSON));
        }
        else
            response = invocation.method(request.getMethod().name());
        
        return response;
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
