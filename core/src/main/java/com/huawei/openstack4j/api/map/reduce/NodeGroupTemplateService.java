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
package com.huawei.openstack4j.api.map.reduce;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.map.reduce.NodeGroupTemplate;

/**
 * The manipulation of {@link NodeGroupTemplate}
 * 
 * @author Ekasit Kijsipongse
 */
public interface NodeGroupTemplateService extends RestService {

    /**
     * List all node group templates
     * 
     * @return list of node group templates or empty
     */
     List<? extends NodeGroupTemplate> list();

    /**
     * Get a node group template by ID
     * @param templateId the template identifier
     * @return the node group template or null if not found
     */
     NodeGroupTemplate get(String templateId);

    /**
     * Create a new node group template
     * 
     * @param template the node group template to create
     * @return the created node group template
     */
     NodeGroupTemplate create(NodeGroupTemplate template);

    /**
     * Delete the specified node group template
     * 
     * @param templateId the template identifier
     * @return the action response
     */
     ActionResponse delete(String templateId);

}
