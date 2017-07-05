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
package org.openstack4j.openstack.sahara.domain;

import java.util.Date;
import java.util.HashMap;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.ToString;

/**
 * 
 * HuaWei Job Execution, incompatible with Sahara.
 *
 * @author QianBiao.NG
 * @date   2017-07-05 16:07:41
 */
@ToString
@JsonRootName("job_execution")
@JsonIgnoreProperties(value = { "jobIdForExecution" }, ignoreUnknown = true)
public class SaharaJobExe implements ModelEntity {

	private static final long serialVersionUID = 1L;

	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("cluster_id")
	private String clusterId;
	
	@JsonProperty("input")
	private String input;
	
	@JsonProperty("output")
	private String output;
	
	@JsonProperty("job_configs")
	private SaharaJobConfig jobConfigs;
	
	@JsonProperty("job_id")
	private String jobId;
	
	@JsonProperty("job_name")
	private String jobName;
	
	@JsonProperty("job_type")
	private String jobType;
	
	@JsonProperty("group_id")
	private String groupId;
	
	@JsonProperty("jar_path")
	private String jarPath;
	
	@JsonProperty("job_log")
	private String jobLog;
	
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("start_time")
	private Date startTime;
	
	@JsonProperty("end_time")
	private Date endTime;
	
	@JsonProperty("created_at")
	private Date createdAt;
	
	@JsonProperty("updated_at")
	private Date updatedAt;
	
	@JsonProperty("oozie_job_id")
	private String oozieJobId;
	
	@JsonProperty("return_code")
	private String returnCode;
	
	@JsonProperty("progress")
	private String progress;
	
	@JsonProperty("info")
	private SaharaJobExecutionInfo info;

	private String jobIdForExecute;

	@JsonProperty("data_source_urls")
	HashMap<String, String> dataSourceUrls;

	@JsonProperty("engine_job_id")
	String engineJobId;
	@JsonProperty("is_protected")
	Boolean isProtected;
	@JsonProperty("is_public")
	Boolean isPublic;
	
//	file_action
//	arguments
//	hql
//	job_state
//	job_final_status
//	hive_script_path
//	create_by
//	finished_step
//	job_main_id
//	job_step_id
//	postpone_at
//	step_name
//	step_num
//	task_num
//	update_by
//	spend_time
//	step_seq
//	progress


}
