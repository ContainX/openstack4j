package org.openstack4j.core.transport.functions;

import java.util.Map;

import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.compute.ActionResponse;

import com.google.common.base.Function;

/**
 * Takes an HttpResponse as input and returns an ActionResponse as an output
 * 
 * @author Jeremy Unruh
 */
public class ResponseToActionResponse implements Function<HttpResponse, ActionResponse> {

    public static final ResponseToActionResponse INSTANCE = new ResponseToActionResponse();
    
    @Override
    public ActionResponse apply(HttpResponse response) {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = response.readEntity(Map.class);
        ActionResponse ar = ParseActionResponseFromJsonMap.INSTANCE.apply(map);
        if (ar != null)
            return ar;

        return ActionResponse.actionFailed(String.format("Status: %d, Reason: %s", response.getStatus(), response.getStatusMessage()));    
    }

}
