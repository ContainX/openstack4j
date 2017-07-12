package org.openstack4j.openstack.sahara.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum ClusterType {

	Analyse(0), Stream(1);

	Integer value;

	ClusterType(Integer value) {
		this.value = value;
	}
	
	@JsonValue
	Integer value() {
		return value;
	}

	@JsonCreator
	public static ClusterType forValue(Object value) {
		if (value != null) {
			for (ClusterType s : ClusterType.values()) {
				if (value.equals(s.value)) {
					return s;
				} else if(value.toString().equalsIgnoreCase(s.name())){
					return s;
				}
			}
		}
		return null;
	}
}