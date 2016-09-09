package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j

import org.junit.Rule;
import org.junit.rules.TestName;
import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Token
import org.openstack4j.openstack.OSFactory

import spock.lang.IgnoreIf
import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.junit.RecorderRule
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule


@Slf4j
class KeystoneTokenServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneTokenServiceTest
    @Rule public RecorderRule recorder = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .build());

    // additional attributes for token service tests
    // Unfortunately betamax stops us from generating a second USER_TOKEN (not able to record and play back) so a fixed token id is provided here.
    def static final String USER_TOKEN_ID = "fc0fb8dc13994aa7b539279e4b7a304b"
    def String ADMIN_TOKEN_ID

    static final boolean skipTest

    static {
        if(
        USER_ID == null ||
        AUTH_URL == null ||
        PASSWORD == null ||
        DOMAIN_ID == null ||
        PROJECT_ID == null ) {

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
        log.info("-> Test: '$KeystoneTokenServiceTest.methodName'")
    }


    // ------------ TokenService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="tokenService_crud.tape")
    def "create, read, update, delete token-service test cases"() {

        given: "authenticated OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "getting the id of the token used in the current session which has a different id"
        ADMIN_TOKEN_ID = os.getToken().getId()
        ADMIN_TOKEN_ID != USER_TOKEN_ID

        when: "validate and get details on an existing token"
        Token token = os.identity().tokens().get(USER_TOKEN_ID)

        then: "verify the token"
        token.getDomain().getId() == DOMAIN_ID
        token.getUser().getId() == USER_ID

        when: "validate an existing, valid token"
        ActionResponse response_tokenCheck_success = os.identity().tokens().check(USER_TOKEN_ID)

        then: "this should be successful"
        response_tokenCheck_success.isSuccess() == true

        when: "deleting an existing token"
        ActionResponse response_tokenDelete_success = os.identity().tokens().delete(USER_TOKEN_ID)

        then: "the token should be successfully deleted"
        response_tokenDelete_success.isSuccess() == true

    }
}
