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
package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.HostAggregate;
import org.testng.annotations.Test;

/**
 * Host Aggregate Tests
 * @author chenyan
 *
 */
@Test(suiteName = "HostAggregate")
public class HostAggregateTests extends AbstractTest {
	
	private static final String JSON_HOST_AGGREGATES = "/compute/aggregates.json";
	private static final String JSON_HOST_AGGREGATE_CREATE = "/compute/aggregate_create.json";
	
	@Test
	public void serviceListingTest() throws Exception {
        respondWith(JSON_HOST_AGGREGATES);

        List<? extends HostAggregate> hostAggregateList = osv3().compute().hostAggregates().list();
        assertEquals(hostAggregateList.size(),2);

        HostAggregate aggregate = hostAggregateList.get(0);
        assertEquals(aggregate.getAvailabilityZone(),"uec_zone_1");
        assertEquals(aggregate.getId(),"8");
        assertEquals(aggregate.getName(),"aggregate_zl_test");
    }
	
	@Test
	public void createTest() throws Exception {
		respondWith(JSON_HOST_AGGREGATE_CREATE);
		
		String name = "testAggregate01";
		String availabilityZone = "nova";
		HostAggregate hostAggregate = osv3().compute().hostAggregates().create(name, availabilityZone);
		assertEquals(null != hostAggregate, true);
		assertEquals(hostAggregate.getName(),name);
		assertEquals(hostAggregate.getAvailabilityZone(),availabilityZone);
	}
	
	@Override
	protected Service service() {
		return Service.COMPUTE;
	}

}
