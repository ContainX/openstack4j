package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Cluster.
 * All getters map to the possible return values of
 * <code> GET /v1/clusters/​{cluster_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author lion
 * 
 */
public interface Cluster extends ModelEntity {

	/**
	 * Returns the id of the Cluster
	 * 
	 * @return the id of the Cluster
	 */
	String getId();

	/**
	 * Returns the name of the Cluster
	 * 
	 * @return the name of the Cluster
	 */
	String getName();
}
