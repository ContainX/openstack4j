package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.identity.v3.Project;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 ProjectService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneProjectServiceTests extends AbstractTest {

    private static final String JSON_PROJECTS_CREATE = "/identity/v3/projects_create_response.json";
    private static final String JSON_PROJECTS_UPDATE = "/identity/v3/projects_update_response.json";
    private static final String JSON_PROJECTS_GET_BYID = "/identity/v3/projects_get_byId.json";
    private static final String PROJECT_NAME = "ProjectX";
    private static final String PROJECT_DOMAIN_ID = "7a71863c2d1d4444b3e6c2cd36955e1e";
    private static final String PROJECT_DESCRIPTION = "Project used for CRUD tests";
    private static final String PROJECT_DESCRIPTION_UPDATE = "An updated project used for CRUD tests";
    private String PROJECT_ID;

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Project Tests ------------

    // The following tests are to verify the update() method of the
    // ProjectService
    // using HTTP PATCH, which is not supported by betamax.
    // Find more tests in KeystoneGroupServiceSpec in core-integration-test
    // module.

    @Test(expectedExceptions = NullPointerException.class)
    public void projects_create_does_not_accept_null() throws Exception {
        osv3().identity().projects().create(null);
    }

    public void projects_crud_test() throws Exception {

        Project project = Builders.project().name(PROJECT_NAME).description(PROJECT_DESCRIPTION)
                .domainId(PROJECT_DOMAIN_ID).enabled(true).build();

        respondWith(JSON_PROJECTS_CREATE);

        Project newProject = osv3().identity().projects().create(project);

        assertEquals(newProject.getName(), PROJECT_NAME);
        assertEquals(newProject.getDomainId(), PROJECT_DOMAIN_ID);
        assertEquals(newProject.getDescription(), PROJECT_DESCRIPTION);

        PROJECT_ID = newProject.getId();

        respondWith(JSON_PROJECTS_GET_BYID);

        Project project_setToUpdate = osv3().identity().projects().get(PROJECT_ID);

        respondWith(JSON_PROJECTS_UPDATE);

        Project updatedProject = osv3().identity().projects()
                .update(project_setToUpdate.toBuilder().description(PROJECT_DESCRIPTION_UPDATE).build());

        assertEquals(updatedProject.getId(), PROJECT_ID);
        assertEquals(updatedProject.getName(), PROJECT_NAME);
        assertEquals(updatedProject.getDomainId(), PROJECT_DOMAIN_ID);
        assertEquals(updatedProject.getDescription(), PROJECT_DESCRIPTION_UPDATE);

    }

}
