package org.openstack4j.connectors.http;

import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.core.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpResponseImpl implements HttpResponse {

    private static final Logger LOG = LoggerFactory.getLogger(HttpResponseImpl.class);

    private Map<String, List<String>> headers;
    private int responseCode;
    private String responseMessage;
    private byte[] data;

    private HttpResponseImpl(Map<String, List<String>> headers,
            int responseCode, String responseMessage, byte[] data) {
        this.headers = headers;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    /**
     * Wrap the given Response
     *
     * @param headers
     * @param responseCode
     * @param responseMessage
     * @return the HttpResponse
     */
    public static HttpResponseImpl wrap(Map<String, List<String>> headers,
            int responseCode, String responseMessage, byte[] data) {
        return new HttpResponseImpl(headers, responseCode, responseMessage, data);
    }

    /**
     * Gets the entity and Maps any errors which will result in a
     * ResponseException
     *
     * @param <T> the generic type
     * @param returnType the return type
     * @return the entity
     */
    public <T> T getEntity(Class<T> returnType) {
        return getEntity(returnType, null);
    }

    /**
     * Gets the entity and Maps any errors which will result in a
     * ResponseException
     *
     * @param <T> the generic type
     * @param returnType the return type
     * @param options the execution options
     * @return the entity
     */
    @Override
    public <T> T getEntity(Class<T> returnType, ExecutionOptions<T> options) {
        return HttpEntityHandler.handle(this, returnType, options, Boolean.TRUE);
    }

    /**
     * Gets the status from the previous Request
     *
     * @return the status code
     */
    public int getStatus() {
        return responseCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatusMessage() {
        return responseMessage;
    }

    /**
     * @return the input stream
     */
    public InputStream getInputStream() {
    	if (data == null)
    		return null;

        return new ByteArrayInputStream(data);
    }

    /**
     * Returns a Header value from the specified name key
     *
     * @param name the name of the header to query for
     * @return the header as a String or null if not found
     */
    public String header(String name) {
    	if (name == null) return null;
    	for (String key : headers.keySet()) {
    		if (key != null && key.equalsIgnoreCase(name))
    		{
    			return headers.get(key).get(0);
    		}
    	}
    	return null;
    }

    /**
     * @return the a Map of Header Name to Header Value
     */
    public Map<String, String> headers() {
        Map<String, String> retHeaders = new HashMap<String, String>();

        Set<String> keys = headers.keySet();

        for (String key : keys) {
            if (key == null) continue; // Ignore null header where HttpURLConnection stores HTTP method info
            List<String> values = headers.get(key);
            for (String value : values) {
                retHeaders.put(key, value);
            }
        }

        return retHeaders;
    }

    @Override
    public <T> T readEntity(Class<T> typeToReadAs) {

        if (data == null) {
            return null;
        }

        try {
            return ObjectMapperSingleton.getContext(typeToReadAs).reader(typeToReadAs).readValue(data);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ClientResponseException(e.getMessage(), 0, e);
        }
    }

		@Override
		public void close() throws IOException {
			// Not Implemented - closing handle by HttpCommand
		}

        @Override
        public String getContentType() {
            return header(ClientConstants.HEADER_CONTENT_TYPE);
        }
}
