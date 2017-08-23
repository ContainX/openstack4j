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
package com.huawei.openstack4j.openstack.compute.domain.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.compute.actions.LiveMigrateOptions;

/**
 * Live Migration Action for a Server
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("os-migrateLive")
public class LiveMigrationAction implements ServerAction {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String host;
    
    @JsonProperty("block_migration")
    private boolean blockMigration;
    
    @JsonProperty("disk_over_commit")
    private boolean diskOverCommit;
    
    public static LiveMigrationAction create(LiveMigrateOptions options) {
        LiveMigrationAction action = new LiveMigrationAction();
        action.host = options.getHost();
        action.blockMigration = options.getBlockMigration();
        action.diskOverCommit = options.getDiskOverCommit();
        return action;
    }

    public String getHost() {
        return host;
    }

    @JsonIgnore
    public boolean isBlockMigration() {
        return blockMigration;
    }

    @JsonIgnore
    public boolean isDiskOverCommit() {
        return diskOverCommit;
    }
    
    @Override
    public String toString() {
        return "os-migrateLive";
    }
    
}
