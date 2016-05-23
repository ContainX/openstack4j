package org.openstack4j.model.senlin;

import org.openstack4j.model.ModelEntity;

/**
 * This interface describes the getter-methods (and thus components) of a ProfileType.
 * All getters map to the possible return values of
 * <code> GET /v1/profile-types/​{profile_type}​</code>
 * 
 * @see http://developer.openstack.org/api-ref-orchestration-v1.html
 * 
 * @author lion
 * 
 */
public interface ProfileType extends ModelEntity {

	/**
	 * Returns the name of the ProfileType
	 * 
	 * @return the name of the ProfileType
	 */
	String getName();
}
