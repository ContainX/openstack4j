package org.openstack4j.model.heat.builder;

import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.heat.StackCreate;

/**
 * This interface describes a builder for {@link StackCreate} objects
 * 
 * @author Matthias Reisser
 */
public interface StackCreateBuilder extends Buildable.Builder<StackCreateBuilder, StackCreate> {
	
	StackCreateBuilder name(String name);
	
	/**
	 * Sets the template in YAML/JSON format.  If the template begins with a "{" then JSON is assumed
	 * @param template the template
	 * @return StackCreateBuilder
	 */
	StackCreateBuilder template(String template);
	
	/**
     * Sets the template URL
     * @param template the template URL
     * @return StackCreateBuilder
     */
    StackCreateBuilder templateURL(String templateURL);
	
	/**
	 * Sets the parameters which are passed to the server. It might contain Information about flavor, image, etc.
	 * @param parameters Map of parameters. Key is name, value is the value of the parameters
	 * @return the modified StackCreateBuilder
	 */
	StackCreateBuilder parameters(Map<String,String> parameters);
	
	/**
	 * Sets the stack creation timeout in minutes
	 * @param timeoutMins timeout in minutes
	 * @return the modified StackCreateBuilder
	 */
	StackCreateBuilder timeoutMins(Long timeoutMins);
	
	/**
	 * sets the boolean for whether or not rollback is enabled or not
	 * @param disableRollback boolean value for disabling rollback
	 * @return the modified StackCreateBuilder
	 */
	StackCreateBuilder disableRollback(boolean disableRollback);
	

}
