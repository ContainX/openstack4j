package org.openstack4j.connectors.resteasy;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.openstack4j.connectors.resteasy.executors.ApacheHttpClientExecutor;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;

/**
 * HttpCommand is responsible for executing the actual request driven by the HttpExecutor. 
 * 
 * @param <R>
 */
public final class HttpCommand<R> {

    private HttpRequest<R> request;
    private ClientRequest client;
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
        client = new ClientRequest(UriBuilder.fromUri(new EndpointURIFromRequestFunction().apply(request)), 
                ApacheHttpClientExecutor.create(request.getConfig()), ResteasyClientFactory.getInstance());
        
        client.followRedirects(true);
        
        populateQueryParams(request);
        populateHeaders(request);
    }

    /**
     * Executes the command and returns the Response
     * 
     * @return the response
     * @throws Exception 
     */
    public ClientResponse<R> execute() throws Exception {
        
        if (request.getEntity() != null) {
            client.body(request.getContentType(), request.getEntity());
        }
        else if(request.hasJson()) {
            client.body(ClientConstants.CONTENT_TYPE_JSON, request.getJson());
        }
        ClientResponse<R> response = client.httpMethod(request.getMethod().name(), request.getReturnType());
        return response;
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

    private void populateQueryParams(HttpRequest<R> request) {

        if (!request.hasQueryParams()) return;

        for(Map.Entry<String, List<Object> > entry : request.getQueryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                client = client.queryParameter(entry.getKey(), o);
            }
        }
    }

    private void populateHeaders(HttpRequest<R> request) {

        if (!request.hasHeaders()) return;

        for(Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            client.header(h.getKey(), h.getValue());
        }
    }
}
