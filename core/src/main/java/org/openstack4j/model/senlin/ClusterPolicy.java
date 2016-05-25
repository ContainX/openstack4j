package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.ResourceEntity;

/**
 * This interface describes the getter-methods (and thus components) of a ClusterPolicy.
 * All getters map to the possible return values of
 * <code> GET /v1/clusters/​{cluster_id}​/policies/​{policy_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-clustering-v1.html
 * 
 * @author lion
 * 
 */
public interface ClusterPolicy extends ModelEntity {

	/**
	 * Returns the id of the ClusterPolicy
	 * 
	 * @return the id of the ClusterPolicy
	 */
	String getId();

}
