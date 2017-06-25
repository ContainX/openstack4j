package org.openstack4j.api.cloudeye;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.cloudeye.Quota;
import org.openstack4j.model.cloudeye.QuotaType;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

@Test(groups = "Cloudeye", suiteName = "Cloudeye/Quota")
public class CloudEyeQuotaServiceTest extends AbstractTest{
    private static final String JSON_QUOTA = "/cloudeye/get_quota.json";
    @Override
    protected Service service() {
        return Service.CLOUD_EYE;
    }

    public void getQuotaTest() throws Exception{
        respondWith(JSON_QUOTA);
        Quota quotas = osv3().cloudEye().quotas().get();
        assertNotNull(quotas);
        assertNotNull(quotas.getResources());
        assertEquals(quotas.getResources().size(), 1);
        assertEquals(quotas.getResources().get(0).getType(), QuotaType.ALARM);
    }
}
