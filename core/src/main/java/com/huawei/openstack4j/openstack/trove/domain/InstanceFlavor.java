/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.openstack.trove.domain;

import java.util.List;

import com.google.common.base.MoreObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent for attributes of Database instance Flavor
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class InstanceFlavor implements ModelEntity {

	static final long serialVersionUID = 1640365078802343096L;

	String id;

	String name;

	@JsonProperty("str_id")
	String strId;

	Integer ram;

	@JsonProperty("links")
	List<GenericLink> links;

	@JsonProperty("flavor_detail")
	List<InstanceFlavorSpec> flavorDetail;

	@JsonProperty("price_detail")
	List<InstanceFlavorCharging> priceDetail;

	/**
	 * Model represent an Instance Flavor Specification
	 *
	 * @author QianBiao.NG
	 * @date   2017-08-03 10:19:41
	 */
	public static class InstanceFlavorSpec {

		String name;
		String value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this).toString();
		}

	}

	/**
	 * Model represent an Instance Flavor charging
	 *
	 * @author QianBiao.NG
	 * @date   2017-08-03 10:19:41
	 */
	public static class InstanceFlavorCharging {

		String timeUnit;
		String price;

		public String getTimeUnit() {
			return timeUnit;
		}

		public void setTimeUnit(String timeUnit) {
			this.timeUnit = timeUnit;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}
		
		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this).toString();
		}
		
	}

	public static class Flavors extends ListResult<InstanceFlavor> {

		static final long serialVersionUID = 1L;

		@JsonProperty("flavors")
		List<InstanceFlavor> flavors;

		@Override
		protected List<InstanceFlavor> value() {
			return flavors;
		}

	}

}
