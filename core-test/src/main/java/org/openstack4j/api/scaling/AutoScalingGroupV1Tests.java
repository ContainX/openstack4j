/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                    
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
package org.openstack4j.api.scaling;


import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.scaling.ScalingGroup;
import org.testng.annotations.Test;

@Test(suiteName="AutoScaling/AutoScalingGroupV1", enabled = true)
public class AutoScalingGroupV1Tests extends AbstractTest {

    public void testsListAutoScalingGroups() throws IOException {
        respondWith("/scaling/as_scaling_group_list.json");
        List<? extends ScalingGroup> list = osv3().autoScaling().groups().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).groupId(), "9d841f24-755a-4706-ba1a-11fcd27d5891");
    }

    @Override
    protected Service service() {
        return Service.AUTO_SCALING;
    }
}
