package org.openstack4j.model.heat;

import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.heat.builder.StackCreateBuilder;

/**
 * This interface describes the model of a {@link Stack}, before it is sent to
 * the server for creation
 * 
 * @author Matthias Reisser
 * 
 */
public interface StackCreate extends ModelEntity, Buildable<StackCreateBuilder> {

	boolean getDisableRollback();

	/**
	 * Returns the name of the stack to create
	 * 
	 * @return the name of the stack to create
	 */
	String getName();

	// Future versions: Replace with Template-Object
	/**
	 * Returns the Heat template if it was stored in JSON format or YAML format
	 * 
	 * @return the JSON or YAML formatted template out of which the stack is to be
	 *         created. Returns <code> null </code> if no JSON formatted template has been set.
	 */
	String getTemplate();

	/**
	 * Returns the parameters which are used for creation of the stack
	 * 
	 * @return Map of parameters. This map is <code> null </code> if no
	 *         parameter has been set. Returns empty if no parameter has been
	 *         set.
	 */
	Map<String, String> getParameters();

}
