package org.openstack4j.api.telemetry;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.Alarm;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import okhttp3.mockwebserver.RecordedRequest;
/**
 * telemetry alarm test cases
 * @author zhangjianweibj
 *
 */
@Test(suiteName="Alarms")
public class AlarmTests extends AbstractTest {

    private static final String JSON_ALARMS = "/telemetry/alarms.json";

    private static final String JSON_ALARM= "/telemetry/alarm.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }


    public void listAlarmTest() throws IOException , InterruptedException {
        respondWith(JSON_ALARMS);
        List<? extends Alarm> list=osv3().telemetry().alarms().list();

        RecordedRequest listRequest = server.takeRequest();
        assertEquals(7,list.size());

    }


    public void getAlarmTest() throws IOException, InterruptedException{
        respondWith(JSON_ALARM);

        Alarm alarm=osv3().telemetry().alarms().getById("03757eede9c540338e732d1a7fb07966");
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(alarm);
        assertEquals(alarm.getAlarmId(),"03757eede9c540338e732d1a7fb07966");
    }



}
