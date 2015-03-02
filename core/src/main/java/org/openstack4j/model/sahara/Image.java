package org.openstack4j.model.sahara;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * A Sahara image
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface Image extends ModelEntity {

        /**
         * Status can be used while an image is being saved.  It provides state of the progress indicator.  Images with ACTIVE status
         * are available for install.
         */
        enum Status {
                UNRECOGNIZED, UNKNOWN, ACTIVE, SAVING, ERROR, DELETED;

                @JsonCreator
                public static Status forValue(String value) {
                        if (value != null)
                        {
                                for (Status s : Status.values()) {
                                        if (s.name().equalsIgnoreCase(value))
                                                return s;
                                }
                        }
                        return Status.UNKNOWN;
                }
        }

	/**
	 * @return the status of this image
	 */
	Status getStatus();

	/**
	 * @return the username of this image
	 */
	String getUsername();

	/**
	 * @return the date the image was last updated
	 */
	Date getUpdated();
	
	/**
	 * @return the size in bytes
	 */
	long getSize();
	
	/**
	 * @return the descriptive name of the image
	 */
	String getName();
	
	/**
	 * @return the date the image was created
	 */
	Date getCreated();
	
	/**
	 * @return the tags associated with this image
	 */
	List<String> getTags();
	
	/**
	 * @return the minimum disk in bytes
	 */
	int getMinDisk();

	/**
	 * @return the progress of the image during upload or setup
	 */
	int getProgress();
	
	/**
	 * @return the minimum ram in bytes
	 */
	int getMinRam();
	
	/**
	 * @return extra metadata/specs associated with the image
	 */
	Map<String, Object> getMetaData();
	
	/**
	 * @return the identifier of this image
	 */
	String getId();
	
	/**
	 * @return the description of this image
	 */
	String getDescription();
	
}
