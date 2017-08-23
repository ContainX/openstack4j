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
package com.huawei.openstack4j.model.loadbalance;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;

import com.google.common.base.Strings;

public interface LoadBalancer extends ModelEntity {

	public enum Status {
		ACTIVE, PENDING_CREATE, ERROR;

		@JsonCreator
		public Status forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (Status status : Status.values()) {
					if (status.name().equalsIgnoreCase(value)) {
						return status;
					}
				}
			}
			return null;
		}
	}

	public enum Type {
		INTERNAL("Internal"), EXTERNAL("External");

		private String val;

		private Type(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public Type forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (Type type : Type.values()) {
					if (type.getVal().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}

	/**
	 * @return load balancer id
	 */
	String getId();

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
	Type getType();

	/**
	 * @return administration state of load balancer
	 */
	Integer getAdminStateUp();

	/**
	 * @return vip subnet id of load balancer
	 */
	String getVipSubnetId();

	/**
	 * @return security group id
	 */
	String getSecurityGroupId();

	/**
	 * @return vip address
	 */
	String getVipAddress();

	/**
	 * @return load balancer status
	 */
	Status getStatus();

	/**
	 * @return creation time of load balancer
	 */
	Date getCreateTime();

	/**
	 * @return update time of load balancer
	 */
	Date getUpdateTime();
}
