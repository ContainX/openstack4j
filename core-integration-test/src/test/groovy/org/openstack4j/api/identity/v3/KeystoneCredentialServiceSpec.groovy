package org.openstack4j.api.identity

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Credential
import org.openstack4j.model.identity.v3.User
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneCredentialServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneCredentialServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // additional attributes for credential tests
    def static final String CREDENTIAL_CRUD_TYPE = "ec2"
    def static final String CREDENTIAL_CRUD_BLOB = "{\"access\":\"181920\",\"secret\":\"secretKey\"}"
    def static final String CREDENTIAL_CRUD_BLOB_UPDATE = "{\"access\":\"181920\",\"secret\":\"updatedSecretKey\"}"
    def static final String CREDENTIAL_CRUD_PASSWORD = "secretPassword"
    def static final String CREDENTIAL_CRUD_USER_NAME = "Credential_CRUD_USER"
    def static final String CREDENTIAL_CRUD_USER_EMAIL = "user@example.com"
    def String CREDENTIAL_CRUD_ID
    def String CREDENTIAL_CRUD_USER_ID


    static final boolean skipTest

    static {
        if (
        USER_ID == null ||
                AUTH_URL == null ||
                PASSWORD == null ||
                DOMAIN_ID == null ||
                PROJECT_ID == null) {

            skipTest = true
        } else {
            skipTest = false
        }
    }

    def setupSpec() {

        if (skipTest != true) {
            log.info("USER_ID: " + USER_ID)
            log.info("AUTH_URL: " + AUTH_URL)
            log.info("PASSWORD: " + PASSWORD)
            log.info("DOMAIN_ID: " + DOMAIN_ID)
            log.info("PROJECT_ID: " + PROJECT_ID)
        } else {
            log.warn("Skipping integration-test cases because not all mandatory attributes are set.")
        }
    }

    def setup() {
        log.info("-> Test: '$KeystoneCredentialServiceTest.methodName'")
    }

    // ------------ CredentialService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape = "credentialService_all.tape")
    def "credential service crud test cases"() {

        given: "authenticated v3 OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "we create a new user for this scenario"
        User user = os.identity().users().create(DOMAIN_ID, CREDENTIAL_CRUD_USER_NAME, CREDENTIAL_CRUD_PASSWORD, CREDENTIAL_CRUD_USER_EMAIL, true)

        and: "get the user id"
        CREDENTIAL_CRUD_USER_ID = user.getId()

        when: "creating a credential with invalid args"
        os.identity().credentials().create(null, null, null, null)

        then: "this should fail and throw a NPE"
        thrown NullPointerException

        when: "a new credential for this user is created"
        Credential credential = os.identity().credentials().create(CREDENTIAL_CRUD_BLOB, CREDENTIAL_CRUD_TYPE, PROJECT_ID, CREDENTIAL_CRUD_USER_ID)

        then: "verify credential was created successfully"
        credential.getType() == CREDENTIAL_CRUD_TYPE
        credential.getBlob() == CREDENTIAL_CRUD_BLOB
        credential.getProjectId() == PROJECT_ID
        credential.getUserId() == CREDENTIAL_CRUD_USER_ID

        when: "get the credentials id"
        CREDENTIAL_CRUD_ID = credential.getId()

        then: "this shouldn't be null"
        CREDENTIAL_CRUD_ID != null

        when: "list all credentials"
        List<? extends Credential> credentialList = os.identity().credentials().list()

        then: "the list shouldn't be empty and the recently created credentials should be found"
        credentialList.isEmpty() == false
        credentialList.find { it.getId() == CREDENTIAL_CRUD_ID }

        when: "get credential by id"
        Credential credential_byId = os.identity().credentials().get(CREDENTIAL_CRUD_ID)

        then: "check the credential"
        credential_byId.getId() == CREDENTIAL_CRUD_ID
        credential_byId.getBlob() == CREDENTIAL_CRUD_BLOB
        credential_byId.getType() == CREDENTIAL_CRUD_TYPE

        when: "get nonexistent credential by id"
        Credential credential_byId_nonexistent = os.identity().credentials().get("bogusId")

        then: "this should not be found"
        credential_byId_nonexistent == null

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "a credential is updated"
        //        Credential updatedCredential = os.identity().credentials().update(KeystoneCredential.builder()
        //                .id(CREDENTIAL_CRUD_ID)
        //                .blob(CREDENTIAL_CRUD_BLOB_UPDATE)
        //                .build())
        //
        //        then: "check if the update was successful"
        //        updatedCredential.getId() == CREDENTIAL_CRUD_ID
        //        updatedCredential.getBlob() == CREDENTIAL_CRUD_BLOB_UPDATE

        when: "delete credential"
        ActionResponse response_deleteCredential_success = os.identity().credentials().delete(CREDENTIAL_CRUD_ID)

        then: "this should be successful"
        response_deleteCredential_success.isSuccess() == true

        when: "deleting nonexistent credential"
        ActionResponse response_deleteCredential_fail = os.identity().credentials().delete("invalidId")

        then: "this should fail"
        response_deleteCredential_fail.isSuccess() == false

        cleanup: "we delete the user used in this scenario"
        os.identity().users().delete(CREDENTIAL_CRUD_USER_ID)


    }

}
