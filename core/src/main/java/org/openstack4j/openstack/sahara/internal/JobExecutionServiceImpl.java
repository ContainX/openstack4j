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
package org.openstack4j.openstack.sahara.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.sahara.JobExecutionService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.JobExecution;
import org.openstack4j.model.sahara.options.JobExecutionListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecution;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecution.JobExecutions;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecutionUnwrapped;

/**
 * Sahara Data Processing Operations
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class JobExecutionServiceImpl extends BaseSaharaServices implements JobExecutionService {

	@Override
	public JobExecution create(JobExecution jobExecution) {
		checkNotNull(jobExecution);
		SaharaJobExecutionUnwrapped unwrapped = new SaharaJobExecutionUnwrapped(jobExecution);
		return post(SaharaJobExecution.class, uri("/jobs/%s/execute", jobExecution.getJobIdForExecution()))
				.entity(unwrapped).execute();
	}

	@Override
	public List<? extends JobExecution> list(JobExecutionListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(JobExecutions.class, uri("/job-executions")).params(params).execute().getList();
	}

	@Override
	public JobExecution get(String jobExecutionId) {
		checkNotNull(jobExecutionId);
		return get(SaharaJobExecution.class, uri("/job-executions/%s", jobExecutionId)).execute();
	}

	@Override
	public JobExecution refreshStatus(String jobExecutionId) {
		checkNotNull(jobExecutionId);
		return get(SaharaJobExecution.class, uri("/job-executions/%s/refresh-status", jobExecutionId)).execute();
	}

	@Override
	public JobExecution cancel(String jobExecutionId) {
		checkNotNull(jobExecutionId);
		return get(SaharaJobExecution.class, uri("/job-executions/%s/cancel", jobExecutionId)).execute();
	}

	@Override
	public ActionResponse delete(String jobExecutionId) {
		checkNotNull(jobExecutionId);
		return deleteWithResponse(uri("/job-executions/%s", jobExecutionId)).execute();
	}
}
