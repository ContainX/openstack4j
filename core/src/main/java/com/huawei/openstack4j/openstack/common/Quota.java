package com.huawei.openstack4j.openstack.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.ModelEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * HuaWei Resouce Quota
 *
 * @author QianBiao.NG
 * @date   2017-07-14 14:16:37
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quota implements ModelEntity {

	private static final long serialVersionUID = 8118640445519970866L;

	public enum ResourceType {
		ALARM, CMK, QUEUE, ELB, LISTENER;

		@JsonCreator
		public static ResourceType value(String v) {
			try {
				return valueOf(v.toUpperCase());
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
	}

	ResourceType type;
	Integer used;
	Integer quota;
	String unit;
	Integer max;
	Integer min;
	
	@JsonRootName("quotas")
	public static class Quotas extends ListResult<Quota> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("resources")
		private List<Quota> resources;

		public List<Quota> value() {
			return resources;
		}
	}

}
