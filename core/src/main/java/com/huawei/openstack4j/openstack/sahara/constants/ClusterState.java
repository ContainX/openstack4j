package com.huawei.openstack4j.openstack.sahara.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:34:45
 */
public enum ClusterState {

	//@off
	Existing("existing"),
	History("history"),
	Starting("starting"),
	Running("running"),
	Terminated("terminated"),
	Failed("failed"),
	Abnormal("abnormal"),
	Terminating("terminating"),
	Rebooting("rebooting"),
	Shutdown("shutdown"),
	Frozen("frozen"),
	ScalingOut("scaling-out"),
	ScalingIn("scaling-in"),
	ScalingError("scaling-error"),
	;
	//@on

	String value;

	ClusterState(String value) {
		this.value = value;
	}

	@JsonCreator
	public static ClusterState forValue(String value) {
		if (value != null) {
			for (ClusterState s : ClusterState.values()) {
				if (value.equals(s.value))
					return s;
			}
		}
		return null;
	}
}