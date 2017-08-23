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
package com.huawei.openstack4j.model.manila;

/**
 * Options used to update a share.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ShareUpdateOptions {
    private String displayName;
    private String displayDescription;
    private Boolean isPublic;

    public static ShareUpdateOptions create() {
        return new ShareUpdateOptions();
    }

    /**
     * Update the share name.
     *
     * @param displayName the share name
     * @return ShareUpdateOptions
     */
    public ShareUpdateOptions displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Update the share description.
     *
     * @param displayDescription the share description
     * @return ShareUpdateOptions
     */
    public ShareUpdateOptions displayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
        return this;
    }

    /**
     * Update the level of visibility for the share.
     *
     * @param isPublic the level of visibility for the share
     * @return ShareUpdateOptions
     */
    public ShareUpdateOptions isPublic(Boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    /**
     * @return the share name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return the share description.
     */
    public String getDisplayDescription() {
        return displayDescription;
    }

    /**
     * @return the level of visibility for the share
     */
    public Boolean isPublic() {
        return isPublic;
    }
}
