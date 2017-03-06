package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.Builders
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Domain
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneDomainServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneDomainServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // used for domain crud tests
    def static String DOMAIN_CRUD_NAME = "Domain_CRUD"
    def static String DOMAIN_CRUD_DESCRIPTION = "Domain used for CRUD tests"
    def static String DOMAIN_CRUD_DESCRIPTION_UPDATED = "An updated domain used for CRUD tests"

    static final boolean skipTest

    static {
        if(
        USER_ID == null ||
        AUTH_URL == null ||
        PASSWORD == null ||
        DOMAIN_ID == null ) {

            skipTest = true
        }
        else{
            skipTest = false
        }
    }


    // run before the first feature method; similar to JUnit's @BeforeClass
    def setupSpec() {

        if( skipTest != true ) {
            log.info("USER_ID: " + USER_ID)
            log.info("AUTH_URL: " + AUTH_URL)
            log.info("PASSWORD: " + PASSWORD)
            log.info("DOMAIN_ID: " + DOMAIN_ID)
        }
        else {
            log.warn("Skipping integration-test cases because not all mandatory attributes are set.");
        }
    }

    def setup() {
        log.info("-> Test: '$KeystoneDomainServiceTest.methodName'")
    }


    // ------------ DomainService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="domainService_crud.tape")
    def "create, read, update, delete domain service test cases"() {

        given: "an authenticated OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        when: "we try to create a domain with argument 'null' "
        os.identity().domains().create(null)

        then: "a NPE is thrown"
        thrown NullPointerException

        when: "a new domain is created using DomainBuilder with valid arguments"
        Domain domain = Builders.domain()
                .name(DOMAIN_CRUD_NAME)
                .description(DOMAIN_CRUD_DESCRIPTION)
                .enabled(false)
                .build()

        and:
        Domain newDomain = os.identity().domains().create(domain)

        then: "verify that the new domain is correct"
        newDomain.getName() == DOMAIN_CRUD_NAME
        newDomain.getDescription() == DOMAIN_CRUD_DESCRIPTION
        newDomain.getId() != null

        when: "details on the recently created domain are requested by domain name"
        List<? extends Domain> domainList_byName = os.identity().domains().getByName(DOMAIN_CRUD_NAME);

        then: "verify thtat the correct domain was returned"
        domainList_byName.isEmpty() == false
        domainList_byName.get(0).getName() == DOMAIN_CRUD_NAME
        domainList_byName.get(0).getDescription() == DOMAIN_CRUD_DESCRIPTION

        and: "get the domain id"
        def String DOMAIN_CRUD_ID = domainList_byName.get(0).getId()

        when: "details on a domain specified by id are requested"
        Domain domain_byId = os.identity().domains().get(DOMAIN_CRUD_ID);

        then: "check that the correct domain was returned"
        domain_byId.getId() == DOMAIN_CRUD_ID
        domain_byId.getName() == DOMAIN_CRUD_NAME
        domain_byId.getDescription() == DOMAIN_CRUD_DESCRIPTION

        when: "a list of all domains is requested"
        List<? extends Domain> domainList = os.identity().domains().list()

        then: "this list shouldn't be empty"
        domainList.isEmpty() == false

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "an existing domain's description and enabled status is updated"
        //        Domain domain_setToUpdate = os.identity().domains().get(DOMAIN_CRUD_ID)
        //
        //        if(domain != null)
        //          Domain updatedDomain = os.identity().domains().update(domain_setToUpdate.toBuilder().description(DOMAIN_CRUD_DESCRIPTION_UPDATED).build())
        //        
        //        then: "verify the updated attributes"
        //        updatedDomain.getDescription() == DOMAIN_CRUD_DESCRIPTION_UPDATED
        //        updatedDomain.getName() == DOMAIN_CRUD_NAME
        //        updatedDomain.getId() == DOMAIN_CRUD_ID

        when: "an existing domain is deleted"
        ActionResponse response_deleteDomain_success = os.identity().domains().delete(DOMAIN_CRUD_ID)

        then: "the response indicates that the domain has been successfully deleted"
        response_deleteDomain_success.isSuccess() == true

        when: "trying to delete a domain that does not exist"
        ActionResponse response_deleteNonExistingDomain_success = os.identity().domains().delete(DOMAIN_CRUD_ID)

        then: "this should also be successful"
        response_deleteNonExistingDomain_success.isSuccess() == true

    }

}
