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

import java.util.List;

import org.openstack4j.api.sahara.NodeGroupTemplateService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.NodeGroupTemplate;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroupTemplate;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroupTemplateUnwrapped;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroupTemplate.NodeGroupTemplates;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sahara Data Processing Operations
 * 
 * @author Ekasit Kijsipongse
 */
public class NodeGroupTemplateServiceImpl extends BaseSaharaServices implements NodeGroupTemplateService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NodeGroupTemplate> list() {
        return get(NodeGroupTemplates.class, uri("/node-group-templates")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeGroupTemplate get(String templateId) {
        checkNotNull(templateId);
        return get(SaharaNodeGroupTemplate.class, uri("/node-group-templates/%s", templateId)).execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public NodeGroupTemplate create(NodeGroupTemplate template) {
        checkNotNull(template);
        SaharaNodeGroupTemplateUnwrapped unwrapped = new SaharaNodeGroupTemplateUnwrapped(template);  
        return post(SaharaNodeGroupTemplate.class, uri("/node-group-templates"))
                     .entity(unwrapped)  // setup request
                     .execute();
       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String templateId) {
        checkNotNull(templateId);
        return deleteWithResponse(uri("/node-group-templates/%s", templateId)).execute();
    }

}
