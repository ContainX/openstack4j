package org.openstack4j.api.sahara;

import org.openstack4j.model.sahara.Cluster;
import org.openstack4j.model.sahara.NodeGroup;
import org.openstack4j.model.sahara.ServiceConfig;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

/**
 * Test cases for Server based Services
 * 
 * @author Ekasit Kijsipongse
 */
@Test(suiteName="Sahara/Cluster")
public class ClusterTests extends AbstractTest {

    private static final String JSON_CLUSTER_CREATE_REQUEST = "/sahara/cluster_create_req.json";
    private static final String JSON_CLUSTER_CREATE_RESPONSE = "/sahara/cluster_create_resp.json";

    @Test
    public void createCluster() throws Exception {
        respondWith(JSON_CLUSTER_CREATE_RESPONSE);

        // Create a new cluster from node group template
        NodeGroup workerGroup = Builders.nodeGroup().name("worker")
                                  .count(2)
                                  .flavor("ef7f4d7f-267f-4762-abe2-729fe350256c")
                                  .nodeGroupTemplateId("f78dda4d-17e1-48cd-ab85-ee56261382ef")
                                  .build();

        NodeGroup masterGroup = Builders.nodeGroup().name("master")
                                  .count(1)
                                  .flavor("ef7f4d7f-267f-4762-abe2-729fe350256c")
                                  .nodeGroupTemplateId("0febf422-98ee-47e6-a1c5-60f90f1f9c96")
                                  .build();

        ServiceConfig hdfsConf = Builders.serviceConfig()
                                  .set("dfs.replication",1)
                                  .build();
        ServiceConfig sparkConf = Builders.serviceConfig()
                                  .set("spark.executor.memory","1g")
                                  .set("spark.executor.cores",2)
                                  .build();

        Cluster cluster = Builders.cluster().name("cluster-test-1")
                            .hadoopVersion("1.6.2")
                            .pluginName("spark")
                            .image("f56cc7c5-9588-49fa-8bcd-5c5d5eda5466")
                            .keypairName("tester")
                            .managementNetworkId("4c065f9c-ad1b-43c4-ba1e-893d330da079")
                            .addNodeGroup(workerGroup)
                            .addNodeGroup(masterGroup)
                            .addServiceConfig("HDFS",hdfsConf)
                            .addServiceConfig("Spark",sparkConf)
                            .build();


        cluster = osv2().sahara().clusters().create(cluster);

        // Check that the request is the one we expect
        RecordedRequest request = server.takeRequest();

        String requestBody = request.getBody().readUtf8();
        assertTrue(requestBody.contains("\"spark.executor.memory\" : \"1g\""));
        assertTrue(requestBody.contains("\"spark.executor.cores\" : 2"));


        assertEquals("cluster-test-1", cluster.getName());
        assertEquals("1.6.2", cluster.getHadoopVersion());
        assertEquals("spark", cluster.getPluginName());
        assertEquals(2, cluster.getNodeGroups().size());
        assertEquals(2, cluster.getClusterConfigs().size());
        assertEquals(1, cluster.getClusterConfigs().get("HDFS").getConfigs().size());
        assertEquals(2, cluster.getClusterConfigs().get("Spark").getConfigs().size());
        assertEquals(2, cluster.getClusterConfigs().get("Spark").get("spark.executor.cores"));
        assertEquals("1g", cluster.getClusterConfigs().get("Spark").get("spark.executor.memory"));

    }

    @Override
    protected Service service() {
        return Service.SAHARA;
    }



}
