package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Group
import org.openstack4j.model.identity.v3.Role
import org.openstack4j.model.identity.v3.User
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneUserServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneUserServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // additional attributes for user service tests
    def static final String USER_CRUD_NAME = "foobar"
    def static final String USER_CRUD_EMAIL = "foobar@example.com"
    def static final String USER_CRUD_PASSWORD = "secret"
    def String USER_CRUD_ID
    def String ANOTHER_GROUP_ID

    static final boolean skipTest

    static {
        if (
        USER_ID == null ||
                AUTH_URL == null ||
                PASSWORD == null ||
                DOMAIN_ID == null ||
                USER_DOMAIN_ID == null ||
                PROJECT_ID == null) {
            skipTest = true
        } else {
            skipTest = false
        }
    }

    // run before the first feature method; similar to JUnit's @BeforeClass
    def setupSpec() {

        if (skipTest != true) {
            log.info("USER_ID: " + USER_ID)
            log.info("AUTH_URL: " + AUTH_URL)
            log.info("PASSWORD: " + PASSWORD)
            log.info("DOMAIN_ID: " + DOMAIN_ID)
            log.info("USER_DOMAIN_ID: " + USER_DOMAIN_ID)
            log.info("PROJECT_ID: " + PROJECT_ID)
        } else {
            log.warn("Skipping integration-test cases because not all mandatory attributes are set.")
        }
    }

    def setup() {
        log.info("-> Test: '$KeystoneUserServiceTest.methodName'")
    }

    // ------------ UserService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape = "userService_user_crud")
    def "create, read, update, delete user-service test cases"() {

        given: "authenticated v3 OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "another group used in the following test scenario"
        def anotherGroup = os.identity().groups().create(DOMAIN_ID, "anotherGroupForUserServiceTest", "group used for user service integration test")

        and: "get the id of the recently created group"
        ANOTHER_GROUP_ID = anotherGroup.getId()

        when: "a new user is created"
        User newUser = os.identity().users().create(USER_DOMAIN_ID, USER_CRUD_NAME, USER_CRUD_PASSWORD, USER_CRUD_EMAIL, true)

        then: "user attributes should match with the ones used on creation"
        newUser.getName() == USER_CRUD_NAME
        newUser.getDomainId() == USER_DOMAIN_ID
        newUser.getEmail() == USER_CRUD_EMAIL
        newUser.isEnabled() == true
        newUser.getId() != null

        when: "all users are listed"
        List<? extends User> userList = os.identity().users().list()

        then: "that list should at least have one user"
        userList.size() >= 1

        when: "an existing user is 'read' by name and domain"
        User user_byName = os.identity().users().getByName(USER_CRUD_NAME, USER_DOMAIN_ID)

        and: "get the users unique identifier"
        USER_CRUD_ID = user_byName.getId()

        then: "check if the user is the same as requested "
        user_byName.getId() == newUser.getId()
        user_byName.getName() == USER_CRUD_NAME
        user_byName.getEmail() == USER_CRUD_EMAIL
        user_byName.isEnabled() == true
        user_byName.getDomainId() == USER_DOMAIN_ID

        when: "searching for a user by name across all domains"
        List<? extends User> user_byName_list = os.identity().users().getByName(USER_CRUD_NAME)

        then: "check if the list contains at least the newly created user and if the name matches with the one requested"
        user_byName_list.size() >= 1
        user_byName_list.first().getName() == USER_CRUD_NAME

        when: "an existing user is 'read' by its unique identifier"
        User user_byId = os.identity().users().get(USER_CRUD_ID)

        then: "check if the user is the same as requested"
        user_byId.getId() == USER_CRUD_ID
        user_byId.getName() == USER_CRUD_NAME
        user_byId.getEmail() == USER_CRUD_EMAIL
        user_byId.isEnabled() == true
        user_byId.getDomainId() == USER_DOMAIN_ID

        when: "a nonexistent user is requested"
        User user_nonExistent = os.identity().users().get("nonExistentUserId")

        then: "this should fail and be handled"
        user_nonExistent == null

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "a users attributes are updated"
        //        User user_setToUpdate = os.identity().users().get(USER_CRUD_ID)
        //
        //        if( user_setToUpdate != null)
        //          User updatedUser = os.identity().users().update(user_setToUpdate.toBuilder().email("updatedFoobar@example.org").build())
        //
        //        then: "check if the update was successful"
        //        updatedUser.getEmail() == "updatedFoobar@example.org"
        //        updatedUser.getName() == USER_CRUD_NAME
        //        updatedUser.isEnabled() == true
        //        updatedUser.getId() == USER_CRUD_ID
        //        updatedUser.getDomainId() == USER_DOMAIN_ID

        when: "an non-existing user is 'read' by name and domain"
        User userByName_nonExistent = os.identity().users().getByName("nonExistentUserName", USER_DOMAIN_ID)

        then: "this should return null"
        userByName_nonExistent == null

        when: "roles for an existing user in domain context are requested"
        List<? extends Role> domainUserRolesList = os.identity().users().listDomainUserRoles(USER_ID, USER_DOMAIN_ID)

        then: "there should be at least one assignment"
        domainUserRolesList.isEmpty() == false

        when: "roles for an existing user in project context are requested"
        List<? extends Role> projectUserRolesList = os.identity().users().listProjectUserRoles(USER_ID, PROJECT_ID)

        then: "check that there's at least one assignment"
        projectUserRolesList.isEmpty() == false

        when: "an existing user is added to an existing group where he is not already a member"
        ActionResponse result_addUserToGroup_success = os.identity().groups().addUserToGroup(ANOTHER_GROUP_ID, USER_CRUD_ID)

        then: "the user became a member of that group as indicated by the successful response"
        result_addUserToGroup_success.isSuccess() == true

        when: "the users groups are requested"
        List<? extends Group> userGroupsList = os.identity().users().listUserGroups(USER_ID)

        then: "the user should be a member in at least one group"
        userGroupsList.isEmpty() == false

        when: "the user that has recently been added to the group is removed from it"
        ActionResponse result_removeUserFromGroup_success = os.identity().groups().removeUserFromGroup(ANOTHER_GROUP_ID, USER_CRUD_ID)

        then: "the user is no longer a groupmember as indicated by an successful response"
        result_removeUserFromGroup_success.isSuccess() == true

        when: "trying to add an existing user to a non-existing group"
        ActionResponse result_addUserToGroup_fail = os.identity().groups().addUserToGroup("nonExistingGroupId", USER_CRUD_ID)

        then: "this fails and is indicated by the response"
        result_addUserToGroup_fail.isSuccess() == false

        when: "deleting user specified by id"
        ActionResponse response_deleteUser_success = os.identity().users().delete(USER_CRUD_ID)

        then: "the user should be deleted as indicated by an successful ActionResponse"
        response_deleteUser_success.isSuccess() == true

        when: "deleting an non-existing user specified by id"
        ActionResponse response_deleteUser_nonExistent_success = os.identity().users().delete("nonExistentUser_Id")

        then: "the user is not present, therefore the response evaluates to false"
        response_deleteUser_nonExistent_success.isSuccess() == false

        cleanup:
        os.identity().groups().delete(ANOTHER_GROUP_ID)
    }

}
