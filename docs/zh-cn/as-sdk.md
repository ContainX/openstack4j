# AS SDK

OTC OpenStack4j AutoScaling SDK
- 服务入口: `osclient.autoScaling()`
- 服务类型: `auto-scaling`


## 弹性伸缩组
### 创建弹性伸缩组
```java
IdResourceEntity network = new IdResourceEntity();
network.setId("network id");

IdResourceEntity securityGroup = new IdResourceEntity();
securityGroup.setId("security group id");

ASAutoScalingGroupCreate group = ASAutoScalingGroupCreate.builder()
		.groupName("groupName")
		.vpcId("vpc id")
		.networks(Lists.newArrayList(network))
		.securityGroups(Lists.newArrayList(securityGroup))
		.build();

ScalingGroupCreate result = osclient.autoScaling().groups().create(group);
```

### 查询弹性伸缩组列表
```java
List<? extends ScalingGroup> list = osclient.autoScaling().groups().list();
List<? extends ScalingGroup> list = osclient.autoScaling().groups().list(
	ScalingGroupListOptions.create()
	.groupName("groupName")
	.limit(5)
	.startNumber(1));

```

### 查询弹性伸缩组详情
```java
ScalingGroup group = osclient.autoScaling().groups().get("groupId");
```

### 修改弹性伸缩组
```java
ScalingGroup group = osclient.autoScaling().groups().get("groupId");

ScalingGroupUpdate result = osclient.autoScaling().groups().update(group.groupId(),
	ASAutoScalingGroupUpdate.fromScalingGroup(group).toBuilder()
		.groupName("updateGroupName").build());
```

### 删除弹性伸缩组
```java
ActionResponse resp = osclient.autoScaling().groups().delete("groupId");
```

### 启用弹性伸缩组
```java
ActionResponse resp = osclient.autoScaling().groups().resume(groupId);
```

### 停止弹性伸缩组
```java
ActionResponse resp = osclient.autoScaling().groups().pause(groupId);
```

## 弹性伸缩配置
### 创建弹性伸缩配置
```java
Map<String, String> metaData = Maps.newHashMap();
metaData.put("key1", "val1");
metaData.put("key2", "val2");

Disk disk = Disk.builder()
	.size(40)
	.volumeType(DiskType.SATA)
	.diskType(DiskType.SYS)
	.build();
InstanceConfig instanceConfig = InstanceConfig.builder()
	.flavorRef("flavorId")
	.imageRef("imageId")
	.disks(Lists.newArrayList(disk))
	.keyName("keyname")
	.metadata(metaData)
	.build();
ScalingConfigCreate config = ASAutoScalingConfigCreate.builder()
	.configName("configName")
	.instanceConfig(instanceConfig)
	.build();

ScalingConfigCreate result = osv3().autoScaling().configs().create(config);
```

### 查询弹性伸缩配置列表
```java
List<? extends ScalingConfig> all = osv3().autoScaling().configs().list();

ScalingConfigListOptions options = ScalingConfigListOptions.create().configName("configName");
List<? extends ScalingConfig> list = osv3().autoScaling().configs().list(options);
```

### 查询弹性伸缩配置详情
```java
ScalingConfigCreate config = osv3().autoScaling().configs().get("configId");
```

### 删除弹性伸缩配置
```java
ActionResponse resp = osv3().autoScaling().configs().delete("configId");

ActionResponse resp2 = osv3().autoScaling().configs().delete(Lists.newArrayList("configId"));
```

## 弹性伸缩实例
### 查询弹性伸缩组中的实例列表
```java
List<? extends ScalingGroupInstance> list = osclient.autoScaling().groupInstances().list("groupId");

ScalingGroupInstanceListOptions options = ScalingGroupInstanceListOptions.create()
		.lifeCycleState(LifeCycleState.INSERVICE)
		.heathStatus(HealthStatus.NORMAL)
		.limit(2);
List<? extends ScalingGroupInstance> filterList = osclient.autoScaling()
		.groupInstances().list("groupId", options);
```

### 移除弹性伸缩组实例
```java
ActionResponse resp = osclient.autoScaling().groupInstances().delete("instanceId", false);
```

### 批量添加弹性伸缩组实例
```java
List<String> instanceIds =  Lists.newArrayList("id1", "id2");
ActionResponse resp = osclient.autoScaling().groupInstances().batchAdd("groupId", instanceIds, false);
```

### 批量移除弹性伸缩组实例
```java
List<String> instanceIds =  Lists.newArrayList("id1", "id2");
ActionResponse resp = osclient.autoScaling().groupInstances().batchRemove("groupId", instanceIds, false);
```

## 弹性伸缩策略
### 创建弹性伸缩策略
```java
ScheduledPolicy scheduledPolicy = ScheduledPolicy.builder()
		.launchTime("launchTime")
		.recurrenceType(RecurrenceType.Daily.name())
		.build();
ScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder()
		.policyName("policyName")
		.groupId("groupId")
		.policyType(PolicyType.SCHEDULED.name())
		.scheduledPolicy(scheduledPolicy)
		.build();
ScalingPolicyCreateUpdate create = osclient.autoScaling().policies().create(policy);
```

### 修改弹性伸缩策略
```java
ASAutoScalingPolicy policy = ...; //get policy
ScalingPolicyCreateUpdate update = osclient.autoScaling().policies()
		.update(policy.toBuilder().policyName("newPolicyName").build());
```

### 查询弹性伸缩策略列表
```java
List<? extends ScalingPolicy> all = osclient.autoScaling().policies().list("groupId");

ScalingPolicyListOptions options = ScalingPolicyListOptions.create().policyName("policyName");
List<? extends ScalingPolicy> list = osclient.autoScaling().policies().list("groupId", options);
```

### 查询弹性伸缩策略详情
```java
ScalingPolicy policy = osclient.autoScaling().policies().get("policyId");
```

### 执行弹性伸缩策略
```java
ActionResponse resp = osclient.autoScaling().policies().execute("policyId");
```

### 启用弹性伸缩策略
```java
ActionResponse resp = osclient.autoScaling().policies().resume("policyId");
```

### 停止弹性伸缩策略
```java
ActionResponse resp = osclient.autoScaling().policies().pause("policyId");
```

### 删除弹性伸缩策略
```java
ActionResponse resp = osclient.autoScaling().policies().delete("policyId");
```

## 伸缩活动日志
### 查询伸缩活动日志
```java
List<? extends ScalingActivityLog> all = osclient.autoScaling().activityLogs().list("groupId");

ScalingActivityLogListOptions options = ScalingActivityLogListOptions.create()
		.startNumber(5)
		.limit(5);
List<? extends ScalingActivityLog> list = osclient.autoScaling().activityLogs().list("groupId", options);
```

## 配额
### 查询配额
```java
List<? extends ScalingQuota> all = osclient.autoScaling().quotas().list();

List<? extends ScalingQuota> list = osclient.autoScaling().quotas().list("groupId");
```