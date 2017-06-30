package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.common.serializer.YNBooleanDeserializer;
import org.openstack4j.model.common.serializer.YNBooleanSerializer;
import org.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupPolicyStatus;
import org.openstack4j.model.storage.block.VolumeBackupPolicy.VolumeBackupScheduledPolicy;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
public class VBSVolumeBackupScheduledPolicy implements VolumeBackupScheduledPolicy {

	private static final long serialVersionUID = -5660930519824771L;
	
	@JsonProperty("rentention_num")
	Integer maxBackupAmount;

	Integer frequency;

	@JsonSerialize(using = YNBooleanSerializer.class)
	@JsonDeserialize(using = YNBooleanDeserializer.class)
	@JsonProperty("remain_first_backup_of_curMonth")
	Boolean retainFirstBackupOfCurrentMonth;

	@JsonProperty("start_time")
	String startTime;
	
	VolumeBackupPolicyStatus status;

}
