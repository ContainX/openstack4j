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
package com.huawei.openstack4j.openstack.database.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of database restore point
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("restore")
public class DatabaseRestorePoint implements ModelEntity {

	private static final long serialVersionUID = 5294355671374520846L;

	/**
	 * the backup file Identifier to be restored 
	 */
	@JsonProperty("backupRef")
	String backupRef;
	
	/**
	 * time point the DB instance is restored to. 
	 * 
	 * At least one of the backupRef and restoreTime parameters should be specified. 
	 * If both parameters are specified, the DB instance is restored using the full backup file.
	 */
	@JsonProperty("restoreTime")
	Date restoreTime;
	
	
	/**
	 * the source DB instance ID.
	 */
	@JsonProperty("sourceInstanceId")
	String sourceInstanceId;

}
