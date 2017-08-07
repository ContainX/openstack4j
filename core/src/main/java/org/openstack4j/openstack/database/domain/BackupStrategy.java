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
package org.openstack4j.openstack.database.domain;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of Database Backup Strategy
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BackupStrategy implements ModelEntity {

	private static final long serialVersionUID = 5294355671374520846L;

	/**
	 * Specifies the time when automated backup is triggered. 
	 * 
	 * <li>The value cannot be empty and must use the following format: hh:mm:ss. </li>
	 * <li>Its value must indicate a valid time.</li>
	 */
	@JsonProperty("startTime")
	String startTime;
	
	
	/** 
	 * Specifies the number of days the generated backup files can be stored. Its value range is 0 to 35. 
	 * If this parameter is not specified or set to 0, the automated backup policy is not set 
	 */
	@JsonProperty("keepDays")
	String keepDays;

}
