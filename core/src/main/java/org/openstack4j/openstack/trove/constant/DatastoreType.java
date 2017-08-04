package org.openstack4j.openstack.trove.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Datastore Type Constant
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum DatastoreType {

	MySQL, PostgreSQL, SQLServer;

	@JsonCreator
	public static DatastoreType from(String value) {
		if (value != null) {
			for (DatastoreType datastore : DatastoreType.values()) {
				if (value.equals(datastore.name())) {
					return datastore;
				}
			}
		}
		return null;
	}
}