package org.openstack4j.api.workflow;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.workflow.ActionExecution;
import org.openstack4j.model.workflow.State;
import org.openstack4j.openstack.workflow.domain.MistralActionExecution;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for {@link ActionExecutionService}.
 *
 * @author Renat Akhmerov
 */
@Test(suiteName = "ActionExecutions")
public class ActionExecutionTest extends WorkflowBaseTest {

    private static final String JSON_ACTION_EXEC = "/workflow/action_exec.json";
    private static final String JSON_ACTION_EXECS = "/workflow/action_execs.json";
    private static final String JSON_ACTION_EXEC_CREATE = "/workflow/action_exec_create.json";

    private ActionExecutionService service;

    @BeforeTest
    public void setUp() {
        this.service = osv3().workflow().actionExecutions();
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_ACTION_EXECS);

        List<? extends ActionExecution> actionExecs = service.list();

        assertEquals(actionExecs.size(), 2);

        // Check first action execution.
        ActionExecution actionExec = actionExecs.get(0);

        assertNotNull(actionExec);
        assertIsUUID(actionExec.getId());
        assertEquals(actionExec.getName(), "std.noop");
        assertEquals(actionExec.getTaskName(), "task_1");
        assertEquals(actionExec.getDescription(), "");
        assertNotNull(actionExec.getCreatedAt());
        assertNotNull(actionExec.getUpdatedAt());
        assertEquals(actionExec.getWorkflowName(), "parallel_join_2");
        assertEquals(actionExec.getTaskExecutionId(), "c40c26cb-f695-4f20-8455-c57ca5c0770a");
        assertEquals(actionExec.getInput().size(), 1);
        assertEquals(actionExec.getInput().get("param1"), "val1");
        assertEquals(actionExec.getOutput().size(), 1);
        assertNull(actionExec.getOutput().get("result"));
        assertEquals(actionExec.getState(), State.SUCCESS);
        assertNull(actionExec.getStateInfo());
        assertTrue(actionExec.isAccepted());

        // Check second action execution.
        actionExec = actionExecs.get(1);

        assertNotNull(actionExec);
        assertIsUUID(actionExec.getId());
        assertEquals(actionExec.getName(), "std.noop");
        assertEquals(actionExec.getTaskName(), "task_2");
        assertEquals(actionExec.getDescription(), "");
        assertNotNull(actionExec.getCreatedAt());
        assertNotNull(actionExec.getUpdatedAt());
        assertEquals(actionExec.getWorkflowName(), "parallel_join_2");
        assertEquals(actionExec.getTaskExecutionId(), "c40c26cb-f695-4f20-8455-c57ca5c0770a");
        assertEquals(actionExec.getInput().size(), 1);
        assertEquals(actionExec.getInput().get("param1"), "val1");
        assertEquals(actionExec.getOutput().size(), 1);
        assertNull(actionExec.getOutput().get("result"));
        assertEquals(actionExec.getState(), State.ERROR);
        assertEquals(actionExec.getStateInfo(), "Some error info");
        assertTrue(actionExec.isAccepted());
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_ACTION_EXEC);

        ActionExecution actionExec = service.get("294725fa-980d-436f-b882-a75cfeffa8c0");

        assertNotNull(actionExec);
        assertIsUUID(actionExec.getId());
        assertEquals(actionExec.getName(), "std.noop");
        assertEquals(actionExec.getTaskName(), "task_1");
        assertEquals(actionExec.getDescription(), "");
        assertNotNull(actionExec.getCreatedAt());
        assertNotNull(actionExec.getUpdatedAt());
        assertEquals(actionExec.getWorkflowName(), "parallel_join_2");
        assertEquals(actionExec.getTaskExecutionId(), "c40c26cb-f695-4f20-8455-c57ca5c0770a");
        assertEquals(actionExec.getInput().size(), 1);
        assertEquals(actionExec.getInput().get("param1"), "val1");
        assertEquals(actionExec.getOutput().size(), 1);
        assertNull(actionExec.getOutput().get("result"));
        assertEquals(actionExec.getState(), State.SUCCESS);
        assertNull(actionExec.getStateInfo());
        assertTrue(actionExec.isAccepted());
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_ACTION_EXEC_CREATE);

        ActionExecution actionExec = new MistralActionExecution.MistralActionExecutionBuilder().
                name("std.echo").
                input(Collections.<String, Object>singletonMap("output", "Hello Mistral Java Client!")).
                build();

        actionExec = service.create(actionExec);

        assertNotNull(actionExec);
        assertIsUUID(actionExec.getId());
        assertEquals(actionExec.getName(), "std.noop");
        assertEquals(actionExec.getTaskName(), "task_1");
        assertEquals(actionExec.getDescription(), "");
        assertNotNull(actionExec.getCreatedAt());
        assertNotNull(actionExec.getUpdatedAt());
        assertEquals(actionExec.getWorkflowName(), "parallel_join_2");
        assertEquals(actionExec.getTaskExecutionId(), "c40c26cb-f695-4f20-8455-c57ca5c0770a");
        assertEquals(actionExec.getInput().size(), 1);
        assertEquals(actionExec.getInput().get("param1"), "val1");
        assertEquals(actionExec.getOutput().size(), 1);
        assertNull(actionExec.getOutput().get("result"));
        assertEquals(actionExec.getState(), State.SUCCESS);
        assertNull(actionExec.getStateInfo());
        assertTrue(actionExec.isAccepted());
    }

    @Test
    public void delete() throws Exception {
        respondWith(204); // No content.

        ActionResponse resp = service.delete("294725fa-980d-436f-b882-a75cfeffa8c0");

        assertEquals(resp.getCode(), 204);
    }
}
