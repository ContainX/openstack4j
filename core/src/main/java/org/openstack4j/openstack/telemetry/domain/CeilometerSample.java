package org.openstack4j.openstack.telemetry.domain;

import java.util.Date;
import java.util.Map;

import org.openstack4j.model.telemetry.Meter.Type;
import org.openstack4j.model.telemetry.Sample;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * A single measurement for a given meter and resource.
 * 
 * @author Jeremy Unruh
 */
public class CeilometerSample implements Sample {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("counter_name")
	private String counterName;
	
	@JsonProperty("counter_type")
	private Type counterType;
	
	@JsonProperty("counter_unit")
	private String counterUnit;
	
	@JsonProperty("counter_volume")
	private Float counterVolume;
	
	private String source;
	
	@JsonProperty("project_id")
	private String projectId;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("resource_id")
	private String resourceId;
	
	private Date timestamp;
	
	@JsonProperty("message_id")
	private String messageId;
	
	@JsonProperty("resource_metadata")
	private Map<String, Object> metadata;
	
	@JsonProperty("recorded_at")
	private Date recordedAt;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCounterName() {
		return counterName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getCounterType() {
		return counterType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCounterUnit() {
		return counterUnit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Float getCounterVolume() {
		return counterVolume;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSource() {
		return source;
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
	public String getUserId() {
		return userId;
	}
	
  /**
   * {@inheritDoc}
   */
  @Override
  public Date getRecordedAt() {
    return recordedAt;
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
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessageId() {
		return messageId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> getMetadata() {
		return metadata;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				    .add("name", counterName).add("type", counterType).add("unit", counterUnit)
				    .add("volume", counterVolume).add("timestamp", timestamp).add("source", source)
				    .add("project", projectId).add("user", userId).add("resource", resourceId)
				    .add("message", messageId).addValue("\n").add("metadata", metadata)
				    .add("recorded_at", recordedAt)
				    .toString();
	}

}
