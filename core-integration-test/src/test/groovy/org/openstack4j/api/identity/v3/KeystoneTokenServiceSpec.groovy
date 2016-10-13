package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.api.types.ServiceType
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Domain
import org.openstack4j.model.identity.v3.Project
import org.openstack4j.model.identity.v3.Service
import org.openstack4j.model.identity.v3.Token
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf
import spock.lang.Shared
import spock.lang.Stepwise

@Slf4j
@Stepwise // Needs to be stepwise to ensure USER_TOKEN_ID is acquired first
class KeystoneTokenServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneTokenServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // additional attributes for token service tests
    @Shared String USER_TOKEN_ID
    def String ADMIN_TOKEN_ID

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
        log.info("-> Test: '$KeystoneTokenServiceTest.methodName'")
    }

    // ------------ TokenService Tests ------------

    // acquire another token used in the crud test scenario;
    // needs to be in a separated method to be able able to record and playback using betamax (which req. this to be in a sep. tape)
    @IgnoreIf({ skipTest })
    @Betamax(tape = "tokenService_getUserToken")
    def "get user token"() {
        when: "authenticated OSClient"
        OSClientV3 os_user = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "get the id of the user token"
        USER_TOKEN_ID = os_user.getToken().getId()

        then: "this shouldn't be null"
        USER_TOKEN_ID != null
    }

    @IgnoreIf({ skipTest })
    @Betamax(tape = "tokenService_crud")
    def "create, read, update, delete token-service test cases"() {

        given: "authenticated OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "getting the id of the token used in the current session which has a different id"
        ADMIN_TOKEN_ID = os.getToken().getId()
        ADMIN_TOKEN_ID != USER_TOKEN_ID

        when: "validate and get details on an existing token"
        Token token = os.identity().tokens().get(USER_TOKEN_ID)

        then: "verify the user id found in the token"
        token.getUser().getId() == USER_ID

        when: "validate an existing, valid token"
        ActionResponse response_tokenCheck_success = os.identity().tokens().check(USER_TOKEN_ID)

        then: "this should be successful"
        response_tokenCheck_success.isSuccess() == true

        when: "getting the service catalog for another token"
        List<? extends Service> serviceCatalog = os.identity().tokens().getServiceCatalog(USER_TOKEN_ID);

        then: "this should contain at least contain one service"
        serviceCatalog.isEmpty() == false
        serviceCatalog.find{ it.getName() == "keystone" }

        when: "getting available project scopes"
        List<? extends Project> availableProjectScopes = os.identity().tokens().getProjectScopes(USER_TOKEN_ID);

        then: "the list should contain at least the project scope used for initial authentication"
        availableProjectScopes.isEmpty() == false
        availableProjectScopes.first().getName() != null
        availableProjectScopes.find { it.getId() == PROJECT_ID }

        when: "getting available domain scopes"
        List<? extends Domain> availableDomainScopes = os.identity().tokens().getDomainScopes(USER_TOKEN_ID);

        then: "the list should contain at least one available domain scope"
        availableDomainScopes.isEmpty() == false
        availableDomainScopes.first().getName() != null

        when: "deleting an existing token"
        ActionResponse response_tokenDelete_success = os.identity().tokens().delete(USER_TOKEN_ID)

        then: "the token should be successfully deleted"
        response_tokenDelete_success.isSuccess() == true

    }
}
