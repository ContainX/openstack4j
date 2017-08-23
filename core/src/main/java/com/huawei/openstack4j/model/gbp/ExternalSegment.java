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
package com.huawei.openstack4j.model.gbp;

import java.util.List;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.common.Resource;
import com.huawei.openstack4j.model.gbp.builder.ExternalSegmentBuilder;
import com.huawei.openstack4j.openstack.gbp.domain.GbpExternalRoutes;
/**
 * External Segment Model Entity
 * 
 * @author vinod borole
 */
public interface ExternalSegment extends Resource, Buildable<ExternalSegmentBuilder> {

    /**
     * Gets the external policies
     *
     * @return the external policies
     */
    List<String> getExternalPolicies();

    /**
     * Gets the Ip version
     *
     * @return the Ip version
     */
    int getIpVersion();

    /**
     * Gets the cidr
     *
     * @return the cidr
     */
    String getCidr();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

    /**
     * Is external segment shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the subnet Id
     *
     * @return the subnet Id
     */
    String getSubnetId();

    /**
     * Gets the L3 policies
     *
     * @return the L3 Policies
     */
    List<String> getL3Policies();

    /**
     * Gets Is Port address Transalation
     *
     * @return True or False
     */
    boolean isPortAddressTranslation();

    /**
     * Gets the list of external routes
     *
     * @return the list of external routes
     */
    List<GbpExternalRoutes> getExternalRoutes();

    /**
     * Gets the list of nat pools
     *
     * @return the list of nat pools
     */
    List<String> getNatpools();

}
 