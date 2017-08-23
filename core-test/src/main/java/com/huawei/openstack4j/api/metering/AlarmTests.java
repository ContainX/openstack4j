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
package com.huawei.openstack4j.api.metering;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.telemetry.Meter;

/**
 * Test cases for Compute Images
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Alarms", enabled = false)
public class AlarmTests extends AbstractTest {

	private static final String JSON_ALARMS = "/metering/alarms.json";

	public void meterListingTest() throws Exception {
		System.out.println("METER LISTING TEST");

		respondWith(JSON_ALARMS);

		List<? extends Meter> meterList = osv3().telemetry().meters().list();
		assertEquals(2, meterList.size());

		throw new Exception("Alarm test failed by max");

	}

	@Override
	protected Service service() {
		return Service.METERING;
	}

}
