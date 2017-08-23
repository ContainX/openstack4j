/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.workflow;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.workflow.WorkbookDefinitionService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.workflow.Scope;
import com.huawei.openstack4j.model.workflow.WorkbookDefinition;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for {@link WorkbookDefinitionService}.
 * 
 * @author Renat Akhmerov
 */
@Test(suiteName="WorkbookDefinitions")
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
    public void listWorkbookDefinitions() throws Exception {
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
        assertEquals(wbDef.getTags().get(0),"test");
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
    public void getWorkbookDefinition() throws Exception {
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
    public void createWorkbookDefinition() throws Exception {
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
    public void deleteWorkbookDefinition() throws Exception {
        respondWith(204); // No content.

        ActionResponse resp = service.delete("my_wb");

        // TODO(rakhmerov): Change to 204 once ActionResponse can return other 2xx codes.
        assertEquals(resp.getCode(), 200);
    }
}
