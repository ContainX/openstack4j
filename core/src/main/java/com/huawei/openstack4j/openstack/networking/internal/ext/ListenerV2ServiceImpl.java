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
package com.huawei.openstack4j.openstack.networking.internal.ext;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.api.networking.ext.ListenerV2Service;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.network.ext.ListenerV2;
import com.huawei.openstack4j.model.network.ext.ListenerV2Update;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.networking.domain.ext.NeutronListenerV2;
import com.huawei.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Lbaas listener imlementation
 * @author emjburns
 */
public class ListenerV2ServiceImpl extends BaseNetworkingServices implements ListenerV2Service {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ListenerV2> list(){
        return get(NeutronListenerV2.Listeners.class, uri("/lbaas/listeners")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ListenerV2> list(Map<String, String> filteringParams){
        Invocation<NeutronListenerV2.Listeners> req = get(NeutronListenerV2.Listeners.class, uri("/lbaas/listeners"));
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
    public ListenerV2 get(String listenerId){
        checkNotNull(listenerId);
        return get(NeutronListenerV2.class, uri("/lbaas/listeners/%s",listenerId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String listenerId){
        checkNotNull(listenerId);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/lbaas/listeners/%s",listenerId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerV2 create(ListenerV2 listener){
        checkNotNull(listener);
        return post(NeutronListenerV2.class, uri("/lbaas/listeners")).entity(listener).execute();
    }

    @Override
    public ListenerV2 update(String listenerId, ListenerV2Update listener){
        checkNotNull(listenerId);
        checkNotNull(listener);
        return put(NeutronListenerV2.class, uri("/lbaas/listeners/%s",listenerId)).entity(listener).execute();
    }
}
