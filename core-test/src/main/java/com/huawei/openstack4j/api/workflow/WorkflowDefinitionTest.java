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

import com.huawei.openstack4j.api.workflow.WorkflowDefinitionService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.workflow.Scope;
import com.huawei.openstack4j.model.workflow.WorkflowDefinition;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for {@link WorkflowDefinitionService}.
 * 
 * @author Renat Akhmerov
 */
@Test(suiteName="WorkflowDefinitions")
public class WorkflowDefinitionTest extends WorkflowBaseTest {

    private static final String JSON_WF_DEF = "/workflow/wf_def.json";
    private static final String JSON_WF_DEFS = "/workflow/wf_defs.json";
    private static final String NEW_WF = "/workflow/new_wf.yaml";
    private static final String JSON_WF_DEF_CREATE = "/workflow/wf_def_create.json";

    private WorkflowDefinitionService service;

    @BeforeTest
    public void setUp() {
        this.service = osv3().workflow().workflowDefinitions();
    }

    @Test
    public void listWorkflowDefinitions() throws Exception {
        respondWith(JSON_WF_DEFS);
        
        List<? extends WorkflowDefinition> wfDefs = service.list();

        assertEquals(wfDefs.size(), 2);

        // Check first workflow definition.
        WorkflowDefinition wfDef = wfDefs.get(0);

        assertNotNull(wfDef);
        assertIsUUID(wfDef.getId());
        assertNotEmptyString(wfDef.getProjectId());
        assertEquals(wfDef.getName(), "parallel_join_2");
        assertNotNull(wfDef.getTags());
        assertEquals(wfDef.getTags().get(0),"test");
        assertEquals(wfDef.getTags().get(1), "private");
        assertNotNull(wfDef.getCreatedAt());
        assertNull(wfDef.getUpdatedAt());
        assertNotEmptyString(wfDef.getDefinition());
        assertTrue(wfDef.getDefinition().contains("join: all"));
        assertNotNull(wfDef.getInput());
        assertEquals(wfDef.getInput().length(), 0);

        // Check second workflow definition.
        wfDef = wfDefs.get(1);

        assertNotNull(wfDef);
        assertIsUUID(wfDef.getId());
        assertNotEmptyString(wfDef.getProjectId());
        assertEquals(wfDef.getName(), "with_items_50");
        assertNotNull(wfDef.getTags());
        assertEquals(wfDef.getTags().size(), 0);
        assertNull(wfDef.getCreatedAt());
        assertNotNull(wfDef.getUpdatedAt());
        assertNotEmptyString(wfDef.getDefinition());
        assertTrue(wfDef.getDefinition().contains("with-items: i in <% range(0, 50) %>"));
        assertNotNull(wfDef.getInput());
        assertEquals(wfDef.getInput().length(), 0);
    }

    @Test
    public void getWorkflowDefinition() throws Exception {
        respondWith(JSON_WF_DEF);

        WorkflowDefinition wfDef = service.get("eecf6cad-65af-4a11-9e6f-692b23ffac08");

        assertNotNull(wfDef);
        assertIsUUID(wfDef.getId());
        assertNotEmptyString(wfDef.getProjectId());
        assertEquals(wfDef.getName(), "parallel_join_2");
        assertNotNull(wfDef.getTags());
        assertEquals(wfDef.getTags().get(0), "test");
        assertEquals(wfDef.getTags().get(1), "private");
        assertNotNull(wfDef.getCreatedAt());
        assertNull(wfDef.getUpdatedAt());
        assertNotEmptyString(wfDef.getDefinition());
        assertTrue(wfDef.getDefinition().contains("join: all"));
        assertNotNull(wfDef.getInput());
        assertEquals(wfDef.getInput().length(), 0);
    }

    @Test
    public void createWorkflowDefinition() throws Exception {
        respondWith(JSON_WF_DEF_CREATE);

        List<? extends WorkflowDefinition> wfDefs = service.create(
                getClass().getResourceAsStream(NEW_WF),
                Scope.PRIVATE
        );

        assertEquals(wfDefs.size(), 1);

        WorkflowDefinition wfDef = wfDefs.get(0);

        assertNotNull(wfDef);
        assertIsUUID(wfDef.getId());
        assertNotEmptyString(wfDef.getProjectId());
        assertEquals(wfDef.getName(), "with_items_40");
        assertNotNull(wfDef.getTags());
        assertEquals(wfDef.getTags().get(0), "test");
        assertEquals(wfDef.getTags().get(1), "private");
        assertNotNull(wfDef.getCreatedAt());
        assertNull(wfDef.getUpdatedAt());
        assertNotEmptyString(wfDef.getDefinition());
        assertTrue(wfDef.getDefinition().contains("with-items: i in <% range(0, 40) %>"));
        assertNotNull(wfDef.getInput());
        assertEquals(wfDef.getInput().length(), 0);
    }

    @Test
    public void deleteWorkflowDefinition() throws Exception {
        respondWith(204); // No content.

        ActionResponse resp = service.delete("with_items_40");

        // TODO(rakhmerov): Change to 204 once ActionResponse can return other 2xx codes.
        assertEquals(resp.getCode(), 200);
    }
}
