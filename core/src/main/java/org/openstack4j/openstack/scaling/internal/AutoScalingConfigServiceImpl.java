/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.openstack.scaling.internal;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.scaling.AutoScalingConfigService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.scaling.Disk;
import org.openstack4j.model.scaling.Eip;
import org.openstack4j.model.scaling.InstanceConfig;
import org.openstack4j.model.scaling.Personality;
import org.openstack4j.model.scaling.PublicIp;
import org.openstack4j.model.scaling.ScalingConfig;
import org.openstack4j.model.scaling.ScalingConfigCreate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingConfig;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingConfig.ASAutoScalingConfigs;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingConfigCreate;
import org.openstack4j.openstack.scaling.domain.ASAutoScalingConfigDelete;
import org.openstack4j.openstack.scaling.options.ScalingConfigListOptions;

import com.google.common.base.Strings;

public class AutoScalingConfigServiceImpl extends BaseAutoScalingServices implements AutoScalingConfigService {

	@Override
	public ScalingConfigCreate create(ScalingConfigCreate config) {
		checkArgument(config != null, "config is required");
		checkArgument(!Strings.isNullOrEmpty(config.getConfigName()), "configName is required");
		checkArgument(config.getInstanceConfig() != null, "instanceConfig is required");

		checkDiskWhenPresent(config.getInstanceConfig().getDisks());
		checkPersonalityWhenPresent(config.getInstanceConfig().getPersonality());
		checkPublicIpWhenPresent(config.getInstanceConfig().getPublicIp());

		InstanceConfig instanceConfig = config.getInstanceConfig();
		if (Strings.isNullOrEmpty(instanceConfig.getInstanceId())) {
			checkArgument(!Strings.isNullOrEmpty(instanceConfig.getFlavorRef()),
					"flavorRef is required when instanceId not present");
			checkArgument(!Strings.isNullOrEmpty(instanceConfig.getImageRef()),
					"imageRef is required when instanceId not present");
			checkDiskWhenInstanceIdNotPresent(instanceConfig.getDisks());
		} else {
			instanceConfig.setFlavorRef(null);
			instanceConfig.setImageRef(null);
			instanceConfig.setDisks(null);
		}

		return post(ASAutoScalingConfigCreate.class, uri("/scaling_configuration")).entity(config).execute();
	}

	private void checkPublicIpWhenPresent(PublicIp publicIp) {
		if (publicIp != null) {
			Eip eip = publicIp.getEip();
			checkArgument(eip != null, "eip is required");
			checkArgument(!Strings.isNullOrEmpty(eip.getIpType()), "ipType is required");
			checkArgument(eip.getBandwidth() != null, "bandwidth is required");
			checkArgument(!Strings.isNullOrEmpty(eip.getBandwidth().getSize()), "bandwidth size is required");
			checkArgument(!Strings.isNullOrEmpty(eip.getBandwidth().getShareType()), "bandwidth shareType is required");
			checkArgument(!Strings.isNullOrEmpty(eip.getBandwidth().getChargingMode()),
					"bandwidth chargingMode is required");
		}
	}

	@Override
	public List<? extends ScalingConfig> list(ScalingConfigListOptions options) {
		return get(ASAutoScalingConfigs.class, uri("/scaling_configuration")).params(options.getOptions()).execute()
				.getList();
	}

	@Override
	public List<? extends ScalingConfig> list() {
		return get(ASAutoScalingConfigs.class, uri("/scaling_configuration")).execute().getList();
	}

	@Override
	public ScalingConfigCreate get(String configId) {
		checkNotNull(configId, "configId");
		return get(ASAutoScalingConfig.class, uri("/scaling_configuration/%s", configId)).execute();
	}

	@Override
	public ActionResponse delete(String configId) {
		checkNotNull(configId, "configId");
		return deleteWithResponse(uri("/scaling_configuration/%s", configId)).execute();
	}

	@Override
	public ActionResponse delete(List<String> configIds) {
		checkArgument(configIds != null && !configIds.isEmpty(), "configIds null or empty");
		return post(ActionResponse.class, uri("/scaling_configurations"))
				.entity(ASAutoScalingConfigDelete.builder().configIds(configIds).build()).execute();
	}

	private void checkDiskWhenInstanceIdNotPresent(List<Disk> disks) {
		checkArgument(disks != null && !disks.isEmpty(), "disk is required when instanceId not present");
		for (Disk disk : disks) {
			checkNotNull(disk.getSize(), "disk size");
			checkNotNull(disk.getVolumeType(), "disk volume type");
			checkNotNull(disk.getDiskType(), "disk type");
		}
	}

	private void checkPersonalityWhenPresent(List<Personality> personality) {
		if (personality != null) {
			for (Personality p : personality) {
				checkArgument(!Strings.isNullOrEmpty(p.getPath()), "personality path is required");
				checkArgument(!Strings.isNullOrEmpty(p.getContent()), "personality content is required");
			}
		}
	}

	private void checkDiskWhenPresent(List<Disk> disks) {
		if (disks != null) {
			for (Disk disk : disks) {
				checkArgument(disk.getSize() != null, "diskSize is required");
				checkArgument(!Strings.isNullOrEmpty(disk.getVolumeType()), "diskVolumeType is required");
				checkArgument(!Strings.isNullOrEmpty(disk.getDiskType()), "diskType is required");
			}
		}
	}
}
