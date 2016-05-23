package org.openstack4j.api.senlin;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.senlin.Action;
import org.openstack4j.model.senlin.Node;
import org.openstack4j.model.senlin.NodeActionCreate;
import org.openstack4j.model.senlin.NodeCreate;

import java.util.List;

/**
 * This interface defines all methods for the manipulation of Node
 * 
 * @author lion
 * 
 */
public interface SenlinNodeService {
	
	/**
	 * Gets a list of currently existing {@link Node}s.
	 * 
	 * @return the list of {@link Node}s
	 */
	List<? extends Node> list();

	/**
	 * <code>POST /v1/nodes</code><br \>
	 *
	 * Creates a new {@link Node} out of a {@link NodeCreate} object
	 *
	 * @param newNode
	 *            {@link NodeCreate} object out of which node is to be created
	 * @return new {@link Node} as returned from the server
	 */
	Node create(NodeCreate newNode);

	/**
	 * returns details of a {@link Node}.
	 *
	 * @param nodeID
	 *            Id of {@link Node}
	 * @return Node
	 */
	Node get(String nodeID);

	/**
	 * Deletes the specified {@link Node} from the server.
	 *
	 * @param nodeID
	 *            Id of {@link Node}
	 * @return the action response
	 */
	ActionResponse delete(String nodeID);

	/**
	 * Service implementation which provides methods for manipulation of action
	 *
	 * @return Action
	 */
	Action action(String nodeID, NodeActionCreate newNodeAction);
}
