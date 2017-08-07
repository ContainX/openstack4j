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

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.DateTimeUtils;
import org.openstack4j.openstack.common.IdResourceEntity;
import org.openstack4j.openstack.common.ListResult;
import org.openstack4j.openstack.database.constants.InstanceType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of database instance
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
public class DatabaseInstance implements ModelEntity {

	static final long serialVersionUID = -7399474725379713926L;

	String id;

	/**
	 * DB instance name
	 */
	String name;

	/**
	 * instance status
	 */
	String status;

	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZ)
	Date created;
	
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZ)
	Date updated;

	String hostname;
	InstanceType type;
	String region;
	String availabilityZone;

	String vpc;

	@JsonProperty("nics")
	NIC nic;

	/**
	 * security group of the DB instance
	 */
	@JsonProperty("securityGroup")
	IdResourceEntity securityGroup;

	@JsonProperty("flavor")
	IdResourceEntity flavor;

	List<String> ip;

	@JsonProperty("volume")
	Volume volume;

	/**
	 * datastore of this database
	 */
	@JsonProperty("dataStoreInfo")
	String datastore;

	/**
	 * backup policy of the DB instance
	 */
	@JsonProperty("backupStrategy")
	BackupStrategy backupStrategy;

	/**
	 * setup HA configuration of the DB instance
	 * @see HA
	 */
	@JsonProperty("ha")
	HA ha;

	@JsonProperty("replica_of")
	String replicaOf;

	public static class DatabaseInstances extends ListResult<DatabaseInstance> {

		static final long serialVersionUID = 1L;

		@JsonProperty("instances")
		List<DatabaseInstance> instances;

		@Override
		protected List<DatabaseInstance> value() {
			return instances;
		}
	}

}
