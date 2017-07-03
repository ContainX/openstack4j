package org.openstack4j.api.workflow;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.workflow.Scope;
import org.openstack4j.model.workflow.WorkbookDefinition;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for {@link WorkbookDefinitionService}.
 *
 * @author Renat Akhmerov
 */
@Test(suiteName = "WorkbookDefinitions")
public class WorkbookDefinitionTest extends WorkflowBaseTest {

    private static final String JSON_WB_DEF = "/workflow/wb_def.json";
    private static final String JSON_WB_DEFS = "/workflow/wb_defs.json";
    private static final String NEW_WB = "/workflow/new_wb.yaml";
    private static final String JSON_WB_DEF_CREATE = "/workflow/wb_def_create.json";

    private WorkbookDefinitionService service;

    @BeforeTest
    public void setUp() {
        this.service = osv3().workflow().workbookDefinitions();
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_WB_DEFS);

        List<? extends WorkbookDefinition> wbDefs = service.list();

        assertEquals(wbDefs.size(), 2);

        // Check first workbook definition.
        WorkbookDefinition wbDef = wbDefs.get(0);

        assertNotNull(wbDef);
        assertIsUUID(wbDef.getId());
        assertNotEmptyString(wbDef.getProjectId());
        assertEquals(wbDef.getName(), "my_wb0");
        assertNotNull(wbDef.getTags());
        assertEquals(wbDef.getTags().get(0), "test");
        assertEquals(wbDef.getTags().get(1), "private");
        assertNotNull(wbDef.getCreatedAt());
        assertNull(wbDef.getUpdatedAt());
        assertNotEmptyString(wbDef.getDefinition());
        assertTrue(wbDef.getDefinition().contains("join: all"));

        // Check second workbook definition.
        wbDef = wbDefs.get(1);

        assertNotNull(wbDef);
        assertIsUUID(wbDef.getId());
        assertNotEmptyString(wbDef.getProjectId());
        assertEquals(wbDef.getName(), "my_wb1");
        assertNotNull(wbDef.getTags());
        assertEquals(wbDef.getTags().size(), 0);
        assertNotNull(wbDef.getCreatedAt());
        assertNull(wbDef.getUpdatedAt());
        assertNotEmptyString(wbDef.getDefinition());
        assertTrue(wbDef.getDefinition().contains("with-items: i in <% range(0, 40) %>"));
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_WB_DEF);

        WorkbookDefinition wbDef = service.get("eecf6cad-65af-4a11-9e6f-692b23ffac08");

        assertNotNull(wbDef);
        assertIsUUID(wbDef.getId());
        assertNotEmptyString(wbDef.getProjectId());
        assertEquals(wbDef.getName(), "my_wb");
        assertNotNull(wbDef.getTags());
        assertEquals(wbDef.getTags().get(0), "test");
        assertEquals(wbDef.getTags().get(1), "private");
        assertNotNull(wbDef.getCreatedAt());
        assertNull(wbDef.getUpdatedAt());
        assertNotEmptyString(wbDef.getDefinition());
        assertTrue(wbDef.getDefinition().contains("with-items: i in <% range(0, 40) %>"));
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_WB_DEF_CREATE);

        WorkbookDefinition wbDef = service.create(getClass().getResourceAsStream(NEW_WB), Scope.PRIVATE);

        assertNotNull(wbDef);
        assertIsUUID(wbDef.getId());
        assertNotEmptyString(wbDef.getProjectId());
        assertEquals(wbDef.getName(), "my_wb");
        assertNotNull(wbDef.getTags());
        assertEquals(wbDef.getTags().get(0), "test");
        assertEquals(wbDef.getTags().get(1), "private");
        assertNotNull(wbDef.getCreatedAt());
        assertNull(wbDef.getUpdatedAt());
        assertNotEmptyString(wbDef.getDefinition());
        assertTrue(wbDef.getDefinition().contains("with-items: i in <% range(0, 40) %>"));
    }

    @Test
    public void delete() throws Exception {
        respondWith(204); // No content.

        ActionResponse resp = service.delete("my_wb");

        assertEquals(resp.getCode(), 204);
    }
}
