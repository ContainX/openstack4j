package org.openstack4j.api.workflow;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.workflow.Scope;
import org.openstack4j.model.workflow.CronTrigger;
import org.openstack4j.openstack.workflow.domain.MistralCronTrigger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * Test cases for {@link CronTriggerService}.
 *
 * @author Renat Akhmerov
 */
@Test(suiteName = "WorkflowEnvironments")
public class CronTriggerTest extends WorkflowBaseTest {

    private static final String JSON_CRON_TRIGGER = "/workflow/cron_trigger.json";
    private static final String JSON_CRON_TRIGGERS = "/workflow/cron_triggers.json";
    private static final String JSON_CRON_TRIGGER_CREATE = "/workflow/cron_trigger_create.json";

    private CronTriggerService service;

    @BeforeTest
    public void setUp() {
        this.service = osv3().workflow().cronTriggers();
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_CRON_TRIGGERS);

        List<? extends CronTrigger> triggers = service.list();

        assertEquals(triggers.size(), 2);

        // Check first environment.
        CronTrigger trigger = triggers.get(0);

        assertNotNull(trigger);
        assertIsUUID(trigger.getId());
        assertEquals(trigger.getName(), "my_trigger1");
        assertEquals(trigger.getPattern(), "* * * * *");
        assertEquals(trigger.getScope(), Scope.PRIVATE);
        assertNotNull(trigger.getCreatedAt());
        assertNotNull(trigger.getUpdatedAt());
        assertEquals(trigger.getWorkflowName(), "my_wf");
        assertIsUUID(trigger.getWorkflowId());
        assertEquals(trigger.getWorkflowParameters().size(), 0);
        assertEquals(trigger.getWorkflowInput().size(), 2);
        assertEquals(trigger.getWorkflowInput().get("param1"), "val1");
        assertEquals(trigger.getWorkflowInput().get("param2"), "val2");
        assertNotNull(trigger.getFirstExecutionTime());
        assertNotNull(trigger.getNextExecutionTime());

        // Check second environment.
        trigger = triggers.get(1);

        assertNotNull(trigger);
        assertIsUUID(trigger.getId());
        assertEquals(trigger.getName(), "my_trigger2");
        assertEquals(trigger.getPattern(), "* * * * */5");
        assertEquals(trigger.getScope(), Scope.PUBLIC);
        assertNotNull(trigger.getCreatedAt());
        assertNotNull(trigger.getUpdatedAt());
        assertEquals(trigger.getWorkflowName(), "my_wf");
        assertIsUUID(trigger.getWorkflowId());
        assertEquals(trigger.getWorkflowParameters().size(), 0);
        assertEquals(trigger.getWorkflowInput().size(), 1);
        assertEquals(trigger.getWorkflowInput().get("param1"), "val1");
        assertNotNull(trigger.getFirstExecutionTime());
        assertNotNull(trigger.getNextExecutionTime());
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_CRON_TRIGGER);

        CronTrigger trigger = service.get("eecf6cad-65af-4a11-9e6f-692b23ffac08");

        assertNotNull(trigger);
        assertIsUUID(trigger.getId());
        assertEquals(trigger.getName(), "my_trigger");
        assertEquals(trigger.getPattern(), "* * * * *");
        assertEquals(trigger.getScope(), Scope.PRIVATE);
        assertNotNull(trigger.getCreatedAt());
        assertNotNull(trigger.getUpdatedAt());
        assertEquals(trigger.getWorkflowName(), "my_wf");
        assertIsUUID(trigger.getWorkflowId());
        assertEquals(trigger.getWorkflowParameters().size(), 0);
        assertEquals(trigger.getWorkflowInput().size(), 2);
        assertEquals(trigger.getWorkflowInput().get("param1"), "val1");
        assertEquals(trigger.getWorkflowInput().get("param2"), "val2");
        assertNotNull(trigger.getFirstExecutionTime());
        assertNotNull(trigger.getNextExecutionTime());
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_CRON_TRIGGER_CREATE);

        CronTrigger trigger = MistralCronTrigger.builder().
                name("my_trigger").
                workflowName("my_wf").
                workflowInput(Collections.<String, Object>singletonMap("param1", "val1")).
                pattern("* * * * *").
                build();

        trigger = service.create(trigger);

        assertNotNull(trigger);
        assertIsUUID(trigger.getId());
        assertEquals(trigger.getName(), "my_trigger");
        assertEquals(trigger.getPattern(), "* * * * *");
        assertEquals(trigger.getScope(), Scope.PRIVATE);
        assertNotNull(trigger.getCreatedAt());
        assertNull(trigger.getUpdatedAt());
        assertEquals(trigger.getWorkflowName(), "my_wf");
        assertIsUUID(trigger.getWorkflowId());
        assertEquals(trigger.getWorkflowParameters().size(), 0);
        assertEquals(trigger.getWorkflowInput().size(), 2);
        assertEquals(trigger.getWorkflowInput().get("param1"), "val1");
        assertEquals(trigger.getWorkflowInput().get("param2"), "val2");
        assertNotNull(trigger.getFirstExecutionTime());
        assertNotNull(trigger.getNextExecutionTime());
    }

    @Test
    public void delete() throws Exception {
        respondWith(204); // No content.

        ActionResponse resp = service.delete("concat");

        assertEquals(resp.getCode(), 204);
    }
}
