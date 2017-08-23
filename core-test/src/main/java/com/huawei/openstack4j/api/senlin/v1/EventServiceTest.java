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
import com.huawei.openstack4j.model.senlin.Event;

import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for event on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/event")
public class EventServiceTest extends AbstractTest {

    private static final String EVENTS="/senlin/v1/events.json";
    private static final String EVENT="/senlin/v1/event.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListEvent() throws Exception{
        respondWith(EVENTS);
        List<? extends Event> eventList = osv3().senlin().event().list();
        assertEquals(4, eventList.size());
        Preconditions.checkNotNull(eventList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Event from List : "+ eventList.get(0));
        assertEquals(eventList.get(0).getId(), "b6d7b823-1811-492b-8a54-fb15a5a0bafe");
    }
    @Test
    public void testGetEvent() throws Exception{
        respondWith(EVENT);
        String enentID = "2d255b9c-8f36-41a2-a137-c0175ccc29c3";
        Event event = osv3().senlin().event().get(enentID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Event by ID : "+ event);
        assertNotNull(event);
        assertEquals(enentID, event.getId());
    }

}
