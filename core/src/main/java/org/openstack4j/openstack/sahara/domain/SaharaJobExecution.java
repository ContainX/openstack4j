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
import java.util.List;

import org.openstack4j.model.sahara.JobConfig;
import org.openstack4j.model.sahara.JobExecution;
import org.openstack4j.model.sahara.JobExecutionInfo;
import org.openstack4j.model.sahara.builder.JobExecutionBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.ToString;

/**
 * For mapping JSON response to/from java objects
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
@ToString
@JsonRootName("job_execution")
@JsonIgnoreProperties(value = { "jobIdForExecution" }, ignoreUnknown = true)
public class SaharaJobExecution implements JobExecution {

	private static final long serialVersionUID = 1L;

	@JsonProperty("cluster_id")
	private String clusterId;
	@JsonProperty("input_id")
	private String inputId;
	@JsonProperty("output_id")
	private String outputId;
	@JsonProperty("job_configs")
	private SaharaJobConfig jobConfigs;
	@JsonProperty("job_id")
	private String jobId;
	@JsonProperty("tenant_id")
	private String tenantId;
	@JsonProperty("start_time")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date startTime;
	@JsonProperty("end_time")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date endTime;
	@JsonProperty("created_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdAt;
	@JsonProperty("updated_at")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date updatedAt;
	@JsonProperty("oozie_job_id")
	private String oozieJobId;
	@JsonProperty("return_code")
	private String returnCode;
	@JsonProperty("progress")
	private String progress;
	@JsonProperty("id")
	private String id;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJobIdForExecution() {
		return jobIdForExecute;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClusterId() {
		return clusterId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInputId() {
		return inputId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOutputId() {
		return outputId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobConfig getJobConfigs() {
		return jobConfigs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOozieJobId() {
		return oozieJobId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJobId() {
		return jobId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProgress() {
		return progress;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobExecutionInfo getInfo() {
		return info;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public HashMap<String, String> getDataSourceUrls() {
		return dataSourceUrls;
	}

	public String getEngineJobId() {
		return engineJobId;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonProperty(value="is_protected")   
	public Boolean isProtected() {
		return isProtected;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	@JsonProperty(value="is_public")   
	public Boolean isPublic() {
		return isPublic;
	}

	public static class JobExecutions extends ListResult<SaharaJobExecution> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("job_executions")
		private List<SaharaJobExecution> jobExecutions;

		public List<SaharaJobExecution> value() {
			return jobExecutions;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobExecutionBuilder toBuilder() {
		return new ConcreteJobExecutionBuilder(this);
	}

	/**
	 * @return the job execution Builder
	 */
	public static JobExecutionBuilder builder() {
		return new ConcreteJobExecutionBuilder();
	}

	public static class ConcreteJobExecutionBuilder implements JobExecutionBuilder {

		SaharaJobExecution m;

		ConcreteJobExecutionBuilder() {
			this(new SaharaJobExecution());
		}

		ConcreteJobExecutionBuilder(SaharaJobExecution m) {
			this.m = m;
		}

		@Override
		public JobExecution build() {
			return m;
		}

		@Override
		public JobExecutionBuilder from(JobExecution in) {
			m = (SaharaJobExecution) in;
			return this;
		}

		@Override
		public JobExecutionBuilder clusterId(String clusterId) {
			m.clusterId = clusterId;
			return this;
		}

		@Override
		public JobExecutionBuilder inputId(String inputId) {
			m.inputId = inputId;
			return this;
		}

		@Override
		public JobExecutionBuilder outputId(String outputId) {
			m.outputId = outputId;
			return this;
		}

		@Override
		public JobExecutionBuilder setJobConfig(JobConfig jobConfig) {
			m.jobConfigs = (SaharaJobConfig) jobConfig;
			return this;
		}

		@Override
		public JobExecutionBuilder jobId(String jobId) {
			m.jobIdForExecute = jobId;
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobExecutionBuilder isPublic(boolean isPublic) {
			m.isPublic = isPublic;
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobExecutionBuilder isProtect(boolean isProtected) {
			m.isProtected = isProtected;
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobExecutionBuilder engineJobId(String engineJobId) {
			m.engineJobId = engineJobId;
			return this;
		}

		/*
		 * {@inheritDoc}
		 */
		@Override
		public JobExecutionBuilder dataSourceUrls(HashMap<String, String> dataSourceUrls) {
			m.dataSourceUrls = dataSourceUrls;
			return this;
		}
	}

}
