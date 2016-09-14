package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNull;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Group;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.identity.v3.domain.KeystoneUser;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 UserService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneUserServiceTests extends AbstractTest {

    private static final String JSON_USERS = "/identity/v3/users.json";
    private static final String JSON_USER_GET_BYID = "/identity/v3/user_get_byId.json";
    private static final String JSON_USER_GET_BYNAME = "/identity/v3/user_get_byName.json";
    private static final String JSON_USER_GET_BYNAME_BYDOMAINID = "/identity/v3/user_get_byName_byDomainId.json";
    private static final String JSON_USER_GET_BYNAME_BYDOMAINID_NOT_EXIST = "/identity/v3/user_get_byName_byDomainId_not_exist.json";
    private static final String JSON_USER_CREATE = "/identity/v3/create_user.json";
    private static final String JSON_USER_READ = "/identity/v3/read_user.json";
    private static final String JSON_USER_UPDATE = "/identity/v3/update_user.json";
    private static final String JSON_USER_LISTDOMAINUSERROLES = "/identity/v3/list_domain_user_roles.json";
    private static final String JSON_USER_LISTPROJECTUSERROLES = "/identity/v3/list_project_user_roles.json";
    private static final String JSON_USER_LISTUSERGROUPS = "/identity/v3/list_user_groups.json";
    private static final String JSON_USER_LISTUSERPROJECTS = "/identity/v3/list_user_projects.json";
    private static final String JSON_USER_DELETE_FAIL = "/identity/v3/user_delete_fail.json";
    private static final String JSON_USER_CHANGE_PASSWORD_FAIL = "/identity/v3/user_changeUserPassword_fail.json";

    // module test
    private static final String USER_NAME = "admin";
    private static final String USER_ID = "aa9f25defa6d4cafb48466df83106065";
    private static final String USER_DOMAIN_ID = "default";
    private static final String PROJECT_ID = "123ac695d4db400a9001b91bb3b8aa46";
	

    /**
     * @return the identity service
     */
    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ UserTests ------------

    /**
     * gets a list of users
     *
     * @throws Exception
     */
    public void listAllUsers_Test() throws Exception {

        respondWith(JSON_USERS);

        List<? extends User> userList = osv3().identity().users().list();

        assertNotNull(userList);

    }

    /**
     * get speciific user by user identifier
     *
     * @throws Exception
     */
    public void getUser_byId_Test() throws Exception {

        respondWith(JSON_USER_GET_BYID);

        User user = osv3().identity().users().get(USER_ID);

        assertEquals(user.getId(), USER_ID);
        assertEquals(user.getName(), USER_NAME);
        assertEquals(user.getDomainId(), USER_DOMAIN_ID);

    }

    /**
     * returns users across all domains matching given name
     *
     * @throws Exception
     */
    public void getUsers_byName_AcrossAllDomains_Test() throws Exception {

        respondWith(JSON_USER_GET_BYNAME);

        List<? extends User> adminusers = osv3().identity().users().getByName(USER_NAME);

        assertEquals(adminusers.size(), 2);
    }

    /**
     * returns the user specified by name and domain.
     *
     * @throws Exception
     */
    public void getUser_byName_byDomainId_Test() throws Exception {

        respondWith(JSON_USER_GET_BYNAME_BYDOMAINID);

        User user = osv3().identity().users().getByName(USER_NAME, USER_DOMAIN_ID);

        assertEquals(user.getName(), USER_NAME);
        assertEquals(user.getId(), USER_ID);
        assertEquals(user.getDomainId(), USER_DOMAIN_ID);
    }
    
    /**
     * returns null for an non-existing user when the user specified by name and domain.
     *
     * @throws Exception
     */
    public void getUser_byName_byDomainId_NotExist_Test() throws Exception {

        respondWith(JSON_USER_GET_BYNAME_BYDOMAINID_NOT_EXIST);

        User user = osv3().identity().users().getByName(USER_NAME, USER_DOMAIN_ID);
        assertNull(user);
    }

    /**
     * CRUD user tests
     *
     * @throws Exception
     */
    public void crud_User_Test() throws Exception {

        // --create

        respondWith(JSON_USER_CREATE);

        User newUser = osv3().identity().users().create(USER_DOMAIN_ID, "foobar", "secret", "foobar@example.org", true);
        assertEquals(newUser.getName(), "foobar");
        assertEquals(newUser.getDomainId(), USER_DOMAIN_ID);
        assertEquals(newUser.getEmail(), "foobar@example.org");
        assertEquals(newUser.isEnabled(), true);
        assertNotNull(newUser.getId());

        // --read

        respondWith(JSON_USER_READ);

        User user = osv3().identity().users().getByName("foobar", USER_DOMAIN_ID);
        assertEquals(user.getId(), newUser.getId());
        assertEquals(user.getName(), "foobar");
        assertEquals(user.getEmail(), "foobar@example.org");
        assertEquals(user.isEnabled(), true);
        assertEquals(user.getDomainId(), USER_DOMAIN_ID);

        String crudUserId = user.getId();

        // --update

        respondWith(JSON_USER_UPDATE);

        User updatedUser = osv3().identity().users().update(KeystoneUser.builder()
                                                                    .id(crudUserId)
                                                                    .email("updatedFoobar@example.org")
                                                                    .enabled(true)
                                                                    .build());

        assertEquals(updatedUser.getEmail(),"updatedFoobar@example.org");
        assertEquals(updatedUser.getName(), "foobar");
        assertEquals(updatedUser.isEnabled(), true);
        assertEquals(updatedUser.getId(), crudUserId);
        assertEquals(updatedUser.getDomainId(), USER_DOMAIN_ID);

        // --delete

        respondWith(204);

        ActionResponse response_delete = osv3().identity().users().delete(crudUserId);
        assertTrue(response_delete.isSuccess());
    }

    /**
     * tries to delete an non existent user fails
     *
     * @throws Exception
     */
    public void deleteUser_fail_Test() throws Exception {

        respondWithCodeAndResource(404, JSON_USER_DELETE_FAIL);

        ActionResponse response_delete_fail = osv3().identity().users().delete("invalidUser");
        assertFalse(response_delete_fail.isSuccess());
    }

    /**
     * list roles for a user in domain context
     *
     * @throws Exception
     */
    public void listDomainUserRoles_Test() throws Exception {

        respondWith(JSON_USER_LISTDOMAINUSERROLES);

        List<? extends Role> domainUserRolesList = osv3().identity().users().listDomainUserRoles(USER_ID, USER_DOMAIN_ID);
        assertEquals(domainUserRolesList.size(), 1);
    }

    /**
     * list roles for user in project context
     *
     * @throws Exception
     */
    public void listProjectUserRoles_Test() throws Exception {

        respondWith(JSON_USER_LISTPROJECTUSERROLES);

        List<? extends Role> projectUserRolesList = osv3().identity().users().listProjectUserRoles(USER_ID, PROJECT_ID);
        assertEquals(projectUserRolesList.size(), 1);
    }

    /**
     * lists the groups for user
     *
     * @throws Exception
     */
    public void listUserGroups_Test() throws Exception {

        respondWith(JSON_USER_LISTUSERGROUPS);

        List<? extends Group> userGroupsList = osv3().identity().users().listUserGroups(USER_ID);
        assertEquals(userGroupsList.size(), 2);
    }

    /**
     * list the projects given user has access to
     *
     * @throws Exception
     */
    public void listUserProjects_Test() throws Exception {

        respondWith(JSON_USER_LISTUSERPROJECTS);

        List<? extends Project> userProjectsList = osv3().identity().users().listUserProjects(USER_ID);
        assertEquals(userProjectsList.size(), 2);
    } 
    
    /**
     * Changes the password for a user
     *
     * @throws Exception
     */
    public void changeUserPassword_Test() throws Exception {
    	
    	respondWith(204);

        ActionResponse response_changeUserPassword = osv3().identity().users().changePassword("aa9f25defa6d4cafb48466df83106065", "originalPassword", "password");
        assertTrue(response_changeUserPassword.isSuccess());
    }
    
    /**
     * tries to change the password for an non existent user fails
     *
     * @throws Exception
     */
    public void changeUserPassword_fail_Test() throws Exception {

        respondWithCodeAndResource(404, JSON_USER_CHANGE_PASSWORD_FAIL);

        ActionResponse response_changePassword_fail = osv3().identity().users().changePassword("invalidUser", "OriginalPassword", "password");
        assertFalse(response_changePassword_fail.isSuccess());
    }

}
