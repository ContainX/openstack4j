package org.openstack4j.api.workflow;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.workflow.Scope;
import org.openstack4j.model.workflow.WorkflowEnvironment;
import org.openstack4j.openstack.workflow.domain.MistralWorkflowEnvironment;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for {@link WorkflowEnvironmentService}.
 *
 * @author Renat Akhmerov
 */
@Test(suiteName = "WorkflowEnvironments")
public class WorkflowEnvironmentTest extends WorkflowBaseTest {

    private static final String JSON_WF_ENV = "/workflow/wf_env.json";
    private static final String JSON_WF_ENVS = "/workflow/wf_envs.json";
    private static final String JSON_WF_ENV_CREATE = "/workflow/wf_env_create.json";

    private WorkflowEnvironmentService service;

    @BeforeTest
    public void setUp() {
        this.service = osv3().workflow().environments();
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_WF_ENVS);

        List<? extends WorkflowEnvironment> envs = service.list();

        assertEquals(envs.size(), 2);

        // Check first environment.
        WorkflowEnvironment env = envs.get(0);

        assertNotNull(env);
        assertIsUUID(env.getId());
        assertEquals(env.getName(), "my_env1");
        assertEquals(env.getDescription(), "This is my test environment 1");
        assertEquals(env.getProjectId(), "<default-project>");
        assertEquals(env.getScope(), Scope.PRIVATE);
        assertNotNull(env.getCreatedAt());
        assertNotNull(env.getUpdatedAt());
        assertEquals(env.getVariables().get("var1"), "val1");
        assertEquals(env.getVariables().get("var2"), "val2");

        // Check second environment.
        env = envs.get(1);

        assertNotNull(env);
        assertIsUUID(env.getId());
        assertEquals(env.getName(), "my_env2");
        assertEquals(env.getDescription(), "This is my test environment 2");
        assertEquals(env.getProjectId(), "<default-project>");
        assertEquals(env.getScope(), Scope.PUBLIC);
        assertNotNull(env.getCreatedAt());
        assertNotNull(env.getUpdatedAt());
        assertEquals(env.getVariables().get("var3"), "val3");
        assertEquals(env.getVariables().get("var4"), "val4");
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_WF_ENV);

        WorkflowEnvironment env = service.get("eecf6cad-65af-4a11-9e6f-692b23ffac08");

        assertNotNull(env);
        assertIsUUID(env.getId());
        assertEquals(env.getName(), "my_env");
        assertEquals(env.getDescription(), "This is my test environment");
        assertEquals(env.getProjectId(), "<default-project>");
        assertEquals(env.getScope(), Scope.PRIVATE);
        assertNotNull(env.getCreatedAt());
        assertNotNull(env.getUpdatedAt());
        assertEquals(env.getVariables().get("var1"), "val1");
        assertEquals(env.getVariables().get("var2"), "val2");
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_WF_ENV_CREATE);

        WorkflowEnvironment env = MistralWorkflowEnvironment.builder().
                name("my_env").
                description("This is my test environment").
                variables(Collections.<String, Object>singletonMap("var1", 1)).
                build();

        env = service.create(env);

        assertNotNull(env);
        assertIsUUID(env.getId());
        assertEquals(env.getName(), "my_env");
        assertEquals(env.getDescription(), "This is my test environment");
        assertEquals(env.getProjectId(), "<default-project>");
        assertEquals(env.getScope(), Scope.PRIVATE);
        assertNotNull(env.getCreatedAt());
        assertNotNull(env.getUpdatedAt());
        assertEquals(env.getVariables().get("var1"), "val1");
        assertEquals(env.getVariables().get("var2"), "val2");
    }

    @Test
    public void delete() throws Exception {
        respondWith(204); // No content.

        ActionResponse resp = service.delete("concat");

        assertEquals(resp.getCode(), 204);
    }
}
