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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;

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
	 * Volume used for the DB instance
	 */
	@JsonProperty("volume")
	Volume volume;

	/**
	 * Region of the DB instance
	 */
	@JsonProperty("region")
	String region;
	
	/**
	 * availability zone of the DB instance
	 */
	@JsonProperty("availabilityZone")
	String availabilityZone;
	
	
	/**
	 * VPC (known as Router) of the DB instance
	 */
	@JsonProperty("vpc")
	String vpcId;
	
	/**
	 * NIC (known as Subnet) of the DB instance
	 */
	@JsonProperty("nics")
	NIC nic;
	
	
	/**
	 * security group of the DB instance
	 */
	@JsonProperty("securityGroup")
	IdResourceEntity securityGroup;
	
	
	/**
	 * the password for user `root` of the DB instance
	 */
	@JsonProperty("dbRtPd")
	String rootPassword;
	
	
	/**
	 * High Available(replica) configuration of the DB instance
	 * (should not set this for create read only instance)
	 * 
	 * @see HA
	 */
	@JsonProperty("ha")
	HA ha;
	
	
	/**
	 * backup policy of the DB instance
	 * (should not set this for create read only instance)
	 */
	@JsonProperty("backupStrategy")
	BackupStrategy backupStrategy;
	
	/**
	 *  Used to create a read replica of a primary DB instance
	 */
	@JsonProperty("replicaOf")
	String replicaOf;

}
