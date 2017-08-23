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
package com.huawei.openstack4j.model.network.builder;

import com.huawei.openstack4j.common.Buildable.Builder;
import com.huawei.openstack4j.model.network.NetQuota;

/**
 * A Builder which creates a NetQuota entity
 * 
 * @author Jeremy Unruh
 */
public interface NetQuotaBuilder extends Builder<NetQuotaBuilder, NetQuota>{

    /**
     * See {@link NetQuota#getSubnet()} for details
     * 
     * @param subnet the max subnets allowed
     * @return NetQuotaBuilder
     */
    NetQuotaBuilder subnet(int subnet);
 
    /**
     * See {@link NetQuota#getRouter()} for details
     * 
     * @param router the max routers allowed
     * @return NetQuotaBuilder
     */
    NetQuotaBuilder router(int router);
    
    /**
     * See {@link NetQuota#getPort()} for details
     * 
     * @param port the max ports allowed
     * @return NetQuotaBuilder
     */
    NetQuotaBuilder port(int port);
    
    /**
     * See {@link NetQuota#getNetwork()} for details
     * 
     * @param network the max networks allowed
     * @return NetQuotaBuilder
     */
    NetQuotaBuilder network(int network);
    
    /**
     * See {@link NetQuota#getFloatingIP()} for details
     * 
     * @param floatingIP the max floating IPAddresses allowed
     * @return NetQuotaBuilder
     */
    NetQuotaBuilder floatingIP(int floatingIP);

    /**
     * See {@link NetQuota#getSecurityGroup()} for details
     *
     * @param securityGroup the max security group allowed
     * @return NetQuotaBuilder
     */
    NetQuotaBuilder securityGroup(int securityGroup);

    /**
     * See {@link NetQuota#getSecurityGroupRule()} for details
     *
     * @param securityGroupRule the max security group rules allowed
     * @return NetQuotaBuilder
     */
    NetQuotaBuilder securityGroupRule(int securityGroupRule);

}
