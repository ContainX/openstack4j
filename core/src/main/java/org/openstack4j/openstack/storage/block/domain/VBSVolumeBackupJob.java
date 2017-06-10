/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.storage.block.CloudVolumeBackupJob;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 10:44:01
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class VBSVolumeBackupJob implements CloudVolumeBackupJob {

	private static final long serialVersionUID = -9174668588171960734L;

	@JsonProperty("job_id")
	String jobId;

	@JsonProperty("message")
	String message;

}
