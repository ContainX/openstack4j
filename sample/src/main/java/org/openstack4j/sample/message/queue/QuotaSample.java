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
package org.openstack4j.sample.message.queue;

import java.util.List;

import org.openstack4j.openstack.common.Quota;
import org.openstack4j.openstack.common.Quota.ResourceType;
import org.openstack4j.sample.AbstractSample;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "MessageQueue/Quota/Sample")
public class QuotaSample extends AbstractSample {

	@Test
	public void testGetTopic() {
		List<Quota> qutoas = osclient.messageQueue().quotas().get();
		Assert.assertEquals(qutoas.size(), 1);

		Quota quota = qutoas.get(0);
		Assert.assertEquals(quota.getType(), ResourceType.QUEUE);
	}

}
