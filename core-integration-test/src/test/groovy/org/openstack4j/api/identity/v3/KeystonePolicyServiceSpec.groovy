package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Policy
import org.openstack4j.model.identity.v3.User
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystonePolicyServiceSpec extends AbstractSpec {

    @Rule TestName KeystonePolicyServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // additional attributes for policy tests
    def static String POLICY_CRUD_TYPE = "application/json"
    def static String POLICY_CRUD_USER_NAME = "PolicyCrudUser"
    def static String POLICY_CRUD_BLOB = "{'PolicyCrudUser' : 'role:admin-user'}"
    def String POLICY_CRUD_USER_ID
    def String POLICY_CRUD_ID

    static final boolean skipTest

    static {
        if(
        USER_ID == null ||
        AUTH_URL == null ||
        PASSWORD == null ||
        DOMAIN_ID == null ||
        PROJECT_ID == null  ) {

            skipTest = true
        }
        else{
            skipTest = false
        }
    }

    def setupSpec() {

        if( skipTest != true ) {
            log.info("USER_ID: " + USER_ID)
            log.info("AUTH_URL: " + AUTH_URL)
            log.info("PASSWORD: " + PASSWORD)
            log.info("DOMAIN_ID: " + DOMAIN_ID)
            log.info("PROJECT_ID: " + PROJECT_ID)
        }
        else {
            log.warn("Skipping integration-test cases because not all mandatory attributes are set.")
        }
    }

    def setup() {
        log.info("-> Test: '$KeystonePolicyServiceTest.methodName'")
    }


    // ------------ PolicyService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="policyService_all.tape")
    def "policy service CRUD test cases"() {

        given: "authenticated v3 OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "a user for this scenario is created"
        User user = os.identity().users().create(USER_DOMAIN_ID, POLICY_CRUD_USER_NAME, "secret", "user@policyCRUDtest.com", true)

        and: "get the id of the user"
        POLICY_CRUD_USER_ID = user.getId()

        when: "create a policy"
        Policy policy = os.identity().policies().create(POLICY_CRUD_BLOB, POLICY_CRUD_TYPE, PROJECT_ID, POLICY_CRUD_USER_ID)

        then: "check the newly created policy"
        policy.getBlob() == POLICY_CRUD_BLOB
        policy.getType() == POLICY_CRUD_TYPE
        policy.getUserId() == POLICY_CRUD_USER_ID
        policy.getProjectId() == PROJECT_ID

        when: "get the id of the policy"
        POLICY_CRUD_ID = policy.getId()

        then: "this shouldn't be null"
        POLICY_CRUD_ID != null

        when: "list all policies"
        List<? extends Policy> policyList = os.identity().policies().list()

        then: "this list should contain at least the recently created policy"
        policyList.isEmpty() == false
        policyList.find { it.getId() == POLICY_CRUD_ID}

        when: "get details on a policy by id"
        Policy policy_byId = os.identity().policies().get(POLICY_CRUD_ID)

        then: "check the correct policy was returned"
        policy_byId.getId() == POLICY_CRUD_ID
        policy_byId.getBlob() == POLICY_CRUD_BLOB
        policy_byId.getProjectId() == PROJECT_ID
        policy_byId.getUserId() == POLICY_CRUD_USER_ID
        policy_byId.getType() == POLICY_CRUD_TYPE

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "a policy is updated"
        //        Policy policy_setToUpdate = os.identity().policies().get(POLICY_CRUD_ID)
        //        if(policy != null)
        //            Policy updatedPolicy = os.identity().policies().update(policy_setToUpdate.toBuilder().blob(POLICY_CRUD_BLOB_UPDATE).build());
        //
        //        then: "check if the update was successful"
        //        updatedPolicy.getId() == POLICY_CRUD_ID
        //        updatedPolicy.getBlob() == POLICY_CRUD_BLOB_UPDATE

        when: "delete a policy"
        ActionResponse response_deletePolicy_success = os.identity().policies().delete(POLICY_CRUD_ID)

        then: "this should be successful"
        response_deletePolicy_success.isSuccess() == true

        when: "deleting a policy non-existent policy"
        ActionResponse response_deletePolicy_fail = os.identity().policies().delete("nonExistentPolicy_ID")

        then: "this should fail and the ActionResponse should indicate this"
        response_deletePolicy_fail.isSuccess() == false

        cleanup: "finally the user is deleted"
        os.identity().users().delete(POLICY_CRUD_USER_ID)

    }

}
