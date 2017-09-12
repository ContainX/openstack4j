/*
package org.openstack4j.api.dns.v2

import co.freeside.betamax.TapeMode
import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName
import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.Builders
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.dns.v2.Nameserver
import org.openstack4j.model.dns.v2.Zone
import org.openstack4j.model.dns.v2.ZoneType
import org.openstack4j.openstack.OSFactory
import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule
import spock.lang.IgnoreIf

@Slf4j
class DesignateZoneServiceSpec extends AbstractSpec {

    @Rule TestName DesignateZoneServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "dns.v2"))
                    //.defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.WRITE_ONLY)
                    .build());

    // used for domain crud tests
    def static final String ZONE_NAME = "b.org."
    def static final String ZONE_EMAIL = "a@b.org"
    def static final Integer ZONE_TTL = 3602
    def static final ZoneType ZONE_TYPE = ZoneType.PRIMARY
    def static final String ZONE_DESCRIPTION = "This is my zone."
    def static final String NAMESERVER_HOSTNAME = "hostname"
    def String ZONE_ID

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
            log.info("USER_NAME: " + USER_NAME)
            log.info("USER_DOMAIN_ID: " + USER_DOMAIN_ID)
            log.info("AUTH_URL: " + AUTH_URL)
            log.info("PROJECT_ID: " + PROJECT_ID)
        }
        else {
            log.warn("Skipping integration-test cases because not all mandatory attributes are set.");
        }
    }

    def setup() {
        log.info("-> Test: '$DesignateZoneServiceTest.methodName'")
    }


    // ------------ DomainService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="zoneService_crud")
    def "dns/v2/zone service test cases"() {

        given: "an authenticated OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_NAME, PASSWORD, Identifier.byId(USER_DOMAIN_ID))
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        when: "we try to create a zone with argument 'null' "
        os.dns().zones().create(null)

        then: "a NPE is thrown"
        thrown NullPointerException

        // commented out due to permission foo
//        when: "a new domain is created using DomainBuilder with valid arguments"
//        Zone zone = Builders.zone()
//                .name(ZONE_NAME)
//                .email(ZONE_EMAIL)
//                .ttl(ZONE_TTL)
//                .description(ZONE_DESCRIPTION)
//                .type(ZONE_TYPE)
//                .build()
//
//        and:
//        Zone newZone = os.dns().zones().create(zone)
//
//        then: "verify that the new domain is correct"
//        newZone.getId != null
//        newZone.getName() == ZONE_NAME
//        newZone.getEmail() == ZONE_EMAIL
//        newZone.getTTL() == ZONE_TTL
//        newZone.getDescription() == ZONE_DESCRIPTION
//        newZone.getType() == ZONE_TYPE

        when: "listing all zones"
        List<? extends Zone> zoneList = os.dns().zones().list()

        then: "the list shouldn't be empty"
        zoneList.isEmpty() == false

        when: "get the id of the first zone"
        ZONE_ID = zoneList.first().getId()

        then: "the id of the first item shouldn't be null"
        ZONE_ID != null

        and: "the attributes of the zone should be equal"
        zoneList.first().getProjectId() == PROJECT_ID
        zoneList.first().getType() == ZONE_TYPE
        zoneList.first().getTTL() == ZONE_TTL

        when: "get a zone by id"
        Zone zoneById = os.dns().zones().get(ZONE_ID)

        then: "the attributes of the zone should be as expected "
        zoneById.getId() == ZONE_ID
        zoneById.getProjectId() == PROJECT_ID

        when: "get nameserver for a zone specified by id"
        List<? extends Nameserver> nameserverList = os.dns().zones().listNameservers(ZONE_ID)

        then: "this list shouldn't be empty"
        nameserverList.isEmpty() == false
        nameserverList.first().getHostname() == NAMESERVER_HOSTNAME
        nameserverList.first().getPriority() == 1

        // commented out due to permission foo
//        when: "updating a zone description"
//        Zone zoneUpdated = os.dns().zones().update(Builders.zone().id(ZONE_ID).description(ZONE_DESCRIPTION).build())
//
//        then: "the description should be updated"
//        zoneUpdated.getDescription() == ZONE_DESCRIPTION

        // commented out due to permission foo
//        when: "deleting a zone"
//        ActionResponse deleteZoneResponse = os.dns().zones().delete(ZONE_ID)
//
//        then: "this should be successfull"
//        deleteZoneResponse.isSuccess() == true

    }
}
*/
