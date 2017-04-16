package org.openstack4j.openstack.compute.domain.actions;

import org.openstack4j.model.compute.Server.Status;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Resets the state of a server to a specified state.
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("os-resetState")
public class ResetStateAction implements ServerAction {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("state")
    private final String state;
    
    public ResetStateAction(Status state) {
		switch (state) {
		case ACTIVE:
			this.state = "active";
			break;
		case ERROR:
			this.state = "error";
			break;
		default:
			this.state = "active";
			break;
		}
    }
    
    public static ResetStateAction create(Status state) {
        return new ResetStateAction(state);
    }

    public String getState() {
        return state;
    }
}

