package org.openstack4j.model.telemetry;

import java.util.List;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An Alarm is triggered when a specificied rule is satisfied
 * 
 * @author Massimiliano Romano
 */
public interface Alarm extends ModelEntity {

	/*
	
	{
    "alarm_actions": [
        "http://site:8000/alarm"
    ],
    "alarm_id": null,
    "combination_rule": null,
    "description": "An alarm",
    "enabled": true,
    "insufficient_data_actions": [
        "http://site:8000/nodata"
    ],
    "name": "SwiftObjectAlarm",
    "ok_actions": [
        "http://site:8000/ok"
    ],
    "project_id": "c96c887c216949acbdfbd8b494863567",
    "repeat_actions": false,
    "state": "ok",
    "state_timestamp": "2013-11-21T12:33:08.486228",
    "threshold_rule": null,
    "timestamp": "2013-11-21T12:33:08.486221",
    "type": "threshold",
    "user_id": "c96c887c216949acbdfbd8b494863567"
}
	
	*/
	
	List<String> getAlarmActions();
	String getAlarmId();
	
	String getCombinationRule();
	String description();
	boolean isEnabled();
	void isEnabled(boolean newValue);
	List<String> getInsufficientDataActions();
	/**
	 * @return the unique name of the meter
	 */
	String getName();
	List<String> getOkActions();
	/**
	 * @return the ID of the project/tenant that owns the resource
	 */
	String getProjectId();
	boolean getRepeatActions();
	String getState();
	String getStateTimestamp();
	String getThresholdRule();
	String getTimestamp();
	
	/**
	 * @return the alarm type
	 */
	Type getType();
	
	/**
	 * @return The user id who last modified the resource
	*/
	String getUserId();
	
	
	
	
	
	
	/**
	 * The Meter Type
	 */
	public enum Type {
		THRESHOLD, UNRECOGNIZED;

		@JsonValue
		public String value() {
			return this.name().toLowerCase();
		}

		@Override
		public String toString() {
			return value();
		}

		@JsonCreator
		public static Type fromValue(String type) {
			try {
				return valueOf(type.toUpperCase());
			} catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}
	}


}
