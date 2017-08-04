package org.openstack4j.openstack.trove.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * DataStore Type Constant
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum DataStoreType {

	MySQL, PostgreSQL, SQLServer;

	@JsonCreator
	public static DataStoreType from(String value) {
		if (value != null) {
			for (DataStoreType datastore : DataStoreType.values()) {
				if (value.equals(datastore.name())) {
					return datastore;
				}
			}
		}
		return null;
	}
}