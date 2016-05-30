package org.openstack4j.model.senlin;

import org.openstack4j.model.ResourceEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Action.
 * All getters map to the possible return values of
 * <code> GET /v1/actions/​{action_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface Action extends ResourceEntity {

    /**
     * Returns the action name of the action
     *
     * @return the action name of the action
     */
    String getAction();
}
