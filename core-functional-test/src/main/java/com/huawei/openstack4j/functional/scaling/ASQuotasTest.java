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
package com.huawei.openstack4j.functional.scaling;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.scaling.ScalingGroup;
import com.huawei.openstack4j.openstack.common.Quota;
import com.huawei.openstack4j.openstack.scaling.options.ScalingGroupListOptions;

public class ASQuotasTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(ASQuotasTest.class);
	
	ScalingGroup scalingGroup = null;

	@BeforeClass
	public void prepare() {
		ScalingGroupListOptions options = ScalingGroupListOptions.create().limit(1);
		List<? extends ScalingGroup> list = osclient.autoScaling().groups().list(options);
		if (list == null || list.size() != 1) {
			throw new RuntimeException("no group for test");
		}

		scalingGroup = list.get(0);
	}

	@Test
	public void testListAutoScalingQuotas() {
		List<Quota> all = osclient.autoScaling().quotas().list();
		logger.info("{}", all);

		List<Quota> list = osclient.autoScaling().quotas().list(scalingGroup.getGroupId());
		logger.info("{}", list);
	}
}
