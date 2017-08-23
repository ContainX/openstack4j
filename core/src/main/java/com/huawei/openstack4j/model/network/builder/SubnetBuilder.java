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
import com.huawei.openstack4j.model.identity.v3.Tenant;
import com.huawei.openstack4j.model.network.IPVersionType;
import com.huawei.openstack4j.model.network.Ipv6AddressMode;
import com.huawei.openstack4j.model.network.Ipv6RaMode;
import com.huawei.openstack4j.model.network.Network;
import com.huawei.openstack4j.model.network.Subnet;

/**
 * A Builder which creates a Subnet
 * 
 * @author Jeremy Unruh
 */
public interface SubnetBuilder extends Builder<SubnetBuilder, Subnet> {

    /**
     * @see Subnet#getName()
     */
    SubnetBuilder name(String name);

    /**
     * @see Subnet#getNetworkId()
     */
    SubnetBuilder networkId(String networkId);

    /**
     * @see Subnet#getNetworkId()
     */
    SubnetBuilder network(Network network);

    /**
     * @see Subnet#getIpVersion()
     */
    SubnetBuilder ipVersion(IPVersionType ipVersion);

    /**
     * @see Subnet#getCidr()
     */
    SubnetBuilder cidr(String cidr);

    /**
     * Adds a allocation pool
     *
     * @param start the starting IP
     * @param end the ending IP
     * @return the builder
     */
    SubnetBuilder addPool(String start, String end);

    /**
     * @see Subnet#getTenantId()
     */
    SubnetBuilder tenantId(String tenantId);

    /**
     * @see Subnet#getTenantId()
     */
    SubnetBuilder tenant(Tenant tenant);

    /**
     * @see Subnet#isDHCPEnabled()
     */
    SubnetBuilder enableDHCP(boolean enable);

    /**
     * @see Subnet#gateway()
     */
    SubnetBuilder gateway(String gateway);

    /**
     * @see Subnet#isNoGateway()
     */
    SubnetBuilder noGateway();

    /**
     * @see Subnet#getDnsNames()
     */
	SubnetBuilder addDNSNameServer(String host);
	
	/**
	 * Adds a host route to this subnet 
	 * 
	 * @param destination the destination subnet (ex: 10.0.0.0/16)
	 * @param nexthop the next gateway ip adddress (ex: 192.168.0.1)
	 * @returnSubnetBuilder
	 */
	SubnetBuilder addHostRoute(String destination, String nexthop);
	
    /**
     * @see Subnet#getIpv6AddressMode()
     */
	SubnetBuilder ipv6AddressMode (Ipv6AddressMode ipv6AddressMode);
	
    /**
     * @see Subnet#getIpv6RaMode()
     */
	SubnetBuilder ipv6RaMode(Ipv6RaMode ipv6RaMode);
}
