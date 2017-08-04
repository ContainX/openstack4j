package org.openstack4j.openstack.trove.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum VolumeType {

	SATA, SAS, SSD, COMMON;

	@JsonCreator
	public static VolumeType forValue(String value) {
		if (value != null) {
			for (VolumeType s : VolumeType.values()) {
				if (value.equals(s.name())) {
					return s;
				}
			}
		}
		return null;
	}
}