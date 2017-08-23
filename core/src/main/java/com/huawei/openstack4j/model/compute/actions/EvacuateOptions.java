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
package com.huawei.openstack4j.model.compute.actions;

/**
 * Options to evacuates a server from a failed host to a new host
 * 
 */
public class EvacuateOptions extends BaseActionOptions {
    
    private enum Option implements OptionEnum {
        HOST("host"),        
        ADMIN_PASS("adminPass"),
        ON_SHARED_STORAGE("onSharedStorage");
        private final String param;
        private Option(String param) { this.param = param; }
        
        public String getParam() {
            return param;
        }
    }

    private EvacuateOptions() {
    	super();   
    	add(Option.HOST, null);
        add(Option.ADMIN_PASS, null);
        add(Option.ON_SHARED_STORAGE, false);            
    }
    
    public static EvacuateOptions create() {
        return new EvacuateOptions();
    }
    
    /**
     * Name of the the host (Optional)
     * 
     * @param name or ID of the host to which the server is evacuated
     * @return EvacuateOptions
     */
    public EvacuateOptions host(String host) {
        add(Option.HOST, host);
        return this;
    }
    
    /**
     * An administrative password (Optional)
     * 
     * @param adminPass to access the evacuated or rebuilt instance
     * @return EvacuateOptions
     */
    public EvacuateOptions adminPass(String adminPass) {
        add(Option.ADMIN_PASS, adminPass);
        return this;
    }
    
    /**
     * Server on shared storage
     * 
     * @param isShare if isShare, server on shared storage
     * @return EvacuateOptions
     */
    public EvacuateOptions onSharedStorage(boolean isShare) {
        add(Option.ON_SHARED_STORAGE, isShare);
        return this;
    }
    
    public String getHost() {
        return get(Option.HOST);
    }
    
    public String getAdminPass() {
        return get(Option.ADMIN_PASS);
    }
    
    public boolean isOnSharedStorage() {
        return get(Option.ON_SHARED_STORAGE);
    }
}
