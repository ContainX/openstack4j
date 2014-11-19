package org.openstack4j.openstack.compute.functions;

import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;

import com.google.common.base.Function;

/**
 * A Function which consumes the input of an HttpResponse and returns the corresponding ActionResponse
 * 
 * @author Jeremy Unruh
 */
public class ToActionResponseFunction implements Function<HttpResponse, ActionResponse> {

    public static final ToActionResponseFunction INSTANCE = new ToActionResponseFunction();
    private static final Logger LOG = LoggerFactory.getLogger(ToActionResponseFunction.class);
    
    
    @Override
    public ActionResponse apply(HttpResponse response) {
       return apply(response, null);
    }
    
    public ActionResponse apply(HttpResponse response, String action) {
        if (response.getStatus() == 409)
        {
            LOG.error(response.getStatus() + "," + response.getStatusMessage());
            if (action == null)
                return ActionResponse.actionFailed("Instance currently is in build state");
            return ActionResponse.actionFailed(String.format("Cannot '%s' while instance in in state of %s", action, action));
        }
        if (response.getStatus() >= 400 && response.getStatus() < 409) {
            String message = response.readEntity(String.class);
            if (message != null && message.contains("message"))
                return ActionResponse.actionFailed(JsonToMessageFunction.INSTANCE.apply(message));
        }
        return ActionResponse.actionSuccess();
    }

}
