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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.loadbalance.LoadBalancer.Type;

import com.google.common.base.Strings;

public interface LoadBalancerCreate extends ModelEntity {

	public enum ChargeMode {
		BANDWIDTH("bandwidth"), TRAFFIC("traffic");

		private String val;

		private ChargeMode(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public ChargeMode forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (ChargeMode mode : ChargeMode.values()) {
					if (mode.getVal().equalsIgnoreCase(value)) {
						return mode;
					}
				}
			}
			return null;
		}
	}

	public enum EipType {
		TELCOM_5("5_telcom"), UNION_5("5_union"), BGP_5("5_bgp");

		private String val;

		private EipType(String val) {
			this.val = val;
		}

		@JsonValue
		public String getVal() {
			return this.val;
		}

		@JsonCreator
		public EipType forValue(String value) {
			if (!Strings.isNullOrEmpty(value)) {
				for (EipType type : EipType.values()) {
					if (type.getVal().equalsIgnoreCase(value)) {
						return type;
					}
				}
			}
			return null;
		}
	}

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
	 * @return available zone id
	 */
	String getAzId();

	/**
	 * @return charge mode
	 */
	ChargeMode getChargeMode();

	/**
	 * @return eip type
	 */
	EipType getEipType();

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
