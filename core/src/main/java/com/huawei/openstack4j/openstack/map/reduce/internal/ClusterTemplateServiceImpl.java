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

import com.huawei.openstack4j.api.map.reduce.ClusterTemplateService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.Cluster;
import com.huawei.openstack4j.model.map.reduce.ClusterTemplate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterTemplate;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterTemplate.ClusterTemplates;
import com.huawei.openstack4j.openstack.map.reduce.domain.MapReduceClusterTemplateUnwrapped;

/**
 * The implementation of manipulation of {@link Cluster}
 * 
 * @author Ekasit Kijsipongse
 */
public class ClusterTemplateServiceImpl extends BaseMapReduceServices implements ClusterTemplateService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ClusterTemplate> list() {
        return get(ClusterTemplates.class, uri("/cluster-templates")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClusterTemplate get(String templateId) {
        checkNotNull(templateId);
        return get(MapReduceClusterTemplate.class, uri("/cluster-templates/%s", templateId)).execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ClusterTemplate create(ClusterTemplate template) {
        checkNotNull(template);
        MapReduceClusterTemplateUnwrapped unwrapped = new MapReduceClusterTemplateUnwrapped(template);  
        return post(MapReduceClusterTemplate.class, uri("/cluster-templates"))
                     .entity(unwrapped)  // setup request
                     .execute();
       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String templateId) {
        checkNotNull(templateId);
        return deleteWithResponse(uri("/cluster-templates/%s", templateId)).execute();
    }

}
