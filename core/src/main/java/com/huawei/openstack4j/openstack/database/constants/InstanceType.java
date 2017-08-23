package com.huawei.openstack4j.openstack.database.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Database Instance Type Constant
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum InstanceType {

	Master, ReadReplica, Slave;
	
	@JsonValue
	public String value() {
		return this.name().toLowerCase();
	}

	@JsonCreator
	public static InstanceType from(String value) {
		if (value != null) {
			for (InstanceType datastore : InstanceType.values()) {
				if (value.equalsIgnoreCase(datastore.name())) {
					return datastore;
				}
			}
		}
		return null;
	}
}