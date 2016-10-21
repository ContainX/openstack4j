package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Region
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneRegionServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneRegionServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());;

    // additional attributes for region tests
    def static String REGION_CRUD_ID = "Region_CRUD"
    def static String REGION_CRUD_DESCRIPTION = "A region used for CRUD tests."
    def static String REGION_CRUD_PARENTREGIONID = "RegionOne"
    def static String REGION_CRUD_DESCRIPTION_UPDATE = "A updated region used for CRUD tests."

    static final boolean skipTest

    static {
        if(
        USER_ID == null ||
        AUTH_URL == null ||
        PASSWORD == null ||
        DOMAIN_ID == null  ) {

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
        log.info("-> Test: '$KeystoneRegionServiceTest.methodName'")
    }


    // ------------ RegionService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="regionService_all.tape")
    def "region service CRUD test cases"() {

        given: "authenticated v3 OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        when: "create a new region"
        Region region = os.identity().regions().create(REGION_CRUD_ID, REGION_CRUD_DESCRIPTION, REGION_CRUD_PARENTREGIONID)

        then: "verify the region"
        region.getId() == REGION_CRUD_ID
        region.getDescription() == REGION_CRUD_DESCRIPTION
        region.getParentRegionId() == REGION_CRUD_PARENTREGIONID

        when: "list all region"
        List<? extends Region> regionList = os.identity().regions().list()

        then: "the list should contain at least the recently created region"
        regionList.isEmpty() == false
        regionList.find { it.getId() == REGION_CRUD_ID }

        when: "get a region by id"
        Region region_byId = os.identity().regions().get(REGION_CRUD_ID)

        then: "check the correct region was returned"
        region_byId.getId() == REGION_CRUD_ID
        region_byId.getDescription() == REGION_CRUD_DESCRIPTION
        region_byId.getParentRegionId() == REGION_CRUD_PARENTREGIONID

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "a region attribute is updated"
        //        Region region_setToUpdate = os.identity().regions().get(REGION_CRUD_ID)
        //        if(region_setToUpdate != null)
        //            Region updatedRegion = os.identity().regions().update(region_setToUpdate.toBuilder().description(REGION_CRUD_DESCRIPTION_UPDATE).build())
        //
        //        then: "check if the update was successful"
        //        updatedRegion.getId() == REGION_CRUD_ID
        //        updatedRegion.getDescription() == REGION_CRUD_DESCRIPTION_UPDATE

        when: "delete a region"
        ActionResponse response_deleteRegion_success = os.identity().regions().delete(REGION_CRUD_ID)

        then: "this should be successful"
        response_deleteRegion_success.isSuccess() == true

        when: "trying to get a non-existent region by id"
        Region region_nonexistent_byId = os.identity().regions().get("nonExistentRegionId")

        then: "this should be null"
        region_nonexistent_byId == null

        when: "try to find a non-existent region in the list of regions"
        List<? extends Region> regionList_nonExistentRegion = os.identity().regions().list()

        then: "ensure the non-existent region is not found"
        regionList.find { it.getId() == "nonExistentRegionId" } == null

    }

}
