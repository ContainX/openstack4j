package org.openstack4j.openstack.telemetry.domain;

import java.util.ArrayList;
import java.util.List;

import org.openstack4j.model.telemetry.Alarm;
import org.openstack4j.model.telemetry.Alarm.ThresholdRule.ComparisonOperator;
import org.openstack4j.model.telemetry.Alarm.ThresholdRule.Query;

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


	//@JsonProperty("combination_rule")
	//private String combinationRule;


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
	private CeilometerThresholdRule thresholdRule;


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

	/*
	@Override
	public String getCombinationRule() {
		return combinationRule;
	}
	*/
	

	@Override
	public String getDescription() {
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
	public ThresholdRule getThresholdRule() {
		return thresholdRule;
	}


	@Override
	public String getTimestamp() {
		return timestamp;
	}
	
	public static class CeilometerThresholdRule implements ThresholdRule{

		@JsonProperty("meter_name")
		String meterName;
		
		@JsonProperty("evaluation_periods")
		int evaluationPeriods;
		
		@JsonProperty("statistic")
		Statistic statistic;
		
		@JsonProperty("period")
		int period;
		
		@JsonProperty("threshold")
		float threshold;
		
		@JsonProperty("query")
		List<CeilometerQuery> query;
		
		@JsonProperty("comparison_operator")
		ComparisonOperator comparisonOperator;
		
		@JsonProperty("exclude_outliers")
		boolean excludeOutliers;
		
		@Override
		public String getMeterName() {
			return meterName;
		}

		@Override
		public int getEvaluationPeriods() {
			return evaluationPeriods;
		}

		@Override
		public Statistic getStatistic() {
			return statistic;
		}

		@Override
		public int getPeriod() {
			return period;
		}

		@Override
		public float getThreshold() {
			return threshold;
		}

		@Override
		public List<? extends Query> getQuery() {
			return query;
		}

		@Override
		public ComparisonOperator getComparisonOperator() {
			return comparisonOperator;
		}

		@Override
		public boolean getExcludeOutliers() {
			return excludeOutliers;
		}
		
	}
	
	public static class CeilometerQuery implements Query{
		public CeilometerQuery(){}
		
		@JsonProperty("field")
		String field;
		
		@JsonProperty("value")
		String value;
		
		@JsonProperty("op")
		ComparisonOperator op;

		@Override
		public String getField() {
			return field;
		}

		@Override
		public String getValue() {
			return value;
		}

		@Override
		public ComparisonOperator getOp() {
			return op;
		}
		
	}
}
