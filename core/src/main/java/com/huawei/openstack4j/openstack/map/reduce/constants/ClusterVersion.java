package com.huawei.openstack4j.openstack.map.reduce.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:34:45
 */
public enum ClusterVersion {

	//@off
	MRS12("MRS 1.2"),
	MRS13("MRS 1.3.0"),
	;
	//@on

	String value;

	ClusterVersion(String value) {
		this.value = value;
	}
	
	@JsonValue
	String value() {
		return value;
	}

	@JsonCreator
	public static ClusterVersion forValue(String value) {
		if (value != null) {
			for (ClusterVersion s : ClusterVersion.values()) {
				if (value.equals(s.value))
					return s;
			}
		}
		return null;
	}
}