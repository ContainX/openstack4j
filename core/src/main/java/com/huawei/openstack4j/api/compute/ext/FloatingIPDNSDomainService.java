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
package com.huawei.openstack4j.api.compute.ext;

import java.util.List;

import com.huawei.openstack4j.common.RestService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.ext.DomainEntry;

/**
 * Service that handles domain names for the floating IP DNS Extension
 * 
 * @author Jeremy Unruh
 */
public interface FloatingIPDNSDomainService extends RestService {

    /**
     * Return the list of available DNS domains
     * 
     * @return list of domain entries
     */
    List<? extends DomainEntry> list();
    
    /**
     * Delete the specified {@code domainName}
     * 
     * @param domainName the name of the domain
     * @return the action response
     */
    ActionResponse delete(String domainName);
    
    /**
     * Creates or Updates a PUBLIC domain name record
     * 
     * @param domainName the FQ Domain name
     * @param project the project name
     * @return the created/updated domain entry
     */
    DomainEntry createPublic(String domainName, String project);
    
    /**
     * Creates or Updates a PRIVATE domain name record
     * 
     * @param domainName the FQ Domain name
     * @param availabilityZone the availability zone
     * @return he created/updated domain entry
     */
    DomainEntry createPrivate(String domainName, String availabilityZone);
    
}
