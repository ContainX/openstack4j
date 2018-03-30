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
import org.openstack4j.model.dns.v2.Recordset
import org.openstack4j.openstack.OSFactory
import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule
import spock.lang.IgnoreIf

@Slf4j
class DesignateRecordsetServiceSpec extends AbstractSpec {

    @Rule TestName DesignateRecordsetServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    //.defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.WRITE_SEQUENTIAL)
                    .build());

    // used for domain crud tests
    def static final String RECORDSET_NAME = "Atest."
    def static final String RECORDSET_TYPE = "A"
    def static final List<String> RECORDSET_RECORDS= ["10.1.0.2"]
    def static final String RECORDSET_DESCRIPTION = "This is my recordset."
    def String ZONE_ID, ZONE_NAME, RECORDSET_ID

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
        log.info("-> Test: '$DesignateRecordsetServiceTest.methodName'")
    }


    // ------------ DomainService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="recordsetService_crud")
    def "dns/v2/recordset test cases"() {

        given: "an authenticated OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_NAME, PASSWORD, Identifier.byId(USER_DOMAIN_ID))
                .scopeToProject(Identifier.byId(PROJECT_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        and: "get the id of a dns zone"
        ZONE_ID = os.dns().zones().list().first().getId()

        and: "the id of the zone shouldn't be null"
        ZONE_ID != null

        and: "get name of the zone"
        ZONE_NAME = os.dns().zones().list().first().getName()

        and: "the name of the zone shouldn't be null"
        ZONE_NAME != null

        when: "we try to create a recordset with argument 'null' "
        os.dns().recordsets().create(null, null)

        then: "a NPE is thrown"
        thrown NullPointerException

        when: "creating a recordset"
        Recordset recordset = os.dns().recordsets().create(ZONE_ID, RECORDSET_NAME+ZONE_NAME, RECORDSET_TYPE, RECORDSET_RECORDS)

        then: "the attributes of the recordset should be equal"
        recordset.getName() == RECORDSET_NAME+ZONE_NAME
        recordset.getZoneId() == ZONE_ID
        recordset.getType() == RECORDSET_TYPE
        recordset.getRecords() == RECORDSET_RECORDS

        when: "getting the id of the recordset"
        RECORDSET_ID = recordset.getId()

        then: "this shouldn't be null"
        RECORDSET_ID != null

        when: "list recordsets owned by project"
        List<? extends Recordset> recordsetList = os.dns().recordsets().list()

        then: "the list shouldn't be empty and the recordset belongs to the project"
        recordsetList.isEmpty() == false
        recordsetList.first().getProjectId() == PROJECT_ID

        when: "list recordsets in a zone"
        List<? extends Recordset> recordsetListZone = os.dns().recordsets().list(ZONE_ID)

        then: "the list shouldn't be empty and the recordset is within the zone"
        recordsetListZone.isEmpty() == false
        recordsetListZone.first().getZoneId() == ZONE_ID

        when: "get a recordset by id"
        Recordset recordsetById = os.dns().recordsets().get(ZONE_ID,RECORDSET_ID)

        then: "the attributes of the recordset should be equal"
        recordsetById.getZoneId() == ZONE_ID
        recordsetById.getId() == RECORDSET_ID

        when: "updating a recordset"
        Recordset recordsetUpdated = os.dns().recordsets().update(ZONE_ID, Builders.recordset().id(RECORDSET_ID).description(RECORDSET_DESCRIPTION).build())

        then: "the attribute of the recordset should be updated"
        recordsetUpdated.getDescription() == RECORDSET_DESCRIPTION

        when: "deleting a recordset"
        ActionResponse deleteRecordset = os.dns().recordsets().delete(ZONE_ID,RECORDSET_ID)

        then: "this should be successful"
        deleteRecordset.isSuccess() == true

        cleanup: "delete created recordset in case of errors"
        os.dns().recordsets().delete(ZONE_ID,RECORDSET_ID)

    }
}
*/
