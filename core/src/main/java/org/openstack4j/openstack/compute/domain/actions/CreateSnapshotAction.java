package org.openstack4j.openstack.compute.domain.actions;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * Create Snapshot action which creates a new Image snapshot from the present state of the server instance
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("createImage")
public class CreateSnapshotAction implements ServerAction {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String name;
    
    @JsonProperty
    private Map<String, String> metadata;
    
    public CreateSnapshotAction() { }
    
    public CreateSnapshotAction(String name) {
        this.name = name;
    }
    
    public static CreateSnapshotAction create(String name) {
        return new CreateSnapshotAction(name);
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
    
    
    
}
