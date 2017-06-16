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
package org.openstack4j.api.dns.v2;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.Nameserver;
import org.openstack4j.model.dns.v2.Zone;

import java.util.List;
import java.util.Map;

/**
 * Designate V2 Zone Service
 *
 */
public interface ZoneService extends RestService {

    /**
     * create a new zone
     *
     * @param zone the zone
     * @return the newly created zone
     */
    Zone create(Zone zone);

    /**
     * creates a new zone
     *
     * @param name the zone name
     * @param email the e-mail for the zone
     *
     * @return the newly created group
     */
    Zone create(String name, String email);

    /**
     * gets detailed information about a specified zone by id
     *
     * @param zoneId the zone identifier
     * @return the zone
     */
    Zone get(String zoneId);

    /**
     * updates an existing zone
     *
     * @param zone the zone set to update
     * @return the updated zone
     */
    Zone update(Zone zone);

    /**
     * delete a zone by id
     *
     * @param zoneId the zone id
     * @return the action response
     */
    ActionResponse delete(String zoneId);

    /**
     * list nameservers for a zone
     *
     * @param zoneId the zone identifier
     * @return list of nameservers for a zone
     */
    List<? extends Nameserver> listNameservers(String zoneId);

    /**
     * lists zones.
     *
     * @return list of zones
     */
    List<? extends Zone> list();

    /**
     * lists zones.
     * @param filters
     * @return
     */
    List<? extends Zone> list(Map<String, Object> filters);

}
