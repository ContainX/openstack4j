package org.openstack4j.openstack.compute.domain.actions;

import org.openstack4j.model.compute.actions.RebuildOptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An Action which Rebuilds an existing Server Instance
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("rebuild")
public class RebuildAction implements ServerAction {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty
    private String imageRef;
    
    @JsonProperty
    private String name;
    
    @JsonProperty
    private String adminPass;
    
    public static RebuildAction create(RebuildOptions options) {
        RebuildAction action = new RebuildAction();
        
        if (options != null)
        {
            action.name = options.getName();
            action.adminPass = options.getAdminPass();
            action.imageRef = options.getImageRef();
        }
        return action;
    }

    public String getImageRef() {
        return imageRef;
    }

    public String getName() {
        return name;
    }

    public String getAdminPass() {
        return adminPass;
    }
    
}
