package com.huawei.openstack4j.openstack.database.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * DataStore Type Constant
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