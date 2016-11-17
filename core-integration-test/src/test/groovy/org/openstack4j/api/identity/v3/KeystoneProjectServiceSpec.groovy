package org.openstack4j.api.identity.v3

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName

import org.openstack4j.api.AbstractSpec
import org.openstack4j.api.Builders
import org.openstack4j.api.OSClient.OSClientV3
import org.openstack4j.model.common.ActionResponse
import org.openstack4j.model.common.Identifier
import org.openstack4j.model.identity.v3.Project
import org.openstack4j.openstack.OSFactory

import software.betamax.Configuration
import software.betamax.MatchRules
import software.betamax.TapeMode
import software.betamax.junit.Betamax
import software.betamax.junit.RecorderRule

import spock.lang.IgnoreIf

@Slf4j
class KeystoneProjectServiceSpec extends AbstractSpec {

    @Rule TestName KeystoneProjectServiceTest
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());

    // used for project crud
    def static String PROJECT_NAME = "Project_CRUD"
    def static String PROJECT_DESCRIPTION = "Project used for CRUD tests"
    def static String PROJECT_DESCRIPTION_UPDATED = "An updated project used for CRUD tests"

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
        log.info("-> Test: '$KeystoneProjectServiceTest.methodName'")
    }


    // ------------ ProjectService Tests ------------

    @IgnoreIf({ skipTest })
    @Betamax(tape="projectService_crud.tape")
    def "create, read, update, delete project service test cases"() {

        given: "an authenticated OSClient"
        OSClientV3 os = OSFactory.builderV3()
                .endpoint(AUTH_URL)
                .credentials(USER_ID, PASSWORD)
                .scopeToDomain(Identifier.byId(DOMAIN_ID))
                .withConfig(CONFIG_PROXY_BETAMAX)
                .authenticate()

        when: "we try to create a project with argument 'null' "
        os.identity().projects().create(null);

        then: "a NPE is thrown"
        thrown NullPointerException

        when: "a project object is created using using ProjectBuilder with valid string attributes"
        Project project = Builders.project()
                .name(PROJECT_NAME)
                .description(PROJECT_DESCRIPTION)
                .domainId(PROJECT_DOMAIN_ID)
                .enabled(true)
                .build()

        and:
        Project newProject = os.identity().projects().create(project)

        then: "check that the project has been created correctly"
        newProject.getName() == PROJECT_NAME
        newProject.getDomainId() == PROJECT_DOMAIN_ID
        newProject.getDescription() == PROJECT_DESCRIPTION
        newProject.isEnabled() == true

        when: "details about the recently created project are requested by the project's name and domain identifier"
        Project project_byName_byDomainId = os.identity().projects().getByName(PROJECT_NAME, PROJECT_DOMAIN_ID)

        then: "verify that the correct project was returned"
        project_byName_byDomainId.getName() == PROJECT_NAME
        project_byName_byDomainId.getDescription() == PROJECT_DESCRIPTION
        project_byName_byDomainId.getDomainId() == PROJECT_DOMAIN_ID

        and: "get the projects unique identifier"
        def PROJECT_CRUD_ID = project_byName_byDomainId.getId()

        when: "details about the recently created project are requested by the project's name across all domains"
        List<? extends Project> projectList_byName = os.identity().projects().getByName(PROJECT_NAME)

        then: "that list should contain at least one project with matching name"
        projectList_byName.isEmpty() == false
        projectList_byName.get(0).getName() == PROJECT_NAME

        when: "details about a project specified by id are requested"
        Project project_byId = os.identity().projects().get(PROJECT_CRUD_ID)

        then: "check if it's the correct project"
        project_byId.getId() == PROJECT_CRUD_ID
        project_byId.getName() == PROJECT_NAME
        project_byId.getDescription() == PROJECT_DESCRIPTION
        project_byId.getDomainId() == PROJECT_DOMAIN_ID

        when: "a list of all projects is requested the current token has access to"
        List<? extends Project> projectList = os.identity().projects().list()

        then: "this list should contain at least one project"
        projectList.isEmpty() == false

        // TODO: Commented out, because currently the HttpClient used betamax v1.1.2 does not support HTTP PATCH method.
        //       See DefaultHttpRequestFactory used in co.freeside.betamax.proxy.handler.TargetConnector .
        //       Therefore update() is tested in core-test.
        //
        //        when: "an existing project is updated"
        //        Project project_setToUpdate = os.identity().projects().get(PROJECT_ID)
        //        if (project != null)
        //            Project updatedProject = os.identity().projects().update(project_setToUpdate.toBuilder().description(PROJECT_DESCRIPTION_UPDATED).build());
        //
        //        then: "verify the updated attributes"
        //        updatedProject.getDescription() == PROJECT_DESCRIPTION_UPDATED
        //        updatedProject.getName() == PROJECT_NAME
        //        updatedProject.getId() == PROJECT_CRUD_ID

        when: "a project is deleted"
        ActionResponse response_deleteProject_success = os.identity().projects().delete(PROJECT_CRUD_ID)

        then: "the response indicates that is has been successfully removed"
        response_deleteProject_success.isSuccess() == true

        when: "trying to delete a project that doesn't exist"
        ActionResponse response_deleteProject_fail = os.identity().projects().delete(PROJECT_CRUD_ID)

        then: "the response also returns true"
        response_deleteProject_fail.isSuccess() == true

        when: "details about a nonexistent project specified by name across all domains are requested"
        List<? extends Project> projectList_byName_empty = os.identity().projects().getByName("nonExistentProject")

        then: "an empty project list should be returned"
        projectList_byName_empty.isEmpty() == true

        when: "details about a nonexistend project specified by name and domain identifier are requested"
        Project nonExistentProject_ByName = os.identity().projects().getByName("nonExistentProject", PROJECT_DOMAIN_ID)

        then: "this should return null"
		nonExistentProject_ByName == null

    }

}
