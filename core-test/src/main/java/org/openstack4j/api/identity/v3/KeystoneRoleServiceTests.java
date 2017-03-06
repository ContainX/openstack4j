package org.openstack4j.api.identity.v3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.RoleAssignment;
import org.testng.annotations.Test;

/**
 * Tests the Identity/Keystone API version 3 RoleService
 */
@Test(groups = "idV3", suiteName = "Identity/Keystone_V3")
public class KeystoneRoleServiceTests extends AbstractTest {

    private static final String JSON_ROLES_EMPTY = "/identity/v3/roles_empty.json";
    private static final String JSON_ROLES_ONE_ENTRY = "/identity/v3/roles_one_entry.json";
    private static final String JSON_ROLES_MULTIPLE_ENTRIES = "/identity/v3/roles_multiple_entries.json";
    private static final String JSON_ROLES_LIST = "/identity/v3/roles_list.json";
    private static final String JSON_ROLES_REVOKEROLE_ERROR = "/identity/v3/roles_revokeRole_error.json";
    private static final String JSON_ROLES_GRANTROLE_ERROR = "/identity/v3/roles_grantRole_error.json";
    private static final String JSON_ROLES_ASSIGNMENT_LIST = "/identity/v3/roles_assignment_list.json";
    private static final String JSON_ROLES_UPDATE = "/identity/v3/roles_update.json";
    private static final String JSON_ROLES_GET_BYID = "/identity/v3/roles_get_byId.json";
    private static final String ROLE_NAME = "admin";
    private static final String USER_ID = "aa9f25defa6d4cafb48466df83106065";
    private static final String PROJECT_ID = "123ac695d4db400a9001b91bb3b8aa46";
    private static final String ROLE_ID = "aae88952465d4c32b0a1140a76601b68";
    private static final String USER_DOMAIN_ID = "default";
    private static final String ROLE_NAME_UPDATE = "cloudAdmin";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    // ------------ Role Tests ------------

    @Test(expectedExceptions = NullPointerException.class)
    public void roles_getByName_with_null_throws_NullPointerException() {
        osv3().identity().roles().getByName(null);
    }

    public void roles_empty_list_when_none_found() throws Exception {
        respondWith(JSON_ROLES_EMPTY);
        List<? extends Role> list = osv3().identity().roles().getByName(ROLE_NAME);
        assertTrue(list.isEmpty());
    }

    public void roles_one_entry_in_list_when_one_returned() throws Exception {
        respondWith(JSON_ROLES_ONE_ENTRY);
        List<? extends Role> list = osv3().identity().roles().getByName(ROLE_NAME);
        assertTrue(list.size() == 1);
        assertEquals(list.get(0).getId(),ROLE_ID);
        assertEquals(list.get(0).getName(), ROLE_NAME);
        assertEquals(list.get(0).getDomainId(), USER_DOMAIN_ID);
    }

    public void roles_list_with_multiple_entries_for_list_of_roles() throws Exception {
        respondWith(JSON_ROLES_MULTIPLE_ENTRIES);
        List<? extends Role> list = osv3().identity().roles().getByName(ROLE_NAME);
        assertTrue(list.size() > 1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addRoletoUserInProject_projectIdMustBeNonNull() throws Exception {
        osv3().identity().roles().grantProjectUserRole(null, "fake", "fake");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addRoletoUserInProject_userIdMustBeNonNull() throws Exception {
        osv3().identity().roles().grantProjectUserRole("fake", null, "fake");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addRoletoUserInProject_roleIdMustBeNonNull() throws Exception {
        osv3().identity().roles().grantProjectUserRole("fake", "fake", null);
    }

    public void addRoleToUserInProject_check_correct_response() throws Exception {
        respondWith(204);
        ActionResponse actionResponse = osv3().identity().roles().grantProjectUserRole("fake", "fake", "fake");
        assertTrue(actionResponse.isSuccess());
    }

    public void list_roles() throws Exception {
        respondWith(JSON_ROLES_LIST);
        List<? extends Role> list = osv3().identity().roles().list();
        assertEquals(list.size(), 6);
    }

    /**
     * checks if a user has a role in project context
     *
     * @throws Exception
     */
    public void checkProjectUserRole_success_Test() throws Exception {

        respondWith(204);

        ActionResponse response_success = osv3().identity().roles().checkProjectUserRole(PROJECT_ID, USER_ID, ROLE_ID);
        assertTrue(response_success.isSuccess());
    }

    // TODO: this test is disabled due to a malformed response returned by
    // OpenStack as described in issue #530
    /**
     * checks if a user has a role in domain context
     *
     * @throws Exception
     */
    @Test(enabled = false)
    public void checkProjectUserRole_fail_Test() throws Exception {

        respondWith(404);

        ActionResponse response_success = osv3().identity().roles().checkProjectUserRole(PROJECT_ID, USER_ID,
                "existingUnassignedRoleId");
        assertFalse(response_success.isSuccess());

    }

    /**
     * grants and revokes a role to/from a user in project context
     *
     * @throws Exception
     */
    public void grantRevokeProjectUserRole_Test() throws Exception {

        respondWith(204);

        ActionResponse result_grant = osv3().identity().roles().grantProjectUserRole(PROJECT_ID, USER_ID, ROLE_ID);
        assertTrue(result_grant.isSuccess());

        respondWith(204);

        ActionResponse result_revoke = osv3().identity().roles().revokeProjectUserRole(PROJECT_ID, USER_ID, ROLE_ID);
        assertTrue(result_revoke.isSuccess());

    }

    /**
     * try to grant a project role to a user using role that doesn't exist
     * results in failing ActionResponse
     *
     * @throws Exception
     */
    public void grantProjectUserRole_fail_Test() throws Exception {

        respondWithCodeAndResource(404, JSON_ROLES_GRANTROLE_ERROR);

        ActionResponse response_fail = osv3().identity().roles().grantProjectUserRole(PROJECT_ID, USER_ID,
                "nonExistingRoleId");
        assertFalse(response_fail.isSuccess());

    }

    /**
     * try to revoke a project role from a user that isn't assigned to him
     * results in failing ActionResponse
     *
     * @throws Exception
     */
    public void revokeProjectUserRole_fail_Test() throws Exception {

        respondWithCodeAndResource(404, JSON_ROLES_REVOKEROLE_ERROR);

        ActionResponse response_fail = osv3().identity().roles().revokeProjectUserRole(PROJECT_ID, USER_ID,
                "existingUnassignedRoleId");
        assertFalse(response_fail.isSuccess());
    }

    /**
     * checks if a user has a role in domain context
     *
     * @throws Exception
     */
    public void checkDomainUserRole_success_Test() throws Exception {

        respondWith(204);

        ActionResponse response_success = osv3().identity().roles().checkDomainUserRole(USER_DOMAIN_ID, USER_ID, ROLE_ID);
        assertTrue(response_success.isSuccess());
    }

    // TODO: this test is disabled due to a malformed response returned by
    // OpenStack as described in issue #530
    /**
     * checks if a user has a role in domain context
     *
     * @throws Exception
     */
    @Test(enabled = false)
    public void checkDomainUserRole_fail_Test() throws Exception {

        respondWith(404);

        ActionResponse response_success = osv3().identity().roles().checkDomainUserRole(USER_DOMAIN_ID, USER_ID,
                "existingUnassignedRoleId");
        assertFalse(response_success.isSuccess());

    }

    /**
     * grants and revokes a role to/from a user in domain context
     *
     * @throws Exception
     */
    public void grantRevokeDomainUserRole_Test() throws Exception {

        respondWith(204);

        ActionResponse result_grant = osv3().identity().roles().grantDomainUserRole(USER_DOMAIN_ID, USER_ID, ROLE_ID);
        assertTrue(result_grant.isSuccess());

        respondWith(204);

        ActionResponse result_revoke = osv3().identity().roles().revokeDomainUserRole(USER_DOMAIN_ID, USER_ID, ROLE_ID);
        assertTrue(result_revoke.isSuccess());

    }

    /**
     * try to grant a domain role to a user using a role that doesn't exist
     * results in failing ActionResponse
     *
     * @throws Exception
     */
    public void grantDomainUserRole_fail_Test() throws Exception {

        respondWithCodeAndResource(404, JSON_ROLES_GRANTROLE_ERROR);

        ActionResponse response_fail = osv3().identity().roles().grantDomainUserRole(USER_DOMAIN_ID, USER_ID,"nonExistingRoleId");

        assertFalse(response_fail.isSuccess());

    }

    /**
     * try to revoke a domain role from a user that isn't assigned to him
     * results in failing ActionResponse
     *
     * @throws Exception
     */
    public void revokeDomainUserRole_fail_Test() throws Exception {

        respondWithCodeAndResource(404, JSON_ROLES_REVOKEROLE_ERROR);

        ActionResponse response_fail = osv3().identity().roles().revokeDomainUserRole(USER_DOMAIN_ID, USER_ID, "existingUnassignedRoleId");

        assertFalse(response_fail.isSuccess());

    }

    public void updateRole_Test() throws Exception {

        respondWith(JSON_ROLES_GET_BYID);

        Role role_setToUpdate = osv3().identity().roles().get(ROLE_ID);

        respondWith(JSON_ROLES_UPDATE);

        Role updatedRole = osv3().identity().roles().update(role_setToUpdate.toBuilder().name(ROLE_NAME_UPDATE).build());

        assertEquals(updatedRole.getId(), ROLE_ID);
        assertEquals(updatedRole.getName(), ROLE_NAME_UPDATE);

    }


    public void listRoleAssignments_Test() throws Exception {

        respondWith(JSON_ROLES_ASSIGNMENT_LIST);

        List<? extends RoleAssignment> roleAssignments = osv3().identity().roles().listRoleAssignments(PROJECT_ID);

        assertTrue(roleAssignments.size() > 1);
        for (RoleAssignment each : roleAssignments) {
            assertTrue(isNotEmpty(each.getGroupId()) || isNotEmpty(each.getUserId()));
            assertTrue(isNotEmpty(each.getDomainId()) || isNotEmpty(each.getProjectId()));
            assertEquals(each.getRoleId(), ROLE_ID);
            assertEquals(each.getProjectId(), PROJECT_ID);
        }
    }

    private boolean isNotEmpty(String value) {
        return (value != null && value.length() != 0);
    }

}
