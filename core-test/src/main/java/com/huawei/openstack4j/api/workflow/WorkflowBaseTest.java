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

import java.util.UUID;

import com.huawei.openstack4j.api.AbstractTest;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Base class for all Workflow Service tests.
 *
 * @author Renat Akhmerov
 */
public class WorkflowBaseTest extends AbstractTest {

    static void assertIsUUID(String value) {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new AssertionError(e);
        }
    }

    static void assertNotEmptyString(String value) {
        assertNotNull(value);
        assertTrue(value.length() > 0);
    }

    @Override
    protected Service service() {
        return Service.WORKFLOW;
    }
}
