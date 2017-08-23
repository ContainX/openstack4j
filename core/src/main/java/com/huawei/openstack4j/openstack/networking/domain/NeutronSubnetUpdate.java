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
package com.huawei.openstack4j.openstack.networking.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.model.network.Subnet;

/**
 * Encapsulates the updateable view for a Subnet within Neutron
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("subnet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronSubnetUpdate implements ModelEntity {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty
    private String name;
    @JsonProperty("dns_nameservers")
    private List<String> dnsNames;
    @JsonProperty("allocation_pools")
    private List<NeutronPool> pools;
    @JsonProperty("host_routes")
    private List<NeutronHostRoute> hostRoutes;
    @JsonProperty("gateway_ip")
    private String gateway;
    @JsonProperty("enable_dhcp")
    private boolean enabledhcp;
    
    @SuppressWarnings("unchecked")
    public static NeutronSubnetUpdate createFromSubnet(Subnet in) {
        NeutronSubnetUpdate ns = new NeutronSubnetUpdate();
        ns.name = in.getName();
        ns.dnsNames = in.getDnsNames();
        ns.pools = (List<NeutronPool>) in.getAllocationPools();
        ns.hostRoutes = (List<NeutronHostRoute>) in.getHostRoutes();
        ns.gateway = in.getGateway();
        ns.enabledhcp = in.isDHCPEnabled();
        return ns;
    }

}
