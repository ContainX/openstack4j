package org.openstack4j.api.workflow;

import org.openstack4j.model.workflow.State;
import org.openstack4j.model.workflow.TaskExecution;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for {@link TaskExecutionService}.
 *
 * @author Renat Akhmerov
 */
@Test(suiteName = "TaskExecutions")
public class TaskExecutionTest extends WorkflowBaseTest {

    private static final String JSON_TASK_EXEC = "/workflow/task_exec.json";
    private static final String JSON_TASK_EXECS = "/workflow/task_execs.json";

    private TaskExecutionService service;

    @BeforeTest
    public void setUp() {
        this.service = osv3().workflow().taskExecutions();
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_TASK_EXECS);

        List<? extends TaskExecution> taskExecs = service.list();

        assertEquals(taskExecs.size(), 2);

        // Check first task execution.
        TaskExecution taskExec = taskExecs.get(0);

        assertNotNull(taskExec);
        assertIsUUID(taskExec.getId());
        assertEquals(taskExec.getName(), "join_task");
        assertEquals(taskExec.getType(), "ACTION");
        assertNotNull(taskExec.getCreatedAt());
        assertNotNull(taskExec.getUpdatedAt());
        assertEquals(taskExec.getWorkflowName(), "parallel_join_2");
        assertIsUUID(taskExec.getWorkflowDefinitionId());
        assertIsUUID(taskExec.getWorkflowExecutionId());
        assertEquals(taskExec.getState(), State.SUCCESS);
        assertNull(taskExec.getStateInfo());
        assertEquals(taskExec.getRuntimeContext().size(), 1);
        assertEquals(taskExec.getRuntimeContext().get("retry_cnt"), 1);
        assertEquals(taskExec.getPublished().size(), 1);
        assertEquals(taskExec.getPublished().get("my_var"), "my_val");
        assertEquals(taskExec.getResult(), "my task result");
        assertTrue(taskExec.isProcessed());

        // Check second task execution.
        taskExec = taskExecs.get(1);

        assertNotNull(taskExec);
        assertIsUUID(taskExec.getId());
        assertEquals(taskExec.getName(), "task1");
        assertEquals(taskExec.getType(), "WORKFLOW");
        assertNotNull(taskExec.getCreatedAt());
        assertNotNull(taskExec.getUpdatedAt());
        assertEquals(taskExec.getWorkflowName(), "parallel_join_2");
        assertIsUUID(taskExec.getWorkflowDefinitionId());
        assertIsUUID(taskExec.getWorkflowExecutionId());
        assertEquals(taskExec.getState(), State.ERROR);
        assertEquals(taskExec.getStateInfo(), "Some error info");
        assertEquals(taskExec.getRuntimeContext().size(), 1);
        assertEquals(taskExec.getRuntimeContext().get("retry_cnt"), 1);
        assertEquals(taskExec.getPublished().size(), 1);
        assertEquals(taskExec.getPublished().get("my_var"), "my_val");
        assertEquals(taskExec.getResult(), "my task result");
        assertFalse(taskExec.isProcessed());

    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_TASK_EXEC);

        TaskExecution taskExec = service.get("6e7cce36-1ab2-45c8-baf5-88d5d726ea1d");

        assertNotNull(taskExec);
        assertIsUUID(taskExec.getId());
        assertEquals(taskExec.getName(), "join_task");
        assertEquals(taskExec.getType(), "ACTION");
        assertNotNull(taskExec.getCreatedAt());
        assertNotNull(taskExec.getUpdatedAt());
        assertEquals(taskExec.getWorkflowName(), "parallel_join_2");
        assertIsUUID(taskExec.getWorkflowDefinitionId());
        assertIsUUID(taskExec.getWorkflowExecutionId());
        assertEquals(taskExec.getState(), State.SUCCESS);
        assertNull(taskExec.getStateInfo());
        assertEquals(taskExec.getRuntimeContext().size(), 1);
        assertEquals(taskExec.getRuntimeContext().get("retry_cnt"), 1);
        assertEquals(taskExec.getPublished().size(), 1);
        assertEquals(taskExec.getPublished().get("my_var"), "my_val");
        assertEquals(taskExec.getResult(), "my task result");
        assertTrue(taskExec.isProcessed());
    }
}
