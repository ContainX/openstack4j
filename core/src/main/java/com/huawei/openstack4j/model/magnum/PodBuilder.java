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
package com.huawei.openstack4j.model.magnum;

import java.util.List;

import com.huawei.openstack4j.common.Buildable.Builder;

public interface PodBuilder extends Builder<PodBuilder, Pod> {
    /**
     * @see Pod#getId
     */
    PodBuilder id(String id);

    /**
     * @see Pod#getUuid
     */
    PodBuilder uuid(String uuid);

    /**
     * @see Pod#getName
     */
    PodBuilder name(String name);

    /**
     * @see Pod#getDesc
     */
    PodBuilder desc(String desc);

    /**
     * @see Pod#getBayUuid
     */
    PodBuilder bayUuid(String bayUuid);

    /**
     * @see Pod#getImages
     */
    PodBuilder images(List<String> images);

    /**
     * @see Pod#getLabels
     */
    PodBuilder labels(Label labels);

    /**
     * @see Pod#getStatus
     */
    PodBuilder status(String status);

}
