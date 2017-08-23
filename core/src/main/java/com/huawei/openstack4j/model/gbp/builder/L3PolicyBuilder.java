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
import com.huawei.openstack4j.model.gbp.L3Policy;
/**
 * A builder which produces a L3Policies object
 * 
 * @author vinod borole
 */
public interface L3PolicyBuilder extends Builder<L3PolicyBuilder, L3Policy> {

    L3PolicyBuilder name(String name);
    L3PolicyBuilder description(String description);
    L3PolicyBuilder ipVersion(int ipVersion);
    L3PolicyBuilder ippool(String ippool);
    L3PolicyBuilder subnetPrefixLength(String subnetPrefixLength);
    L3PolicyBuilder isShared(boolean shared);
    L3PolicyBuilder externalSegments(List<String> extSegmentIds);
}
 