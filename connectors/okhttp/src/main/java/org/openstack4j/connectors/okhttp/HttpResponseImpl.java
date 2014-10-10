package org.openstack4j.connectors.okhttp;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.api.exceptions.ServerResponseException;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.ListType;
import org.openstack4j.core.transport.ObjectMapperSingleton;

import com.google.common.base.Function;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Response;

public class HttpResponseImpl implements HttpResponse {
    private static final Pattern MESSAGE_PATTERN = Pattern.compile(".*message\\\":\\s\\\"([^\"]+)\\\".*");
    private Response response;

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
     * @param parser an optional parser which will handle the HttpResponse and return the corresponding return type.  Error codes are handled and thrown prior to the parser being called
     * @return the entity
     */
    public <T> T getEntity(Class<T> returnType, Function<HttpResponse, T> parser) {
        int status = response.code();
        if(status >= 400) {
            if (status == 404)
            {
                try
                {
                    if (ListType.class.isAssignableFrom(returnType))
                        return returnType.newInstance();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            if (status < 500)
            {
                try
                {
                   
                    String json =  response.body().string();
                    if (json != null && json.contains("message")) {
                        Matcher m = MESSAGE_PATTERN.matcher(json);
                        if (m.matches())
                        {
                            throw mapException(m.group(1), status);
                        }
                    }
                }
                catch (ResponseException re) {
                    throw re;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            throw mapException(response.message(), status);
        }

        if (parser != null)
            return parser.apply(this);

        if (returnType == Void.class) return null;
        
        return readEntity(returnType);
    }

    /**
     * Gets the status from the previous Request
     *
     * @return the status code
     */
    public int getStatus() {
        return response.code();
    }

    /**
     * @return the input stream
     */
    public InputStream getInputStream() {
        return response.body().byteStream();
    }

    /**
     * Returns a Header value from the specified name key
     *
     * @param name the name of the header to query for
     * @return the header as a String or null if not found
     */
    public String header(String name) {
        return response.header(name);
    }

    /**
     * @return the a Map of Header Name to Header Value
     */
    public Map<String, String> headers() {
        Map<String, String> retHeaders = new HashMap<String, String>();
        Headers headers =  response.headers();

        for (String name : headers.names()) {
            retHeaders.put(name, headers.get(name));
        }
        return retHeaders;
    }

    /**
     * Maps an Exception based on the underlying status code
     *
     * @param message the message
     * @param status the status
     * @return the response exception
     */
    public static ResponseException mapException(String message, int status) {
        return mapException(message, status, null);
    }

    /**
     * Maps an Exception based on the underlying status code
     *
     * @param message the message
     * @param status the status
     * @param cause the cause
     * @return the response exception
     */
    public static ResponseException mapException(String message, int status, Throwable cause) {
        if (status == 401)
            return new AuthenticationException(message, status, cause);
        if (status >= 400 && status < 499)
            return new ClientResponseException(message, status, cause);
        if (status >= 500 && status < 600)
            return new ServerResponseException(message, status, cause);

        return new ResponseException(message, status, cause);
    }

    @Override
    public <T> T readEntity(Class<T> typeToReadAs) {
        try {
            return ObjectMapperSingleton.getContext(typeToReadAs).reader(typeToReadAs).readValue(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientResponseException(e.getMessage(), 0, e);
        }
    }
}
