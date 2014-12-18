package org.openstack4j.model.storage.block;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.storage.block.builder.VolumeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;

/**
 * An OpenStack Volume
 * 
 * @author Jeremy Unruh
 */
public interface Volume extends ModelEntity, Buildable<VolumeBuilder> {

	/**
	 * The current Volume Status
	 * 
	 */
	public enum Status {
		AVAILABLE, ATTACHING, BACKING_UP, CREATING, DELETING, DOWNLOADING, UPLOADING, ERROR, ERROR_DELETING, ERROR_RESTORING, IN_USE, RESTORING_BACKUP, UNRECOGNIZED;
		
		@JsonValue
		public String value() {
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
		}

		@Override
		public String toString() {
			return value();
		}

		@JsonCreator
		public static Status fromValue(String status) {
			try {
				return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, checkNotNull(status, "status")));
			} catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}
	}
	
	public enum MigrationStatus {
	    NONE, MIGRATING
	    ;
	    
	    @JsonValue
        public String value() {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
        }
	    
	    @Override
        public String toString() {
            return value();
        }

        @JsonCreator
        public static MigrationStatus fromValue(String migrationStatus) {
            if (migrationStatus != null)
            {
                try {
                    return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, checkNotNull(migrationStatus, "migrationStatus")));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
            return NONE;
        }
	}
	
	/**
	 * @return the identifier for the volume
	 */
	String getId();
	
	/**
	 * @return the name of the volume
	 */
	String getName();
	
	/**
	 * @return the description of the volume
	 */
	String getDescription();
	
	/**
	 * @return the status of the volume
	 */
	Status getStatus();
	
	/**
	 * @return the size in GB of the volume
	 */
	int getSize();
	
	/**
	 * @return the zone of availability to use
	 */
	String getZone();
	
	/**
	 * @return the created date of the volume
	 */
	Date getCreated();
	
	/**
	 * @return the type of volume
	 */
	String getVolumeType();
	
	/**
	 * @return the snapshot identifier
	 */
	String getSnapshotId();
	
	/**
	 * @return the image reference identifier (if an image was associated) otherwise null
	 */
	String getImageRef();
	
	/**
	 * @return ID of source volume to clone from
	 */
	String getSourceVolid();
	
	/**
	 * @return extended meta data information. key value pair of String key, String value
	 */
	Map<String, String> getMetaData();
	
	/**
	 * @return volume attachment data information. 
	 */
	List<? extends VolumeAttachment> getAttachments();

	/**
	 * @return the status of volume migrate status, default null
	 */
	MigrationStatus getMigrateStatus();
}
