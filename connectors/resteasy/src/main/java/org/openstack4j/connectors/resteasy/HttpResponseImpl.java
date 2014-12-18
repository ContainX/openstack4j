package org.openstack4j.connectors.resteasy;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.client.ClientResponse;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.HttpEntityHandler;
import org.openstack4j.core.transport.HttpResponse;

public class HttpResponseImpl implements HttpResponse {
    private ClientResponse<?> response;

    private HttpResponseImpl(ClientResponse<?> response) {
        this.response = response;
    }

    /**
     * Wrap the given Response
     *
     * @param response the response
     * @return the HttpResponse
     */
    public static HttpResponseImpl wrap(ClientResponse<?> response) {
        return new HttpResponseImpl(response);
    }

    /**
     * Unwrap and return the original Response
     *
     * @return the response
     */
    public ClientResponse<?> unwrap() {
        return response;
    }

    /**
     * Gets the entity and Maps any errors which will result in a ResponseException
     *
     * @param <T> the generic type
     * @param returnType the return type
     * @return the entity
     */
    public <T> T getEntity(Class<T> returnType) {
        return getEntity(returnType, null);
    }

    /**
     * Gets the entity and Maps any errors which will result in a ResponseException
     *
     * @param <T> the generic type
     * @param returnType the return type
     * @param options execution based options
     * @return the entity
     */
    @Override
    public <T> T getEntity(Class<T> returnType, ExecutionOptions<T> options) {
       return HttpEntityHandler.handle(this, returnType, options, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStatus() {
        return response.getStatus();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatusMessage() {
        return response.getResponseStatus().getReasonPhrase();
    }

    /**
     * @return the input stream
     */
    public InputStream getInputStream() {
        return (InputStream) response.getEntity();
    }

    /**
     * Returns a Header value from the specified name key
     *
     * @param name the name of the header to query for
     * @return the header as a String or null if not found
     */
    public String header(String name) {
        return response.getHeaders().getFirst(name).toString();
    }

    /**
     * @return the a Map of Header Name to Header Value
     */
    public Map<String, String> headers() {
        Map<String, String> headers = new HashMap<String, String>();
        MultivaluedMap<String, String> responseHeaders = response.getHeaders();

        for (String key : responseHeaders.keySet()) {
            headers.put(key, responseHeaders.getFirst(key).toString());
        }

        return headers;
    }

    @Override
    public <T> T readEntity(Class<T> typeToReadAs) {
        return response.getEntity(typeToReadAs);
    }
}
