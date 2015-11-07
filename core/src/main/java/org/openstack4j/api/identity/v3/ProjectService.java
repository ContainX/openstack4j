package org.openstack4j.api.identity.v3;

import org.openstack4j.common.RestService;
import org.openstack4j.model.identity.v3.Project;

/**
 * Identity Project Service
 * 
 */
public interface ProjectService extends RestService {

	/**
	 * Creates a new Project
	 *
	 * @param project the project to create
	 * @return the new Project including it's id
	 */
	Project create(Project project);

}
