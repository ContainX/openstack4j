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
package com.huawei.openstack4j.openstack.map.reduce.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.map.reduce.JobExecutionService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.JobExecution;
import com.huawei.openstack4j.model.map.reduce.options.JobExecutionListOptions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExecution;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExecution.JobExecutions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExecutionUnwrapped;

/**
 *  * The implementation of manipulation of {@link JobExecution}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class JobExecutionServiceImpl extends BaseMapReduceServices implements JobExecutionService {

	@Override
	public JobExecution create(JobExecution jobExecution) {
		checkNotNull(jobExecution);
		MapReduceJobExecutionUnwrapped unwrapped = new MapReduceJobExecutionUnwrapped(jobExecution);
		return post(MapReduceJobExecution.class, uri("/jobs/%s/execute", jobExecution.getJobIdForExecution()))
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
		return get(MapReduceJobExecution.class, uri("/job-executions/%s", jobExecutionId)).execute();
	}

	@Override
	public JobExecution refreshStatus(String jobExecutionId) {
		checkNotNull(jobExecutionId);
		return get(MapReduceJobExecution.class, uri("/job-executions/%s/refresh-status", jobExecutionId)).execute();
	}

	@Override
	public JobExecution cancel(String jobExecutionId) {
		checkNotNull(jobExecutionId);
		return get(MapReduceJobExecution.class, uri("/job-executions/%s/cancel", jobExecutionId)).execute();
	}

	@Override
	public ActionResponse delete(String jobExecutionId) {
		checkNotNull(jobExecutionId);
		return deleteWithResponse(uri("/job-executions/%s", jobExecutionId)).execute();
	}
}
