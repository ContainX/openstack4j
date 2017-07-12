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

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.sahara.constants.JobType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * HuaWei Job Execution, incompatible with Sahara.
 *
 * @author QianBiao.NG
 * @date   2017-07-05 16:07:41
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("job_execution")
public class SaharaJobExeCreate implements ModelEntity {

	static final long serialVersionUID = 1L;

	@JsonProperty("cluster_id")
	String clusterId;

	@JsonProperty("input")
	String input;

	@JsonProperty("output")
	String output;

	@JsonProperty("job_name")
	String jobName;

	@JsonProperty("job_type")
	JobType jobType;

	@JsonProperty("job_log")
	String jobLog;

	@JsonProperty("jar_path")
	String jarPath;

	@JsonProperty("file_action")
	String fileAction;

	@JsonProperty("arguments")
	String arguments;

	@JsonProperty("hql")
	String hql;

	@JsonProperty("hive_script_path")
	String hiveScriptPath;
	
	@JsonProperty("shutdown_cluster")
	Boolean shutdownCluster;
	
	@JsonProperty("submit_job_once_cluster_run")
	Boolean submitJobOnceClusterRun;

	@JsonProperty("is_protected")
	Boolean isProtected;

	@JsonProperty("is_public")
	Boolean isPublic;
	
}
