package com.huawei.openstack4j.functional.scaling;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.google.common.collect.Lists;

import com.beust.jcommander.internal.Maps;
import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.model.compute.Keypair;
import com.huawei.openstack4j.model.scaling.Disk;
import com.huawei.openstack4j.model.scaling.Disk.DiskType;
import com.huawei.openstack4j.model.scaling.Disk.VolumeType;
import com.huawei.openstack4j.model.scaling.InstanceConfig;
import com.huawei.openstack4j.model.scaling.ScalingConfigCreate;
import com.huawei.openstack4j.model.scaling.ScalingGroup.HealthPeriodicAuditMethod;
import com.huawei.openstack4j.model.scaling.ScalingGroup.InstanceTerminatePolicy;
import com.huawei.openstack4j.openstack.common.IdResourceEntity;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingConfigCreate;
import com.huawei.openstack4j.openstack.scaling.domain.ASAutoScalingGroupCreate;

/**
 *
 * @author QianBiao.NG
 * @date   2017-08-25 15:50:55
 */
public class BaseAutoScalingTest extends AbstractTest {

	String vpcId = "31d158b8-e7d7-4b4a-b2a7-a5240296b267";
	String networkId = "85d0d006-44f9-4f32-8384-7f8a8198bed6";
	String sgId = "0005ba27-b937-4a7c-a280-c7b65cea2e47";

	public String createConfig(String name) {
		Map<String, String> metaData = Maps.newHashMap();
		metaData.put("tag", "app");
		metaData.put("node", "sdk-unittest");
		Disk disk = Disk.builder().size(40).volumeType(VolumeType.SATA).diskType(DiskType.SYS).build();
		InstanceConfig instanceConfig = InstanceConfig.builder().flavorRef(getFirstFlavor().getId())
				.imageRef(getFirstImage().getId()).disks(Lists.newArrayList(disk)).keyName(getFirstKeypair().getName())
				.metadata(metaData).build();

		ScalingConfigCreate config = ASAutoScalingConfigCreate.builder().configName(name).instanceConfig(instanceConfig)
				.build();

		String configId = osclient.autoScaling().configs().create(config);
		return configId;
	}

	public String createGroup(String name, String asConfigId) {
		IdResourceEntity network = new IdResourceEntity(networkId);
		IdResourceEntity securityGroup = new IdResourceEntity(sgId);

		ASAutoScalingGroupCreate group = ASAutoScalingGroupCreate.builder().groupName(name).vpcId(vpcId)
				.networks(Lists.newArrayList(network)).configId(asConfigId)
				.securityGroups(Lists.newArrayList(securityGroup)).maxInstanceNumber(2).minInstanceNumber(0)
				.desireInstanceNumber(1).coolDownTime(200)
				.healthPeriodicAuditMethod(HealthPeriodicAuditMethod.NOVA_AUDIT).healthPeriodicAuditTime(5)
				.instanceTerminatePolicy(InstanceTerminatePolicy.OLD_CONFIG_OLD_INSTANCE).build();

		String groupId = osclient.autoScaling().groups().create(group);
		Assert.assertNotNull(groupId);
		return groupId;
	}

	/**
	 * @return 
	 * 
	 */
	public Keypair getKeypair() {
		List<? extends Keypair> list = osclient.compute().keypairs().list();
		if (list == null || list.size() == 0) {
			return osclient.compute().keypairs().create("sdk-unittest", null);
		} else {
			return list.get(0);
		}
	}

}
