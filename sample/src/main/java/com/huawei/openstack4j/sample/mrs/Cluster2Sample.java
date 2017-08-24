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
package com.huawei.openstack4j.sample.mrs;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.openstack.sahara.constants.ClusterType;
import com.huawei.openstack4j.openstack.sahara.constants.ClusterVersion;
import com.huawei.openstack4j.openstack.sahara.constants.JobType;
import com.huawei.openstack4j.openstack.sahara.constants.VolumeType;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaCluster2;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaClusterCreate;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaClusterCreateResult;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaComponent;
import com.huawei.openstack4j.openstack.sahara.domain.SaharaJobExeCreate;
import com.huawei.openstack4j.sample.AbstractSample;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-05 15:36:39
 */
@Test
public class Cluster2Sample extends AbstractSample {

	public void testGetCluster() {
		SaharaCluster2 cluster = osclient.sahara().clusters2().get("0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
		Assert.assertEquals(cluster.getId(), "0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
	}

	public void testCreateClusterAndRunJob() {

		String vpcId = "31d158b8-e7d7-4b4a-b2a7-a5240296b267";
		String vpcName = "vpc-console-bosh";
		String subnetId = "cb9a6ede-39c6-498f-ad85-c554ef7220fc";
		String subnetName = "cf2";
		String keypairName = "KeyPair-28ice";
		// initial cluster create model
		SaharaComponent component = SaharaComponent.builder().id("MRS 1.3.0_001").name("Hadoop").version("").desc("")
				.build();
		SaharaClusterCreate cluster = SaharaClusterCreate.builder().dataCenter("eu-de").masterNodeNum(2)
				.masterNodeSize("c2.4xlarge.linux.mrs").coreNodeNum(3).coreNodeSize("s1.xlarge.linux.mrs")
				.name("newcluster").availablilityZoneId("eu-de-01").vpcName(vpcName).vpcId(vpcId).subnetName(subnetName)
				.subnetId(subnetId).version(ClusterVersion.MRS13).type(ClusterType.Analyse).volumeSize(100)
				.volumeType(VolumeType.SSD).keypair(keypairName).safeMode(0).components(Lists.newArrayList(component))
				.build();

		// initial job exe create model
		SaharaJobExeCreate jobExe = SaharaJobExeCreate.builder().jobType(JobType.MapReduce).jobName("sdk")
				.jarPath("s3a://sdk/sdk.jar").arguments("wordcount").input("s3a://input/").output("s3a://output/")
				.jobLog("s3a://log/").fileAction("").hql("").hiveScriptPath("").shutdownCluster(false)
				.submitJobOnceClusterRun(true).build();

		SaharaClusterCreateResult result = osclient.sahara().clusters2().createAndRunJob(cluster, null);
		Assert.assertTrue(result.getResult());
	}

}
