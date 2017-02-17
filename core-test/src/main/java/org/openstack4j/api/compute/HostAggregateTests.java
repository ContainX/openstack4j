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
