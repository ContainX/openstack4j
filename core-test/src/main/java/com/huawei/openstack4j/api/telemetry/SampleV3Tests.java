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
package com.huawei.openstack4j.api.telemetry;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.telemetry.Sample;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(suiteName = "SampleV3 Tests")
public class SampleV3Tests extends AbstractTest {
    private static final String JSON_SAMPLES = "/telemetry/samples.json";
    private static final String JSON_SAMPLE = "/telemetry/sample.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listSampleTest() throws IOException {
        respondWith(JSON_SAMPLES);

        List<? extends Sample> samples = osv3().telemetry().samples().list();
        assertEquals(samples.size(), 2);

        Sample sample = samples.get(0);
        assertEquals(sample.getMeter(), "image.size");
        assertNotNull(sample.getMetadata());
    }

    @Test
    public void getSampleTest() throws IOException {
        respondWith(JSON_SAMPLE);
        Sample sample = osv3().telemetry().samples().get("1e93a890-3732-11e6-a491-005056ac9b87");
        assertEquals(sample.getMeter(), "image.size");
        assertNotNull(sample.getMetadata());
    }

}
