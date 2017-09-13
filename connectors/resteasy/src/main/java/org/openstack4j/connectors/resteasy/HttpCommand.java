package org.openstack4j.connectors.resteasy;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.openstack4j.connectors.resteasy.executors.ApacheHttpClientEngine;
import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.HttpRequest;
import org.openstack4j.core.transport.functions.EndpointURIFromRequestFunction;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.Map;

/**
 * HttpCommand is responsible for executing the actual request driven by the HttpExecutor.
 *
 * @param <R>
 */
public final class HttpCommand<R> {

    private HttpRequest<R> request;
    private ResteasyWebTarget resteasyWebTarget;
    private int retries;
    private Invocation.Builder resteasyRequest ;

    private HttpCommand(HttpRequest<R> request) {
        this.request = request;
    }

    /**
     * Creates a new HttpCommand from the given request
     * @param request the request
     * @return the command
     */
    public static <R> HttpCommand<R> create(HttpRequest<R> request) {
        HttpCommand<R> command = new HttpCommand<>(request);
        command.initialize();
        return command;
    }

    private void initialize() {

       resteasyWebTarget = new ResteasyClientBuilder().httpEngine(ApacheHttpClientEngine.create(request.getConfig()))
                .providerFactory(ResteasyClientFactory.getInstance()).build()
                .target(UriBuilder.fromUri(new EndpointURIFromRequestFunction().apply(request)));

        populateQueryParams(request);
        resteasyRequest = resteasyWebTarget.request();
        populateHeaders(request);
    }

    /**
     * Executes the command and returns the Response
     *
     * @return the response
     */
    public Response execute(){

        Invocation webRequest;
        if (request.getEntity() != null) {
            webRequest = resteasyRequest.build(request.getMethod().name(), Entity.entity(request.getEntity(), request.getContentType()));
        } else if (request.hasJson()) {
            webRequest= resteasyRequest.build(request.getMethod().name() , Entity.entity(request.getJson(),ClientConstants.CONTENT_TYPE_JSON));
        }else{
            webRequest = resteasyRequest.build(request.getMethod().name());
        }

        return webRequest.invoke();
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

        for (Map.Entry<String, List<Object>> entry : request.getQueryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                resteasyWebTarget = resteasyWebTarget.queryParam(entry.getKey(), o);
            }
        }
    }

    private void populateHeaders(HttpRequest<R> request) {

        if (!request.hasHeaders()) return;

        for (Map.Entry<String, Object> h : request.getHeaders().entrySet()) {
            resteasyRequest.header(h.getKey(), h.getValue());
        }
    }
}
