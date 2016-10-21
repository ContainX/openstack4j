package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.Builders
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Group
import org.openstack4j.model.identity.v3.User
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneGroupServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneGroupServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // additional attributes for group service tests
    def static final String GROUP_CRUD_NAME = "GROUP_GRUD"
    def static final String GROUP_CRUD_DESCRIPTION = "Group for CRUD tests"
    def static final String GROUP_CRUD_DESCRIPTION_UPDATE = "An updated group for CRUD tests"
    def static final String GROUP_CRUD_USER_NAME = "Group_CRUD_foobar"
    def static final String GROUP_CRUD_USER_DESCRIPTION = "User used in KeystoneGroupServiceSpec scenario"
    def String GROUP_CRUD_USER_ID
    def String GROUP_CRUD_ID

    static final boolean skipTest

    static {
        if (
        USER_ID == null ||
                AUTH_URL == null ||
                PASSWORD == null ||
                DOMAIN_ID == null ||
                USER_DOMAIN_ID == null) {

            skipTest = false
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
        } else {
            log.warn("Skipping integration-test cases because not all mandatory attributes are set.")
        }
    }

    def setup() {
        log.info("-> Test: '$KeystoneGroupServiceTest.methodName'")
    }

    // ------------ GroupService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape = "groupService_group_crud.tape")
    def "create, read, update, delete group-service test cases"() {

        given: "authenticated OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "a user for the following test scenario is created"
        User user = os.identity().users().create(Builders.user()
                .domainId(DOMAIN_ID)
                .name(GROUP_CRUD_USER_NAME)
                .password("secret")
                .email("mail@example.com")
                .enabled(true)
                .build())

        and: "get the id of the recently created user"
        GROUP_CRUD_USER_ID = user.getId()

        when: "a new group is created"
        Group group = os.identity().groups().create(DOMAIN_ID, GROUP_CRUD_NAME, GROUP_CRUD_DESCRIPTION)

        then: "verify the group has been created successfully"
        group.getName() == GROUP_CRUD_NAME
        group.getDescription() == GROUP_CRUD_DESCRIPTION
        group.getDomainId() == DOMAIN_ID

        when: "get the groups identifier"
        GROUP_CRUD_ID = group.getId()

        then: "it shouldn't be null"
        GROUP_CRUD_ID != null

        when: "list all groups"
        List<? extends Group> groupList = os.identity().groups().list()

        then: "the list should at least contain the recently created group"
        groupList.isEmpty() == false
        groupList.contains(group) == true

        when: "details for a group specified by id are requested"
        Group group_byId = os.identity().groups().get(GROUP_CRUD_ID)

        then: "check the correct group has been returned"
        group_byId.getId() == GROUP_CRUD_ID
        group_byId.getName() == GROUP_CRUD_NAME
        group_byId.getDescription() == GROUP_CRUD_DESCRIPTION

        when: "groups are listed by name"
        List<? extends Group> groupList_byName = os.identity().groups().getByName(GROUP_CRUD_NAME)

        then: "this list should at least contain the recently created group and the group's name should match"
        groupList_byName.isEmpty() == false
        groupList_byName.get(0).getName() == GROUP_CRUD_NAME

        when: "groups are listed by name and domain id"
        Group group_byName_byDomainId = os.identity().groups().getByName(GROUP_CRUD_NAME, DOMAIN_ID)

        then: "this list should at least contain the recently created group and the group's name should match"
        group_byName_byDomainId.getId() == GROUP_CRUD_ID
        group_byName_byDomainId.getName() == GROUP_CRUD_NAME
        group_byName_byDomainId.getDomainId() == DOMAIN_ID

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "a groups attributes are updated"
        //        Group updatedGroup = os.identity().groups().update(group_byId.toBuilder().description(GROUP_DESCRIPTION_UPDATED).build());
        //
        //        then: "check if the update was successful"
        //        updatedGroup.getId() == GROUP_CRUD_ID
        //        updatedGroup.getDescription() == GROUP_CRUD_DESCRIPTION_UPDATE

        when: "the recently created user is added ta a group"
        ActionResponse response_addUserToGroup_success = os.identity().groups().addUserToGroup(GROUP_CRUD_ID, GROUP_CRUD_USER_ID)

        then: "the response should indicate it was successful"
        response_addUserToGroup_success.isSuccess() == true

        when: "the users in the group are listed"
        List<? extends User> userGroupList = os.identity().groups().listGroupUsers(GROUP_CRUD_ID)

        then: "the group should only have only one member, which is the recently added user"
        userGroupList.get(0).getId() == GROUP_CRUD_USER_ID

        when: "we validate the user belongs to the group"
        ActionResponse response_checkGroupUser_success = os.identity().groups().checkGroupUser(GROUP_CRUD_ID, GROUP_CRUD_USER_ID)

        then: "the user should be a member indicated by an successful response"
        response_checkGroupUser_success.isSuccess() == true

        when: "a user is removed from a group"
        ActionResponse response_removeUserFromGroup_success = os.identity().groups().removeUserFromGroup(GROUP_CRUD_ID, GROUP_CRUD_USER_ID)

        then: "the response should be successful"
        response_removeUserFromGroup_success.isSuccess() == true

        //TODO: This check is disabled for now, because it fails sometimes if it is done to fast after the previous removeUserFromGroup().
        //		That the User is successfully removed from the group can be checked via e.g. dashboard. Will need to come back and investigate.
        //
        //		when: "we validate the user no longer belongs to the group"
        //		ActionResponse response_checkGroupUser_fail = os.identity().groups().checkGroupUser(GROUP_CRUD_ID, GROUP_CRUD_USER_ID)
        //
        //		then: "the response should indicate this"
        //		response_checkGroupUser_fail.isSuccess() == false

        when: "we delete a existing group"
        ActionResponse response_deleteExistingGroup_success = os.identity().groups().delete(GROUP_CRUD_ID)

        then: "this should be successful"
        response_deleteExistingGroup_success.isSuccess() == true

        //TODO: This check is disabled for now, because it fails sometimes if it is done to fast after the previous deletion of the group.
        //		That the Group is successfully deleted can be checked via e.g. dashboard. Will need to come back and investigate.
        //
        //		when: "list all groups"
        //		List<? extends Group> groupList_afterDeletingGroup = os.identity().groups().list()
        //
        //		then: "we should no longer find the group used in this scenario"
        //		groupList.contains(group) == false

        when: "non-existent group is listed by name and domain id"
        Group nonExistent_group_byName_byDomainId = os.identity().groups().getByName("nonExistentGroup", DOMAIN_ID)

        then: "the return value should be null"
        nonExistent_group_byName_byDomainId == null

        cleanup: "we delete the user used in this scenario"
        ActionResponse response_deleteUser_success = os.identity().users().delete(GROUP_CRUD_USER_ID)

    }
}
