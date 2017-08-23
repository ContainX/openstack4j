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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.DateTimeUtils;
import com.huawei.openstack4j.openstack.common.ListResult;
import com.huawei.openstack4j.openstack.database.constants.BackupStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of Database Backup Creation
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("backup")
public class DatabaseBackup implements ModelEntity {

	private static final long serialVersionUID = 5294355671374520846L;

	/**
	 * backup identifier
	 */
	String id;
	
	/**
	 * backup name
	 */
	String name;
	
	/**
	 * backup description
	 */
	String description;
	
	/**
	 * Reserved
	 */
	String locationRef;

	/**
	 * backup file size
	 */
	Double size;
	
	/**
	 * backup status
	 */
	BackupStatus status;
	
	/**
	 * backup type, 1(snapshot) by default
	 */
	@JsonProperty("backuptype")
	String backupType;
	
	/**
	 * datastore information of this backup
	 */
	@JsonProperty("dataStore")
	Datastore datastore;
	
	@JsonProperty("instance_id")
	String instanceId;
	
	@JsonProperty("parent_id")
	String parentId;

	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS)
	Date created;
	
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS)
	Date updated;
	
	
	public static class Backups extends ListResult<DatabaseBackup> {
		private static final long serialVersionUID = 7666104777418585874L;

		@JsonProperty("backups")
		List<DatabaseBackup> backups;

		@Override
		protected List<DatabaseBackup> value() {
			return backups;
		}

	}

}
