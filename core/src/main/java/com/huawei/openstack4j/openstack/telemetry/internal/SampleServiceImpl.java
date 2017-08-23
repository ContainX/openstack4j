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
package com.huawei.openstack4j.openstack.telemetry.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.huawei.openstack4j.api.telemetry.SampleService;
import com.huawei.openstack4j.model.telemetry.Sample;
import com.huawei.openstack4j.model.telemetry.SampleCriteria;
import com.huawei.openstack4j.openstack.telemetry.domain.CeiloMeterSample;

/**
 * Provides Measurements for Samples within an OpenStack deployment
 * 
 * @author Shital Patil
 */

public class SampleServiceImpl extends BaseTelemetryServices implements SampleService {

    private static final String FIELD = "q.field";
    private static final String OPER = "q.op";
    private static final String VALUE = "q.value";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Sample> list() {
        CeiloMeterSample[] samples = get(CeiloMeterSample[].class, uri("/samples")).execute();
        return wrapList(samples);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Sample> list(SampleCriteria criteria) {
        Invocation<CeiloMeterSample[]> invocation = get(CeiloMeterSample[].class, uri("/samples"));
        if (criteria != null && !criteria.getCriteriaParams().isEmpty()) {
            for (SampleCriteria.NameOpValue c : criteria.getCriteriaParams()) {
                invocation.param(FIELD, c.getField());
                invocation.param(OPER, c.getOperator().getQueryValue());
                invocation.param(VALUE, c.getValue());
            }
        }
        CeiloMeterSample[] samples = invocation.execute();
        return wrapList(samples);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sample get(String sampleId) {
        checkNotNull(sampleId);

        CeiloMeterSample sample = get(CeiloMeterSample.class, uri("/samples/%s", sampleId)).execute();
        return sample;
    }

}
