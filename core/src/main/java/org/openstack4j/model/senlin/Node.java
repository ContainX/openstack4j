package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a Node.
 * All getters map to the possible return values of
 * <code> GET /v1/nodes/​{node_id}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author lion
 * 
 */
public interface Node extends ModelEntity {

	/**
	 * Returns the id of the Node
	 * 
	 * @return the id of the Node
	 */
	String getId();

	/**
	 * Returns the name of the Node
	 * 
	 * @return the name of the Node
	 */
	String getName();
}
