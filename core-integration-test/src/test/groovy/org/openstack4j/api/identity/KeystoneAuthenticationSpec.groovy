package org.openstack4j.api.identity

import java.util.logging.Logger

import org.junit.Rule
import org.junit.rules.TestName
import org.openstack4j.api.OSClient
import org.openstack4j.core.transport.internal.HttpExecutor
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.AuthVersion
import org.openstack4j.openstack.OSFactory

import spock.lang.IgnoreIf
import spock.lang.Specification



class KeystoneAuthenticationSpec extends Specification {

    @Rule TestName KeystoneAuthenticationTest

    def static String USER_ID = System.getenv('OS_USER_ID')
    def static String AUTH_URL = System.getenv('OS_AUTH_URL')
    def static String PASSWORD = System.getenv('OS_PASSWORD')
    def static String PROJECT_ID = System.getenv('OS_PROJECT_ID')

    static final boolean skipTest

    static {
        if( USER_ID == null |
            AUTH_URL == null |
            PASSWORD == null |
            PROJECT_ID == null ) {

            Logger.getLogger(getClass().getName()).warning("Skipping integration-test cases because not all mandatory attributes are set.");

            skipTest = true
        }
        else{
            skipTest = false
        }
    }

    @IgnoreIf({ skipTest })
    def "authenticate with userId, password, projectId"() {

        Logger.getLogger(getClass().getName()).info("-> Test: '$KeystoneAuthenticationTest.methodName'"+" using connector: " + HttpExecutor.create().getExecutorName())
        Logger.getLogger(getClass().getName()).info("USER_ID: " + USER_ID)
        Logger.getLogger(getClass().getName()).info("AUTH_URL: " + AUTH_URL)
        Logger.getLogger(getClass().getName()).info("PASSWORD: " + PASSWORD)
        Logger.getLogger(getClass().getName()).info("PROJECT_ID: " + PROJECT_ID)

        given:
        OSClient osClient = OSFactory.builder()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .authenticate()

        expect: "token has a project,user and ids match with the ones used for authentication"
        osClient.getToken().getVersion() == AuthVersion.V3
        osClient.getToken().getUser().getId() == USER_ID
        osClient.getToken().getProject().getId() == PROJECT_ID

        println "-> finished: '$KeystoneAuthenticationTest.methodName'"
    }
}
