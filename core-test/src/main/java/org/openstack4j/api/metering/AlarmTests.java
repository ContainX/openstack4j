package org.openstack4j.api.metering;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.Meter;
import org.testng.annotations.Test;

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
