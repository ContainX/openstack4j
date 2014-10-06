package org.openstack4j.api.heat;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.heat.Stack;
import org.openstack4j.model.heat.StackCreate;

/**
 * This interface defines all methods for the manipulation of stacks
 * 
 * @author Matthias Reisser
 * 
 */
public interface StackService {
	/**
	 * <code>POST /v1/{tenant_id}/stacks</code><br \>
	 * 
	 * Creates a new {@link Stack} out of a {@link StackCreate} object
	 * 
	 * @param newStack
	 *            {@link StackCreate} object out of which stack is to be created
	 * @return new {@link Stack} as returned from the server
	 */
	Stack create(StackCreate newStack);

	/**
	 * <code> POST /v1/{tenant_id}/stacks </code> <br\>
	 * Creates a new {@link StackCreate} Object and returns a new {@link Stack} as sent from the
	 * server.
	 * 
	 * @param name
	 *            Name of Stack
	 * @param template
	 *            Template in Json-Format
	 * @param parameters
	 *            Map of parameters
	 * @param disableRollback boolean to enable or disable rollback
	 * @param timeOutMins timeout in minutes
	 * @return new {@link Stack} as returned from the server
	 */
	Stack create(String name, String template, Map<String, String> parameters,
			String disableRollback, Long timeOutMins);

	/**
	 * Gets a list of currently existing {@link Stack}s.
	 * 
	 * @return the list of {@link Stack}s
	 */
	List<? extends Stack> list();

	/**
	 * returns details of a {@link Stack}.
	 * 
	 * @param stackName
	 *            Name of {@link Stack}
	 * @param stackId
	 *            Id of {@link Stack}
	 * @return
	 */
	Stack getDetails(String stackName, String stackId);

	/**
	 * Deletes the specified {@link Stack} from the server.
	 * 
	 * @param stackName
	 *            Name of {@link Stack}
	 * @param stackId
	 *            Id of {@link Stack}
	 */
	void delete(String stackName, String stackId);


}
