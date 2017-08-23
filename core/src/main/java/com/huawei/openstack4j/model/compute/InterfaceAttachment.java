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
package com.huawei.openstack4j.model.compute;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

/**
 * Interface attachment model entity
 * 
 * @author Jeremy Unruh
 */
public interface InterfaceAttachment extends ModelEntity {
    
    /**
     * List of Fixed IpAddresses
     * 
     * @return list of fix addresses
     */
    List<? extends FixedIp> getFixedIps();
    
    /**
     * The interface MacAddress
     * 
     * @return the MacAddress for this Interface
     */
    String getMacAddr();
    
    /**
     * The network identifier
     * 
     * @return the network identifier associated to this interface
     */
    String getNetId();
    
    /**
     * The port aka interface identifier
     * 
     * @return the port/interface identifier
     */
    String getPortId();
    
    /**
     * The current port state
     * 
     * @return the port state
     */
    PortState getPortState();

    public interface FixedIp extends ModelEntity {
        
        /**
         * @return the IpAddress
         */
        String getIpAddress();
        
        /**
         * @return the subnet unique identifier
         */
        String getSubnetId();
    }
    
}
