/*******************************************************************************
 *******************************************************************************/
package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.storage.block.CloudVolumeBackupCreate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author QianBiao.NG
 * @date   2017-06-07 08:54:29
 */
@Getter
@ToString
@Builder(toBuilder = true)
@JsonRootName("backup")
public class VBSVolumeBackupCreate implements CloudVolumeBackupCreate {

	private static final long serialVersionUID = 5031528431685550961L;

	@JsonProperty("name")
	String name;

	@JsonProperty("description")
	String description;

	@JsonProperty("volume_id")
	String volumeId;

}
