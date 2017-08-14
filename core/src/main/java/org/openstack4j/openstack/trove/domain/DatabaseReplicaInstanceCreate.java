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
package org.openstack4j.openstack.trove.domain;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent for attributes of Database replica instance creation
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("instance")
public class DatabaseReplicaInstanceCreate implements ModelEntity {

	static final long serialVersionUID = -7844139328996206764L;

	/**
	 * datastore to be created
	 */
	@JsonProperty("datastore")
	Datastore datastore;

	/**
	 * DB instance name
	 */
	@JsonProperty("name")
	String name;

	/**
	 * DB Flavor to be used to created the DB instance
	 */
	@JsonProperty("flavorRef")
	String flavorRef;

	/**
	 * Volume used for the DB instance
	 */
	@JsonProperty("volume")
	Volume volume;

	
	/**
	 * the master DB instance id
	 */
	@JsonProperty("slave_of")
	String slaveOf;
	
	/**
	 * the master DB instance id, (the same as slaveOf but with higher priority)
	 */
	@JsonProperty("replica_of")
	String replicaOf;
	
	
	/**
	 * The amount of replica should be created. 
	 * 
	 * Currently, creating multiple read replicas at a time is not supported. Valid value: 1 or not specified.
	 */
	@JsonProperty("replica_count")
	Integer replicaCount;

}
