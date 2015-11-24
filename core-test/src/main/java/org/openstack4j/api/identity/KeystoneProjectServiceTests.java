package org.openstack4j.api.identity;

import static org.testng.Assert.assertEquals;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.identity.Project;
import org.testng.annotations.Test;

public class KeystoneProjectServiceTests extends AbstractTest {

    private static final String JSON_PROJECTS_CREATE = "/identity/projects_create_response.json";
    private static final String PROJECT_NAME = "ProjectX";
    private static final String PROJECT_DOMAIN_ID = "7a71863c2d1d4444b3e6c2cd36955e1e";
    private static final String PROJECT_DESCRIPTION = "";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

 // ------------ Project Tests ------------
    @Test(expectedExceptions = NullPointerException.class)
    public void projects_create_does_not_accept_null() throws Exception {
        os().identity().projects().create(null);
    }

    public void projects_create_project() throws Exception {
        Project project = Builders.project()
                .name(PROJECT_NAME)
                .description(PROJECT_DESCRIPTION)
                .domainId(PROJECT_DOMAIN_ID)
                .enabled(true)
                .build();

        respondWith(JSON_PROJECTS_CREATE);

        Project newProject = os().identity().projects().create(project);

        assertEquals(newProject.getName(),PROJECT_NAME);
        assertEquals(newProject.getDomainId(), PROJECT_DOMAIN_ID);
        assertEquals(newProject.getDescription(), PROJECT_DESCRIPTION);
    }

}
