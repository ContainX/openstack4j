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
package org.openstack4j.api.sahara;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.DataSource;
import org.openstack4j.model.sahara.DataSource.DataSourceType;
import org.openstack4j.model.sahara.options.DataSourceListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaDataSource;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Sahara/DataSource", enabled = true)
public class SaharaDataSourceTest extends AbstractTest {

	@Test
	public void testGetDataSource() throws IOException, InterruptedException {
		respondWith("/sahara/get_data_source_response.json");
		DataSource dataSource = osv3().sahara().dataSources().get("data-source-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/data-sources/data-source-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(dataSource.getId(), "953831f2-0852-49d8-ac71-af5805e25256");
		Assert.assertEquals(dataSource.isPublic(), Boolean.FALSE);
		Assert.assertEquals(dataSource.isProtected(), Boolean.FALSE);
		Assert.assertEquals(dataSource.getTenantId(), "9cd1314a0a31493282b6712b76a8fcda");
		Assert.assertEquals(dataSource.getCreatedAt().getTime(), 1481310734000L);
		Assert.assertEquals(dataSource.getUpdatedAt(), null);
		Assert.assertEquals(dataSource.getName(), "swift_input");
		Assert.assertEquals(dataSource.getDescription(), "This is input");
		Assert.assertEquals(dataSource.getURL(), "/container/text");
		Assert.assertEquals(dataSource.getType(), DataSourceType.HDFS);
	}

	@Test
	public void testUpdateDataSource() throws IOException, InterruptedException {
		respondWith("/sahara/update_data_source_response.json");

		DataSource update = SaharaDataSource.builder().id("data-source-id").name("SDK-unittests")
				.type(DataSourceType.HDFS).url("/sdk/unittests/input1").build();
		DataSource dataSource = osv3().sahara().dataSources().update(update);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/data-sources/data-source-id");
		Assert.assertEquals(request.getMethod(), "PUT");

		String requestBody = request.getBody().readUtf8();
		JsonNode requestNode = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("SDK-unittests", requestNode.get("name").asText());
		Assert.assertEquals("hdfs", requestNode.get("type").asText());
		Assert.assertEquals("/sdk/unittests/input1", requestNode.get("url").asText());
		Assert.assertEquals("/sdk/unittests/input1", requestNode.get("url").asText());

		Assert.assertEquals(dataSource.getId(), "9bcd0e98-d927-4cae-a111-c30ebc2454ed");
		Assert.assertEquals(dataSource.isPublic(), Boolean.FALSE);
		Assert.assertEquals(dataSource.isProtected(), Boolean.FALSE);
		Assert.assertEquals(dataSource.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(dataSource.getCreatedAt().getTime(), 1481310734000L);
		Assert.assertEquals(dataSource.getUpdatedAt().getTime(), 1481706344000L);
		Assert.assertEquals(dataSource.getName(), "my-input");
		Assert.assertEquals(dataSource.getDescription(), "");
		Assert.assertEquals(dataSource.getURL(), "/simple/mapreduce/input");
		Assert.assertEquals(dataSource.getType(), DataSourceType.HDFS);
	}

	@Test
	public void testListDataSource() throws IOException, InterruptedException {
		respondWith("/sahara/list_data_source_response.json");

		DataSourceListOptions options = DataSourceListOptions.create().asc("created_at").limit(10).marker("last-ds-id");
		List<? extends DataSource> dataSources = osv3().sahara().dataSources().list(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(),
				"/v1.1/project-id/data-sources?limit=10&sort_by=created_at&marker=last-ds-id");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(dataSources.size(), 2);
		DataSource dataSource = dataSources.get(0);
		Assert.assertEquals(dataSource.getId(), "9bcd0e98-d927-4cae-a111-c30ebc2454ed");
		Assert.assertEquals(dataSource.isPublic(), Boolean.FALSE);
		Assert.assertEquals(dataSource.isProtected(), Boolean.FALSE);
		Assert.assertEquals(dataSource.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(dataSource.getCreatedAt().getTime(), 1481310734000L);
		Assert.assertEquals(dataSource.getUpdatedAt().getTime(), 1481706344000L);
		Assert.assertEquals(dataSource.getName(), "my-input");
		Assert.assertEquals(dataSource.getDescription(), "");
		Assert.assertEquals(dataSource.getURL(), "/simple/mapreduce/input");
		Assert.assertEquals(dataSource.getType(), DataSourceType.HDFS);
	}

	@Test
	public void testDeleteDataSource() throws IOException, InterruptedException {
		respondWith(200);

		ActionResponse delete = osv3().sahara().dataSources().delete("data-source-id");
		Assert.assertTrue(delete.isSuccess());

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/data-sources/data-source-id");
		Assert.assertEquals(request.getMethod(), "DELETE");
	}
	
	@Test
	public void testCreateDataSource() throws IOException, InterruptedException {
		respondWith("/sahara/create_data_source_response.json");
		
		DataSource create = SaharaDataSource.builder().name("SDK-unittests")
				.type(DataSourceType.HDFS).url("/sdk/unittests/input1").build();

		DataSource dataSource = osv3().sahara().dataSources().create(create);
		

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.1/project-id/data-sources");
		Assert.assertEquals(request.getMethod(), "POST");
		
		String requestBody = request.getBody().readUtf8();
		JsonNode requestNode = ObjectMapperSingleton.getContext(Object.class).readTree(requestBody);
		Assert.assertEquals("SDK-unittests", requestNode.get("name").asText());
		Assert.assertEquals("hdfs", requestNode.get("type").asText());
		Assert.assertEquals("/sdk/unittests/input1", requestNode.get("url").asText());
		
		Assert.assertEquals(dataSource.getId(), "9bcd0e98-d927-4cae-a111-c30ebc2454ed");
		Assert.assertEquals(dataSource.isPublic(), Boolean.FALSE);
		Assert.assertEquals(dataSource.isProtected(), Boolean.FALSE);
		Assert.assertEquals(dataSource.getTenantId(), "46abc9f8044f40428f83a06cba0d5ddb");
		Assert.assertEquals(dataSource.getCreatedAt().getTime(), 1481310734000L);
		Assert.assertEquals(dataSource.getUpdatedAt().getTime(), 1481310734000L);
		Assert.assertEquals(dataSource.getName(), "my-input");
		Assert.assertEquals(dataSource.getDescription(), "");
		Assert.assertEquals(dataSource.getURL(), "/simple/mapreduce/input");
		Assert.assertEquals(dataSource.getType(), DataSourceType.HDFS);
	}

	@Override
	protected Service service() {
		return Service.SAHARA;
	}

}
