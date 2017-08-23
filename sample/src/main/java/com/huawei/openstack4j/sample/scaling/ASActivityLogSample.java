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
package com.huawei.openstack4j.sample.scaling;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.huawei.openstack4j.model.scaling.ScalingActivityLog;
import com.huawei.openstack4j.openstack.scaling.options.ScalingActivityLogListOptions;
import com.huawei.openstack4j.sample.AbstractSample;

public class ASActivityLogSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASActivityLogSample.class);

	@Test
	public void testListAutoScalingActivityLog() {
		String groupId = "6e42cf82-8157-41eb-a2bc-784f18fa9c2a";
		List<? extends ScalingActivityLog> all = osclient.autoScaling().activityLogs().list(groupId);
		logger.info("{}", all);

		ScalingActivityLogListOptions options = ScalingActivityLogListOptions.create().startNumber(5).limit(5);
		List<? extends ScalingActivityLog> list = osclient.autoScaling().activityLogs().list(groupId, options);
		logger.info("{}", list);
	}
}
