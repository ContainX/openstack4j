package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.OSFactory;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import java.util.List;

/**
 * Tests the Identity/Keystone API version 3
 */
@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneTests extends AbstractTest {

    //for authentication tests
    private static final String JSON_AUTH_PROJECT = "/identity.v3/authv3_project.json";
    private static final String JSON_AUTH_TOKEN = "/identity.v3/authv3_token.json";
    private static final ImmutableMap<String, String> HEADER_AUTH_PROJECT_RESPONSE = ImmutableMap.of("X-Subject-Token", "763fd7e197ab4e00b2e6e0a8d22a8e87", "Content-Type", "application/json");
    private static final ImmutableMap<String, String> HEADER_AUTH_TOKEN_RESPONSE = ImmutableMap.of("X-Subject-Token", "3ecb5c2063904566be4b10406c0f7568", "Content-Type", "application/json");

    private String userName = "admin";
    private String userId = "aa9f25defa6d4cafb48466df83106065";
    private String projectId = "123ac695d4db400a9001b91bb3b8aa46";
    private String projectDomainId = "default";
    private String password = "test";

    // for role tests
    private static final String JSON_ROLES_EMPTY = "/identity.v3/roles_empty.json";
    private static final String JSON_ROLES_ONE_ENTRY = "/identity.v3/roles_one_entry.json";
    private static final String JSON_ROLES_MULTIPLE_ENTRIES = "/identity.v3/roles_multiple_entries.json";
    private static final String JSON_ROLES_LIST = "/identity.v3/roles_list.json";
    private static final String ROLE_NAME = "admin";

    // for users
    private static final String JSON_USERS_ONE_ENTRY = "/identity.v3/users_one_entry.json";
    private static final String USER_NAME = "admin";

    //for projects
    private static final String JSON_PROJECTS_CREATE = "/identity.v3/projects_create_response.json";
    private static final String PROJECT_NAME = "ProjectX";
    private static final String PROJECT_DOMAIN_ID = "7a71863c2d1d4444b3e6c2cd36955e1e";
    private static final String PROJECT_DESCRIPTION = "";

    /**
     * @return the identity service
     */
    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Authentication Tests ------------

    /**
     * authenticates with userId+password+projectId+projectDomainId
     *
     * @throws Exception
     */
    @Test(enabled = true)
    public void authenticate_userId_password_projectId_projectDomainId_Test() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);
    }

    /**
     * authenticates with userName+password+projectId+projectDomainId
     *
     * @throws Exception
     */
    @Test(enabled = true)
    public void authenticate_userName_password_projectId_projectDomainId_Test() throws Exception {

        respondWith(JSON_AUTH_PROJECT);

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userName, password)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);
    }

    /**
     * authenticates with a token+projectId+projectDomainId
     *
     * @throws Exception
     */
    @Test(enabled = true)
    public void auth_Token_Test() throws Exception {

        respondWithHeaderAndResource(HEADER_AUTH_PROJECT_RESPONSE, 201, JSON_AUTH_PROJECT);

        OSClient myOs = OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .credentials(userId, password)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate();

        respondWithHeaderAndResource(HEADER_AUTH_TOKEN_RESPONSE, 200, JSON_AUTH_TOKEN);

        String tokenId = myOs.getToken().getId();

        associateClient(OSFactory.builderV3()
                .endpoint(authURL("/v3"))
                .token(tokenId)
                .scopeToProject(Identifier.byId(projectId), Identifier.byId(projectDomainId))
                .authenticate());

        String newTokenId = osv3().getToken().getId();

        assertTrue(newTokenId != tokenId);
        assertEquals(osv3().getToken().getVersion(), AuthVersion.V3);
        assertEquals(osv3().getAccess().getUser().getId(), userId);
    }

    // ------------ Role Tests ------------

    @Test(expectedExceptions = NullPointerException.class)
    public void roles_getByName_with_null_throws_NullPointerException() {
        osv3().identityV3().roles().getByName(null);
    }

    public void roles_empty_list_when_none_found() throws Exception {
        respondWith(JSON_ROLES_EMPTY);
        List<? extends Role> list = osv3().identityV3().roles().getByName(ROLE_NAME);
        assertTrue(list.isEmpty());
    }

    public void roles_one_entry_in_list_when_one_returned() throws Exception {
        respondWith(JSON_ROLES_ONE_ENTRY);
        List<? extends Role> list = osv3().identityV3().roles().getByName(ROLE_NAME);
        assertTrue(list.size()==1);
    }

    public void roles_list_with_multiple_entries_for_list_of_roles() throws Exception {
        respondWith(JSON_ROLES_MULTIPLE_ENTRIES);
        List<? extends Role> list = osv3().identityV3().roles().getByName(ROLE_NAME);
        assertTrue(list.size() > 1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addRoletoUserInProject_projectIdMustBeNonNull() throws Exception {
        osv3().identityV3().roles().addRoleToUserInProject(null, "fake", "fake");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addRoletoUserInProject_userIdMustBeNonNull() throws Exception {
        osv3().identityV3().roles().addRoleToUserInProject("fake", null, "fake");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addRoletoUserInProject_roleIdMustBeNonNull() throws Exception {
        osv3().identityV3().roles().addRoleToUserInProject("fake", "fake", null);
    }

    public void addRoleToUserInProject_check_correct_response() throws Exception {
        respondWith(204);
        ActionResponse actionResponse = osv3().
                identityV3().
                roles().
                addRoleToUserInProject("fake", "fake", "fake");
        assertTrue(actionResponse.isSuccess());
    }

    public void list_roles() throws Exception {
        respondWith(JSON_ROLES_LIST);
        List<? extends Role> list = osv3().identityV3().roles().list();
        assertEquals(list.size(), 6);
    }

    // ------------ User Tests ------------
    @Test(expectedExceptions = NullPointerException.class)
    public void users_getByName_does_not_accept_null() throws Exception {
        osv3().identityV3().users().getByName(null);
    }

    public void users_getByName_for_one_name() throws Exception {
        respondWith(JSON_USERS_ONE_ENTRY);
        List<? extends User> userList = osv3().identityV3().users().getByName(USER_NAME);
        assertTrue(userList.size()==1);
    }

    // ------------ Project Tests ------------
    @Test(expectedExceptions = NullPointerException.class)
    public void projects_create_does_not_accept_null() throws Exception {
        osv3().identityV3().projects().create(null);
    }

    public void projects_create_project() throws Exception {
        Project project = Builders.project()
                .name(PROJECT_NAME)
                .description(PROJECT_DESCRIPTION)
                .domainId(PROJECT_DOMAIN_ID)
                .enabled(true)
                .build();

        respondWith(JSON_PROJECTS_CREATE);

        Project newProject = osv3().identityV3().projects().create(project);

        assertEquals(newProject.getName(),PROJECT_NAME);
        assertEquals(newProject.getDomainId(), PROJECT_DOMAIN_ID);
        assertEquals(newProject.getDescription(), PROJECT_DESCRIPTION);
    }
}
