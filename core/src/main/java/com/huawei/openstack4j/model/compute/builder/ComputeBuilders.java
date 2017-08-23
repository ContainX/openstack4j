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
package com.huawei.openstack4j.model.compute.builder;

import com.huawei.openstack4j.openstack.compute.domain.*;

/**
 * The Compute Builders
 */
public interface ComputeBuilders {

    /**
     * The builder to create a Server
     *
     * @return the server create builder
     */
    public ServerCreateBuilder server();

    /**
     * The builder to create a Block Device Mapping
     *
     * @return the block device mapping builder
     */
    public BlockDeviceMappingBuilder blockDeviceMapping();

    /**
     * The builder to create a Flavor.
     *
     * @return the flavor builder
     */
    public FlavorBuilder flavor();

    /**
     * The builder to create a Compute/Nova Floating IP
     *
     * @return the floating ip builder
     */
    public FloatingIPBuilder floatingIP();

    /**
     * This builder which creates a QuotaSet for updates
     *
     * @return the QuotaSet update builder
     */
    public QuotaSetUpdateBuilder quotaSet();

}
