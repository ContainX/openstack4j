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
package com.huawei.openstack4j.model.network;

import com.huawei.openstack4j.common.Buildable;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.builder.NetQuotaBuilder;

/**
 * Network quotas that are bound to a Tenant
 * 
 * @author Jeremy Unruh
 */
public interface NetQuota extends ModelEntity, Buildable<NetQuotaBuilder> {

    /**
     * Number of subnets allowed per tenant
     * 
     * @return number of subnets
     */
    int getSubnet();
    
    /**
     * Number of routers allowed per tenant
     * 
     * @return number of routers
     */
    int getRouter();
    
    /**
     * Number of ports allowed per tenant
     * 
     * @return number of ports
     */
    int getPort();
    
    /**
     * Number of networks allowed per tenant
     * 
     * @return number of networks
     */
    int getNetwork();
    
    /**
     * Number of floating IpAddresses allowed per tenant
     * 
     * @return number of float IpAddresses
     */
    int getFloatingIP();

    /**
     * Number of security groups per tenant
     *
     * @return number of security groups
     */
    int getSecurityGroup();

    /**
     * Number of security groups rules per security group per tenant
     *
     * @return number of security groups rules
     */
    int getSecurityGroupRule();
}
