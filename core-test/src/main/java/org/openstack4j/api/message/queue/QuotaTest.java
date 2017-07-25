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
package org.openstack4j.api.message.queue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.openstack.common.Quota;
import org.openstack4j.openstack.common.Quota.ResourceType;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "MessageQueue/Quota", enabled = true)
public class QuotaTest extends AbstractTest {

	@Test
	public void testListQuota() throws IOException, InterruptedException {
		respondWith("/message/queue/list_quota_response.json");
		List<Quota> quotas = osv3().messageQueue().quotas().get();

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/v1.0/project-id/quotas/dms");
		Assert.assertEquals(request.getMethod(), "GET");
		
		Assert.assertEquals(quotas.size(), 1);
		Quota quota = quotas.get(0);

		Assert.assertEquals(quota.getType(), ResourceType.QUEUE);
		Assert.assertEquals(quota.getQuota().intValue(), 5);
		Assert.assertEquals(quota.getUsed().intValue(), 3);
	}
	
	@Override
	protected Service service() {
		return Service.MESSAGE_QUEUE;
	}

}
