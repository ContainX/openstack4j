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

import org.openstack4j.common.RestService;
import org.openstack4j.model.sahara.options.JobExeListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe.JobExes;

/**
 * Sahara Data Processing Operations
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class JobExeServiceImpl extends BaseSaharaServices implements RestService {

	/**
	 * 
	 * list job exe with filter options
	 * 
	 * @param options filter options
	 * @return
	 */
	public List<? extends SaharaJobExe> list(JobExeListOptions options) {
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
	public SaharaJobExe get(String jobExeId) {
		checkNotNull(jobExeId);
		return get(SaharaJobExe.class, uri("/job-exes/%s", jobExeId)).execute();
	}

}
