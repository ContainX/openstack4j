package org.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.senlin.ClusterPolicy;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for clusterPolicy on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/clusterPolicy")
public class ClusterPolicyServiceTest extends AbstractTest {

    private static final String CLUSTERPOLICYS="/senlin/v1/cluster_policies.json";
    private static final String CLUSTERPOLICY="/senlin/v1/cluster_policy.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListClusterPolicy() throws Exception{
        respondWith(CLUSTERPOLICYS);
        List<? extends ClusterPolicy> clusterPolicyList = osv3().senlin().clusterPolicy().list("7d85f602-a948-4a30-afd4-e84f47471c15");
        assertEquals(2, clusterPolicyList.size());
        Preconditions.checkNotNull(clusterPolicyList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : ClusterPolicy from List : "+ clusterPolicyList.get(0));
        assertEquals(clusterPolicyList.get(0).getId(), "06be3a1f-b238-4a96-a737-ceec5714087e");
    }
    @Test
    public void testGetClusterPolicy() throws Exception{
        respondWith(CLUSTERPOLICY);
        String clusterID = "7d85f602-a948-4a30-afd4-e84f47471c15";
        String policyID = "714fe676-a08f-4196-b7af-61d52eeded15";
        ClusterPolicy clusterPolicy = osv3().senlin().clusterPolicy().get(clusterID, policyID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : ClusterPolicy by ID : "+ clusterPolicy);
        assertNotNull(clusterPolicy);
        assertEquals("06be3a1f-b238-4a96-a737-ceec5714087e", clusterPolicy.getId());
    }

}
