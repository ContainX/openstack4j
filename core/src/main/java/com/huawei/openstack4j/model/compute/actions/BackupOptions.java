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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Options for Creating a Backup schedule
 * 
 * @author Jeremy Unruh
 */
public final class BackupOptions extends BaseActionOptions {
    
    private enum Option implements OptionEnum {
        NAME("name"),
        BACKUP_TYPE("backup_type"),
        ROTATION("rotation")
        ;
        private final String param;
        private Option(String param) { this.param = param; }
        
        public String getParam() {
            return param;
        }
    }

    private BackupOptions(String name) {
        add(Option.NAME, name);
    }
    
    /**
     * Create a new Backup schedule with the given {@code backupName}
     * 
     * @param backupName the name of the backup
     * @return BackupOptions
     */
    public static BackupOptions create(String backupName) {
        checkNotNull(backupName);
        return new BackupOptions(backupName);
    }
    
    /**
     * Indicates the backup will run daily keeping {@code rotation} copies
     * 
     * @param rotation the number of backups to maintain
     * @return BackupOptions
     */
    public BackupOptions daily(int rotation) {
        add(Option.BACKUP_TYPE, "daily");
        add(Option.ROTATION, rotation);
        return this;
    }
    
    /**
     * Indicates the backup will run weekly keeping {@code rotation} copies
     * 
     * @param rotation the number of backups to maintain
     * @return BackupOptions
     */
    public BackupOptions weekly(int rotation) {
        add(Option.BACKUP_TYPE, "weekly");
        add(Option.ROTATION, rotation);
        return this;
    }
    
    public String getName() {
        return get(Option.NAME);
    }
    
    public String getBackupType() {
        return get(Option.BACKUP_TYPE);
    }
    
    public Integer getRotation() {
        return get(Option.ROTATION);
    }
}
