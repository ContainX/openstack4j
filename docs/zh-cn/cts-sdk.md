# CTS SDK

OTC OpenStack4j Cloud Trace SDK
- 服务入口: `osclient.cloudTraceV1()` (版本一)
- 服务入口: `osclient.cloudTraceV2()` (版本二)
- 服务类型: `cloud-trace` 


## API documentation

请查阅 [官方接口文档](https://docs.otc.t-systems.com/en-us/api/cts/en-us_topic_0044332888.html)

    
## 追踪器管理

### 查询追踪器列表
```java
List<Tracker> trackers = osclient.cloudTraceV1().trackers().list();
```

### 创建追踪器
```java
Tracker tracker = osclient.cloudTraceV1().trackers().create("some-bucket-name", "file-prefix")
```

### 修改追踪器
```java
// 你要修改的 tracker 的名称，目前只有一个tracker "system“
String trackerName = "system";
TrackerUpdate update = TrackerUpdate.builder().trackerName(trackerName).bucketName("another-bucket-name")
							.filePrefixName("SDK-unittest").status(TrackerStatus.Enabled).build();
Tracker updated = osclient.cloudTraceV1().trackers().update(update);
```

### 查询追踪器
```java
// 你要查询的 tracker的名称
String trackerName = "system";
Tracker get = osclient.cloudTraceV1().trackers().get(trackerName);
```

### 删除追踪器
```java
// 你要删除的tracker的名称
String trackerName = "system";
ActionResponse delete = osclient.cloudTraceV1().trackers().delete(trackerName);
System.out.println(delete.isSuccess());
```

### 删除所有追踪器
```java
ActionResponse delete = osclient.cloudTraceV1().trackers().deleteAll();
System.out.println(delete.isSuccess());
```

## 事件管理
### 查询事件列表（v1.0）
```java
TraceListOptions options = TraceListOptions.create().limit(5).user("zhangdong").serviceType("CTS");
List<Trace> list = osclient.cloudTraceV1().traces().list("system", options);
```

### 查询事件列表（v2.0）
```java
TraceListOptions options = TraceListOptions.create().limit(5).user("zhangdong").serviceType("CTS");
List<Trace> list = osclient.cloudTraceV2().traces().list("system", options);
```
    