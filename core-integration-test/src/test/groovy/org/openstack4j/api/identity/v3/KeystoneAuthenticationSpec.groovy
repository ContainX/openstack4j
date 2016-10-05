package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.api.exceptions.RegionEndpointNotFoundException
import org.openstack4j.api.types.ServiceType
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.AuthVersion
import org.openstack4j.model.identity.v3.User
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneAuthenticationSpec extends AbstractSpec {

    @Rule TestName KeystoneAuthenticationTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    static final boolean skipTest

    // skipTest will evaluate to true if not all mandatory attributes are set causing the project to build without running the tests.
    static {
        if(
        USER_ID == null ||
        USER_NAME == null ||
        USER_DOMAIN_ID == null ||
        AUTH_URL == null ||
        PASSWORD == null ||
        PROJECT_ID == null ||
        PROJECT_NAME == null ||
        PROJECT_DOMAIN_ID == null ||
        DOMAIN_ID == null ||
        DOMAIN_NAME == null ||
        REGION_ONE == null ) {

            skipTest = true
        }
        else{
            skipTest = false
        }
    }

    def setupSpec() {

        if( skipTest != true ) {
            log.info("USER_ID: " + USER_ID)
            log.info("USER_NAME: " + USER_NAME)
            log.info("USER_DOMAIN_ID: " + USER_DOMAIN_ID)
            log.info("AUTH_URL: " + AUTH_URL)
            log.info("PASSWORD: " + PASSWORD)
            log.info("PROJECT_ID: " + PROJECT_ID)
            log.info("PROJECT_NAME: " + PROJECT_NAME)
            log.info("PROJECT_DOMAIN_ID: " + PROJECT_DOMAIN_ID)
            log.info("DOMAIN_ID: " + DOMAIN_ID)
            log.info("DOMAIN_NAME: " + DOMAIN_NAME)
            log.info("REGION_ONE: " + REGION_ONE)
        }
        else {
            log.warn("Skipping integration-test cases because not all mandatory attributes are set.");
        }
    }

    def setup() {
        log.info("-> Test: '$KeystoneAuthenticationTest.methodName'")
    }



    // ------------ Authentification Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userId_password_projectId")
    def "authenticate with userId, password and scope projectId"() {

        given: "authenticate using credentials and projectId-scope to get a valid, scoped token"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        expect: "token is v3 and user,project match the ones used for authentication"
        os.getToken().getVersion() == AuthVersion.V3
        os.getToken().getUser().getId() == USER_ID
        os.getToken().getProject().getId() == PROJECT_ID

    }

    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userId_password_projectName_projectDomainId")
    def "authenticate with userId, password and scope projectName, projectDomainId"() {

        given: "authenticate with using credentials and scope given by projectName and the projects domain to get a valid, scoped token"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byName(PROJECT_NAME), Identifier.byId(PROJECT_DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        expect: "token is v3 and user, project match the ones used for authentication"
        os.getToken().getVersion() == AuthVersion.V3
        os.getToken().getUser().getId() == USER_ID
        os.getToken().getProject().getName() == PROJECT_NAME
        os.getToken().getProject().getDomain().getId() == PROJECT_DOMAIN_ID

    }

    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userName_password_userDomainId_projectId")
    def "authenticate with userName, password, userDomainId and scope projectId"() {

        given:
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_NAME, PASSWORD, Identifier.byId(USER_DOMAIN_ID))
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        expect:
        os.getToken().getVersion() == AuthVersion.V3
        os.getToken().getUser().getId() == USER_ID

    }

    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userId_password_domainId")
    def "authenticate with userId, password, and scope domainId"() {

        given:
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        expect:
        os.getToken().getVersion() == AuthVersion.V3
        os.getToken().getUser().getId() == USER_ID
        os.getToken().getDomain().getId() == DOMAIN_ID

    }

    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userName_password_domainId")
    def "authenticate with userName, password, domainId and scope domainId"() {

        given:
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_NAME, PASSWORD, Identifier.byId(DOMAIN_ID))
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        expect:
        os.getToken().getVersion() == AuthVersion.V3
        os.getToken().getUser().getId() == USER_ID
        os.getToken().getDomain().getId() == DOMAIN_ID

    }

    @IgnoreIf({ skipTest })
    @Betamax(tape = "authenticate_v3_userId_password_domainName")
    def "authenticate with userId, password and scope specified by domainName"() {

        given:
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byName(DOMAIN_NAME))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        expect:
        os.getToken().getVersion() == AuthVersion.V3
        os.getToken().getUser().getId() == USER_ID
        os.getToken().getDomain().getName() == DOMAIN_NAME

    }

    //    TODO: betamax seems to record only the first authentication communication but not the second.
    //    Therefore the tokenId's are identical when we verify, because we only check one http interaction.
    //    Need to manually configure when to start and stop the recording but the following does not work as it produces the same outcome:
    //
    //    def proxyServer = new ProxyServer(recorder)
    //
    //    recorder.insertTape("authenticate_v3_token.tape")
    //    proxyServer.start()
    //
    //    // test method
    //
    //    recorder.ejectTape()
    //    proxyServer.stop()
    //
    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_token.tape")
    def "authenticate with userId, password, scope projectId and use that token to re-authenticate"() {

        given: "authenticate with credentials and project scope to get a valid token"
        OSClientV3 myOs = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: " get the token id"
        def String tokenId = myOs.getToken().getId()

        when: "a list of users is requested after successful authentication"
        List<? extends User> userList = myOs.identity().users().list()

        then: "this list should contain at least one user"
        userList.isEmpty() == false

        when: "we use that token to re-authenticate and get another valid token"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .token(tokenId)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        def String newTokenId = os.getToken().getId()

        then: "expect two different, valid tokens"
        // newTokenId != tokenId
        os.getToken().getVersion() == AuthVersion.V3
        os.getToken().getProject().getId() == PROJECT_ID

        when: "a list of users is requested after successfull authentication using token"
        List<? extends User> userList_tokenAuth = myOs.identity().users().list()

        then: "this list should contain at least one user"
        userList_tokenAuth.isEmpty() == false

    }

    //    TODO: betamax seems to record only the first authentication communication but not the second.
    //    Therefore the tokenId's are identical when we verify, because we only check one http interaction.
    //    Need to manually configure when to start and stop the recording.
    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userId_password_unscoped_reauth")
    def "authenticate with userId, password to get an unscoped token and then reauthenticate with it to get a scoped token"() {

        given: "authenticate with credentials but no scope to get a valid, unscoped token"
        OSClientV3 osclient = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        String tokenId = osclient.getToken().getId()

        and: "authenticate with that token but with project scope this time"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .token(tokenId)
                .scopeToProject(Identifier.byId(PROJECT_ID), Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        expect: "tokens user and project match the ones used for authentication"
        os.getToken().getUser().getId() == USER_ID
        os.getToken().getProject().getId() == PROJECT_ID

    }

    //    TODO: betamax seems to record only the first authentication communication but not the second.
    //    Therefore the tokenId's are identical when we verify, because we only check one http interaction.
    //    Need to manually configure when to start and stop the recording.
    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userId_password_unscopedTokenToScopedToken")
    def "authenticate with userId, password to get and unscoped token used to get scoped token"() {

        given:
        OSClientV3 os_unscoped = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        def String tokenUnscopedId = os_unscoped.getToken().getId()

        and:
        OSClientV3 os_scoped = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .token(tokenUnscopedId)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        def String tokenScopedId = os_scoped.getToken().getId()

        expect:
        // tokenUnscopedId != tokenScopedId
        os_scoped.getToken().getProject().getId() == PROJECT_ID


    }

    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userId_password_domainId_regionValid")
    def "authenticate with userId, password and scope domainId and get a list of users in a valid region"() {

        given: "authenticate with credentials and scope specified by domainId to get a valid, scoped token"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        when: "switch to a valid region"
        os.useRegion(REGION_ONE)

        then: "get a list of users should and test that it is not empty; do not throw RegionEndpointNotFoundException"
        os.identity().users().list().isEmpty() == false
        notThrown RegionEndpointNotFoundException

        cleanup: "finally remove the region used in this test"
        os.removeRegion()

    }

    @IgnoreIf({ skipTest })
    @Betamax(tape="authenticate_v3_userId_password_domainId_regionInvalid")
    def "authenticating with userId, password and scope domainId in an non-existend region should throw an RegionEndpointNotFoundException"() {

        given: "authenticate with credentials and scope specified by domainId to get a valid, scoped token"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        when: "switch to an non-existing region"
        os.useRegion("regionInvalid")

        and: "trying to get a list of users.."
        os.identity().users().list()

        then: ".. results in an RegionEndpointNotFoundException"
        RegionEndpointNotFoundException exception = thrown()
        exception.message == String.format("Endpoint for '%s' service could not be found for region: '%s'", ServiceType.IDENTITY.getServiceName(), "regionInvalid")

        cleanup: "finally unset used region"
        os.removeRegion()

    }
}
