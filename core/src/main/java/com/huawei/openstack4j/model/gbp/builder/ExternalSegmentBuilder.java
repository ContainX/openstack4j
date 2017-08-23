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
package com.huawei.openstack4j.model.gbp.builder;

import java.util.List;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.gbp.ExternalRoutes;
import com.huawei.openstack4j.model.gbp.ExternalSegment;
/**
 * A builder which produces a External Segments object
 * 
 * @author vinod borole
 */
public interface ExternalSegmentBuilder extends Builder<ExternalSegmentBuilder, ExternalSegment> {

    ExternalSegmentBuilder name(String name);
    ExternalSegmentBuilder description(String description);
    ExternalSegmentBuilder externalPolicies(List<String> extPolicyIds);
    ExternalSegmentBuilder ipVersion(int ipVersion);
    ExternalSegmentBuilder cidr(String cidr);
    ExternalSegmentBuilder isShared(boolean shared);
    ExternalSegmentBuilder externalRoutes(List<ExternalRoutes> extRoutes);
    ExternalSegmentBuilder subnetId(String subnetId);
    ExternalSegmentBuilder isPortAddressTranslation(boolean isPortAddressTranslation);
}   
