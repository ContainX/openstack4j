package org.openstack4j.openstack.database.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * SQL statement Type Constant
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum BackupStatus {

	BUILDING, COMPLETED, FAILED, DELETING;

	@JsonCreator
	public static BackupStatus from(String value) {
		if (value != null) {
			for (BackupStatus type : BackupStatus.values()) {
				if (value.equalsIgnoreCase(type.name())) {
					return type;
				}
			}
		}
		return null;
	}
}