package org.openstack4j.openstack.database.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * SQL statement Type Constant
 *
 * @author QianBiao.NG
 * @date   2017-07-18 09:07:40
 */
public enum StatementType {

	INSERT, UPDATE, SELECT, DELETE, CREATE;

	@JsonCreator
	public static StatementType from(String value) {
		if (value != null) {
			for (StatementType type : StatementType.values()) {
				if (value.equalsIgnoreCase(type.name())) {
					return type;
				}
			}
		}
		return null;
	}
}