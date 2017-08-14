package org.openstack4j.openstack.database.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * DataStore Type Constant
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum ReplicationMode {

	ASYNC, SEMISYNC, SYNC;
	
	@JsonValue
	public String value() {
		return this.name().toLowerCase();
	}

	@JsonCreator
	public static ReplicationMode from(String value) {
		if (value != null) {
			for (ReplicationMode datastore : ReplicationMode.values()) {
				if (value.equalsIgnoreCase(datastore.name())) {
					return datastore;
				}
			}
		}
		return null;
	}
}