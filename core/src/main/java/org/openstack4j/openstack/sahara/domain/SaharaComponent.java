package org.openstack4j.openstack.sahara.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 14:34:20
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SaharaComponent {

	@JsonProperty("componentId")
	String id;
	@JsonProperty("componentName")
	String name;
	@JsonProperty("componentVersion")
	String version;
	@JsonProperty("componentDesc")
	String desc;

}
