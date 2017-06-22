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
package org.openstack4j.model.loadbalance;

import org.openstack4j.model.ModelEntity;

public interface ElasticLoadBalancerCreate extends ModelEntity {

	/**
	 * @return name of load balancer
	 */
	String getName();

	/**
	 * @return description of load balancer
	 */
	String getDescription();

	/**
	 * @return vpc id of load balancer
	 */
	String getVpcId();

	/**
	 * @return bandwidth of load balancer
	 */
	Integer getBandwidth();

	/**
	 * @return load balancer type
	 */
	String getType();
	
	/**
	 * @return administration state of load balancer
	 */
	Integer getAdminStateUp();
	
	/**
	 * @return vip subnet id of load balancer
	 */
	String getVipSubnetId();
	
	/**
	 * @return available zone id
	 */
	String getAzId();
	
	/**
	 * @return charge mode
	 */
	String getChargeMode();
	
	/**
	 * @return eip type
	 */
	String getEipType();
	
	/**
	 * @return security group id
	 */
	String getSecurityGroupId();
	
	/**
	 * @return vip address
	 */
	String getVipAddress();
	
	/**
	 * @return tenant id
	 */
	String getTenantId();
}
