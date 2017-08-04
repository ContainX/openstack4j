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
package org.openstack4j.openstack.database.domain;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.IdResourceEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent for attributes of Database instance creation
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
public class DatabaseInstanceCreate implements ModelEntity {

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
	 * <h3>database users</h3>
	 * 
	 * It must contain the administrator username (for mysql and postgres, the administrator username is root) 
	 * and the administrator password.
	 */
	@JsonProperty("users")
	List<DatabaseUser> users;

	/**
	 * Volume used for the DB instance
	 */
	@JsonProperty("volume")
	Volume volume;

	/**
	 *  Database configuration parameter set reference used for initializing the database
	 */
	@JsonProperty("configuration")
	String configurationId;
	
	/**
	 * availability zone of the DB instance
	 */
	@JsonProperty("availability_zone")
	String availabilityZone;
	
	
	/**
	 * VPC (known as Router) of the DB instance
	 */
	@JsonProperty("vpc")
	String vpcId;
	
	/**
	 * NICs (known as Subnet) of the DB instance
	 */
	@JsonProperty("nics")
	List<NIC> nics;
	
	
	/**
	 * (Reserved)
	 */
	@JsonProperty("modules")
	List<String> modules;
	
	/**
	 * (Reserved) Create the DB instance from a Database backup point
	 */
	@JsonProperty("restorePoint")
	RestorePoint restorePoint;
	
	
	/**
	 * (Reserved) 
	 */
	@JsonProperty("cluster_config")
	IdResourceEntity clusterConfig;

}
