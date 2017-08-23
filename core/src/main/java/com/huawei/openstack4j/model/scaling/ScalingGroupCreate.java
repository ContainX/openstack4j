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
package com.huawei.openstack4j.model.scaling;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.scaling.ScalingGroup.HealthPeriodicAuditMethod;
import com.huawei.openstack4j.model.scaling.ScalingGroup.InstanceTerminatePolicy;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;

public interface ScalingGroupCreate extends ModelEntity {

	/**
	 * @return scaling group id
	 */
	String getGroupId();

	/**
	 * @return the name for the scaling group
	 */
	String getGroupName();

	/**
	 * 
	 * @return scaling configuration id
	 */
	String getConfigId();

	/**
	 * 
	 * @return desire instance number
	 */
	Integer getDesireInstanceNumber();

	/**
	 * 
	 * @return minimal instance number
	 */
	Integer getMinInstanceNumber();

	/**
	 * 
	 * @return maximal instance number
	 */
	Integer getMaxInstanceNumber();

	/**
	 * 
	 * @return cool down time
	 */
	Integer getCoolDownTime();

	/**
	 * 
	 * @return load balance listener id
	 */
	String getLbListenerId();

	/**
	 * 
	 * @return availability zones
	 */
	List<String> getAvailabilityZones();

	/**
	 * 
	 * @return networks information
	 */
	List<IdResourceEntity> getNetworks();

	/**
	 * 
	 * @return security groups information
	 */
	List<IdResourceEntity> getSecurityGroups();

	/**
	 * 
	 * @return vpc id
	 */
	String getVpcId();

	/**
	 * 
	 * @return health periodic audit method
	 */
	HealthPeriodicAuditMethod getHealthPeriodicAuditMethod();

	/**
	 * 
	 * @return health periodic audit time
	 */
	Integer getHealthPeriodicAuditTime();

	/**
	 * 
	 * @return instance terminate policy
	 */
	InstanceTerminatePolicy getInstanceTerminatePolicy();

	/**
	 * 
	 * @return notification method
	 */
	List<String> getNotifications();

	/**
	 * 
	 * @return whether delete server's ip
	 */
	Boolean getDeletePublicip();
}
