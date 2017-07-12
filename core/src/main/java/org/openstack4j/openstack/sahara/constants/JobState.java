package org.openstack4j.openstack.sahara.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum JobState {

	Terminated(-1), Starting(1), Running(2), Completed(3), Abnormal(4),;

	Integer value;

	JobState(Integer value) {
		this.value = value;
	}

	@JsonValue
	public Integer value() {
		return value;
	}

	@JsonCreator
	public static JobState value(Integer v) {
		JobState[] values = JobState.values();
		for (JobState state : values) {
			if(state.value.equals(v)) {
				return state;
			}
		}
		return null;
	}
}
