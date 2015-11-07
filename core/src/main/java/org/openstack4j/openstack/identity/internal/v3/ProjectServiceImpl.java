package org.openstack4j.openstack.identity.internal.v3;

import org.openstack4j.api.identity.v3.ProjectService;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.openstack.identity.domain.v3.KeystoneProject;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_PROJECTS;

public class ProjectServiceImpl extends BaseOpenStackService implements ProjectService  {

	@Override
	public Project create(Project project) {
		checkNotNull(project);
		return post(KeystoneProject.class, PATH_PROJECTS).entity(project).execute();
	}

}
