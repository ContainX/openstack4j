# CTS SDK

OTC OpenStack4j Cloud Trace SDK
- Service Entry: `osclient.cloudTraceV1()` (Version 1)
- Service Entry: `osclient.cloudTraceV2()` (Versoin 2)
- Service Type: `cloud-trace` 


## API documentation

Refer: [Official API documentation](https://docs.otc.t-systems.com/en-us/api/cts/en-us_topic_0044332888.html)

    
## Tracker

### List Tracker
```java
List<Tracker> trackers = osclient.cloudTraceV1().trackers().list();
```

### Create Tracker
```java
Tracker tracker = osclient.cloudTraceV1().trackers().create("some-bucket-name", "file-prefix")
```

### Update Tracker
```java
// the name of the tracker which you want to update, now only one tracker named system exists
String trackerName = "system";
TrackerUpdate update = TrackerUpdate.builder().trackerName(trackerName).bucketName("another-bucket-name")
							.filePrefixName("SDK-unittest").status(TrackerStatus.Enabled).build();
Tracker updated = osclient.cloudTraceV1().trackers().update(update);
```

### Get Tracker
```java
// the name of the tracker which you want to get 
String trackerName = "system";
Tracker get = osclient.cloudTraceV1().trackers().get(trackerName);
```

### Delete Tracker
```java
// the name of the tracker which you want to delete 
String trackerName = "system";
ActionResponse delete = osclient.cloudTraceV1().trackers().delete(trackerName);
System.out.println(delete.isSuccess());
```

### Delete all Tracker
```java
ActionResponse delete = osclient.cloudTraceV1().trackers().deleteAll();
System.out.println(delete.isSuccess());
```

## Trace
### List Trace（v1.0）
```java
TraceListOptions options = TraceListOptions.create().limit(5).user("zhangdong").serviceType("CTS");
List<Trace> list = osclient.cloudTraceV1().traces().list("system", options);
```

### List Trace（v2.0）
```java
TraceListOptions options = TraceListOptions.create().limit(5).user("zhangdong").serviceType("CTS");
List<Trace> list = osclient.cloudTraceV2().traces().list("system", options);
```
    