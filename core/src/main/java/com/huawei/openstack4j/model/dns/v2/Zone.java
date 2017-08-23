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
package com.huawei.openstack4j.model.dns.v2;

import java.util.List;
import java.util.Map;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.dns.v2.builder.ZoneBuilder;
import com.huawei.openstack4j.openstack.dns.v2.domain.DesignateZone.Router;

/**
 * Zone model
 *
 * @see <a href="https://developer.openstack.org/api-ref/dns/">API reference</a>
 */
public interface Zone extends ModelEntity, Buildable<ZoneBuilder> {

    /**
     * @return the id of the zone
     */
    String getId();

    /**
     * @return id for the pool hosting this zone
     */
    String getPoolId();

    /**
     * @return id for the project that owns the resource
     */
    String getProjectId();

    /**
     * @return DNS Name for the zone
     */
    String getName();

    /**
     * @return e-mail for the zone. Used in SOA records for the zone
     */
    String getEmail();

    /**
     * @return TTL (time to Live) for the zone.
     */
    Integer getTTL();

    /**
     * @return current serial number for the zone
     */
    String getSerial();

    /**
     * @return status of the resource
     */
    Status getStatus();

    /**
     * @return description for this zone
     */
    String getDescription();

    /**
     * @return mandatory for secondary zones. The servers to slave from to get DNS information
     */
    List<String> getMasters();

    /**
     * @return type of zone.
     */
    ZoneType getType();

    /**
     * @return date / time when resource was created
     */
    String getCreatedAt();

    /**
     * @return date / time when resource last updated
     */
    String getUpdateAt();

    /**
     * @return links to the resource, and other related resources
     */
    Map<String, String> getLinks();

    /**
     * @return the recordset amount belongs to the zone
     */
    Integer getRecordsAmount();
    
    
    /**
     * 
     * @return the routers bound to the zone
     */
    List<Router> getRouters();

}
