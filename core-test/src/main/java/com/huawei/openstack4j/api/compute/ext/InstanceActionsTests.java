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
package com.huawei.openstack4j.api.compute.ext;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.compute.InstanceAction;

/**
 * Tests the os-instance-actions Compute API extension.
 * 
 * @author Christian Banse
 */
@Test(suiteName = "Compute")
public class InstanceActionsTests extends AbstractTest {

    private static final String JSON_INSTANCE_ACTION = "/compute/ext/instance_action.json";
    private static final String JSON_INSTANCE_ACTIONS = "/compute/ext/instance_actions.json";

    public void getInstanceAction() throws Exception {
        respondWith(JSON_INSTANCE_ACTION);
        InstanceAction action = osv3().compute().servers().instanceActions().get("b48316c5-71e8-45e4-9884-6c78055b9b13",
                "req-3293a3f1-b44c-4609-b8d2-d81b105636b8");

        assertEquals("b48316c5-71e8-45e4-9884-6c78055b9b13", action.getInstanceUuid());
        assertEquals("req-3293a3f1-b44c-4609-b8d2-d81b105636b8", action.getRequestId());
        assertEquals("reboot", action.getAction());
        assertEquals("789", action.getUserId());
        assertEquals("147", action.getProjectId());
    }

    public void listInstanceActions() throws Exception {
        respondWith(JSON_INSTANCE_ACTIONS);
        List<? extends InstanceAction> actions = osv3().compute().servers().instanceActions()
                .list("b48316c5-71e8-45e4-9884-6c78055b9b13");

        assertEquals(2, actions.size());
    }

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
}
