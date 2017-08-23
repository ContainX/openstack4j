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
package com.huawei.openstack4j.openstack.compute.builder;

import com.huawei.openstack4j.model.compute.builder.*;
import com.huawei.openstack4j.openstack.compute.domain.*;

/**
 * The Compute V3 Builders
 */
public class NovaBuilders implements ComputeBuilders {

    private ComputeBuilders NovaBuilders() {
        return this;
    }

    @Override
    public ServerCreateBuilder server() {
        return NovaServerCreate.builder();
    }

    @Override
    public BlockDeviceMappingBuilder blockDeviceMapping() {
        return NovaBlockDeviceMappingCreate.builder();
    }

    @Override
    public FlavorBuilder flavor() {
        return NovaFlavor.builder();
    }

    @Override
    public FloatingIPBuilder floatingIP() {
        return NovaFloatingIP.builder();
    }

    @Override
    public QuotaSetUpdateBuilder quotaSet() {
        return NovaQuotaSetUpdate.builder();
    }
}
