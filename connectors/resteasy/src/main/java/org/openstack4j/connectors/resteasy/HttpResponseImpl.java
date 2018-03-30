package org.openstack4j.connectors.resteasy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.openstack4j.core.transport.ClientConstants;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.HttpEntityHandler;
import org.openstack4j.core.transport.HttpResponse;

public class HttpResponseImpl implements HttpResponse {

    private final Response response;

    private HttpResponseImpl(Response response) {
        this.response = response;
    }

    /**
     * Wrap the given Response
     *
     * @param response the response
     * @return the HttpResponse
     */
    public static HttpResponseImpl wrap(Response response) {
        return new HttpResponseImpl(response);
    }

    /**
     * Unwrap and return the original Response
     *
     * @return the response
     */
    public Response unwrap() {
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
        return response.getStatusInfo().getReasonPhrase();
    }

    /**
     * @return the input stream
     */
    public InputStream getInputStream() {
        response.bufferEntity();
        return response.readEntity(InputStream.class);
    }

    /**
     * Returns a Header value from the specified name key
     *
     * @param name the name of the header to query for
     * @return the header as a String or null if not found
     */
    public String header(String name) {
        return response.getStringHeaders().getFirst(name);
    }

    /**
     * @return the a Map of Header Name to Header Value
     */
    public Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        MultivaluedMap<String, String> responseHeaders = response.getStringHeaders();

        for (String key : responseHeaders.keySet()) {
            headers.put(key, responseHeaders.getFirst(key));
        }

        return headers;
    }

    @Override
    public <T> T readEntity(Class<T> typeToReadAs) {
        response.bufferEntity();
        return response.readEntity(typeToReadAs);
    }

    @Override
    public void close() throws IOException {
        response.close();
    }
    
    @Override
    public String getContentType() {
        return header(ClientConstants.HEADER_CONTENT_TYPE);
    }
}
