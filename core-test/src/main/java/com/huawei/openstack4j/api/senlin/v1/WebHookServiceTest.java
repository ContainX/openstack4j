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
package com.huawei.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.senlin.ActionID;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
/**
 * Test cases for webhook on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/webhook")
public class WebHookServiceTest extends AbstractTest {

    private static final String RASPACTION="/senlin/v1/resp_action.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testWebHook() throws Exception{
        respondWith(RASPACTION);
        ActionID respAction = osv3().senlin().webHook().action("http://127.0.0.1:8778/v1/webhooks/51575fae-a83c-44ac-9214-337663dd04f9/trigger?V=1&count=1");
        Preconditions.checkNotNull(respAction);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : webHookAction : " + respAction);
        assertEquals(respAction.getActionID(), "40a436b1-28d1-4de6-b2c3-0a34f478e2c9");
    }

}
