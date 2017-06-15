# DNS SDk

HuaWei OpenStack4j DNS SDK, entry point is: `osclient.as()`

## API document
Not provided for now.

## SDK document

### initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .

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

### Operate AutoScaling Group
```java
ActionResponse resp = osclient.autoScaling().groups().operate("groupId", new Resume());
```
