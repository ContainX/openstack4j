package org.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.senlin.ActionID;
import org.openstack4j.model.senlin.Cluster;
import org.openstack4j.model.senlin.ClusterActionCreate;
import org.openstack4j.model.senlin.ClusterCreate;
import org.openstack4j.openstack.senlin.domain.SenlinClusterActionCreate;
import org.openstack4j.openstack.senlin.domain.SenlinClusterCreate;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for cluster on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/cluster")
public class ClusterServiceTest extends AbstractTest {

    private static final String CLUSTERS="/senlin/v1/clusters.json";
    private static final String CLUSTER="/senlin/v1/cluster.json";
    private static final String RASPACTION="/senlin/v1/resp_action.json";
    private static final String ID="45edadcb-c73b-4920-87e1-518b2f29f54b";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListCluster() throws Exception{
        respondWith(CLUSTERS);
        List<? extends Cluster> clusterList = osv3().senlin().cluster().list();
        assertEquals(4, clusterList.size());
        Preconditions.checkNotNull(clusterList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Cluster from List : "+clusterList.get(0));
        assertEquals(clusterList.get(0).getId(), "7e0c9843-54bf-4823-b545-d2f6ffb4ed25");
    }
    @Test
    public void testGetCluster() throws Exception{
        respondWith(CLUSTER);
        Cluster cluster = osv3().senlin().cluster().get(ID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Cluster by ID : " + cluster);
        assertNotNull(cluster);
        assertEquals(ID, cluster.getId());
    }
    @Test
    public void testCreateCluster() throws Exception{
        respondWith(CLUSTER);
        String clusterName = "test_cluster";
        ClusterCreate newCluster = new SenlinClusterCreate();
        newCluster.toBuilder()
                .name(clusterName);
        Cluster cluster = osv3().senlin().cluster().create(newCluster);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Cluster : " + cluster);
        assertEquals(clusterName, cluster.getName());
    }
    @Test
    public void testUpdateCluster() throws Exception{
        respondWith(CLUSTER);
        String clusterName = "test_cluster";
        ClusterCreate newCluster = new SenlinClusterCreate();
        newCluster.toBuilder()
                .name(clusterName)
                .profileID("aaaaaaa")
                .metadata(new HashMap<String, String>());
        Cluster cluster = osv3().senlin().cluster().update(ID, newCluster);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Cluster : " + cluster);
        assertEquals(clusterName, cluster.getName());
    }
    @Test
    public void testDeleteCluster() {
        respondWith(200);
        ActionResponse result = osv3().senlin().cluster().delete(ID);
        assertTrue(result.isSuccess());
    }
    @Test
    public void testNodeAction() throws Exception{
        respondWith(RASPACTION);
        ClusterActionCreate newClusterAction = new SenlinClusterActionCreate();
        newClusterAction.toBuilder().check(new HashMap<String, String>());
        ActionID respAction = osv3().senlin().cluster().action("573aa1ba-bf45-49fd-907d-6b5d6e6adfd3", newClusterAction);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Trigger node action : " + respAction);
        assertEquals("40a436b1-28d1-4de6-b2c3-0a34f478e2c9", respAction.getActionID());
    }

}
