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
package com.huawei.openstack4j.openstack.gbp.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.gbp.ExternalSegmentService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.gbp.ExternalSegment;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalSegment;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalSegment.ExternalSegments;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;
/**
 * External Segment API Implementation
 * 
 * @author vinod borole
 */
public class ExternalSegmentServiceImpl extends BaseNetworkingServices implements ExternalSegmentService {

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<? extends ExternalSegment> list() {
        return get(ExternalSegments.class, uri("/grouppolicy/external_segments")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public List<? extends ExternalSegment> list(Map<String, String> filteringParams) {
        Invocation<ExternalSegments> externalSegmentInvocation = buildInvocation(filteringParams);
        return externalSegmentInvocation.execute().getList();
    }
    private Invocation<ExternalSegments> buildInvocation(Map<String, String> filteringParams) {
        Invocation<ExternalSegments> externalSegmentInvocation = get(ExternalSegments.class, "/grouppolicy/external_segments");
        if (filteringParams == null) {
            return externalSegmentInvocation;
        } 
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                externalSegmentInvocation = externalSegmentInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return externalSegmentInvocation;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ExternalSegment get(String id) {
        checkNotNull(id);
        return get(GbpExternalSegment.class, uri("/grouppolicy/external_segments/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String id) {
        checkNotNull(id);
        return deleteWithResponse(uri("/grouppolicy/external_segments/%s", id)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExternalSegment create(ExternalSegment externalSegment) {
        return post(GbpExternalSegment.class, uri("/grouppolicy/external_segments")).entity(externalSegment).execute();
    }

    /**
     * {@inheritDoc}
     */
   @Override
    public ExternalSegment update(String externalSegmentId, ExternalSegment externalSegment) {
        checkNotNull(externalSegmentId);
        checkNotNull(externalSegment);
        return put(GbpExternalSegment.class, uri("/grouppolicy/external_segments/%s", externalSegmentId)).entity(externalSegment).execute();
    }

}
