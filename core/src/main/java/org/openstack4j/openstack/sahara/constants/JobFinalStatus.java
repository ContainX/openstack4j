package org.openstack4j.openstack.sahara.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum JobFinalStatus {

	Running(0), Terminated(1), Completed(2), Canceled(4),;

	Integer value;

	JobFinalStatus(Integer value) {
		this.value = value;
	}

	@JsonValue
	public Integer value() {
		return value;
	}

	@JsonCreator
	public static JobFinalStatus value(Integer v) {
		JobFinalStatus[] values = JobFinalStatus.values();
		for (JobFinalStatus state : values) {
			if(state.value.equals(v)) {
				return state;
			}
		}
		return null;
	}

}