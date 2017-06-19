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
package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.ScalingQuota;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ASAutoScalingQuota implements ScalingQuota {

	private static final long serialVersionUID = -9212794561246868065L;

	@JsonProperty
	private String type;
	
	@JsonProperty
	private Integer used;
	
	@JsonProperty
	private Integer quota;
	
	@JsonProperty
	private Integer max;
	
	@JsonRootName("quotas")
	public static class ASAutoScalingQuotas extends ListResult<ASAutoScalingQuota> {

		private static final long serialVersionUID = 2903545947175032303L;
		
		@JsonProperty("resources")
		private List<ASAutoScalingQuota> quotas;

		@Override
		protected List<ASAutoScalingQuota> value() {
			return quotas;
		}
	}
}
