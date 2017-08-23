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
 * Options used to update a share snapshot.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public class ShareSnapshotUpdateOptions {
    private String displayName;
    private String displayDescription;

    public static ShareSnapshotUpdateOptions create() {
        return new ShareSnapshotUpdateOptions();
    }

    /**
     * Update the share snapshot name.
     *
     * @param displayName the share snapshot name
     * @return ShareSnapshotUpdateOptions
     */
    public ShareSnapshotUpdateOptions displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Update the share snapshot description.
     *
     * @param displayDescription the share snapshot description
     * @return ShareSnapshotUpdateOptions
     */
    public ShareSnapshotUpdateOptions displayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
        return this;
    }

    /**
     * @return the share snapshot name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return the share snapshot description.
     */
    public String getDisplayDescription() {
        return displayDescription;
    }
}
