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

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.trove.constant.ParameterValueType;
import com.huawei.openstack4j.openstack.trove.domain.DatabaseParam;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Trove/Parameter", enabled = true)
public class DatabaseParamTest extends AbstractTest {

	@Test
	public void testListDatabaseParam() throws IOException, InterruptedException {
		respondWith("/trove/list_database_param_response.json");
		List<DatabaseParam> params = osv3().trove().params().list("databaseId");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/datastores/versions/databaseId/parameters");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(params.size(), 4);

		DatabaseParam param = params.get(0);
		Assert.assertEquals(param.getName(), "autocommit");
		Assert.assertEquals(param.getType(), ParameterValueType.Boolean);
		Assert.assertEquals(param.getRestartRequired(), Boolean.FALSE);
		Assert.assertEquals(param.getDatastoreVersionId(), "e8a8b8cc-63f8-4fb5-8d4a-24c502317a61");
	}

	@Test
	public void testGetDatabaseParam() throws IOException, InterruptedException {
		respondWith("/trove/get_database_param_response.json");
		DatabaseParam param = osv3().trove().params().get("databaseId", "parameter1");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/datastores/versions/databaseId/parameters/parameter1");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(param.getName(), "connect_timeout");
		Assert.assertEquals(param.getType(), ParameterValueType.Integer);
		Assert.assertEquals(param.getMin().intValue(), 2);
		Assert.assertEquals(param.getMax().intValue(), 31536000);
		Assert.assertEquals(param.getRestartRequired(), Boolean.FALSE);
		Assert.assertEquals(param.getDatastoreVersionId(), "de90043f-7f29-4a3e-ba82-f8beb5678b46");
	}

	@Test
	public void testGetDefaultDatabaseParams() throws IOException, InterruptedException {
		respondWith("/trove/get_default_database_param_response.json");
		Map<String, String> params = osv3().trove().params().getDefaultParamsByInstance("instance-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/instances/instance-id/configuration");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(params.get("basedir"), "/usr");
		Assert.assertEquals(params.get("connect_timeout"), "15");
		Assert.assertEquals(params.get("datadir"), "/var/lib/mysql");
		Assert.assertEquals(params.get("default_storage_engine"), "innodb");
		Assert.assertEquals(params.get("innodb_buffer_pool_size"), "600M");
		Assert.assertEquals(params.get("innodb_data_file_path"), "ibdata1:10M:autoextend");
		Assert.assertEquals(params.get("innodb_file_per_table"), "1");
		Assert.assertEquals(params.get("innodb_log_buffer_size"), "25M");
		Assert.assertEquals(params.get("innodb_log_file_size"), "50M");
		Assert.assertEquals(params.get("innodb_log_files_in_group"), "2");
		Assert.assertEquals(params.get("join_buffer_size"), "1M");
		Assert.assertEquals(params.get("key_buffer_size"), "200M");
		Assert.assertEquals(params.get("local-infile"), "0");
		Assert.assertEquals(params.get("max_allowed_packet"), "4096K");
		Assert.assertEquals(params.get("max_connections"), "400");
		Assert.assertEquals(params.get("max_heap_table_size"), "64M");
		Assert.assertEquals(params.get("max_user_connections"), "400");
		Assert.assertEquals(params.get("myisam-recover"), "BACKUP");
		Assert.assertEquals(params.get("open_files_limit"), "2048");
		Assert.assertEquals(params.get("pid_file"), "/var/run/mysqld/mysqld.pid");
		Assert.assertEquals(params.get("port"), "3306");
		Assert.assertEquals(params.get("query_cache_limit"), "1M");
		Assert.assertEquals(params.get("query_cache_size"), "32M");
		Assert.assertEquals(params.get("query_cache_type"), "1");
		Assert.assertEquals(params.get("read_buffer_size"), "512K");
		Assert.assertEquals(params.get("read_rnd_buffer_size"), "512K");
		Assert.assertEquals(params.get("server_id"), "334596");
		Assert.assertEquals(params.get("skip-external-locking"), "1");
		Assert.assertEquals(params.get("sort_buffer_size"), "1M");
		Assert.assertEquals(params.get("table_definition_cache"), "1024");
		Assert.assertEquals(params.get("table_open_cache"), "1024");
		Assert.assertEquals(params.get("thread_cache_size"), "16");
		Assert.assertEquals(params.get("thread_stack"), "192K");
		Assert.assertEquals(params.get("tmp_table_size"), "64M");
		Assert.assertEquals(params.get("tmpdir"), "/var/tmp");
		Assert.assertEquals(params.get("user"), "mysql");
		Assert.assertEquals(params.get("wait_timeout"), "120");

	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
