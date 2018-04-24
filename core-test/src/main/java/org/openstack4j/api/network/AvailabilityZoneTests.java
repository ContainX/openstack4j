package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.AvailabilityZone;
import org.testng.annotations.Test;

/**
 * Tests the Neutron -> availability_zones API against the mock webserver and spec based
 * json responses
 * 
 * @author Taemin 
 */
@Test(suiteName = "availability_zones")
public class AvailabilityZoneTests extends AbstractTest {

	private static final String JSON_GET_SUBNET = "/network/availability_zones.json";  	
	private static final String STATE = "available";
	private static final String RESOURCE = "network";	
	private static final String NAME = "nova";
	
	@Test
	public void getAvailabilityZones() throws Exception {		
		respondWith(JSON_GET_SUBNET);		 
		List<? extends AvailabilityZone> az = osv3().networking().availabilityzone().list();
		server.takeRequest();
		assertEquals(az.get(0).getState(), STATE);
		assertEquals(az.get(0).getResource(), RESOURCE);
		assertEquals(az.get(0).getName(), NAME);
		
	}

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    
}
