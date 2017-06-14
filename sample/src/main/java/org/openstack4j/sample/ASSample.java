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
package org.openstack4j.sample;

import java.util.List;

import org.openstack4j.model.scaling.ScalingGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Woo Cubic
 * @date   2017-06-06 20:40:10
 */
public class ASSample extends AbstractSample {

	private static final Logger logger = LoggerFactory.getLogger(ASSample.class);

	@Test
	public void testListAutoScalingGroups() {
		List<? extends ScalingGroup> list = osclient.autoScaling().groups().list();
		logger.info("{}", list);
		Assert.assertNotNull(list);
	}


}
