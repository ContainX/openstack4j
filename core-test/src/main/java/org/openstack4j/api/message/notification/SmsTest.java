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
package org.openstack4j.api.message.notification;

import java.io.IOException;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.openstack.message.notification.domain.MessageIdResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Notification/SMS", enabled = true)
public class SmsTest extends AbstractTest {

	@Test
	public void testSend() throws IOException, InterruptedException {
		respondWith("/message/notification/send_sms_response.json");

		MessageIdResponse response = osv3().notification().sms().send("15612341234", "message", "sms-sign-id");

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v2/project-id/notifications/sms");
		Assert.assertEquals(request.getMethod(), "POST");
		String body = request.getBody().readUtf8();
		JsonNode node = ObjectMapperSingleton.getContext(Object.class).readTree(body);
		Assert.assertEquals("+15612341234", node.get("endpoint").asText());
		Assert.assertEquals("message", node.get("message").asText());
		Assert.assertEquals("sms-sign-id", node.get("sign_id").asText());
		
		Assert.assertEquals(response.getId(), "bf94b63a5dfb475994d3ac34664e24f2");
		Assert.assertEquals(response.getRequestId(), "9974c07f6d554a6d827956acbeb4be5f");
	}
	

	@Override
	protected Service service() {
		return Service.NOTIFICATION;
	}

}
