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
package com.huawei.openstack4j.openstack.database.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.database.constants.InstanceType;
import com.huawei.openstack4j.openstack.trove.domain.ExtendParam;

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
	 * DB instance status
	 */
	String status;

	/**
	 * DB instance creation time
	 */
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZONE)
	Date created;

	/**
	 * DB instance updated time
	 */
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMSZONE)
	Date updated;

	String hostname;

	/**
	 * DB instance type, @see InstanceType
	 */
	InstanceType type;

	/**
	 * DB instance region
	 */
	String region;

	/**
	 * DB instance availability zone
	 */
	String availabilityZone;

	/**
	 * router(VPC) of the DB instance
	 */
	@JsonProperty("vpc")
	String vpcId;

	/**
	 * NIC(network interface) of the DB instance
	 */
	@JsonProperty("nics")
	NIC nic;

	/**
	 * security group of the DB instance
	 */
	IdResourceEntity securityGroup;

	/**
	 * DB instance flavor of the DB instance
	 */
	@JsonProperty("flavor")
	IdResourceEntity flavor;

	/**
	 * IP list of the DB instance
	 */
	List<String> ip;

	/**
	 * Volume attached to the DB instance
	 */
	@JsonProperty("volume")
	Volume volume;

	/**
	 * datastore of this database
	 */
	@JsonProperty("dataStoreInfo")
	Datastore datastore;

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

	/**
	 * slave instance Id (only effect when create instance with HA configed)
	 */
	@JsonProperty("slaveId")
	String slaveId;

	/**
	 * the master instance id (only effect when create a read only instance)
	 */
	@JsonProperty("replica_of")
	String replicaOf;
	
	
	@JsonProperty("extendparam")
	ExtendParam extendParam;


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
