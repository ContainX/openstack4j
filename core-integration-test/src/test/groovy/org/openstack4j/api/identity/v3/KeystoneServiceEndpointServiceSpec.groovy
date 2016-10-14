package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.Builders
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.api.types.Facing
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Endpoint
import org.openstack4j.model.identity.v3.Service
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneServiceEndpointServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneServiceEndpointServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // additional attributes for service endpoint and service tests
    def static final String SERVICE_CRUD_TYPE = "identity"
    def static final String SERVICE_CRUD_NAME = "Service_CRUD"
    def static final String SERVICE_CRUD_DESCRIPTION = "A service used for tests."
    def static final String SERVICE_CRUD_DESCRIPTION_UPDATE = "A updated service used for tests."
    def static final String ENDPOINT_CRUD_NAME = "Endpoint_CRUD"
    def static final URL ENDPOINT_CRUD_URL = new URL( "http", "devstack.openstack.stack", 5000, "/v3")
    def static final URL ENDPOINT_CRUD_URL_UPDATE = new URL( "http", "stack.openstack.devstack", 5000, "/v3");
    def static final Facing ENDPOINT_CRUD_IFACE = Facing.ADMIN
    def static final String ENDPOINT_CRUD_REGIONID = "RegionOne"
    def String SERVICE_CRUD_ID
    def String ENDPOINT_CRUD_ID

    static final boolean skipTest

    static {
        if(
        USER_ID == null ||
        AUTH_URL == null ||
        PASSWORD == null ||
        DOMAIN_ID == null ||
        PROJECT_ID == null  ) {

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
        log.info("-> Test: '$KeystoneServiceEndpointServiceTest.methodName'")
    }


    // ------------ ServiceEndpointService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="serviceEndpoints_all.tape")
    def "service manager service test cases"() {

        given: "authenticated v3 OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        when: "a service is created using the service builder"
        Service service = os.identity().serviceEndpoints().create(Builders.service()
                .type(SERVICE_CRUD_TYPE)
                .name(SERVICE_CRUD_NAME)
                .description(SERVICE_CRUD_DESCRIPTION)
                .enabled(true)
                .build())

        then: "verify the service"
        service.getType() == SERVICE_CRUD_TYPE
        service.getName() == SERVICE_CRUD_NAME
        service.getDescription() == SERVICE_CRUD_DESCRIPTION
        service.isEnabled() == true

        when: "we get the id of the service"
        SERVICE_CRUD_ID = service.getId()

        then: "the id shouldn't be null "
        SERVICE_CRUD_ID != null

        when: "all services are listed"
        List<? extends Service> serviceList = os.identity().serviceEndpoints().list()

        then: "the list should contain at least the recently created service"
        serviceList.isEmpty() == false
        serviceList.find { it.getId() == SERVICE_CRUD_ID}

        when: "we get the recently created service by id"
        Service service_byId = os.identity().serviceEndpoints().get(SERVICE_CRUD_ID)

        then: "check if the correct service was returned"
        service_byId.getId() == SERVICE_CRUD_ID
        service_byId.getType() == SERVICE_CRUD_TYPE
        service_byId.getName() == SERVICE_CRUD_NAME
        service_byId.getDescription() == SERVICE_CRUD_DESCRIPTION

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "a service attribute is updated"
        //        Service service_setToUpdate = os.identity().serviceEndpoints().get(SERVICE_CRUD_ID)
        //        if(service_setToUpdate != null)
        //            Service updatedService = os.identity().serviceEndpoints().update(service_setToUpdate.toBuilder().description(SERVICE_CRUD_DESCRIPTION_UPDATE).build())
        //
        //        then: "check if the update was successful"
        //        updatedService.getId() == SERVICE_CRUD_ID
        //        updatedService.getDescription() == SERVICE_CRUD_DESCRIPTION_UPDATE

        when: "an endpoint is created for the recently used service"
        Endpoint endpoint = os.identity().serviceEndpoints().createEndpoint(ENDPOINT_CRUD_NAME, ENDPOINT_CRUD_URL, ENDPOINT_CRUD_IFACE, ENDPOINT_CRUD_REGIONID, SERVICE_CRUD_ID, true)

        then: "verify the new endpoint"
        endpoint.getName() == ENDPOINT_CRUD_NAME
        endpoint.getUrl() == ENDPOINT_CRUD_URL
        endpoint.getIface() == ENDPOINT_CRUD_IFACE
        endpoint.getRegionId() == ENDPOINT_CRUD_REGIONID
        endpoint.getServiceId() == SERVICE_CRUD_ID
        endpoint.isEnabled() == true

        when: "we get the endpoints id"
        ENDPOINT_CRUD_ID = endpoint.getId()

        then: "this shouldn't be null"
        ENDPOINT_CRUD_ID != null

        when: "available endpoints are listed"
        List<? extends Endpoint> endpointList = os.identity().serviceEndpoints().listEndpoints()

        then: "the list should contain at least one endpoint"
        endpointList.isEmpty() == false
        endpointList.find { it.getId() == ENDPOINT_CRUD_ID}

        when: "get details on the recently created endpoint specified by id"
        Endpoint endpoint_byId = os.identity().serviceEndpoints().getEndpoint(ENDPOINT_CRUD_ID)

        then: "verify the correct endpoint was returned"
        endpoint_byId.getId() == ENDPOINT_CRUD_ID
        endpoint_byId.getName() == ENDPOINT_CRUD_NAME
        endpoint_byId.getUrl() == ENDPOINT_CRUD_URL
        endpoint_byId.getIface() == ENDPOINT_CRUD_IFACE
        endpoint_byId.getRegionId() == ENDPOINT_CRUD_REGIONID
        endpoint_byId.getServiceId() == SERVICE_CRUD_ID

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "an attribute of the endpoint is updated"
        //        Endpoint endpoint_setToUpdate = os.identity().serviceEndpoints().getEndpoint(ENDPOINT_CRUD_ID)
        //        if(endpoint_setToUpdate != null)
        //            Endpoint updatedEndpoint = os.identity().serviceEndpoints().updateEndpoint(endpoint_setToUpdate.toBuilder().description(ENDPOINT_CRUD_URL_UPDATE).build())
        //
        //        then: "check if the update was successful"
        //        updatedEndpoint.getId() == SERVICE_CRUD_ID
        //        updatedEndpoint.getUrl() == ENDPOINT_CRUD_URL_UPDATE

        when: "delete an endpoint"
        ActionResponse response_deleteEndpoint_success = os.identity().serviceEndpoints().deleteEndpoint(ENDPOINT_CRUD_ID)

        then: "this should be successful"
        response_deleteEndpoint_success.isSuccess() == true

        when: "delete an existing service"
        ActionResponse response_deleteService_success = os.identity().serviceEndpoints().delete(SERVICE_CRUD_ID)

        then: "this should be successful"
        response_deleteEndpoint_success.isSuccess() == true

    }

}
