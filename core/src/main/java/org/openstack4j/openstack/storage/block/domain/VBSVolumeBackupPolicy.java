package org.openstack4j.openstack.storage.block.domain;

import java.util.List;

import org.openstack4j.model.storage.block.VolumeBackupPolicy;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-29 16:13:25
 */
@Getter
@ToString
@Builder(toBuilder = true)
public class VBSVolumeBackupPolicy implements VolumeBackupPolicy {

	private static final long serialVersionUID = -1027176809768928487L;

	@JsonProperty("backup_policy_id")
	String id;

	@JsonProperty("backup_policy_name")
	String name;

	@JsonProperty("scheduled_policy")
	VBSVolumeBackupScheduledPolicy scheduledPolicy;
	
	public static class VolumeBackupPolicies extends ListResult<VBSVolumeBackupPolicy> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("backups")
		private List<VBSVolumeBackupPolicy> policies;

		@Override
		protected List<VBSVolumeBackupPolicy> value() {
			return policies;
		}
	}

}
