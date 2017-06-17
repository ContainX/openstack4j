package org.openstack4j.model.scaling;

import org.openstack4j.model.ModelEntity;

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
public class ScheduledPolicy implements ModelEntity {

	private static final long serialVersionUID = -6411187348817338454L;

	@JsonProperty("launch_time")
	private String launchTime;

	@JsonProperty("recurrence_type")
	private String recurrenceType;

	@JsonProperty("recurrence_value")
	private String recurrenceValue;

	@JsonProperty("start_time")
	private String startTime;

	@JsonProperty("end_time")
	private String endTime;
}
