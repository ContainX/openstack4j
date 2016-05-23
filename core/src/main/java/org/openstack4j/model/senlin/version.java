package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of the version of senlin.
 * All getters map to the possible return values of
 * <code> GET /</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author lion
 * 
 */
public interface version extends ModelEntity {

	/**
	 * Returns the version of the senlin
	 * 
	 * @return the version of the senlin
	 */
	String getVersion();

}
