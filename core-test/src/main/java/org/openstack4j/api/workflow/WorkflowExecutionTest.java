package org.openstack4j.api.workflow;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.workflow.State;
import org.openstack4j.model.workflow.WorkflowExecution;
import org.openstack4j.openstack.workflow.domain.MistralWorkflowExecution.MistralWorkflowExecutionBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for {@link WorkflowExecutionService}.
 *
 * @author Renat Akhmerov
 */
@Test(suiteName = "WorkflowExecutions")
public class WorkflowExecutionTest extends WorkflowBaseTest {

    private static final String JSON_WF_EXEC = "/workflow/wf_exec.json";
    private static final String JSON_WF_EXECS = "/workflow/wf_execs.json";
    private static final String JSON_WF_EXEC_CREATE = "/workflow/wf_exec_create.json";

    private WorkflowExecutionService service;

    @BeforeTest
    public void setUp() {
        this.service = osv3().workflow().workflowExecutions();
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_WF_EXECS);

        List<? extends WorkflowExecution> wfExecs = service.list();

        assertEquals(wfExecs.size(), 2);

        // Check first workflow execution.
        WorkflowExecution wfExec = wfExecs.get(0);

        assertNotNull(wfExec);
        assertIsUUID(wfExec.getId());
        assertEquals(wfExec.getWorkflowName(), "parallel_join_2");
        assertNull(wfExec.getTags());
        assertNotNull(wfExec.getCreatedAt());
        assertNotNull(wfExec.getUpdatedAt());
        assertNotNull(wfExec.getInput());
        assertEquals(wfExec.getInput().size(), 0);
        assertNotNull(wfExec.getParameters());
        assertEquals(wfExec.getParameters().size(), 0);
        assertEquals(wfExec.getState(), State.SUCCESS);
        assertNull(wfExec.getStateInfo());

        // Check second workflow execution.
        wfExec = wfExecs.get(1);

        assertNotNull(wfExec);
        assertIsUUID(wfExec.getId());
        assertEquals(wfExec.getWorkflowName(), "parallel_join_2");
        assertNotNull(wfExec.getTags());
        assertEquals(wfExec.getTags().size(), 2);
        assertEquals(wfExec.getTags().get(0), "test");
        assertEquals(wfExec.getTags().get(1), "private");
        assertNotNull(wfExec.getCreatedAt());
        assertNotNull(wfExec.getUpdatedAt());
        assertNotNull(wfExec.getInput());
        assertEquals(wfExec.getInput().size(), 0);
        assertEquals(wfExec.getState(), State.ERROR);
        assertEquals(wfExec.getStateInfo(), "Failed for reason X");
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_WF_EXEC);

        WorkflowExecution wfExec = service.get("79d187f4-b8e5-4288-b2cd-ed27ee31e4b0");

        assertNotNull(wfExec);
        assertIsUUID(wfExec.getId());
        assertEquals(wfExec.getWorkflowName(), "parallel_join_2");
        assertNull(wfExec.getTags());
        assertNotNull(wfExec.getCreatedAt());
        assertNotNull(wfExec.getUpdatedAt());
        assertNotNull(wfExec.getInput());
        assertEquals(wfExec.getInput().size(), 0);
        assertNotNull(wfExec.getParameters());
        assertEquals(wfExec.getParameters().size(), 0);
        assertEquals(wfExec.getState(), State.SUCCESS);
        assertNull(wfExec.getStateInfo());
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_WF_EXEC_CREATE);

        WorkflowExecution wfExec = new MistralWorkflowExecutionBuilder().
                workflowName("parallel_join_2").
                parameters(Collections.<String, Object>singletonMap("env", "my_env")).
                build();

        wfExec = service.create(wfExec);

        assertNotNull(wfExec);
        assertIsUUID(wfExec.getId());
        assertEquals(wfExec.getWorkflowName(), "parallel_join_2");
        assertNull(wfExec.getTags());
        assertNotNull(wfExec.getCreatedAt());
        assertNull(wfExec.getUpdatedAt());
        assertNotNull(wfExec.getInput());
        assertEquals(wfExec.getInput().size(), 0);
        assertNotNull(wfExec.getParameters());
        assertEquals(wfExec.getParameters().size(), 1);
        assertEquals(wfExec.getParameters().get("env"), "my_env");
        assertEquals(wfExec.getState(), State.RUNNING);
        assertNull(wfExec.getStateInfo());
    }

    @Test
    public void delete() throws Exception {
        respondWith(204); // No content.

        ActionResponse resp = service.delete("parallel_join_2");

        assertEquals(resp.getCode(), 204);
    }
}
