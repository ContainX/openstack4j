/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package com.huawei.openstack4j.functional.database;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.huawei.openstack4j.api.exceptions.ClientResponseException;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.database.constants.DatastoreType;
import com.huawei.openstack4j.openstack.database.constants.InstanceType;
import com.huawei.openstack4j.openstack.database.constants.ReplicationMode;
import com.huawei.openstack4j.openstack.database.constants.VolumeType;
import com.huawei.openstack4j.openstack.database.domain.BackupStrategy;
import com.huawei.openstack4j.openstack.database.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.database.domain.DatabaseInstanceCreate;
import com.huawei.openstack4j.openstack.database.domain.Datastore;
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;
import com.huawei.openstack4j.openstack.database.domain.HA;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;
import com.huawei.openstack4j.openstack.database.domain.NIC;
import com.huawei.openstack4j.openstack.database.domain.Volume;

@Test(suiteName = "Database/Instance/Test")
public class DatabaseInstanceTest extends BaseDatabaseTest {

	String name = randomName();

	// 华为对router做了自定义修正
	// 由于SDK无法通过 vpc 查询到 network(?) 所以这边使用德电环境已有的变量
	String vpcId = "31d158b8-e7d7-4b4a-b2a7-a5240296b267";
	String networkId = "85d0d006-44f9-4f32-8384-7f8a8198bed6";
	String sgId = "0005ba27-b937-4a7c-a280-c7b65cea2e47";

	DatabaseInstance instance = null;

	/**
	 * create DB instance for testing
	 * 
	 * 这边由于创建实例所需的时间太长了。。所以后续的那些操作没办法全自动测试
	 */
	@BeforeClass
	public void testCreateInstance() {
		// build datastore version
		DatastoreVersion datastoreVersion = this.getFirstDatastoreVersion(DatastoreType.MySQL);
		Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version(datastoreVersion.getName()).build();

		// get flavor
		InstanceFlavor flavor = this.getFirstFlavor(datastoreVersion.getId());
		Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();

		// vpc
		// Router router = getFirstRouter();
		// subnet
		NIC nic = NIC.builder().subnetId(networkId).build();
		BackupStrategy backupStrategy = BackupStrategy.builder().keepDays(1).startTime("15:00:00").build();
		HA ha = HA.builder().enable(true).replicationMode(ReplicationMode.ASYNC).build();

		DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().name(name).datastore(datastore)
				.flavorRef(flavor.getId()).volume(volume).region("eu-de").availabilityZone("eu-de-01").vpcId(vpcId)
				.nic(nic).securityGroup(new IdResourceEntity(sgId)).rootPassword("1qaz@WSX")
				.backupStrategy(backupStrategy).ha(ha).build();

		instance = osclient.database().instances().create(instanceCreate);

		Assert.assertTrue(instance.getName().startsWith(name));
		Assert.assertEquals(instance.getStatus(), "BUILD");
		Assert.assertEquals(instance.getType(), InstanceType.Master);
		Assert.assertEquals(instance.getRegion(), "eu-de");
		Assert.assertEquals(instance.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(instance.getVpcId(), vpcId);
		Assert.assertEquals(instance.getNic().getSubnetId(), "85d0d006-44f9-4f32-8384-7f8a8198bed6");
		Assert.assertEquals(instance.getSecurityGroup().getId(), sgId);
		Assert.assertEquals(instance.getFlavor().getId(), flavor.getId());
		Assert.assertEquals(instance.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(instance.getBackupStrategy().getKeepDays().intValue(), 1);
		Assert.assertEquals(instance.getBackupStrategy().getStartTime(), "15:00:00");
		Assert.assertEquals(instance.getHa().getReplicationMode(), ReplicationMode.ASYNC);
		Assert.assertEquals(instance.getExtendParam().getJobs().size(), 2);
		Assert.assertTrue(!Strings.isNullOrEmpty(instance.getExtendParam().getJobs().get(0).getId()));
		Assert.assertTrue(!Strings.isNullOrEmpty(instance.getSlaveId()));
	}

	@AfterClass
	public void testDeleteInstance() {
		try {
			// 因为实例还在创建中，没法删除，所以会抛出异常
			// because the instance is creating, so it can not be deleted now. a exception will be thrown
			String jobId = osclient.database().instances().delete(instance.getId());
			Assert.assertNotNull(jobId);
		} catch (ClientResponseException e) {
			Assert.assertEquals(e.getMessage(), "[RDS.0011] 该实例正在进行其它操作或该实例故障，无法执行该操作，请稍后重试");
			Assert.assertEquals(e.getStatusCode().getCode(), 409);
		}
	}

	@Test
	public void testGetInstance() {
		DatabaseInstance get = osclient.database().instances().get(instance.getId());

		Assert.assertEquals(get.getName(), instance.getName());
		Assert.assertEquals(get.getStatus(), "BUILD");
		Assert.assertEquals(get.getType(), InstanceType.Master);
		// 服务端未返回 region 数据
		// Assert.assertEquals(instance.getRegion(), "eu-de");
		Assert.assertEquals(get.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(get.getVpcId(), vpcId);
		Assert.assertEquals(get.getNic().getSubnetId(), "85d0d006-44f9-4f32-8384-7f8a8198bed6");
		Assert.assertEquals(get.getSecurityGroup().getId(), sgId);
		Assert.assertEquals(get.getFlavor().getId(), instance.getFlavor().getId());
		Assert.assertEquals(get.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(get.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(get.getBackupStrategy().getKeepDays().intValue(), 1);
		Assert.assertEquals(get.getBackupStrategy().getStartTime(), "15:00:00");
		Assert.assertEquals(get.getHa().getReplicationMode(), ReplicationMode.ASYNC);
		// Assert.assertEquals(get.getExtendParam().getJobs().size(), 2);
		// Assert.assertTrue(!Strings.isNullOrEmpty(instance.getExtendParam().getJobs().get(0).getId()));
		// Assert.assertTrue(!Strings.isNullOrEmpty(instance.getSlaveId()));
	}

	@Test
	public void testListInstance() {
		List<DatabaseInstance> list = osclient.database().instances().list();
		DatabaseInstance found = null;
		for (DatabaseInstance databaseInstance : list) {
			if (databaseInstance.getId().equals(instance.getId())) {
				found = databaseInstance;
			}
		}

		Assert.assertNotNull(found);

		Assert.assertEquals(found.getName(), instance.getName());
		Assert.assertEquals(found.getStatus(), "BUILD");
		Assert.assertEquals(found.getType(), InstanceType.Master);
		// 实际上服务端未返回 region 数据 
		// Assert.assertEquals(instance.getRegion(), "eu-de");
		Assert.assertEquals(found.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(found.getVpcId(), vpcId);
		Assert.assertEquals(found.getNic().getSubnetId(), "85d0d006-44f9-4f32-8384-7f8a8198bed6");
		Assert.assertEquals(found.getSecurityGroup().getId(), sgId);
		Assert.assertEquals(found.getFlavor().getId(), instance.getFlavor().getId());
		Assert.assertEquals(found.getVolume().getType(), VolumeType.COMMON);
		Assert.assertEquals(found.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(found.getBackupStrategy().getKeepDays().intValue(), 1);
		Assert.assertEquals(found.getBackupStrategy().getStartTime(), "15:00:00");
		Assert.assertEquals(found.getHa().getReplicationMode(), ReplicationMode.ASYNC);
	}

}
