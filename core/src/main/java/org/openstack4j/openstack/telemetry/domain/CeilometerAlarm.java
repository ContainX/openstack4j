package org.openstack4j.openstack.telemetry.domain;

import java.util.List;

import org.openstack4j.model.telemetry.Alarm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * An Alarm is triggered when a specificied rule is satisfied
 * 
 * @author Massimiliano Romano
 */
public class CeilometerAlarm implements Alarm {

	private static final long serialVersionUID = 1L;

	
	
	@JsonProperty("project_id")
	private String projectId;

	/**
	 * {@inheritDoc}
	 */
	
	@JsonProperty("name")
	private String name;


	@JsonProperty("type")
	private Type type;


	@JsonProperty("user_id")
	private String userId;


	@JsonProperty("alarm_actions")
	private List<String>alarmActions;


	@JsonProperty("alarm_id")
	private String alarmId;


	@JsonProperty("combination_rule")
	private String combinationRule;


	@JsonProperty("description")
	private String description;


	@JsonProperty("enabled")
	private boolean isEnabled;


	@JsonProperty("insufficient_data_actions")
	private List<String> insufficientDataActions;


	@JsonProperty("ok_actions")
	private List<String> okActions;


	@JsonProperty("repeat_actions")
	private boolean repeatActions;


	@JsonProperty("state")
	private String state;


	@JsonProperty("state_timestamp")
	private String stateTimestamp;


	@JsonProperty("threshold_rule")
	private String thresholdRule;


	@JsonProperty("timestamp")
	private String timestamp;


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
  public String getUserId() {
    return userId;
  }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				    .add("id", alarmId).add("name", name).add("enabled", isEnabled)
				    .add("project_id", projectId).add("type", type)
				    .add("user_id",  userId)
				    .toString();
	}

	@Override
	public List<String> getAlarmActions() {
		return alarmActions;
		
	}

	@Override
	public String getAlarmId() {
		return alarmId;
	}


	@Override
	public String getCombinationRule() {
		return combinationRule;
	}
	

	@Override
	public String description() {
		return description;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	@Override
	public void isEnabled(boolean newValue) {
		isEnabled = newValue;
	}


	@Override
	public List<String> getInsufficientDataActions() {
		return insufficientDataActions;
	}


	@Override
	public List<String> getOkActions() {
		return okActions;
	}


	@Override
	public boolean getRepeatActions() {
		return repeatActions;
	}


	@Override
	public String getState() {
		return state;
	}


	@Override
	public String getStateTimestamp() {
		return stateTimestamp;
	}


	@Override
	public String getThresholdRule() {
		return thresholdRule;
	}


	@Override
	public String getTimestamp() {
		return timestamp;
	}
}
