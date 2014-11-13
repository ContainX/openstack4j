package org.openstack4j.core.transport;

import static org.openstack4j.core.transport.HttpExceptionHandler.mapException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openstack4j.api.exceptions.ResponseException;

import com.google.common.base.Function;

/**
 * Handles retrieving an Entity from an HttpResponse while validating resulting status codes. 
 * 
 * @author Jeremy Unruh
 */
public class HttpEntityHandler {
    
    private static final Pattern MESSAGE_PATTERN = Pattern.compile(".*message\\\":\\s\\\"([^\"]+)\\\".*");

    public static <T> T handle(HttpResponse response, Class<T> returnType, Function<HttpResponse, T> parser) {
        if(response.getStatus() >= 400) {
            if (response.getStatus() == 404)
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
            if (response.getStatus() < 500)
            {
                try
                {
                    String json = response.readEntity(String.class);
                    if (json != null && json.contains("message")) {
                        Matcher m = MESSAGE_PATTERN.matcher(json);
                        if (m.matches())
                        {
                            throw mapException(m.group(1), response.getStatus());
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
            throw mapException(response.getStatusMessage(),
                    response.getStatus());
        }

        if (parser != null)
            return parser.apply(response);

        if (returnType == Void.class) return null;
        return response.readEntity(returnType);
    }
    
}
