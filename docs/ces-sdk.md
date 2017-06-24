# Cloud Eye SDk

HuaWei OpenStack4j Cloud Eye SDK, entry point is: `osclient.cloudeye()`

## API document
Not provided for now.

## initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .


## Metrics

### List All Metrics
```java
List<? extends Metric> list1 = osclient.cloudEye().metrics().getList();
```

### Filter Metrics
```java
//The index of dimension, supports up to three dimensions at most, the string format = key, value. For example: instance_id, 6f3c6f91-4b24-4e1b-b7d1-a94ac1cb011d
String[] dims = new String[]{"instance_id,5b4c1602-fb6d-4f1e-87a8-dcf21d9654ba"}; 
MetricFilterOptions config = MetricFilterOptions.create();
MetricFilterOptions options = config.dim(dims); //Optional
options.limit(50); //Optional
//Optional, accept two values: OrderType.ASC, OrderType.DESC.
options.order(OrderType.ASC); 
//Optional, the namespace the metrics belongs to.
options.namespace("SYS.ECS"); 
 //Optional, the metric's name
options.metricName("network_outgoing_bytes_aggregate_rate");
//Optional, The paging start value in the format: namespace.metric_name.key: value
options.start("SYS.ECS.network_outgoing_bytes_aggregate_rate.instance_id:5b4c1602-fb6d-4f1e-87a8-dcf21d9654ba");

List<? extends Metric> list2 = osclient.cloudEye().metrics().getList(options);
```

### List Favorite Metrics
```java
List<? extends Metric> list = osclient.cloudEye().metrics().getFavoriteList();
```


## Alarms
### List Alarms
```java
  List<? extends Alarm> list1 = osclient.cloudEye().alarms().getList();
```

### Filter Alarms
```java
AlarmFilterOptions config = AlarmFilterOptions.create();
AlarmFilterOptions options = config.limit(5); //Optional
//Optional, accept two values: OrderType.ASC, OrderType.DESC.
options.order(OrderType.ASC); 
//Optional, the paging start from which alarm id
options.start("al1483387711418ZNpR8DX3g"); 

List<? extends Alarm> list2 = osclient.cloudEye().alarms().getList(options);
```

### Get Specific Alarm by ID
```java
String ALARM_ID = "al1483387711418ZNpR8DX3g";
List<? extends Alarm> alarm = osclient.cloudEye().alarms().get(ALARM_ID);
```

### Start Specific Alarm
```java
String ALARM_ID = "al1483387711418ZNpR8DX3g";
ActionResponse actionResponse = osclient.cloudEye().alarms().startAlarm(ALARM_ID);
```

### Stop Specific Alarm
```java
String ALARM_ID = "al1483387711418ZNpR8DX3g";
ActionResponse actionResponse = osclient.cloudEye().alarms().stopAlarm(ALARM_ID);
```

### Delete Specific Alarm
```java
String ALARM_ID = "al1483387711418ZNpR8DX3g";
ActionResponse actionResponse = osclient.cloudEye().alarms().deleteAlarm(ALARM_ID);
```