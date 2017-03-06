package org.openstack4j.api

import groovy.util.logging.Slf4j
import org.openstack4j.core.transport.Config
import org.openstack4j.core.transport.ProxyHost
import org.openstack4j.core.transport.internal.HttpExecutor
import spock.lang.Specification

import java.nio.file.Paths

@Slf4j
abstract class AbstractSpec extends Specification {

    def static String MODULEROOT = Paths.get("").toAbsolutePath().getParent().toString()
    def static String TAPEROOT = MODULEROOT + "/src/test/resources/tapes/"
    def static Config CONFIG_PROXY_BETAMAX = Config.newConfig().withProxy(ProxyHost.of("http://localhost", 5555))

    // get required attributes from system environment according to python-openstackclient specification
    // http://docs.openstack.org/developer/python-openstackclient/authentication.html
    def static String DOMAIN_ID = System.getenv('OS_DOMAIN_ID') ?: 'default'
    def static String DOMAIN_NAME = System.getenv('OS_DOMAIN_NAME') ?: 'Default'
    def static String USER_ID = System.getenv('OS_USER_ID') ?: '71a7dcb0d60a43088a6c8e9b69a39e69'
    def static String USER_NAME = System.getenv('OS_USERNAME') ?: 'admin'
    def static String USER_DOMAIN_ID = System.getenv('OS_USER_DOMAIN_ID') ?: DOMAIN_ID
    def static String AUTH_URL = System.getenv('OS_AUTH_URL') ?: 'http://devstack.openstack.stack:5000/v3'
    def static String PASSWORD = System.getenv('OS_PASSWORD') ?: 'devstack'
    def static String PROJECT_ID = System.getenv('OS_PROJECT_ID') ?: '194dfdddb6bc43e09701035b52edb0d9'
    def static String PROJECT_NAME = System.getenv('OS_PROJECT_NAME') ?: 'admin'
    def static String PROJECT_DOMAIN_ID = System.getenv('OS_PROJECT_DOMAIN_ID') ?: DOMAIN_ID
    def static String REGION_ONE = System.getenv('OS_REGION_NAME') ?: 'europe'

    def setupSpec() {
        log.info("Using connector: " + HttpExecutor.create().getExecutorName())
    }
}
