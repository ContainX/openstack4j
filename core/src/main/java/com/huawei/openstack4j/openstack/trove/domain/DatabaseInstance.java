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
package com.huawei.openstack4j.openstack.trove.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.GenericLink;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.trove.constant.DatastoreType;
import com.huawei.openstack4j.openstack.trove.constant.InstanceType;

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

	private static final long serialVersionUID = -7399474725379713926L;

	String id;

	/**
	 * DB instance name
	 */
	String name;

	/**
	 * DB instance IP addr
	 */
	String ip;

	/**
	 * Reserved
	 */
	String hostname;

	/**
	 * DB instance status
	 */
	String status;

	Volume volume;

	/**
	 * datastore of this database
	 */
	Datastore datastore;

	/**
	 * DB instance flavor of the DB instance
	 */
	InstanceFlavor flavor;

	/**
	 * DB instance creation time
	 */
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS)
	Date created;

	/**
	 * DB instance updated time
	 */
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS)
	Date updated;

	@JsonProperty("links")
	List<GenericLink> links;

	/**
	 * database configuration status
	 */
	@JsonProperty("configurationStatus")
	String configurationStatus;

	/**
	 * database configuration id
	 */
	@JsonProperty("paramsGroupId")
	String configurationId;

	/**
	 * datastore type, @see DatastoreType
	 */
	DatastoreType type;

	/**
	 * network identifier of the DB instance
	 */
	@JsonProperty("subnetid")
	String subnetId;

	/**
	 * DB instance role in HA group, @see InstanceType
	 */
	InstanceType role;

	/**
	 * internal subnet ID
	 */
	String internalSubnetId;

	/**
	 * the group which the DB instance belongs to
	 */
	String group;

	/**
	 * security group id of the DB instance
	 */
	@JsonProperty("securegroup")
	String secureGroupId;

	/**
	 * router(VPC) of the DB instance
	 */
	@JsonProperty("vpc")
	String vpcId;

	/**
	 * DB instance availability zone
	 */
	@JsonProperty("azcode")
	String availabilityZone;

	/**
	 * DB instance region
	 */
	String region;

	/**
	 * Fault reason if the DB instance is faulty
	 */
	String fault;

	DatabaseConfig configuration;

	/**
	 * Reserved
	 */
	String locality;

	/**
	 * Replica DB list of the DB instance 
	 * (not null if the current DB instance is master) 
	 */
	List<DatabaseInstance> replicas;

	/**
	 * Master DB instance of the DB instance
	 * (not null if the current DB instance readreplica) 
	 */
	@JsonProperty("replica_of")
	List<DatabaseInstance> replicaOf;

	/**
	 * new administrator account
	 */
	@JsonProperty("dbuser")
	String dbUser;

	/**
	 * storage engine of the DB instance
	 */
	String storageEngine;

	/**
	 * pay model of the DB instance
	 */
	Integer payModel;

	/**
	 * cluster id the DB instance
	 */
	@JsonProperty("cluster_id")
	String clusterId;
	
	public static class DatabaseInstances extends ListResult<DatabaseInstance> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("instances")
		private List<DatabaseInstance> instances;

		@Override
		protected List<DatabaseInstance> value() {
			return instances;
		}
	}

	public static class DatabaseInstanceWrap {
		
		@JsonProperty("instance")
		DatabaseInstance instance;

		public DatabaseInstance getInstance() {
			return instance;
		}

		public void setInstance(DatabaseInstance instance) {
			this.instance = instance;
		}
		
	}

	public static class DatabaseInstanceWraps extends ListResult<DatabaseInstanceWrap> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("instances")
		private List<DatabaseInstanceWrap> instances;

		@Override
		protected List<DatabaseInstanceWrap> value() {
			return instances;
		}
	}
	
	

}
