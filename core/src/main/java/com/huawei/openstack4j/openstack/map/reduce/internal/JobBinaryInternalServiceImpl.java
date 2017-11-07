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

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.huawei.openstack4j.api.map.reduce.JobBinaryInternalService;
import com.huawei.openstack4j.core.transport.HttpEntityHandler;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.common.Payload;
import com.huawei.openstack4j.model.common.Payloads;
import com.huawei.openstack4j.model.map.reduce.JobBinaryInternal;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinaryInternal;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceJobBinaryInternal.JobBinaryInternals;

/**
 *  * The implementation of manipulation of {@link JobBinaryInternal}
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class JobBinaryInternalServiceImpl extends BaseMapReduceServices implements JobBinaryInternalService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends JobBinaryInternal> list() {
        return get(JobBinaryInternals.class, uri("/job-binary-internals")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobBinaryInternal get(String jobBinaryInternalId) {
        checkNotNull(jobBinaryInternalId);
        return get(MapReduceJobBinaryInternal.class, uri("/job-binary-internals/%s", jobBinaryInternalId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String jobBinaryInternalId) {
        checkNotNull(jobBinaryInternalId);
        return deleteWithResponse(uri("/job-binary-internals/%s", jobBinaryInternalId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobBinaryInternal create(Payload<File> payload) {
        checkNotNull(payload);
        return put(MapReduceJobBinaryInternal.class, uri("/job-binary-internals/%s", payload.getRaw().getName()))
                     .entity(payload)
                     .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Payload<InputStream> getData(String jobBinaryInternalId) {
        HttpResponse response = get(Void.class, uri("/job-binary-internals/%s/data", jobBinaryInternalId)).executeWithResponse();
        try
        {
            if (response.getStatus() < 400)
                return Payloads.create(response.getInputStream());
            return null;
        }
        finally {
            HttpEntityHandler.closeQuietly(response);
        }
    }

}
