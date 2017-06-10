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
package org.openstack4j.api.sahara;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.ClusterTemplate;

/**
 * Sahara Data Processing Operations
 * 
 * @author Ekasit Kijsipongse
 */
public interface ClusterTemplateService extends RestService {

    /**
     * List all cluster templates
     * 
     * @return list of cluster templates or empty
     */
     List<? extends ClusterTemplate> list();

    /**
     * Get a cluster template by ID
     * @param templateId the template identifier
     * @return the cluster template or null if not found
     */
     ClusterTemplate get(String templateId);

    /**
     * Create a new cluster template
     * 
     * @param template the cluster template to create
     * @return the created cluster template
     */
     ClusterTemplate create(ClusterTemplate template);

    /**
     * Delete the specified cluster template
     * 
     * @param templateId the template identifier
     * @return the action response
     */
     ActionResponse delete(String templateId);

}
