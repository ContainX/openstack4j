package com.huawei.openstack4j.model.storage.block;

import java.util.Date;

import com.huawei.openstack4j.model.ModelEntity;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-30 10:12:43
 */
public interface VolumeBackupPolicyBackupTask extends ModelEntity {

	public enum BackupTaskStatus {
		RUNNING, EXECUTE_TIMEOUT, WAITING, EXECUTE_FAIL, EXECUTE_SUCCESS;
	}

	public String getId();

	public String getBackupName();

	public String getResourceId();

	public String getResourceType();

	public BackupTaskStatus getStatus();

	public Date getCreatedAt();

	public Date getFinishedAt();

}
