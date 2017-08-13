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

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of database instance creation by backup restoring
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
public class DatabaseInstanceRestore implements ModelEntity {

	private static final long serialVersionUID = 5294355671374520846L;

	/**
	 * new DB instance name
	 */
	String name;
	
	/**
	 * new DB instance flavor
	 */
	String flavorRef;
	
	/**
	 * volume settings of the new DB instance
	 */
	Volume volume;
	
	/**
	 * high available settings of the new DB instance
	 */
	HA ha;
	
	
	/**
	 * restore settings
	 */
	DatabaseRestorePoint restorePoint;
}
