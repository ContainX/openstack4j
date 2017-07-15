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
package org.openstack4j.openstack.cloud.trace.domain;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.cloud.trace.constants.TrackerStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TrackerUpdate implements ModelEntity {

	private static final long serialVersionUID = -6764087311133427927L;

	/**
	 * the name of the tracker to be updated
	 */
	@JsonIgnore
	String trackerName;

	/**
	 * bucket (name) used to persist the trace log
	 */
	@JsonProperty("bucket_name")
	String bucketName;

	/**
	 * if set, only log files whose name start with `file_prefix_name` will be persisted
	 */
	@JsonProperty("file_prefix_name")
	String filePrefixName;

	/**
	 * tracer status
	 */
	@JsonProperty("status")
	TrackerStatus status;

}
