package org.openstack4j.openstack.compute.domain.actions;

import org.openstack4j.model.compute.actions.BackupOptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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
