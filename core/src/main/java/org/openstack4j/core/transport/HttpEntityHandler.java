package org.openstack4j.core.transport;

import static org.openstack4j.core.transport.HttpExceptionHandler.mapException;

import org.openstack4j.api.exceptions.ResponseException;
import org.openstack4j.core.transport.functions.ResponseToActionResponse;
import org.openstack4j.model.compute.ActionResponse;

/**
 * Handles retrieving an Entity from an HttpResponse while validating resulting status codes. 
 * 
 * @author Jeremy Unruh
 */
public class HttpEntityHandler {
    
    public static <T> T handle(HttpResponse response, Class<T> returnType, ExecutionOptions<T> options) {
        return handle(response, returnType, options, Boolean.FALSE);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T handle(HttpResponse response, Class<T> returnType, ExecutionOptions<T> options, boolean requiresVoidBodyHandling) {
        if(response.getStatus() >= 400) {
            
            if (requiresVoidBodyHandling && ActionResponse.class == returnType) {
                return (T) ResponseToActionResponse.INSTANCE.apply(response);
            }
            
            if (options != null)
                options.propagate(response);
            
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
                    
                    ActionResponse ar = ResponseToActionResponse.INSTANCE.apply(response);
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

        if (options != null && options.hasParser())
            return options.getParser().apply(response);

        if (returnType == Void.class) 
            return null;
        if (returnType == ActionResponse.class) 
            return (T) ActionResponse.actionSuccess();
         return response.readEntity(returnType);
    }
}
