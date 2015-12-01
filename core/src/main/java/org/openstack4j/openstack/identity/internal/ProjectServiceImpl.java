package org.openstack4j.openstack.identity.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.openstack4j.core.transport.ClientConstants.PATH_PROJECTS;

import org.openstack4j.api.identity.ProjectService;
import org.openstack4j.model.identity.Project;
import org.openstack4j.openstack.identity.domain.KeystoneProject;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class ProjectServiceImpl extends BaseOpenStackService implements ProjectService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Project create(Project project) {
        checkNotNull(project);
        return post(KeystoneProject.class, PATH_PROJECTS).entity(project).execute();
    }

}
