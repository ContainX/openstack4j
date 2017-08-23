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
import com.huawei.openstack4j.model.compute.ext.DNSEntry;
import com.huawei.openstack4j.model.compute.ext.DNSRecordType;

/**
 * A Service which handles DNS Entries for the Floating IP DNS Extension
 * 
 * @author Jeremy Unruh
 */
public interface FloatingIPDNSEntryService extends RestService {

    /**
     * Return a list of entries for the given domain and IP Address
     * 
     * @param domain the FQ Domain name
     * @param ip the IP Address
     * @return List of DNS Entries
     */
    List<? extends DNSEntry> listByIP(String domain, String ip);
    
    /**
     * Return a list of entries for the given domain and name
     * 
     * @param domain the FQ Domain name
     * @param name DNS entry name assigned under a domain
     * @return List of DNS Entries
     */
    List<? extends DNSEntry> listByName(String domain, String name);
    
    /**
     * Creates or Updates a DNS Entry
     * 
     * @param domain the FQ Domain name 
     * @param name DNS entry name assigned under a domain
     * @param ip the IP Address associated with the current entry
     * @param type the DNS Record Type
     * @return the created or modified DNSEntry
     */
    DNSEntry create(String domain, String name, String ip, DNSRecordType type);
    
    /**
     * Modifies the IP Address for the specified domain and name
     * 
     * @param domain the FQ Domain name 
     * @param name DNS entry name assigned under a domain
     * @param ip the new IP Address
     * @return the modified DNSEntry
     */
    DNSEntry modifyIP(String domain, String name, String ip);
    
    /**
     * Deletes a specified DNS entry
     *  
     * @param domain the FQ Domain name 
     * @param name DNS entry name assigned under a domain
     * @return the action response
     */
    ActionResponse delete(String domain, String name);
}
