package org.openstack4j.openstack.storage.block.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.base.Objects;

/**
 * An OpenStack Volume
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("volume")
public class CinderVolume implements Volume {

	private static final long serialVersionUID = 1L;
	
	private String id;
	@JsonProperty("display_name")
	private String name;
	@JsonProperty("display_description")
	private String description;
	private Status status;
	@JsonProperty("size")
	private Integer size;
	@JsonProperty("availability_zone")
	private String zone;
	@JsonProperty("created_at")
	private Date created;
	@JsonProperty("volume_type")
	private String volumeType;
	@JsonProperty("imageRef")
	private String imageRef;
	@JsonProperty("source_volid")
	private String sourceVolid;
	@JsonProperty("snapshot_id")
	private String snapshotId;
	private Map<String, String> metadata;
	@JsonProperty("bootable")
	private Boolean bootable;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return (size == null) ? 0 : size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getZone() {
		return zone;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreated() {
		return created;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getVolumeType() {
		return volumeType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSnapshotId() {
		return snapshotId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getImageRef() {
		return imageRef;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSourceVolid() {
		return sourceVolid;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> getMetaData() {
		return metadata;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				     .add("id", id).add("name", name).add("description", description)
				     .add("status", status).add("size", size).add("zone", zone).add("created", created)
				     .add("volumeType", volumeType).add("imageRef", imageRef)
				     .add("sourceVolid", sourceVolid).add("snapshotId", snapshotId).add("metadata", metadata)
				     .add("bootable", bootable)
				     .toString();
	}
	
	public static class Volumes extends ListResult<CinderVolume> {

		private static final long serialVersionUID = 1L;
		
		@JsonProperty("volumes")
		private List<CinderVolume> volumes;
		
		@Override
		protected List<CinderVolume> value() {
			return volumes;
		}
		
	}
}
