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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.manila.AbsoluteLimit;
import com.huawei.openstack4j.model.manila.Limits;
import com.huawei.openstack4j.model.manila.RateLimit;

import java.util.List;

/**
 * Limits are the resource limitations that are allowed for each tenant (project).
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("limits")
public class ManilaLimits implements Limits {
    private static final long serialVersionUID = 1L;

    private List<ManilaRateLimit> rate;
    private ManilaAbsoluteLimit absolute;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends RateLimit> getRate() {
        return rate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbsoluteLimit getAbsolute() {
        return absolute;
    }
}
