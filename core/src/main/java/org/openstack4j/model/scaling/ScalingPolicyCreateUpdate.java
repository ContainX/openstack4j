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
package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

public interface ScalingPolicyCreateUpdate extends ModelEntity {
	/**
	 * @return policy id
	 */
	String getPolicyId();
	
	/**
	 * @return policy name
	 */
	String getPolicyName();
	
	/**
	 * @return scaling group id
	 */
	String getGroupId();
	
	/**
	 * @return policy type
	 */
	String getPolicyType();
	
	/**
	 * @return alarm id
	 */
	String getAlarmId();
	
	/**
	 * @return scheduled policy
	 */
	ScheduledPolicy getScheduledPolicy();
	
	/**
	 * @return scaling policy action
	 */
	ScalingPolicyAction getScalingPolicyAction();
	
	/**
	 * @return cool down time
	 */
	Integer getCoolDownTime();
}
