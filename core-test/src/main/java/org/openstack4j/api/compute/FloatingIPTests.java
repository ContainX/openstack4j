package org.openstack4j.api.compute;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import java.util.UUID;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.ActionResponse;
import org.openstack4j.model.compute.FloatingIP;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Test(suiteName = "Compute")
public class FloatingIPTests extends AbstractTest {    
    
    private static final String JSON_FIPS = "/compute/floatingips.json";
    
    @DataProvider(name = "floatingIPs")
    public Object[][] createFloatingIPData(Method m) {
        final int SIZE = 10;
        final String BASE_IP = "192.168.0.";
        Object[][] fipsData = new Object[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            fipsData[i] = new String[] {BASE_IP + (i + 1)};
        }
        return fipsData;
    }


    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
    
    @Test
    public void listFloatingIPs() throws IOException {
        respondWith(JSON_FIPS);
        List<FloatingIP> fips = (List<FloatingIP>) os().compute().floatingIps().list();
        assertNotNull(fips);
        assertEquals(fips.size(), 5);
        
        // Test empty list
        respondWith(200, "{\"floating_ips\": []}");
        List<FloatingIP> fipsEmpty = (List<FloatingIP>) os().compute().floatingIps().list();
        assertNotNull(fipsEmpty);
        assertEquals(fipsEmpty.size(), 0);
    }

    @Test(dataProvider = "floatingIPs")
    public void allocateFloatingIP(String ip) throws IOException {
        final String POOL = "floating";
        
        String jsonResponse = String.format("{\"floating_ip\": {"
                + "\"instance_id\": null, "
                + "\"ip\": \"%s\", "
                + "\"fixed_ip\": null, "
                + "\"id\": \"%s\", "
                + "\"pool\": \"%s\"}}", ip, UUID.randomUUID().toString(), POOL);
        
        respondWith(200, jsonResponse);

        FloatingIP fip = os().compute().floatingIps().allocateIP(POOL);
        assertNotNull(fip);
        assertEquals(fip.getFloatingIpAddress(), ip);
        assertEquals(fip.getPool(), POOL);
        assertNotNull(fip.getId());
        assertNull(fip.getFixedIpAddress());
        assertNull(fip.getInstanceId());
    }
    
    @Test(dataProvider = "floatingIPs")
    public void deallocateFloatingIP(String ip) throws IOException {
        
        // Test deallocate success
        respondWith(202);

        ActionResponse successResponse = os().compute().floatingIps().deallocateIP(ip);
        assertNotNull(successResponse);
        assertTrue(successResponse.isSuccess());

        String jsonResponse = String.format("{\"itemNotFound\": {"
                + "\"message\": \"Floating ip not found for id %s\", "
                + "\"code\": 404}}", 
                ip);
        
        // Test deallocate error
        respondWith(404, jsonResponse);

        ActionResponse failureResponse = os().compute().floatingIps().deallocateIP(ip);
        assertNotNull(failureResponse);
        assertFalse(failureResponse.isSuccess());
        assertEquals(failureResponse.getCode(), 404);
    }
}
