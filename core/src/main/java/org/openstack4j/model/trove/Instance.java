package org.openstack4j.model.trove;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openstack4j.model.ModelEntity;

/**
 * Created by cp16net on 2/10/16.
 */
public interface Instance extends ModelEntity {
    public enum Status {
        ACTIVE,
        BLOCKED,
        BUILD,
        FAILED,
        REBOOT,
        RESIZE,
        BACKUP,
        SHUTDOWN,
        ERROR,
        RESTART_REQUIRED,
        PROMOTE,
        EJECT,
        UNRECOGNIZED;

        @JsonCreator
		public static Status forValue(String value) {
			if (value != null)
			{
				for (Status s : Status.values()) {
					if (s.name().equalsIgnoreCase(value))
						return s;
				}
			}
			return Status.UNRECOGNIZED;
		}

		@JsonValue
	    public String value() {
	        return name().toLowerCase();
	    }
    }
    /**
     * @return the identifier for the server
     */
    String getId();

    /**
     * @return the descriptive name for the server
     */
    String getName();

    /**
     * @return the flavor identifier
     */
    String getFlavorId();

    /**
     * @return the flavor to boot into
     */
    Flavor getFlavor();

    /**
     * @return the status
     */
    Status getStatus();

    /**
     * @return the UUID for this server
     */
    String getUuid();
}
