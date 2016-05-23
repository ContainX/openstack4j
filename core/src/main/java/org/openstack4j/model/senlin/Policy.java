package org.openstack4j.model.senlin;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Policy.
 * All getters map to the possible return values of
 * <code> GET /v1/policies/​{policy_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author lion
 * 
 */
public interface Policy extends ModelEntity {

	/**
	 * Returns the id of the policy
	 * 
	 * @return the id of the policy
	 */
	String getId();

	/**
	 * Returns the name of the policy
	 * 
	 * @return the name of the policy
	 */
	String getName();
}
