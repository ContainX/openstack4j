# MaaS SDK

HuaWei OpenStack4j MaaS SDK, entry point is: `osclient.maas()`

## API document
Not provided for now.

## SDK document

### initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .

## API Version
### Get API Version
```java
Version[] version = osclient.maas().version().get();
```

## Task
### Create Task
```java
Node srcNode = Node.builder()
				.region("srcRegion")
				.ak("srcAk")
				.sk("srcSk")
				.objectKey("srcObjectKey")
				.bucket("srcBucket")
				.build();
Node dstNode = Node.builder()
				.region("dstRegion")
				.ak("dstAk")
				.sk("dstSk")
				.objectKey("dstObjectKey")
				.bucket("dstBucket")
				.build();

SmnInfo smnInfo = SmnInfo.builder()
				.topicUrn("topicUrn")
				.language("en-us")
				.triggerConditions(Lists.newArrayList(TriggerCondition.SUCCESS, TriggerCondition.FAIL))
				.build();

TaskCreate create = TaskCreate.builder()
					.srcNode(srcNode)
					.dstNode(dstNode)
					.enableKMS(false)
					.threadNum(5)
					.description("description")
					.smnInfo(smnInfo)
					.build();
TaskCreateResp resp = osclient.maas().task().create(create);
```

### Delete Task
```java
ActionResponse resp = osclient.maas().task().delete(taskId);
```

### Start Task
```java
TaskStart task = TaskStart.builder()
					.sourceAk(srcAk)
					.sourceSk(srcSk)
					.targetAk(dstAk)
					.targetSk(dstSk)
					.build();
ActionResponse resp = osclient.maas().task().start(taskId, task);
```

### Stop Task
```java
ActionResponse resp = osclient.maas().task().stop(taskId);
```

### List Task
```java
TaskListOptions options = TaskListOptions.create().start(0).limit(10);
Task[] list = osclient.maas().task().list(options);
```

### Count Task
```java
long count = osclient.maas().task().count();
```

### Get Task
```java
Task task = osclient.maas().task().get(taskId);
```