/*******************************************************************************
 *  Copyright 2017 Huawei TLD                                   
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
package com.huawei.openstack4j.model.storage.block;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.huawei.openstack4j.model.ModelEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:42:15
 */
public interface AsyncVolumeBackupJob extends ModelEntity {

	enum Status {

		SUCCESS, RUNNING, FAIL, INIT;

		@JsonCreator
		public static Status forValue(String value) {
			if (value != null) {
				for (Status s : Status.values()) {
					if (s.name().equalsIgnoreCase(value))
						return s;
				}
			}
			return null;
		}
	}

	/**
	 * @return job id
	 */
	public String getId();

	/**
	 * @return job type
	 */
	public String getType();

	/**
	 * 
	 * @return job entities
	 */
	public HashMap<String, Object> getEntities();

	/**
	 * 
	 * @return sub jobs of this job
	 */
	public List<AsyncVolumeBackupJob> getSubJobs();

	/**
	 * 
	 * @return job status
	 */
	public Status getStatus();

	/**
	 * 
	 * @return UTC date and time of the job start time
	 */
	public Date getBeginTime();

	/**
	 * 
	 * @return UTC date and time of the job end time
	 */
	public Date getEndTime();

	/**
	 * 
	 * @return Error Code if job fails
	 */
	public String getErrorCode();

	/**
	 * 
	 * @return Text fail reason if job fails
	 */
	public String getFailReason();
}
