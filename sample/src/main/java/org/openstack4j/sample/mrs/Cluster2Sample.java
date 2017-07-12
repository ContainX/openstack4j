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
package org.openstack4j.sample.mrs;

import org.openstack4j.openstack.sahara.constants.ClusterType;
import org.openstack4j.openstack.sahara.constants.ClusterVersion;
import org.openstack4j.openstack.sahara.constants.JobType;
import org.openstack4j.openstack.sahara.constants.VolumeType;
import org.openstack4j.openstack.sahara.domain.SaharaCluster2;
import org.openstack4j.openstack.sahara.domain.SaharaClusterCreate;
import org.openstack4j.openstack.sahara.domain.SaharaClusterCreateResult;
import org.openstack4j.openstack.sahara.domain.SaharaComponent;
import org.openstack4j.openstack.sahara.domain.SaharaJobExeCreate;
import org.openstack4j.sample.AbstractSample;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

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

		SaharaClusterCreateResult result = osclient.sahara().clusters2().createAndRunJob(cluster, jobExe);
		Assert.assertTrue(result.getResult());
	}

}
