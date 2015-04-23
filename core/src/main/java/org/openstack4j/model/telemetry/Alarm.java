package org.openstack4j.model.telemetry;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.BasicResource;
import org.openstack4j.model.telemetry.builder.AlarmBuilder;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm.CeilometerQuery;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm.CeilometerThresholdRule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An Alarm is triggered when a specificied rule is satisfied
 * 
 * @author Massimiliano Romano
 */
public interface Alarm extends ModelEntity,BasicResource, Buildable<AlarmBuilder>  {

	List<String> getAlarmActions();
	
	String getAlarmId();
	
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
	
	CombinationRule getCombinationRule();
	
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
		THRESHOLD, COMBINATION, UNRECOGNIZED;

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
	
	public interface CombinationRule{
		List<String> getAlarmIds();
		Operator getOperator();
		
		void setAlarmIds(List<String> alarmIds);
		void setOperator(Operator operator);
		
		public enum Operator {
			AND, OR, UNRECOGNIZED;

			@JsonValue
			public String value() {
				return this.name().toLowerCase();
			}

			@Override
			public String toString() {
				return value();
			}

			@JsonCreator
			public static Operator fromValue(String operator) {
				try {
					return valueOf(operator.toUpperCase());
				} catch (IllegalArgumentException e) {
					return UNRECOGNIZED;
				}
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
		
		void setMeterName(String meterName);
		void setEvaluationPeriods(int evaluationPeriod);
		void setStatistic(Statistic statistic);
		void setPeriod(int period);
		void setThreshold(float threshold);
		void setQuery(List<CeilometerQuery> query);
		void setComparisonOperator(ComparisonOperator comparisonOperator);
		void setExcludeOutliers(boolean excludeOutliers);
		
		
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
			void setField(String field);
			void setValue(String value);
			void setOp(ComparisonOperator comparisonOperator);
		}
	}
	
	void setName(String name);

	void setType(Type type);
	
	void setUserId(String userId);
	
	void setAlarmActions(List<String> alarmActions);
	
	void setDescription(String description);
	
	void setInsufficientDataActions(List<String> insufficientDataActions);
	
	void setOkActions(List<String> okActions);
	
	void setRepeateActions(Boolean repeatActions);

	void setThresholdRule(CeilometerThresholdRule tr);
}