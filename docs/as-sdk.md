# AS SDK

HuaWei OpenStack4j AS SDK, entry point is: `osclient.autoScaling()`

## API document
Not provided for now.

## SDK document

### initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .

## AutoScaling Group
### Create AutoScaling Group
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

### List AutoScaling Groups
```java
List<? extends ScalingGroup> list = osclient.autoScaling().groups().list();
List<? extends ScalingGroup> list = osclient.autoScaling().groups().list(
	ScalingGroupListOptions.create()
	.groupName("groupName")
	.limit(5)
	.startNumber(1));

```

### Get AutoScaling Group
```java
ScalingGroup group = osclient.autoScaling().groups().get("groupId");
```

### Update AutoScaling Group
```java
ScalingGroup group = osclient.autoScaling().groups().get("groupId");

ScalingGroupUpdate result = osclient.autoScaling().groups().update(group.groupId(),
	ASAutoScalingGroupUpdate.fromScalingGroup(group).toBuilder()
		.groupName("updateGroupName").build());
```

### Delete AutoScaling Group
```java
ActionResponse resp = osclient.autoScaling().groups().delete("groupId");
```

### Resume AutoScaling Group
```java
ActionResponse resp = osclient.autoScaling().groups().resume(groupId);
```

### Pause AutoScaling Group
```java
ActionResponse resp = osclient.autoScaling().groups().pause(groupId);
```

## AutoScaling Configuration
### Create AutoScaling Configuration
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

### List AutoScaling Configuration
```java
List<? extends ScalingConfig> all = osv3().autoScaling().configs().list();

ScalingConfigListOptions options = ScalingConfigListOptions.create().configName("configName");
List<? extends ScalingConfig> list = osv3().autoScaling().configs().list(options);
```

### Get AutoScaling Configuration
```java
ScalingConfigCreate config = osv3().autoScaling().configs().get("configId");
```

### Delete AutoScaling Configuration
```java
ActionResponse resp = osv3().autoScaling().configs().delete("configId");

ActionResponse resp2 = osv3().autoScaling().configs().delete(Lists.newArrayList("configId"));
```

## AutoScaling Group Instance
### List AutoScaling Group Instance
```java
List<? extends ScalingGroupInstance> list = osclient.autoScaling().groupInstances().list("groupId");

ScalingGroupInstanceListOptions options = ScalingGroupInstanceListOptions.create()
		.lifeCycleState(LifeCycleState.INSERVICE)
		.heathStatus(HealthStatus.NORMAL)
		.limit(2);
List<? extends ScalingGroupInstance> filterList = osclient.autoScaling()
		.groupInstances().list("groupId", options);
```

### Delete AutoScaling Group Instance
```java
ActionResponse resp = osclient.autoScaling().groupInstances().delete("instanceId", false);
```

### Batch Add AutoScaling Group Instance
```java
List<String> instanceIds =  Lists.newArrayList("id1", "id2");
ActionResponse resp = osclient.autoScaling().groupInstances().batchAdd("groupId", instanceIds, false);
```

### Batch Remove AutoScaling Group Instance
```java
List<String> instanceIds =  Lists.newArrayList("id1", "id2");
ActionResponse resp = osclient.autoScaling().groupInstances().batchRemove("groupId", instanceIds, false);
```

## AutoScaling Policy
### Create AutoScaling Policy
```java
ScheduledPolicy scheduledPolicy = ScheduledPolicy.builder()
		.launchTime("launchTime")
		.recurrenceType(RecurrenceType.DAILY)
		.build();
ScalingPolicyCreateUpdate policy = ASAutoScalingPolicyCreateUpdate.builder()
		.policyName("policyName")
		.groupId("groupId")
		.policyType(ScalingPolicyType.SCHEDULED)
		.scheduledPolicy(scheduledPolicy)
		.build();
ScalingPolicyCreateUpdate create = osclient.autoScaling().policies().create(policy);
```

### Update AutoScaling Policy
```java
ASAutoScalingPolicy policy = ...; //get policy
ScalingPolicyCreateUpdate update = osclient.autoScaling().policies()
		.update(policy.toBuilder().policyName("newPolicyName").build());
```

### List AutoScaling Policy
```java
List<? extends ScalingPolicy> all = osclient.autoScaling().policies().list("groupId");

ScalingPolicyListOptions options = ScalingPolicyListOptions.create().policyName("policyName");
List<? extends ScalingPolicy> list = osclient.autoScaling().policies().list("groupId", options);
```

### Get AutoScaling Policy
```java
ScalingPolicy policy = osclient.autoScaling().policies().get("policyId");
```

### Execute AutoScaling Policy
```java
ActionResponse resp = osclient.autoScaling().policies().execute("policyId");
```

### Resume AutoScaling Policy
```java
ActionResponse resp = osclient.autoScaling().policies().resume("policyId");
```

### Pause AutoScaling Policy
```java
ActionResponse resp = osclient.autoScaling().policies().pause("policyId");
```

### Delete AutoScaling Policy
```java
ActionResponse resp = osclient.autoScaling().policies().delete("policyId");
```

## AutoScaling Activity Log
### List AutoScaling Activity Log
```java
List<? extends ScalingActivityLog> all = osclient.autoScaling().activityLogs().list("groupId");

ScalingActivityLogListOptions options = ScalingActivityLogListOptions.create()
		.startNumber(5)
		.limit(5);
List<? extends ScalingActivityLog> list = osclient.autoScaling().activityLogs().list("groupId", options);
```

## AutoScaling Quotas
### List AutoScaling Quotas
```java
List<? extends ScalingQuota> all = osclient.autoScaling().quotas().list();

List<? extends ScalingQuota> list = osclient.autoScaling().quotas().list("groupId");
```