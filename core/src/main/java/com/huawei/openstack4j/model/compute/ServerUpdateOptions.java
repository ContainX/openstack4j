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

/**
 * Options used to Update a Server instance
 * 
 * @author Jeremy Unruh
 */
public class ServerUpdateOptions {

    private String name;
    private String accessIPv4;
    private String accessIPv6;
    
    public static ServerUpdateOptions create() {
        return new ServerUpdateOptions();
    }

    /**
     * The name of the server. 
     * 
     * If you edit the server name, the server host name does not change. 
     * Also, server names are not guaranteed to be unique.
     * 
     * @param name the new name of the server
     * @return ServerUpdateOptions
     */
    public ServerUpdateOptions name(String name) {
        this.name = name;
        return this;
    }
    
    /**
     * The IP Version 4 Address
     * 
     * @param accessIPv4 the IP Version 4 Address
     * @return ServerUpdateOptions
     */
    public ServerUpdateOptions accessIPv4(String accessIPv4) {
        this.accessIPv4 = accessIPv4;
        return this;
    }
    
    /**
     * The IP Version 6 Address
     * 
     * @param accessIPv6 the IP Version 6 Address
     * @return ServerUpdateOptions
     */
    public ServerUpdateOptions accessIPv6(String accessIPv6) {
        this.accessIPv6 = accessIPv6;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getAccessIPv4() {
        return accessIPv4;
    }

    public String getAccessIPv6() {
        return accessIPv6;
    }
}
