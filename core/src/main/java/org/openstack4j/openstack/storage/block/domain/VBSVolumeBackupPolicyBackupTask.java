package org.openstack4j.openstack.storage.block.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.storage.block.VolumeBackupPolicyBackupTask;
import org.openstack4j.openstack.common.DateTimeUtils;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-30 10:13:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VBSVolumeBackupPolicyBackupTask implements VolumeBackupPolicyBackupTask {

	private static final long serialVersionUID = 948876697834568491L;
	
	@JsonProperty("job_id")
	String id;
	
	@JsonProperty("backup_name")
	String backupName;
	
	@JsonProperty("resource_id")
	String resourceId;
	
	@JsonProperty("resource_type")
	String resourceType;
	
	@JsonProperty("status")
	BackupTaskStatus status;
	
	@JsonProperty("created_at")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS_SSS)
	Date createdAt;
	
	@JsonProperty("finished_at")
	@JsonFormat(pattern = DateTimeUtils.FORMAT_YMDTHMS_SSS)
	Date finishedAt;

	public static class VolumeBackupPolicyBackupTasks extends ListResult<VBSVolumeBackupPolicyBackupTask> {

		private static final long serialVersionUID = 1L;

		private List<VBSVolumeBackupPolicyBackupTask> tasks;

		@Override
		protected List<VBSVolumeBackupPolicyBackupTask> value() {
			return tasks;
		}
	}

}
