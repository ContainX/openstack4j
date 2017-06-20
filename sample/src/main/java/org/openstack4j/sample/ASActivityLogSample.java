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
package org.openstack4j.sample;

import java.util.List;

import org.openstack4j.model.scaling.ScalingActivityLog;
import org.openstack4j.openstack.scaling.options.ScalingActivityLogListOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

//TODO need test
public class ASActivityLogSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASActivityLogSample.class);

	@Test
	public void testListAutoScalingActivityLog() {
		String groupId = "";
		List<? extends ScalingActivityLog> all = osclient.autoScaling().activityLogs().list(groupId);
		logger.info("{}", all);

		ScalingActivityLogListOptions options = ScalingActivityLogListOptions.create().startNumber(5).limit(5);
		List<? extends ScalingActivityLog> list = osclient.autoScaling().activityLogs().list(groupId, options);
		logger.info("{}", list);
	}
}
