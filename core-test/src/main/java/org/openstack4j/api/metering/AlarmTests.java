package org.openstack4j.api.metering;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.Image;
import org.openstack4j.model.telemetry.Alarm;
import org.openstack4j.model.telemetry.Meter;
import org.testng.annotations.Test;

/**
 * Test cases for Compute Images
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Images")
public class AlarmTests extends AbstractTest {

    private static final String JSON_ALARMS = "/metering/alarms.json";
    
    public void meterListingTest() throws Exception {
    	System.out.println("METER LISTING TEST");
    	
    	respondWith(JSON_ALARMS);
        
        List<? extends Meter> meterList = os().telemetry().meters().list();
        
        //List<? extends Alarm> alarms = os().telemetry().alarms().list();
        
        
        
        //assertEquals(2, alarms.size());
        assertEquals(2, meterList.size());
        
        throw new Exception("Alarm test failed by max");

    }

    /*
    public void alarmListingTest() throws Exception {
        respondWith(JSON_ALARMS);
        
        List<? extends Alarm> alarms = os().telemetry().alarms().list();
        
        
        assertEquals(2, alarms.size());
        
        throw new Exception("Alarm test failed by max");

    }
    */
    
    @Override
    protected Service service() {
        return Service.METERING;
    }

}
