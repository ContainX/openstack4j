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
package com.huawei.openstack4j.model.network.options;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Provides server-side filtering options for listing ports
 * 
 * @author Jeremy Unruh
 *
 */
public class PortListOptions {
    
    private Map<String,String> queryParams = Maps.newHashMap();

    private PortListOptions() { }
    
    public static PortListOptions create() {
        return new PortListOptions();
    }
    
    /**
     * The ID of the entity that uses this port. For example, a DHCP agent.
     * 
     * @param deviceOwner the device owner
     * @return options
     */
    public PortListOptions deviceOwner(String deviceOwner) {
        return add("device_owner", deviceOwner);
    }
    
    /**
     * The ID of the device that uses this port. For example, a virtual server.
     * 
     * @param deviceId the device id
     * @return options
     */
    public PortListOptions deviceId(String deviceId) {
        return add("device_id", deviceId);
    }
    
    /**
     * The ID of the attached network
     * 
     * @param networkId the network id
     * @return options
     */
    public PortListOptions networkId(String networkId) {
        return add("network_id", networkId);
    }
    
    /**
     * The administrative state of the router, which is up (true) or down (false)
     * 
     * @param adminState the admin state
     * @return options
     */
    public PortListOptions adminState(boolean adminState) {
        return add("admin_state", String.valueOf(adminState));
    }
    
    /**
     * DEPRECATED: This option is not valid anymore since
     * neutron port does not have attribute named "display_name".
     * Use {@link #name(String)} to filter ports by name
     *
     * @param displayName the port display name
     * @return options
     */
    @Deprecated
    public PortListOptions displayName(String displayName) {
        return add("display_name", displayName);
    }
    
    /**
     * The port name
     *
     * @param name the port name
     * @return options
     */
    public PortListOptions name(String name) {
        return add("name", name);
    }
    
    /**
     * The ID of the tenant who owns the network
     * 
     * @param tenantId the tenant id
     * @return options
     */
    public PortListOptions tenantId(String tenantId) {
        return add("tenant_id", tenantId);
    }
    
    /**
     * The MAC address of the port
     * 
     * @param macAddress the mac address
     * @return options
     */
    public PortListOptions macAddress(String macAddress) {
        return add("mac_address", macAddress);
    }
    
    private PortListOptions add(String param, String value) {
        if (value != null)
            this.queryParams.put(param, value);
        return this;
    }
    
    public Map<String, String> getOptions() {
        return queryParams;
    }
}
