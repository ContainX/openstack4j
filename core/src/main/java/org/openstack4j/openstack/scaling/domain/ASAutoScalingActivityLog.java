package org.openstack4j.openstack.scaling.domain;

import java.util.List;

import org.openstack4j.model.scaling.ScalingActivityLog;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ASAutoScalingActivityLog implements ScalingActivityLog {
	private static final long serialVersionUID = 7380843131412884092L;

	@JsonProperty
	private String status;

	@JsonProperty("start_time")
	private String startTime;

	@JsonProperty("end_time")
	private String endTime;

	@JsonProperty
	private String id;

	@JsonProperty("instance_removed_list")
	private String instanceRemovedList;

	@JsonProperty("instance_deleted_list")
	private String instanceDeletedList;

	@JsonProperty("instance_added_list")
	private String instanceAddedList;

	@JsonProperty("scaling_value")
	private String scalingValue;

	@JsonProperty
	private String description;

	@JsonProperty("instance_value")
	private Integer instanceValue;

	@JsonProperty("desire_value")
	private Integer desireValue;
	
	public static class ASAutoScalingActivityLogs extends ListResult<ASAutoScalingActivityLog> {
		private static final long serialVersionUID = -3295497788104277308L;

		@JsonProperty("scaling_activity_log")
		private List<ASAutoScalingActivityLog> logs;

		@Override
		protected List<ASAutoScalingActivityLog> value() {
			return logs;
		}
	}

}
