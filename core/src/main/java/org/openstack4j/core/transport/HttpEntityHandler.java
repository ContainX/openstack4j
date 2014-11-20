package org.openstack4j.core.transport;

import static org.openstack4j.core.transport.HttpExceptionHandler.mapException;

import java.util.Map;

import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.core.transport.functions.ParseActionResponseFromJsonMap;
import org.openstack4j.model.compute.ActionResponse;

import com.google.common.base.Function;

/**
 * Handles retrieving an Entity from an HttpResponse while validating resulting status codes. 
 * 
 * @author Jeremy Unruh
 */
public class HttpEntityHandler {
    
    public static <T> T handle(HttpResponse response, Class<T> returnType, Function<HttpResponse, T> parser) {
        return handle(response, returnType, parser, Boolean.FALSE);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T handle(HttpResponse response, Class<T> returnType, Function<HttpResponse, T> parser, boolean requiresVoidBodyHandling) {
        if(response.getStatus() >= 400) {
            
            if (requiresVoidBodyHandling && ActionResponse.class == returnType) {
                return (T) createActionResponse(response);
            }
            
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
                if (returnType != ActionResponse.class)
                    return null;
            }
            if (response.getStatus() < 500)
            {
                try
                {
                    
                    ActionResponse ar = createActionResponse(response);
                    if (returnType == ActionResponse.class)
                        return (T) ar;
                    
                    throw mapException(ar.getFault(), response.getStatus());
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

        if (returnType == Void.class) 
            return null;
        if (returnType == ActionResponse.class) 
            return (T) ActionResponse.actionSuccess();
         return response.readEntity(returnType);
    }
    
    @SuppressWarnings("unchecked")
    private static ActionResponse createActionResponse(HttpResponse response) {
        Map<String, Object> map = response.readEntity(Map.class);
        ActionResponse ar = ParseActionResponseFromJsonMap.INSTANCE.apply(map);
        if (ar != null)
            return ar;
        
        return ActionResponse.actionFailed(String.format("Status: %d, Reason: %s", response.getStatus(), response.getStatusMessage()));
    }
    
}
