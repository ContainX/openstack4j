package org.openstack4j.openstack.compute.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openstack4j.model.common.Link;
import org.openstack4j.model.compute.Image;
import org.openstack4j.openstack.common.GenericLink;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.base.Objects;

/**
 * An OpenStack image is a collection of files used to create a Server.  Users provide pre-built OS images by default and or custom
 * images can be built 
 *  
 * @author Jeremy Unruh
 */
@JsonRootName("image")
public class NovaImage implements Image {

	private static final long serialVersionUID = 1L;

  private String id;
	private Status status;
	private String name;
	private int progress;
	private int minRam;
	private int minDisk;
	private Date created;
	private Date updated;
	@JsonProperty("OS-EXT-IMG-SIZE:size")
	private long size;
	private List<GenericLink> links;
	@JsonProperty("metadata")
	private Map<String, String> metadata;

	public NovaImage() {
	}

	public NovaImage(String empty) {
		if (!"".equals(empty)) {
			throw new IllegalArgumentException();
		}
	}

        
	
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
	public long getSize() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinDisk() {
		return minDisk;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinRam() {
		return minRam;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getProgress() {
		return progress;
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
	public Date getCreated() {
		return created;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getUpdated() {
		return updated;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends Link> getLinks() {
		return links;
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
						.add("id", id).add("name", name).add("status", status)
						.add("progress", progress).add("size", size).add("minRam", minRam)
						.add("minDisk", minDisk).add("created", created).add("updated", updated)
						.add("metadata", metadata).add("links", links).addValue("\n")
						.toString();
	}
	
	public static class NovaImages extends ListResult<NovaImage> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("images")
		private List<NovaImage> images;
		
		public List<NovaImage> value() {
			return images;
		}
		
	}

}
