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
package com.huawei.openstack4j.functional.trove;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.database.domain.DatastoreVersion;
import com.huawei.openstack4j.openstack.database.domain.InstanceFlavor;
import com.huawei.openstack4j.openstack.trove.constant.DatastoreType;
import com.huawei.openstack4j.openstack.trove.constant.InstanceType;
import com.huawei.openstack4j.openstack.trove.constant.VolumeType;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfig;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstanceCreate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseUser;
import com.huawei.openstack4j.openstack.trove.domain.Datastore;
import com.huawei.openstack4j.openstack.trove.domain.NIC;
import com.huawei.openstack4j.openstack.trove.domain.Volume;

@Test
public class DatabaseInstanceTest extends BaseTroveTest {

	String name = randomName();

	// 华为对router做了自定义修正
	// 由于SDK无法通过 vpc 查询到 network(?) 所以这边使用德电环境已有的变量
	String vpcId = "31d158b8-e7d7-4b4a-b2a7-a5240296b267";
	String networkId = "85d0d006-44f9-4f32-8384-7f8a8198bed6";
	String sgId = "0005ba27-b937-4a7c-a280-c7b65cea2e47";

	DatabaseInstance instance = null;
	DatastoreVersion datastoreVersion = null;
	DatabaseConfig config = null;

	@BeforeClass
	public void prepare() {
		// get a database config
		List<DatabaseConfig> list = osclient.trove().configs().list();
		for (DatabaseConfig config : list) {
			if (config.getDatastoreName().equals(DatastoreType.MySQL.name())) {
				this.config = config;
				break;
			}
		}

		Assert.assertNotNull(this.config);

		/** 不知道服务端怎么设计的，datastore version 跟 config 根本没法关联。。。。
		 * 
		// get a datastore version compatibility with the config
		List<DatastoreVersion> versions = osclient.database().datastores()
				.listDatastoreVersions(com.huawei.openstack4j.openstack.database.constants.DatastoreType.MySQL);
		for (DatastoreVersion version : versions) {
			if (version.getId().equals(this.config.getDatastoreVersionId())) {
				this.datastoreVersion = version;
				break;
			}
		} */

		this.datastoreVersion = this
				.getFirstDatastoreVersion(com.huawei.openstack4j.openstack.database.constants.DatastoreType.MySQL);
		Assert.assertNotNull(this.datastoreVersion);
	}

	/**
	 * create DB instance for testing
	 * 这边由于创建实例所需的时间太长了。。所以后续的那些操作没办法全自动测试
	 */
	public void testCreateInstance() {
		// build datastore version
		// 不清楚这个version "MySQL-5.6.33" 要从哪里获取，现在是按照文档上的例子，直接设置
		Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("MySQL-5.6.33").build();

		// get flavor
		InstanceFlavor flavor = this.getFirstFlavor(datastoreVersion.getId());
		Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();

		NIC nic = NIC.builder().networkId(networkId).securityGroupId(sgId).build();

		DatabaseUser user = DatabaseUser.builder().username("root").password("Demo@234").build();

		DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().name(name).datastore(datastore)
				.flavorRef(flavor.getId()).users(Lists.newArrayList(user)).volume(volume)
				.configurationId(config.getId()).availabilityZone("eu-de-01").vpcId(vpcId).nics(Lists.newArrayList(nic))
				.build();

		instance = osclient.trove().instances().create(instanceCreate);

		Assert.assertNotNull(instance.getId());
		Assert.assertTrue(instance.getName().startsWith(name));
		Assert.assertEquals(instance.getStatus(), "BUILD");
		Assert.assertEquals(instance.getFlavor().getId(), flavor.getId());
		Assert.assertEquals(instance.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(instance.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(instance.getDatastore().getVersion(), "MySQL-5.6.33");
	}

	@AfterClass
	public void testDeleteInstance() {
		// 因为实例还在创建中，没法删除，所以会抛出异常
		// because the instance is creating, so it can not be deleted now. a exception will be thrown
		ActionResponse response = osclient.trove().instances().delete(instance.getId());
		Assert.assertFalse(response.isSuccess());
	}

	@Test
	public void testGetInstance() {
		DatabaseInstance get = osclient.trove().instances().get(instance.getId());

		Assert.assertEquals(get.getName(), instance.getName());
		Assert.assertEquals(get.getStatus(), "BUILD");
		Assert.assertEquals(get.getSubnetId(), networkId);
		Assert.assertEquals(get.getSecureGroupId(), sgId);
		Assert.assertEquals(get.getVpcId(), vpcId);
		Assert.assertNotNull(get.getFlavor());
		// Assert.assertEquals(get.getVolume().getType(), VolumeType.COMMON);
		// Assert.assertEquals(get.getVolume().getSize().intValue(), 100);
		Assert.assertEquals(get.getAvailabilityZone(), "eu-de-01");
		Assert.assertEquals(get.getRegion(), "eu-de");
		Assert.assertEquals(get.getLinks().size(), 2);

		Assert.assertNotNull(get.getGroup());
		Assert.assertNotNull(get.getClusterId());
		Assert.assertEquals(get.getConfigurationId(), this.config.getId());
		Assert.assertEquals(get.getType(), DatastoreType.MySQL);
		Assert.assertEquals(get.getRole(), InstanceType.Master);

		Assert.assertEquals(get.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(get.getDatastore().getVersion(), "MySQL-5.6.33");
		Assert.assertEquals(get.getDbUser(), "root");
		Assert.assertEquals(get.getPayModel().intValue(), 0);
		Assert.assertEquals(get.getConfiguration().getId(), this.config.getId());
		Assert.assertEquals(get.getConfiguration().getName(), this.config.getName());

	}

	@Test
	public void testListInstance() {
		List<DatabaseInstance> list = osclient.trove().instances().list();
		DatabaseInstance found = null;
		for (DatabaseInstance databaseInstance : list) {
			if (databaseInstance.getId().equals(instance.getId())) {
				found = databaseInstance;
			}
		}

		Assert.assertNotNull(found);

		Assert.assertEquals(found.getName(), instance.getName());
		Assert.assertEquals(found.getStatus(), "BUILD");
		Assert.assertNotNull(found.getFlavor());
		Assert.assertEquals(found.getRegion(), "eu-de");
		Assert.assertEquals(found.getLinks().size(), 2);

		Assert.assertEquals(found.getDatastore().getType(), DatastoreType.MySQL);
		Assert.assertEquals(found.getDatastore().getVersion(), "MySQL-5.6.33");
	}

}
