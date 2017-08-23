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
package com.huawei.openstack4j.api.trove;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.trove.constant.DatastoreType;
import com.huawei.openstack4j.openstack.trove.constant.ParameterValueType;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfig;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigCreate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigParam;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseConfigUpdate;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseInstance;
import com.huawei.openstack4j.openstack.trove.domain.Datastore;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Trove/Config", enabled = true)
public class DatabaseConfigTest extends AbstractTest {

	@Test
	public void testListDatabaseConfig() throws IOException, InterruptedException {
		respondWith("/trove/list_database_config_response.json");
		List<DatabaseConfig> configs = osv3().trove().configs().list();

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/configurations");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(configs.size(), 2);

		DatabaseConfig config = configs.get(0);

		Assert.assertEquals(config.getId(), "904ce226-bcc7-457d-b118-74ebdce59ba1");
		Assert.assertEquals(config.getName(), "default-SQLServer-2014");
		Assert.assertEquals(config.getDescription(), "Default parameter group for sqlserver 2014");
		Assert.assertEquals(config.getDatastoreVersionId(), "4f71c5b5-8939-424e-8825-8e3816e4303d");
		Assert.assertEquals(config.getDatastoreVersionName(), "2014");
		Assert.assertEquals(config.getDatastoreName(), "sqlserver");
		Assert.assertEquals(config.getAllowedUpdated(), Boolean.FALSE);
		Assert.assertEquals(config.getInstanceCount().intValue(), 0);
		Assert.assertEquals(config.getCreated().getTime(), 1483264800000L);
		Assert.assertEquals(config.getUpdated().getTime(), 1483264800000L);
	}

	@Test
	public void testGetDatabaseConfig() throws IOException, InterruptedException {
		respondWith("/trove/get_database_config_response.json");
		DatabaseConfig config = osv3().trove().configs().get("config-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/configurations/config-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(config.getId(), "904ce226-bcc7-457d-b118-74ebdce59ba9");
		Assert.assertEquals(config.getName(), "default-SQLServer-2014");
		Assert.assertEquals(config.getDescription(), "Default parameter group for sqlserver 2014");
		Assert.assertEquals(config.getDatastoreVersionId(), "4f71c5b5-8939-424e-8825-8e3816e4303d");
		Assert.assertEquals(config.getDatastoreVersionName(), "2014");
		Assert.assertEquals(config.getDatastoreName(), "sqlserver");
		Assert.assertEquals(config.getInstanceCount().intValue(), 0);
		Assert.assertEquals(config.getCreated().getTime(), 1493959251000L);
		Assert.assertEquals(config.getUpdated().getTime(), 1493959251000L);

		Assert.assertEquals(config.getValues().size(), 1);
		Assert.assertEquals(config.getValues().get("xp_cmdshell"), "0");
		Assert.assertEquals(config.getParameters().size(), 2);

		DatabaseConfigParam param = config.getParameters().get(0);
		Assert.assertEquals(param.getName(), "auto_increment_increment");
		Assert.assertEquals(param.getValue(), "1");
		Assert.assertEquals(param.getNeedRestart(), Boolean.FALSE);
		Assert.assertEquals(param.getReadonly(), Boolean.TRUE);
		Assert.assertEquals(param.getValueRange(), "1-65535");
		Assert.assertEquals(param.getType(), ParameterValueType.Integer);
		Assert.assertEquals(param.getDescription(),
				"auto_increment_increment and auto_increment_offset are "
						+ "intended for use with master-to-master replication, "
						+ "and can be used to control the operation of AUTO_INCREMENT columns.");

		DatabaseConfigParam param2 = config.getParameters().get(1);
		Assert.assertEquals(param2.getName(), "autocommit");
		Assert.assertEquals(param2.getValue(), "ON");
		Assert.assertEquals(param2.getNeedRestart(), Boolean.FALSE);
		Assert.assertEquals(param2.getReadonly(), Boolean.TRUE);
		Assert.assertEquals(param2.getValueRange(), "ON|OFF");
		Assert.assertEquals(param2.getType(), ParameterValueType.Boolean);
		Assert.assertEquals(param2.getDescription(),
				"The autocommit mode. If set to ON, all changes to a table take effect immediately. "
						+ "If set to OFF, you must use COMMIT to accept a transaction or ROLLBACK to cancel it. ");

	}

	@Test
	public void testListDatabaseInstanceByConfig() throws IOException, InterruptedException {
		respondWith("/trove/list_database_instance_by_config_response.json");
		List<DatabaseInstance> instances = osv3().trove().configs().listDatabaseInstances("config-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/configurations/config-id/instances");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(instances.size(), 2);

		DatabaseInstance config = instances.get(0);
		Assert.assertEquals(config.getId(), "53754eff-3f95-4da8-a908-a88e6ea2f65a");
		Assert.assertEquals(config.getName(), "instances_test_1");
		DatabaseInstance config2 = instances.get(1);
		Assert.assertEquals(config2.getId(), "53754eff-3f95-4da8-a908-a88e6ea2f65b");
		Assert.assertEquals(config2.getName(), "instances_test_2");
	}

	@Test
	public void testCreateDatabaseConfig() throws IOException, InterruptedException {
		respondWith("/trove/create_database_config_response.json");

		Map<String, Object> values = Maps.newHashMap();
		values.put("max_connections", "10");
		values.put("autocommit", "OFF");

		Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("5.6").build();
		DatabaseConfigCreate creation = DatabaseConfigCreate.builder().datastore(datastore).name("sdk")
				.description("openstack4j sdk unittest").values(values).build();
		DatabaseConfig config = osv3().trove().configs().create(creation);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/configurations");
		Assert.assertEquals(request.getMethod(), "POST");

		String requestBody = request.getBody().readUtf8();
		Assert.assertEquals(requestBody, this.getResource("/trove/create_database_config_request.json"));

		Assert.assertEquals(config.getId(), "463b4b58-d0e8-4e2b-9560-5dea4552fde9");
		Assert.assertEquals(config.getName(), "configuration_test");
		Assert.assertEquals(config.getDescription(), "configuration_test");
		Assert.assertEquals(config.getDatastoreVersionId(), "de90043f-7f29-4a3e-ba82-f8beb5678b46");
		Assert.assertEquals(config.getDatastoreVersionName(), "5.6");
		Assert.assertEquals(config.getDatastoreName(), "mysql");
		Assert.assertEquals(config.getInstanceCount().intValue(), 0);
		Assert.assertEquals(config.getCreated().getTime(), 1502257835000L);
		Assert.assertEquals(config.getUpdated().getTime(), 1502257835000L);

		Assert.assertEquals(config.getValues().size(), 2);
		Assert.assertEquals(config.getValues().get("max_connections"), "10");
		Assert.assertEquals(config.getValues().get("autocommit"), "OFF");
	}

	@Test
	public void testUpdateDatabaseConfig() throws IOException, InterruptedException {
		respondWith("/trove/update_database_config_response.json");

		Map<String, String> values = Maps.newHashMap();
		values.put("max_connections", "100");
		values.put("autocommit", "ON");

		DatabaseConfigUpdate update = DatabaseConfigUpdate.builder().id("config-id").name("name").description("desc")
				.values(values).build();
		ActionResponse response = osv3().trove().configs().update(update);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/configurations/config-id");
		Assert.assertEquals(request.getMethod(), "PUT");

		String requestBody = request.getBody().readUtf8();
		Assert.assertEquals(requestBody, this.getResource("/trove/update_database_config_request.json"));

		Assert.assertTrue(response.isSuccess());

	}

	@Test
	public void testUpdateConfigParams() throws IOException, InterruptedException {
		respondWith("/trove/update_database_config_params_response.json");

		Map<String, String> params = Maps.newHashMap();
		params.put("max_connections", "10");
		params.put("autocommit", "OFF");

		ActionResponse response = osv3().trove().configs().updateParams("config-id", params);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/configurations/config-id");
		Assert.assertEquals(request.getMethod(), "PATCH");

		String requestBody = request.getBody().readUtf8();
		Assert.assertEquals(requestBody, this.getResource("/trove/update_database_config_params_request.json"));

		Assert.assertTrue(response.isSuccess());
	}
	
	@Test
	public void testDeleteConfig() throws IOException, InterruptedException {
		respondWith("/trove/delete_database_config_response.json");

		ActionResponse response = osv3().trove().configs().delete("config-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/configurations/config-id");
		Assert.assertEquals(request.getMethod(), "DELETE");

		Assert.assertTrue(response.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
