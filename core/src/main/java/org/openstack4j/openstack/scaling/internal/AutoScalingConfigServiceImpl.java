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
		checkNotNull(config, "scaling config");
		checkNotNull(config.getConfigName(), "scaling config name");
		checkNotNull(config.getInstanceConfig(), "instance config");
		if (config.getInstanceConfig().getDisks() != null) {
			for (Disk disk : config.getInstanceConfig().getDisks()) {
				checkNotNull(disk.getSize(), "disk size");
				checkNotNull(disk.getVolumeType(), "disk volume type");
				checkNotNull(disk.getDiskType(), "disk type");
			}
		}
		if (config.getInstanceConfig().getPersonality() != null) {
			for (Personality p : config.getInstanceConfig().getPersonality()) {
				checkNotNull(p.getPath(), "personality path");
				checkNotNull(p.getContent(), "personality content");
			}
		}
		if (config.getInstanceConfig().getPublicIp() != null) {
			Eip eip = config.getInstanceConfig().getPublicIp().getEip();
			checkNotNull(eip, "eip");
			checkNotNull(eip.getIpType(), "ip type");
			checkNotNull(eip.getBandwidth(), "bandwidth");
			checkNotNull(eip.getBandwidth().getSize(), "bandwidth size");
			checkNotNull(eip.getBandwidth().getShareType(), "bandwidth share type");
			checkNotNull(eip.getBandwidth().getChargingMode(), "bandwidth charging mode");
		}
		
		InstanceConfig instanceConfig = config.getInstanceConfig();
		if(Strings.isNullOrEmpty(instanceConfig.getInstanceId())) {
			checkArgument(!Strings.isNullOrEmpty(instanceConfig.getFlavorRef()), "flavorRef is required when instanceId not present");
			checkArgument(!Strings.isNullOrEmpty(instanceConfig.getImageRef()), "imageRef is required when instanceId not present");
			checkDiskWhenInstanceIdNotPresent(instanceConfig.getDisks());
		} else {
			instanceConfig.setFlavorRef(null);
			instanceConfig.setImageRef(null);
			instanceConfig.setDisks(null);
		}
		
		return post(ASAutoScalingConfigCreate.class, uri("/scaling_configuration")).entity(config).execute();
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
		for(Disk disk : disks) {
			checkNotNull(disk.getSize(), "disk size");
			checkNotNull(disk.getVolumeType(), "disk volume type");
			checkNotNull(disk.getDiskType(), "disk type");
		}
	}

}
