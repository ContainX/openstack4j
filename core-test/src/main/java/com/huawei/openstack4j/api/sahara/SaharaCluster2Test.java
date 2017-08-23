/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.api.sahara;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.sahara.constants.BillingType;
import com.huawei.openstack4j.openstack.sahara.constants.ClusterState;
import com.huawei.openstack4j.openstack.sahara.constants.ClusterType;
import com.huawei.openstack4j.openstack.sahara.constants.ClusterVersion;
import com.huawei.openstack4j.openstack.sahara.constants.JobType;
import com.huawei.openstack4j.openstack.sahara.constants.VolumeType;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaCluster2;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaClusterCreate;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaClusterCreateResult;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaComponent;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaJobExeCreate;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/Cluster", enabled = true)
public class SaharaCluster2Test extends AbstractTest {

	@Test
	public void testGetCluster() throws IOException, InterruptedException {
		respondWith("/sahara/get_cluster2_response.json");
		SaharaCluster2 cluster = osv3().sahara().clusters2().get("cluster-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/cluster_infos/cluster-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(cluster.getId(), "bdb064ff-2855-4624-90d5-e9a6376abd6e");
		Assert.assertEquals(cluster.getName(), "c17022001");
		Assert.assertEquals(cluster.getCoreNodeNum().intValue(), 3);
		Assert.assertEquals(cluster.getMasterNodeNum().intValue(), 2);
		Assert.assertEquals(cluster.getState(), ClusterState.ScalingIn);
		Assert.assertEquals(cluster.getCreateAt().getTime(), 1487570757000L);
		Assert.assertEquals(cluster.getUpdateAt().getTime(), 1487668974000L);
		Assert.assertEquals(cluster.getBillingType(), BillingType.Metered);
		Assert.assertEquals(cluster.getDataCenter(), "eu-de");
		Assert.assertEquals(cluster.getVpcName(), "20161218");
		Assert.assertEquals(cluster.getDuration(), "0");
		Assert.assertEquals(cluster.getFee(), "0");
		Assert.assertEquals(cluster.getHadoopVersion(), "");
		Assert.assertEquals(cluster.getMasterNodeSize(), "c2.2xlarge.linux.mrs");
		Assert.assertEquals(cluster.getCoreNodeSize(), "c2.2xlarge.linux.mrs");
		Assert.assertEquals(cluster.getComponents().size(), 4);
		Assert.assertEquals(cluster.getExternalIp(), "100.64.49.9");
		Assert.assertEquals(cluster.getExternalAlternateIp(), "100.64.49.13");
		Assert.assertEquals(cluster.getInternalIp(), "192.168.1.242");
		Assert.assertEquals(cluster.getDeploymentId(), "4ac46ca7-a488-4b91-82c2-e4d7aa9c40c2");
		Assert.assertEquals(cluster.getRemark(), "");
		Assert.assertEquals(cluster.getOrderId(), "HWS001015A5A1E845A0");
		Assert.assertEquals(cluster.getAzId(), "1d7b939b382c4c3bb3481a8ca10da768");
		Assert.assertEquals(cluster.getAzName(), "eu-de-01");
		Assert.assertEquals(cluster.getMasterNodeProductId(), "b35cf2d2348a445ca74b32289a160882");
		Assert.assertEquals(cluster.getMasterNodeSpecId(), "8ab05e503b4c42abb304e2489560063b");
		Assert.assertEquals(cluster.getCoreNodeProductId(), "dc970349d128460e960a0c2b826c427c");
		Assert.assertEquals(cluster.getCoreNodeSpecId(), "cdc6035a249a40249312f5ef72a23cd7");
		Assert.assertEquals(cluster.getInstanceId(), "4ac46ca7-a488-4b91-82c2-e4d7aa9c40c2");
		Assert.assertEquals(cluster.getVnc(), null);
		Assert.assertEquals(cluster.getTenantId(), "3f99e3319a8943ceb15c584f3325d064");
		Assert.assertEquals(cluster.getVolumeSize().intValue(), 100);
		Assert.assertEquals(cluster.getVolumeType(), VolumeType.SATA);
		Assert.assertEquals(cluster.getSubnetName(), "subnet-f测试");
		Assert.assertEquals(cluster.getSecurityGroupsId(), "930e34e2-195d-401f-af07-0b64ea6603f8");
		Assert.assertEquals(cluster.getSlaveSecurityGroupsId(), "2ef3343e-3477-4a0d-80fe-4d874e4f81b8");
		Assert.assertEquals(cluster.getSafeMode().intValue(), 1);
		Assert.assertEquals(cluster.getVersion(), "FusionInsight V100R002C61");
		Assert.assertEquals(cluster.getKeypair(), "myp");
		Assert.assertEquals(cluster.getMasterNodeIp(), "192.168.1.242");
		Assert.assertEquals(cluster.getPrivateIpFirst(), "192.168.1.234");
		Assert.assertEquals(cluster.getErrorInfo(), null);
		Assert.assertEquals(cluster.getChargingStartTime(), "0");
	}

	@Test
	public void testCreateClusterAndRunJob() throws IOException, InterruptedException {
		respondWith("/sahara/create_cluster_and_run_job_response.json");

		// initial cluster create model
		SaharaComponent component = SaharaComponent.builder().id("MRS 1.3.0_001").name("Hadoop").version("").desc("")
				.build();
		SaharaClusterCreate cluster = SaharaClusterCreate.builder().dataCenter("eu-de").masterNodeNum(2)
				.masterNodeSize("c2.2xlarge.linux.mrs").coreNodeNum(3).coreNodeSize("c2.2xlarge.linux.mrs")
				.name("newcluster").availablilityZoneId("eu-de-01").vpcName("vpc1").vpcId("vpc-id").subnetName("subnet")
				.subnetId("subnet-id").version(ClusterVersion.MRS12).type(ClusterType.Stream).volumeSize(100)
				.volumeType(VolumeType.SSD).keypair("keypair").safeMode(0).components(Lists.newArrayList(component))
				.build();

		// initial job exe create model
		SaharaJobExeCreate jobExe = SaharaJobExeCreate.builder().jobType(JobType.MapReduce).jobName("sdk")
				.jarPath("s3a://sdk/sdk.jar").arguments("wordcount").input("s3a://input/").output("s3a://output/")
				.jobLog("s3a://log/").fileAction("").hql("").hiveScriptPath("").shutdownCluster(false)
				.submitJobOnceClusterRun(true).build();
		
		SaharaClusterCreateResult result = osv3().sahara().clusters2().createAndRunJob(cluster, jobExe);

		Assert.assertEquals(result.getClusterId(), "da1592c2-bb7e-468d-9ac9-83246e95447a");
		Assert.assertEquals(result.getResult().booleanValue(), true);
		Assert.assertEquals(result.getMsg(), "");
		

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/run-job-flow");
		Assert.assertEquals(request.getMethod(), "POST");
		
		String requestBody = request.getBody().readUtf8();
		String expectBody = getResource("/sahara/create_cluster_and_run_job_request.json");
		Assert.assertEquals(expectBody, requestBody);

	}

	@Override
	protected Service service() {
		return Service.SAHARA;
	}

}
