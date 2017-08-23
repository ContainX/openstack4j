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

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.senlin.BuildInfo;

import java.util.logging.Logger;

import static org.testng.Assert.assertNotNull;

/**
 * Test cases for buildInfo on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/buildInfo")
public class BuildInfoServiceTest extends AbstractTest {

    private static final String BUILDINFO="/senlin/v1/build_info.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testGetAction() throws Exception{
        respondWith(BUILDINFO);
        BuildInfo buildInfo = osv3().senlin().buildInfo().get();
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : BuildInfo : "+ buildInfo);
        assertNotNull(buildInfo);
    }

}
