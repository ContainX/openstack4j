package org.openstack4j.connectors.httpclient;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.core.transport.HttpEntityHandler;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.ObjectMapperSingleton;

import com.google.common.base.Function;

public class HttpResponseImpl implements HttpResponse {
    private CloseableHttpResponse response;

    private HttpResponseImpl(CloseableHttpResponse response) {
        this.response = response;
    }

    /**
     * Wrap the given Response
     *
     * @param response the response
     * @return the HttpResponse
     */
    public static HttpResponseImpl wrap(CloseableHttpResponse response) {
        return new HttpResponseImpl(response);
    }

    /**
     * Unwrap and return the original Response
     *
     * @return the response
     */
    public CloseableHttpResponse unwrap() {
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
     * @param parser an optional parser which will handle the HttpResponse and return the corresponding return type.  Error codes are handled and thrown prior to the parser being called
     * @return the entity
     */
    @Override
    public <T> T getEntity(Class<T> returnType, Function<HttpResponse, T> parser) {
        return HttpEntityHandler.handle(this, returnType, parser);
    }

    /**
     * Gets the status from the previous Request
     *
     * @return the status code
     */
    public int getStatus() {
        return response.getStatusLine().getStatusCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatusMessage() {
        return response.getStatusLine().getReasonPhrase();
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
        Header header = response.getFirstHeader(name);
        return (header != null) ? header.getValue() : null;
    }

    /**
     * @return the a Map of Header Name to Header Value
     */
    public Map<String, String> headers() {
        Map<String, String> retHeaders = new HashMap<String, String>();
        Header[] headers =  response.getAllHeaders();

        for (Header h : headers) {
            retHeaders.put(h.getName(), h.getValue());
        }
        return retHeaders;
    }

    @Override
    public <T> T readEntity(Class<T> typeToReadAs) {
        HttpEntity entity = response.getEntity();
        try {
            return ObjectMapperSingleton.getContext(typeToReadAs).reader(typeToReadAs).readValue(entity.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientResponseException(e.getMessage(), 0, e);
        }
    }
}
