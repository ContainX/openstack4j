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
package org.openstack4j.api.scaling;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.scaling.ScalingQuota;
import org.testng.annotations.Test;

//TODO need test
@Test(suiteName = "AutoScaling/AutoScalingQuotasV1", enabled = true)
public class AutoScalingQuotasV1Tests4 extends AbstractTest {

	private static final String JSON_SCALING_QUOTAS_LIST = "";
	private static final String JSON_SCALING_QUOTAS_LIST2 = "";

	public void testListAutoScalingQuotas() throws IOException {
		respondWith(JSON_SCALING_QUOTAS_LIST);
		String groupId = "";
		List<? extends ScalingQuota> all = osv3().autoScaling().quotas().list();
		assertTrue(all != null && all.size() == 5);

		respondWith(JSON_SCALING_QUOTAS_LIST2);
		List<? extends ScalingQuota> list = osv3().autoScaling().quotas().list(groupId);
		assertTrue(list != null && list.size() == 2);
	}

	@Override
	protected Service service() {
		return Service.AUTO_SCALING;
	}

}
