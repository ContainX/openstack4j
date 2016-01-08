package org.openstack4j.api.identity

import java.util.logging.Logger

import org.junit.Rule
import org.junit.rules.TestName
import org.openstack4j.api.Builders
import org.openstack4j.api.OSClient
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.compute.ActionResponse
import org.openstack4j.model.identity.Group
import org.openstack4j.model.identity.User
import org.openstack4j.openstack.OSFactory

import spock.lang.IgnoreIf
import spock.lang.Stepwise
import co.freeside.betamax.Betamax
import co.freeside.betamax.Recorder

@Stepwise
class KeystoneGroupServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneGroupServiceTest
    @Rule Recorder recorder = new Recorder(tapeRoot: new File(TAPEROOT))

    // additional attributes for user service tests
    def static final String GROUP_CRUD_NAME = "GROUP_GRUD"
    def static final String GROUP_CRUD_DESCRIPTION = "Group for CRUD tests"
    def static final String GROUP_CRUD_DESCRIPTION_UPDATE = "An updated group for CRUD tests"
    def static final String GROUP_CRUD_USER_NAME = "Group_CRUD_foobar"
    def static final String GROUP_CRUD_USER_DESCRIPTION = "User used in KeystoneGroupServiceSpec scenario"
    def String GROUP_CRUD_ID

    static final boolean skipTest

    static {
        if(
        USER_ID == null ||
        AUTH_URL == null ||
        PASSWORD == null ||
        DOMAIN_ID == null ||
        USER_DOMAIN_ID == null ) {

            skipTest = false
        }
        else{
            skipTest = false
        }
    }

    // run before the first feature method; similar to JUnit's @BeforeClass
    def setupSpec() {

        if( skipTest != true ) {
            Logger.getLogger(this.class.name).info("USER_ID: " + USER_ID)
            Logger.getLogger(this.class.name).info("AUTH_URL: " + AUTH_URL)
            Logger.getLogger(this.class.name).info("PASSWORD: " + PASSWORD)
            Logger.getLogger(this.class.name).info("DOMAIN_ID: " + DOMAIN_ID)
            Logger.getLogger(this.class.name).info("USER_DOMAIN_ID: " + USER_DOMAIN_ID)
        }
        else {
            Logger.getLogger(this.class.name).warning("Skipping integration-test cases because not all mandatory attributes are set.")
        }
    }

    def setup() {
        Logger.getLogger(this.class.name).info("-> Test: '$KeystoneGroupServiceTest.methodName'")
    }


    // ------------ UserService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="groupService_group_crud.tape")
    def "create, read, update, delete group-service test cases"() {

        given: "authenticated v3 OSClient"
        OSClient os = OSFactory.builder()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

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

        then: "the list should contain at least the recently created group"
        groupList.isEmpty() == false

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

        when: "a user is created"
        User newUser = os.identity().users().create(Builders.user()
                .domainId(DOMAIN_ID)
                .name(GROUP_CRUD_USER_NAME)
                .password("secret")
                .email("mail@example.com")
                .enabled(true)
                .build())

        then: "check the user was created successfully"
        newUser.getName() == GROUP_CRUD_USER_NAME
        newUser.getDomainId() == DOMAIN_ID

        when: "we get the users id"
        def String GROUP_CRUD_USER_ID = newUser.getId()

        then: "the id shouldn't be null "
        GROUP_CRUD_USER_ID != null

        when: "the recently created user is added ta a group"
        ActionResponse response_addUserToGroup_success = os.identity().groups().addUserToGroup(GROUP_CRUD_ID, GROUP_CRUD_USER_ID)

        then: "the response should indicate it was successful"
        response_addUserToGroup_success.isSuccess() == true

        when: "the users in the group are listed"
        List<? extends User> userGroupList = os.identity().groups().listGroupUsers(GROUP_CRUD_ID)

        then: "the group shouldn't be empty"
        userGroupList.isEmpty() == false

        when: "we validate the user belongs to the group"
        ActionResponse response_checkGroupUser_success = os.identity().groups().checkGroupUser(GROUP_CRUD_ID, GROUP_CRUD_USER_ID)

        then: "the user should be a member indicated by an successful response"
        response_checkGroupUser_success.isSuccess() == true

        when: "a user is removed from a group"
        ActionResponse response_removeUserFromGroup_success = os.identity().groups().removeUserFromGroup(GROUP_CRUD_ID, GROUP_CRUD_USER_ID)

        then: "the response should be successful"
        response_removeUserFromGroup_success.isSuccess() == true

        when: "we delete a existing group"
        ActionResponse response_deleteExistingGroup_success = os.identity().groups().delete(GROUP_CRUD_ID)

        then: "this should be successful"
        response_deleteExistingGroup_success.isSuccess() == true

        when: "we delete the user used in this scenario"
        ActionResponse response_deleteUser_success = os.identity().users().delete(GROUP_CRUD_USER_ID)

        then: "this should be successful"
        response_deleteUser_success.isSuccess() == true

    }
}
