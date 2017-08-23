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
package com.huawei.openstack4j.openstack.compute.internal;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.huawei.openstack4j.api.compute.HostAggregateService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.HostAggregate;
import com.huawei.openstack4j.openstack.compute.domain.AggregateAddHost;
import com.huawei.openstack4j.openstack.compute.domain.AggregateRemoveHost;
import com.huawei.openstack4j.openstack.compute.domain.HostAggregateMetadata;
import com.huawei.openstack4j.openstack.compute.domain.NovaHostAggregate;
import com.huawei.openstack4j.openstack.compute.domain.NovaHostAggregateCreate;
import com.huawei.openstack4j.openstack.compute.domain.NovaHostAggregateUpdate;
import com.huawei.openstack4j.openstack.compute.domain.NovaHostAggregate.NovaHostAggregates;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
/**
 * Host Aggregate Operation API implementation
 * @author liujunpeng
 *
 */
public class HostAggregateServiceImpl extends BaseComputeServices implements
		HostAggregateService {

    /**
     * {@inheritDoc}
     */
	@Override
	public List<? extends HostAggregate> list() {
		Invocation<NovaHostAggregates> req = get(NovaHostAggregates.class, uri("/os-aggregates"));
		 return req.execute().getList();
		
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<? extends HostAggregate> list(Map<String, String> filteringParams) {
		Invocation<NovaHostAggregates> req = get(NovaHostAggregates.class, uri("/os-aggregates"));
		if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
            	req = req.param(entry.getKey(), entry.getValue());
            }
        }
		return req.execute().getList();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public NovaHostAggregate get(String aggregateId) {
		checkNotNull(aggregateId);
		return get(NovaHostAggregate.class,uri("/os-aggregates/%s",aggregateId)).execute();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public ActionResponse delete(String aggregateId) {
		checkNotNull(aggregateId);
		return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/os-aggregates/%s", aggregateId)).executeWithResponse()
           );
	}
    
	/**
     * {@inheritDoc}
     */
	@Override
	public HostAggregate create(String name,String availabilityZone) {
		checkNotNull(name);
		checkNotNull(availabilityZone);
		// modify by chenyan 2017.02.13
		//return post(NovaHostAggregate.class, uri("/os-aggregates")).entity(NovaHostAggregate.create(name, availabilityZone)).execute();
		return post(NovaHostAggregate.class, uri("/os-aggregates")).entity(new NovaHostAggregateCreate(name, availabilityZone)).execute();
	}
	/**
     * {@inheritDoc}
     */
	@Override
	public HostAggregate update(String hostAggregateId, String name,
			@Nullable String availabilityZone) {
		checkNotNull(hostAggregateId);
		checkNotNull(name);
		return put(NovaHostAggregate.class, uri("/os-aggregates/%s",hostAggregateId)).entity(new NovaHostAggregateUpdate(name, availabilityZone)).execute();
	}
	
    /**
     * {@inheritDoc}
     */
    @Override
    public HostAggregate setMetadata(String hostAggregateId, Map<String, String> metadata) {
        checkNotNull(hostAggregateId);
        checkNotNull(metadata);
        return post(NovaHostAggregate.class, uri("/os-aggregates/%s/action", hostAggregateId)).entity(new HostAggregateMetadata(metadata)).execute();
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	public HostAggregate addHost(String hostAggregateId, String host) {
		checkNotNull(hostAggregateId);
		checkNotNull(host);
		return post(NovaHostAggregate.class, uri("/os-aggregates/%s/action", hostAggregateId)).entity(new AggregateAddHost(host)).execute();
	}
    
	/**
     * {@inheritDoc}
     */
	@Override
	public HostAggregate removeHost(String hostAggregateId, String host) {
		checkNotNull(hostAggregateId);
		checkNotNull(host);
		
		return post(NovaHostAggregate.class, uri("/os-aggregates/%s/action", hostAggregateId)).entity(new AggregateRemoveHost(host)).execute();
	}
    
    
}
