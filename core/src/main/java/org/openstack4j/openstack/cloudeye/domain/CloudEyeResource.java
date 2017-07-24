package org.openstack4j.openstack.cloudeye.domain;

import org.openstack4j.model.cloudeye.QuotaType;
import org.openstack4j.model.cloudeye.Resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("quotas")
public class CloudEyeResource implements Resource {
	
	private static final long serialVersionUID = 1L;
	
	QuotaType type;
	Integer used;
	String unit;
	Integer quota;
}
