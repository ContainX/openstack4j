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
 * Options used to invoke the Rebuild Action on a Server
 *
 * @author Jeremy Unruh
 */
public final class RebuildOptions extends BaseActionOptions {

    private enum Option implements OptionEnum {
        IMAGE("imageRef"),
        NAME("name"),
        ADMIN_PASS("adminPass")
        ;
        private final String param;
        private Option(String param) { this.param = param; }
        
        public String getParam() {
            return param;
        }
    }
    
    private RebuildOptions() { 
        super();
    }
    
    /**
     * @return a new RebuildOptions object
     */
    public static RebuildOptions create() {
        return new RebuildOptions();
    }
    
    /**
     * Can optionally set a new image to rebuild the server against.  This is the image identifier
     * 
     * @param imageId the image id to rebuild the server against
     * @return RebuildOptions
     */
    public RebuildOptions image(String imageId) {
        add(Option.IMAGE, imageId);
        return this;
    }
    
    /**
     * Can optionally change the name of the instance to a new {@code name}
     * 
     * @param name the new name of the server instance
     * @return RebuildOptions
     */
    public RebuildOptions name(String name) {
        add(Option.NAME, name);
        return this;
    }
    
    /**
     * Can optionally specify a new admin password to be used during the rebuild
     * 
     * @param adminPass the new admin password
     * @return RebuildOptions
     */
    public RebuildOptions adminPass(String adminPass) {
        add(Option.ADMIN_PASS, adminPass);
        return this;
    }
    
    public String getName() {
        return get(Option.NAME);
    }
    
    public String getAdminPass() {
        return get(Option.ADMIN_PASS);
    }
    
    public String getImageRef() {
        return get(Option.IMAGE);
    }
}
