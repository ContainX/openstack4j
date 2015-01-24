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
	
	//String getCombinationRule();
	String getDescription();
	boolean isEnabled();
	void isEnabled(boolean newValue);
	List<String> getInsufficientDataActions();
	/**
	 * @return the unique name of the alarm
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
	ThresholdRule getThresholdRule();
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
	 * The Alarm Type
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
	
	public interface ThresholdRule{
		String getMeterName();
		int getEvaluationPeriods();
		Statistic getStatistic();
		int getPeriod();
		float getThreshold();
		List<? extends Query> getQuery();
		ComparisonOperator getComparisonOperator();
		boolean getExcludeOutliers();
		
		
		
		public enum Statistic {
			MAX, MIN, AVG, SUM, COUNT, UNRECOGNIZED;

			@JsonValue
			public String value() {
				return this.name().toLowerCase();
			}

			@Override
			public String toString() {
				return value();
			}

			@JsonCreator
			public static Statistic fromValue(String statistic) {
				try {
					return valueOf(statistic.toUpperCase());
				} catch (IllegalArgumentException e) {
					return UNRECOGNIZED;
				}
			}
		}
		
		public enum ComparisonOperator {
			LT, LE, EQ, NE, GE, GT, UNRECOGNIZED;

			@JsonValue
			public String value() {
				return this.name().toLowerCase();
			}

			@Override
			public String toString() {
				return value();
			}

			@JsonCreator
			public static ComparisonOperator fromValue(String operator) {
				try {
					return valueOf(operator.toUpperCase());
				} catch (IllegalArgumentException e) {
					return UNRECOGNIZED;
				}
			}
		}
		
		public interface Query{
			String getField();
			String getValue();
			ComparisonOperator getOp();
		}
	}
	
	


}
