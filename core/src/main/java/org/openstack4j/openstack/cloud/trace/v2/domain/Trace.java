/*******************************************************************************
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
package org.openstack4j.openstack.cloud.trace.v2.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.cloud.trace.constants.TraceStatus;
import org.openstack4j.openstack.cloud.trace.constants.TraceType;
import org.openstack4j.openstack.common.ListResult;

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
public class Trace implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;
	
	/**
	 * the id of the trace
	 */
	@JsonProperty("trace_id")
	String id;

	/**
	 * the name of the trace
	 */
	@JsonProperty("trace_name")
	String name;
	
	/**
	 * the source type of the trace (from API, Console, System)
	 */
	@JsonProperty("trace_type")
	TraceType type;
	
	/**
	 * the status of the trace
	 */
	@JsonProperty("trace_status")
	TraceStatus status;
	
	/**
	 * traced resource id
	 */
	@JsonProperty("resource_id")
	String resourceId;
	
	/**
	 * traced resource name
	 */
	@JsonProperty("resource_name")
	String resourceName;
	
	/**
	 * traced resource type
	 */
	@JsonProperty("resource_type")
	String resourceType;
	
	/**
	 * traced operation service type
	 */
	@JsonProperty("service_type")
	String serviceType;
	
	/**
	 * the api version of the operation service
	 */
	@JsonProperty("api_version")
	String apiVersion;
	
	/**
	 * user source ip
	 */
	@JsonProperty("source_ip")
	String sourceIp;
	
	/**
	 * API resource content
	 */
	@JsonProperty("request")
	HashMap<String, Object> request;
	
	/**
	 * API response content
	 */
	@JsonProperty("response")
	HashMap<String, Object> response;
	
	/**
	 * API response code
	 */
	@JsonProperty("code")
	Integer code;
	
	@JsonProperty("message")
	String message;
	
	/**
	 * Cloud Trace record time
	 */
	@JsonProperty("record_time")
	Date recordTime;
	
	/**
	 * event happen time
	 */
	@JsonProperty("time")
	Date time;
	
	/**
	 * operation user
	 */
	@JsonProperty("user")
	HashMap<String, Object> user;
	

	public static class Traces extends ListResult<Trace> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("traces")
		private List<Trace> traces;

		public List<Trace> value() {
			return traces;
		}

	}

}
