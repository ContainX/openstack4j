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
package com.huawei.openstack4j.api.message.queue;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.core.transport.ObjectMapperSingleton;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.openstack.message.queue.domain.Queue;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "MessageQueue/Queue", enabled = true)
public class QueueTest extends AbstractTest {

	@Test
	public void testCreateQueue() throws IOException, InterruptedException {
		respondWith("/message/queue/create_queue_response.json");
		
		Queue queue = osv3().messageQueue().queue().create("queue-name", "description");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("queue-name", node.get("name").asText());
		Assert.assertEquals("description", node.get("description").asText());
		
		Assert.assertEquals(queue.getId(), "9bf46390-38a2-462d-b392-4d5b2d519c55");
		Assert.assertEquals(queue.getName(), "queue_001");
	}
	
	@Test
	public void testGetQueue() throws IOException, InterruptedException {
		respondWith("/message/queue/get_queue_response.json");
		
		Queue queue = osv3().messageQueue().queue().get("queue-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues/queue-id");
		Assert.assertEquals(request.getMethod(), "GET");
		
		Assert.assertEquals(queue.getId(), "9bf46390-38a2-462d-b392-4d5b2d519c55");
		Assert.assertEquals(queue.getName(), "queue_001");
		Assert.assertEquals(queue.getDescription(), "test1");
		Assert.assertEquals(queue.getCreated().getTime(), 1470063965218L);
		Assert.assertEquals(queue.getReservation().intValue(), 4320);
		Assert.assertEquals(queue.getMaxMsgSizeByte().intValue(), 524288);
		Assert.assertEquals(queue.getProducedMessages().intValue(), 5);
	}
	
	@Test
	public void testListQueue() throws IOException, InterruptedException {
		respondWith("/message/queue/list_queue_response.json");
		
		List<? extends Queue> queues = osv3().messageQueue().queue().list();

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues");
		Assert.assertEquals(request.getMethod(), "GET");
		
		Assert.assertEquals(queues.size(), 4);
		
		Queue queue = queues.get(0);
		
		Assert.assertEquals(queue.getId(), "277b772e-05d2-4da4-8539-fe4c06de53b6");
		Assert.assertEquals(queue.getName(), "queue_002");
		Assert.assertEquals(queue.getDescription(), "Used for testing");
		Assert.assertEquals(queue.getCreated().getTime(), 1470063863257L);
		Assert.assertEquals(queue.getReservation().intValue(), 4320);
		Assert.assertEquals(queue.getMaxMsgSizeByte().intValue(), 524288);
		Assert.assertEquals(queue.getProducedMessages().intValue(), 5);
	}
	
	@Test
	public void testDeleteQueue() throws IOException, InterruptedException {
		respondWith(200);
		
		ActionResponse response = osv3().messageQueue().queue().delete("queue-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/queues/queue-id");
		Assert.assertEquals(request.getMethod(), "DELETE");
		
		Assert.assertTrue(response.isSuccess());
	}

	@Override
	protected Service service() {
		return Service.MESSAGE_QUEUE;
	}

}
