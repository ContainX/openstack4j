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

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.map.reduce.JobBinaryService;
import com.huawei.openstack4j.core.transport.HttpEntityHandler;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.common.Payloads;
import com.huawei.openstack4j.model.map.reduce.JobBinary;
import com.huawei.openstack4j.model.map.reduce.options.JobBinaryListOptions;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinary;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinary.JobBinaries;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinaryUnwrapped;

/**
 *  * The implementation of manipulation of {@link JobBinary}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */

public class JobBinaryServiceImpl extends BaseMapReduceServices implements JobBinaryService {

	/*
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends JobBinary> list(JobBinaryListOptions options) {
		Map<String, Object> params = (options == null) ? null : options.getOptions();
		return get(JobBinaries.class, uri("/job-binaries")).params(params).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobBinary get(String JobBinaryId) {
		checkNotNull(JobBinaryId);
		return get(MapReduceJobBinary.class, uri("/job-binaries/%s", JobBinaryId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JobBinary create(JobBinary jobBinary) {
		checkNotNull(jobBinary);
		MapReduceJobBinaryUnwrapped unwrapped = new MapReduceJobBinaryUnwrapped(jobBinary);
		return post(MapReduceJobBinary.class, uri("/job-binaries")).entity(unwrapped) // setup request
				.execute();
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public JobBinary update(JobBinary jobBinary) {
		checkNotNull(jobBinary);
		checkNotNull(jobBinary.getId(), "job-binary `id` attribute is required");
		checkNotNull(jobBinary.getURL(), "job-binary `URL` attribute is required");
		checkNotNull(jobBinary.getName(), "job-binary `name` attribute is required");
		MapReduceJobBinaryUnwrapped unwrapped = new MapReduceJobBinaryUnwrapped(jobBinary);
		return put(MapReduceJobBinary.class, uri("/job-binaries/%s", jobBinary.getId())).entity(unwrapped).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String JobBinaryId) {
		checkNotNull(JobBinaryId);
		return deleteWithResponse(uri("/job-binaries/%s", JobBinaryId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Payload<InputStream> getData(String JobBinaryId) {
		HttpResponse response = get(Void.class, uri("/job-binaries/%s/data", JobBinaryId)).executeWithResponse();
		try {
			if (response.getStatus() < 400)
				return Payloads.create(response.getInputStream());
			return null;
		} finally {
			HttpEntityHandler.closeQuietly(response);
		}
	}

}
