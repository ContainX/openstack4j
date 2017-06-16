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
package org.openstack4j.openstack.dns.v2.domain;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.dns.v2.PTR;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignatePTR implements PTR {
	
	private static final long serialVersionUID = 1649349482218584398L;
	
	String id;
	String ptrdname;
	String description;
	int ttl;
	String address;
	Status status;
	String action;
	Map<String, String> links;
	String region;
	
	@JsonProperty("floatingip_id")
	String floatingIpId;
	
    public static class PTRList extends ListResult<DesignatePTR> {
        private static final long serialVersionUID = 1L;
        
        @JsonProperty("floatingips")
        protected List<DesignatePTR> list;

        @Override
        public List<DesignatePTR> value() {
            return list;
        }
    }

}
