package com.huawei.openstack4j.openstack.storage.block.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.storage.block.VolumeBackupPolicyResource.VolumeBackupPolicyResourceActionResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 17:39:34
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VBSVolumeBackupPolicyResourceActionResult implements VolumeBackupPolicyResourceActionResult {
	
	private static final long serialVersionUID = 3622412528513440384L;
	
	@JsonProperty("success_resources")
	List<VBSVolumeBackupPolicyResource> successResources;
	
	@JsonProperty("fail_resources")
	List<VBSVolumeBackupPolicyResource> failResources;

}
