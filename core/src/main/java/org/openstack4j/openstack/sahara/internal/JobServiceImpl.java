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

import org.openstack4j.api.sahara.JobService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.Job;
import org.openstack4j.model.sahara.JobConfigHint;
import org.openstack4j.model.sahara.options.JobListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaJob;
import org.openstack4j.openstack.sahara.domain.SaharaJob.Jobs;
import org.openstack4j.openstack.sahara.domain.SaharaJobConfigHint;
import org.openstack4j.openstack.sahara.domain.SaharaJobUnwrapped;

import com.google.common.base.Strings;

/**
 * Sahara Data Processing Operations
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class JobServiceImpl extends BaseSaharaServices implements JobService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Job> list(JobListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(Jobs.class, uri("/jobs")).params(params).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Job get(String JobId) {
		checkNotNull(JobId);
		return get(SaharaJob.class, uri("/jobs/%s", JobId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Job create(Job job) {
		checkNotNull(job);
		checkState(!Strings.isNullOrEmpty(job.getName()), "job `name` attribute is required");
		checkNotNull(job.getType(), "job `type` attribute is required");
		SaharaJobUnwrapped unwrapped = new SaharaJobUnwrapped(job);
		return post(SaharaJob.class, uri("/jobs")).entity(unwrapped).execute();
	}
	
	/* 
	 * {@inheritDoc}
	 */
	@Override
	public Job update(Job job) {
		checkNotNull(job);
		checkNotNull(job.getId(), "job `id` attribute is required");
		checkNotNull(job.getDescription(), "job `description` attribute is required");
		SaharaJobUnwrapped unwrapped = new SaharaJobUnwrapped(job);
		return patch(SaharaJob.class, uri("/jobs/%s", job.getId())).entity(unwrapped).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String JobId) {
		checkNotNull(JobId);
		return deleteWithResponse(uri("/jobs/%s", JobId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobConfigHint getConfigHint(String type) {
		checkNotNull(type);
		return get(SaharaJobConfigHint.class, uri("/jobs/config-hints/%s", type)).execute();
	}


}
