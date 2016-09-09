package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.ResourceEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This interface describes the getter-methods (and thus components) of a response Action.
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface ActionID extends ModelEntity {

    /**
     * Returns the action ID of the action
     *
     * @return the action ID of the action
     */
    String getActionID();

}
