package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Action.
 * All getters map to the possible return values of
 * <code> GET /v1/actions/​{action_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author lion
 * 
 */
public interface Action extends ModelEntity {

	/**
	 * Returns the id of the Action
	 * 
	 * @return the id of the Action
	 */
	String getId();

	/**
	 * Returns the name of the Action
	 * 
	 * @return the name of the Action
	 */
	String getName();

}
