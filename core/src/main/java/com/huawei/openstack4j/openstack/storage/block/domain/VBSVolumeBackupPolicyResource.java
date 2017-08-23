package com.huawei.openstack4j.openstack.storage.block.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyResource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:13:25
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VBSVolumeBackupPolicyResource implements VolumeBackupPolicyResource {

	private static final long serialVersionUID = -1027176809768928487L;

	@JsonProperty("resource_id")
	String id;
	
	@JsonProperty("resource_type")
	String type;
	
	@JsonProperty("availability_zone")
	String availabilityZone;
	
	@JsonProperty("os_vol_host_attr")
	String osVolHostAttr;
	
}
