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
import com.huawei.openstack4j.model.compute.actions.BackupOptions;

/**
 * Server Action which encapsulates a Backup
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("createBackup")
public class BackupAction implements ServerAction {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String name;
    @JsonProperty("backup_type")
    private String backupType;
    @JsonProperty
    private Integer rotation;
    
    
    public String getName() {
        return name;
    }
    
    @JsonIgnore
    public String getBackupType() {
        return backupType;
    }
    public int getRotation() {
        return rotation;
    }
    
    public static BackupAction create(BackupOptions options) {
        BackupAction action = new BackupAction();
        action.name = options.getName();
        action.backupType = options.getBackupType();
        action.rotation = options.getRotation();
        return action;
    }
    
    /*
     *  NAME("name"),
        BACKUP_TYPE("backup_type"),
        ROTATION("rotation")
     */
    
}
