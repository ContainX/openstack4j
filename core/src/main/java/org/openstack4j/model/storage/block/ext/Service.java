package org.openstack4j.model.storage.block.ext;

import java.util.Date;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A Service represents a Block storage service
 *
 * @author Stephan Latour
 */
public interface Service extends ModelEntity {
    /**
     * The status of a Block storage service entity
     */
    public enum Status {
        DISABLED, ENABLED, UNRECOGNIZED;

        @JsonCreator
        public static Status forValue(String value) {
            if (value != null) {
                for (Status s : Status.values()) {
                    if (s.name().equalsIgnoreCase(value)) {
                        return s;
                    }
                }
            }
            return Status.UNRECOGNIZED;
        }
    }

    /**
     * The state of a Block storage service entity
     */
    public enum State {
        DOWN, UNRECOGNIZED, UP;

        @JsonCreator
        public static State forValue(String value) {
            if (value != null) {
                for (State s : State.values()) {
                    if (s.name().equalsIgnoreCase(value)) {
                        return s;
                    }
                }
            }
            return State.UNRECOGNIZED;
        }
    }

    /**
     * @return the binary for this service
     */
    String getBinary();

    /**
     * @return the reason for disabled status of this service
     */
    String getDisabledReason();

    /**
     * @return the host for this service
     */
    String getHost();

    /**
     * @return the id for this service
     */
    String getId();

    /**
     * @return the status of the service
     */
    State getState();

    /**
     * @return the status of the service
     */
    Status getStatus();

    /**
     * 
     * @return last updated time
     */
    Date getUpdatedAt();

    /**
     * @return the zone for this service
     */
    String getZone();

}