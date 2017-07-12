package org.openstack4j.openstack.sahara.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum BillingType {

	Metered(12);

	Integer value;

	BillingType(Integer value) {
		this.value = value;
	}
	
	@JsonValue
	Integer value() {
		return value;
	}

	@JsonCreator
	public static BillingType forValue(Object value) {
		if (value != null) {
			for (BillingType s : BillingType.values()) {
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