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

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.IdResourceEntity;

public interface ScalingGroupCreate extends ModelEntity {

	/**
	 * @return scaling group id
	 */
	String groupId();
	
	/**
	 * @return the name for the scaling group
	 */
	String groupName();
	
	/**
	 * 
	 * @return scaling configuration id
	 */
	String configId();
	
	/**
	 * 
	 * @return desire instance number
	 */
	Integer desireInstanceNumber();
	
	/**
	 * 
	 * @return minimal instance number
	 */
	Integer minInstanceNumber();
	
	/**
	 * 
	 * @return maximal instance number
	 */
	Integer maxInstanceNumber();
	
	/**
	 * 
	 * @return cool down time
	 */
	Integer coolDownTime();
	
	/**
	 * 
	 * @return load balance listener id
	 */
	String lbListenerId();
	
	/**
	 * 
	 * @return availability zones
	 */
	List<String> availabilityZones();
	
	/**
	 * 
	 * @return networks information
	 */
	//TODO check
	List<IdResourceEntity> networks();
	
	/**
	 * 
	 * @return security groups information
	 */
	//TODO check
	List<IdResourceEntity> securityGroups();
	
	/**
	 * 
	 * @return vpc id
	 */
	String vpcId();
	
	/**
	 * 
	 * @return health periodic audit method
	 */
	String healthPeriodicAuditMethod();
	
	/**
	 * 
	 * @return health periodic audit time
	 */
	Integer healthPeriodicAuditTime();
	
	/**
	 * 
	 * @return instance terminate policy
	 */
	String instanceTerminatePolicy();
	
	/**
	 * 
	 * @return notification method
	 */
	List<String> notifications();
	
	/**
	 * 
	 * @return whether delete server's ip
	 */
	Boolean deletePublicip();
}
