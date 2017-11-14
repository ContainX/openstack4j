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

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.map.reduce.options.JobExeListOptions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExe;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExe.JobExes;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobExeCreate;

/**
 *  * The implementation of manipulation of {@link MapReduceJobExe}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class JobExeServiceImpl extends BaseMapReduceServices implements RestService {

	/**
	 * Backward compatibility, create a job and execute it
	 * @param jobExecution
	 * @return
	 */
	public MapReduceJobExe create(MapReduceJobExeCreate jobExeCreate) {
		checkNotNull(jobExeCreate);
		return post(MapReduceJobExe.class, uri("/jobs/submit-job")).entity(jobExeCreate).execute();
	}

	/**
	 * 
	 * list job exe with filter options
	 * 
	 * @param options filter options
	 * @return
	 */
	public List<? extends MapReduceJobExe> list(JobExeListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(JobExes.class, uri("/job-exes")).params(params).execute().getList();
	}

	/**
	 * 
	 * get job exe details
	 * 
	 * @param jobExeId the id of job exe
	 * @return
	 */
	public MapReduceJobExe get(String jobExeId) {
		checkNotNull(jobExeId);
		return get(MapReduceJobExe.class, uri("/job-exes/%s", jobExeId)).execute();
	}

}
