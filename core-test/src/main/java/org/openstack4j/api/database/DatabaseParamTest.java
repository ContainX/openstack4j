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
package org.openstack4j.api.database;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.openstack.database.constants.OperateInstanceParamResult;
import org.openstack4j.openstack.database.constants.ParameterValueType;
import org.openstack4j.openstack.database.domain.DatabaseParam;
import org.openstack4j.openstack.database.domain.InstanceParamOperationResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Maps;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Database/InstanceFlavor", enabled = true)
public class DatabaseParamTest extends AbstractTest {

	@Test
	public void testListDatabaseParam() throws IOException, InterruptedException {
		respondWith("/database/list_database_param_response.json");
		List<DatabaseParam> params = osv3().database().params().list("databaseId");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/datastores/versions/databaseId/parameters");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(params.size(), 4);

		DatabaseParam param = params.get(0);

		Assert.assertEquals(param.getName(), "autocommit");
		Assert.assertEquals(param.getType(), ParameterValueType.Boolean);
		Assert.assertEquals(param.getValueRange(), "ON|OFF");
		Assert.assertEquals(param.getRestartRequired(), Boolean.FALSE);
		Assert.assertEquals(param.getDatastoreVersionId(), "e8a8b8cc-63f8-4fb5-8d4a-24c502317a61");
		Assert.assertEquals(param.getDescription(),
				"The autocommit mode. If set to ON, all changes to a table take effect immediately. "
						+ "If set to OFF, you must use COMMIT to accept a transaction or ROLLBACK to cancel it. ");

	}

	@Test
	public void testGetDatabaseParam() throws IOException, InterruptedException {
		respondWith("/database/get_database_param_response.json");
		DatabaseParam param = osv3().database().params().get("databaseId", "parameter1");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/rds/v1/project-id/datastores/versions/databaseId/parameters/parameter1");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(param.getName(), "connect_timeout");
		Assert.assertEquals(param.getType(), ParameterValueType.Integer);
		Assert.assertEquals(param.getMin().intValue(), 2);
		Assert.assertEquals(param.getMax().intValue(), 31536000);
		Assert.assertEquals(param.getRestartRequired(), Boolean.FALSE);
		Assert.assertEquals(param.getDatastoreVersionId(), "f8e67741-e767-4137-b394-3fb8a3fafd2f");
		Assert.assertEquals(param.getDescription(), " mysqld在用‘Bad handshake’消息响应连接请求包之前等待的秒数。");
	}

	@Test
	public void testConfigDatabaseParam() throws IOException, InterruptedException {
		respondWith("/database/config_database_param_response.json");
		Map<String, Object> params = Maps.newHashMap();
		params.put("connect_timeout", 17);
		params.put("sync_binlog", 1);
		InstanceParamOperationResult result = osv3().database().params().config("instance-id", params);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/parameters");
		Assert.assertEquals(request.getMethod(), "PUT");

		Assert.assertFalse(result.getShouldRestart());
		Assert.assertEquals(result.getResult(), OperateInstanceParamResult.SUCCESS);

	}

	@Test
	public void testRestoreDatabaseParam() throws IOException, InterruptedException {
		respondWith("/database/restore_database_param_response.json");
		InstanceParamOperationResult result = osv3().database().params().restore("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/parameters/default");
		Assert.assertEquals(request.getMethod(), "PUT");

		Assert.assertTrue(result.getShouldRestart());
		Assert.assertEquals(result.getResult(), OperateInstanceParamResult.MASTER_SUCCESS_REPLICA_FAIL);
	}
	
	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
