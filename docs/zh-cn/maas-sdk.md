# MaaS SDK

OTC OpenStack4j MaaS SDK
- 服务入口: `osclient.maas()`
- 服务类型: `MaaS`

## API版本
### 获取API版本
```java
Version[] version = osclient.maas().version().get();
```

## 任务接口
### 创建任务
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

### 删除任务
```java
ActionResponse resp = osclient.maas().task().delete(taskId);
```

### 启动任务
```java
TaskStart task = TaskStart.builder()
					.sourceAk(srcAk)
					.sourceSk(srcSk)
					.targetAk(dstAk)
					.targetSk(dstSk)
					.build();
ActionResponse resp = osclient.maas().task().start(taskId, task);
```

### 停止任务
```java
ActionResponse resp = osclient.maas().task().stop(taskId);
```

### 查询租户所有任务
```java
TaskListOptions options = TaskListOptions.create().start(0).limit(10);
Task[] list = osclient.maas().task().list(options);
```

### 查询租户任务总数
```java
long count = osclient.maas().task().count();
```

### 获取指定ID任务
```java
Task task = osclient.maas().task().get(taskId);
```