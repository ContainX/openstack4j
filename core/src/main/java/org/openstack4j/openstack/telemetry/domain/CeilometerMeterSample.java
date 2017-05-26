package org.openstack4j.openstack.telemetry.domain;

import java.util.Map;

import org.openstack4j.model.telemetry.Meter.Type;
import org.openstack4j.model.telemetry.MeterSample;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * A single measurement for a given meter and resource.
 *
 * @author Jeremy Unruh
 */
public class CeilometerMeterSample implements MeterSample {

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

	private String timestamp;

	@JsonProperty("message_id")
	private String messageId;

	@JsonProperty("resource_metadata")
	private Map<String, Object> metadata;

	@JsonProperty("recorded_at")
	private String recordedAt;


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
  public String getRecordedAt() {
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
	public String getTimestamp() {
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
		return MoreObjects.toStringHelper(this).omitNullValues()
				    .add("name", counterName).add("type", counterType).add("unit", counterUnit)
				    .add("volume", counterVolume).add("timestamp", timestamp).add("source", source)
				    .add("project", projectId).add("user", userId).add("resource", resourceId)
				    .add("message", messageId).addValue("\n").add("metadata", metadata)
				    .add("recorded_at", recordedAt)
				    .toString();
	}

	@Override
	public void setCounterName(String counterName) {
		this.counterName = counterName;

	}

	@Override
	public void setCounterType(Type meterType) {
		this.counterType = meterType;
	}

	@Override
	public void setCounterUnit(String counterUnit) {
		this.counterUnit = counterUnit;
	}

	@Override
	public void setCounterVolume(Float counterVolume) {
		this.counterVolume = counterVolume;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public void setRecordedAt(String recordedAt) {
		this.recordedAt = recordedAt;
	}

	@Override
	public void setMessageId(String messageId) {
		this.messageId = messageId;

	}

	@Override
	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}
}
