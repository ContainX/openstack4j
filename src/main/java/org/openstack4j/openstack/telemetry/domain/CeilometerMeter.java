package org.openstack4j.openstack.telemetry.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openstack4j.model.telemetry.Meter;

import com.google.common.base.Objects;

/**
 * A Meter is a category of Measurement
 * 
 * @author Jeremy Unruh
 */
public class CeilometerMeter implements Meter {

	private static final long serialVersionUID = 1L;

	@JsonProperty("meter_id")
	private String id;
	private String name;
	@JsonProperty("resource_id")
	private String resourceId;
	@JsonProperty("project_id")
	private String projectId;
	private Type type;
	private String unit;
	
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
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProjectId() {
		return projectId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUnit() {
		return unit;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				    .add("id", id).add("name", name).add("resource_id", resourceId)
				    .add("project_id", projectId).add("type", type).add("unit", unit)
				    .toString();
	}
}
