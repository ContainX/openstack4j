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
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;

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
public class SaharaJobExe implements ModelEntity {

	static final long serialVersionUID = 1L;

	public enum JobType {

		MapReduce(1), Spark(2), Hive(3), Hql(4), DistCp(5), SparkScript(6), SparkSql(7),;

		Integer value;

		JobType(Integer value) {
			this.value = value;
		}

		@JsonValue
		public Integer value() {
			return value;
		}

		@JsonCreator
		public static JobType value(Integer v) {
			JobType[] values = JobType.values();
			for (JobType jobType : values) {
				if(jobType.value.equals(v)) {
					return jobType;
				}
			}
			return null;
		}
	}

	public enum JobState {

		Terminated(-1), Starting(1), Running(2), Completed(3), Abnormal(4),;

		Integer value;

		JobState(Integer value) {
			this.value = value;
		}

		@JsonValue
		public Integer value() {
			return value;
		}

		@JsonCreator
		public static JobState value(Integer v) {
			JobState[] values = JobState.values();
			for (JobState state : values) {
				if(state.value.equals(v)) {
					return state;
				}
			}
			return null;
		}
	}

	public enum JobFinalStatus {

		Running(0), Terminated(1), Completed(2), Canceled(4),;

		Integer value;

		JobFinalStatus(Integer value) {
			this.value = value;
		}

		@JsonValue
		public Integer value() {
			return value;
		}

		@JsonCreator
		public static JobFinalStatus value(Integer v) {
			JobFinalStatus[] values = JobFinalStatus.values();
			for (JobFinalStatus state : values) {
				if(state.value.equals(v)) {
					return state;
				}
			}
			return null;
		}

	}

	@JsonProperty("id")
	String id;

	@JsonProperty("cluster_id")
	String clusterId;

	@JsonProperty("group_id")
	String groupId;

	@JsonProperty("input")
	String input;

	@JsonProperty("output")
	String output;

	@JsonProperty("job_configs")
	SaharaJobConfig jobConfigs;

	@JsonProperty("job_id")
	String jobId;

	@JsonProperty("job_name")
	String jobName;

	@JsonProperty("job_type")
	JobType jobType;

	@JsonProperty("job_log")
	String jobLog;

	@JsonProperty("job_state")
	JobState jobState;

	@JsonProperty("job_final_status")
	JobFinalStatus jobFinalStatus;

	@JsonProperty("job_main_id")
	String jobMainId;

	@JsonProperty("job_step_id")
	String jobStepId;

	@JsonProperty("jar_path")
	String jarPath;

	@JsonProperty("progress")
	String progress;

	@JsonProperty("file_action")
	String fileAction;

	@JsonProperty("arguments")
	String arguments;

	@JsonProperty("hql")
	String hql;

	@JsonProperty("hive_script_path")
	String hiveScriptPath;

	@JsonProperty("finished_step")
	Integer finishedStep;

	@JsonProperty("postpone_at")
	Date postponeAt;

	@JsonProperty("step_name")
	String stepName;

	@JsonProperty("step_num")
	Integer stepNum;

	@JsonProperty("step_seq")
	Integer stepSeq;

	@JsonProperty("task_num")
	Integer taskNum;

	@JsonProperty("spend_time")
	Integer spendTime;

	@JsonProperty("create_by")
	String createBy;

	@JsonProperty("update_by")
	String updateBy;

	@JsonProperty("tenant_id")
	String tenantId;

	@JsonProperty("start_time")
	Date startTime;

	@JsonProperty("end_time")
	Date endTime;

	@JsonProperty("create_at")
	Date createAt;

	@JsonProperty("update_at")
	Date updateAt;

	@JsonProperty("is_protected")
	Boolean isProtected;

	@JsonProperty("is_public")
	Boolean isPublic;

	public static class JobExes extends ListResult<SaharaJobExe> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("job_executions")
		private List<SaharaJobExe> jobExe;

		public List<SaharaJobExe> value() {
			return jobExe;
		}
	}

}
