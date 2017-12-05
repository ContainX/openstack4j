package org.openstack4j.model.telemetry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.BasicResource;
import org.openstack4j.model.telemetry.builder.AlarmBuilder;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm.*;

import java.util.List;
import java.util.Map;

/**
 * An Alarm is triggered when a specificied rule is satisfied
 *
 * @author Massimiliano Romano
 */
public interface Alarm extends ModelEntity,BasicResource, Buildable<AlarmBuilder> {

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

	Map<String,Object> getCompositeRule();

	GnocchiResourcesThresholdRule getGnocchiResourcesThresholdRule();

	GnocchiAggregationByMetricsThresholdRule getGnocchiAggregationByMetricsThresholdRule();

	GnocchiAggregationByResourcesThresholdRule getGnocchiAggregationByResourcesThresholdRule();

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
		GNOCCHI_AGGREGATION_BY_METRICS_THRESHOLD, COMPOSITE, GNOCCHI_RESOURCES_THRESHOLD, GNOCCHI_AGGREGATION_BY_RESOURCES_THRESHOLD, THRESHOLD, EVENT,COMBINATION, UNRECOGNIZED;
		//THRESHOLD, COMBINATION, UNRECOGNIZED;

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

	public enum AggregationMethod{
		COUNT, MAX, SUM, MIN, MEAN, UNRECOGNIZED;
		@JsonValue
		public String value() {
			return this.name().toLowerCase();
		}

		@Override
		public String toString() {
			return value();
		}

		@JsonCreator
		public static AggregationMethod fromValue(String method) {
			try {
				return valueOf(method.toUpperCase());
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

	}

	public interface Query{
		String getField();
		String getValue();
		ComparisonOperator getOp();
		void setField(String field);
		void setValue(String value);
		void setOp(ComparisonOperator comparisonOperator);
	}

	//gnocchi_resources_threshold
	public interface GnocchiResourcesThresholdRule {
		String getMetric();
		String getResourceId();
		String getResourceType();
		ComparisonOperator getComparisonOperator();
		AggregationMethod getAggregationMethod();
		int getEvaluationPeriods();
		float getThreshold();
		long getGranularity();

		void setMetric(String metric);
		void setResourceId(String resourceId);
		void setResourceType(String resourceType);
		void setComparisonOperator(ComparisonOperator comparisonOperator);
		void setAggregationMethod(AggregationMethod aggregationMethod);
		void setEvaluationPeriods(int evaluationPeriod);
		void setThreshold(float threshold);
		void setGranularity(long granularity);
	}

	//gnocchi_aggregation_by_metrics_threshold
	public interface GnocchiAggregationByMetricsThresholdRule {
		List<String> getMetrics();
		ComparisonOperator getComparisonOperator();
		AggregationMethod getAggregationMethod();
		float getThreshold();
		int getEvaluationPeriods();
		long getGranularity();

		void setMetrics(List<String> metrics);
		void setComparisonOperator(ComparisonOperator comparisonOperator);
		void setAggregationMethod(AggregationMethod aggregationMethod);
		void setThreshold(float threshold);
		void setEvaluationPeriods(int evaluationPeriod);
		void setGranularity(long granularity);
	}

	//gnocchi_aggregation_by_resources_threshold
	public interface GnocchiAggregationByResourcesThresholdRule {
		String getMetric();
		String getResourceType();
		ComparisonOperator getComparisonOperator();
		AggregationMethod getAggregationMethod();
		int getEvaluationPeriods();
		float getThreshold();
		long getGranularity();
		Query getQuery();

		void setMetric(String metric);
		void setResourceType(String resourceType);
		void setComparisonOperator(ComparisonOperator comparisonOperator);
		void setAggregationMethod(AggregationMethod aggregationMethod);
		void setEvaluationPeriods(int evaluationPeriod);
		void setThreshold(float threshold);
		void setGranularity(long granularity);
		void setQuery(CeilometerQuery query);
	}

	//event
	public interface GnocchiEvent{

	}

	public interface MetricOfResourceRule{
		String getMetric();
		String getResourceId();
		String getResourceType();

		void setMetric(String metric);
		void setResourceId(String resourceId);
		void setResourceType(String resourceType);
	}

	public interface AggregationMetricByResourcesLookupRule{
		String getMetric();
		String getResourceType();

		void setMetric(String metric);
		void setResourceType(String resourceType);
	}

	public interface AggregationMetricsByIdLookupRule{
		String getMetric();

		void setMetric(String metric);
	}

	public interface AlarmTimeConstraint{
		String getDescription();
		int getDuration();
		String getName();
		String getStart();
		String getTimezone();

		void setDescription(String description);
		void setDuration(int duration);
		void setName(String name);
		void setStart(String String);
		void setTimezone(String timezone);
	}

	public interface AlarmChange{
		String getAlarmId();
		String getDetail();
		String getEventId();
		String getOnBehalfOf();
		String getProjectId();
		String getTimestamp();
		ChangeType getChangeType();
		String getUserId();

		void setAlarmId(String alarmId);
		void setDetail(String detail);
		void setEventId(String eventId);
		void setOnBehalfOf(String onBehalfOf);
		void setProjectId(String projectId);
		void setTimestamp(String timestamp);
		void setChangeType(ChangeType changeType);
		void setUserId(String userId);
		//The type of change
		public enum ChangeType{
			CREATION, RULE_CHANGE, STATE_TRANSITION, DELETION,UNRECOGNIZED;

			@JsonValue
			public String value() {
				return this.name().toLowerCase();
			}

			@Override
			public String toString() {
				return value();
			}

			@JsonCreator
			public static ChangeType fromValue(String type) {
				try {
					return valueOf(type.toUpperCase());
				} catch (IllegalArgumentException e) {
					return UNRECOGNIZED;
				}
			}
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

	void setGnocchiResourcesThresholdRule(CeilometerGnocchiResourcesThresholdRule ceilometerGnocchiResourcesThresholdRule);

	void setGnocchiAggregationByMetricsThresholdRule(CeilometerGnocchiAggregationByMetricsThresholdRule ceilometerGnocchiAggregationByMetricsThresholdRule);

	void setGnocchiAggregationByResourcesThresholdRule(CeilometerGnocchiAggregationByResourcesThresholdRule ceilometerGnocchiAggregationByResourcesThresholdRule);

}