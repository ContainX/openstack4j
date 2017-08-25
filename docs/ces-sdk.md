# Cloud Eye SDK

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

## Metric Data
### Get Metric Aggregation Data
```java
String namespace = "SYS.ECS";
String metric_name = "network_incoming_bytes_aggregate_rate";
Date from = new Date(1498321875058l);
Date to = new Date(); 
Period period = Period.FIVE_MINS;
Filter filter = Filter.AVERAGE;
String[] dimValues = new String[]{"instance_id,33328f02-3814-422e-b688-bfdba93d4050"};
MetricAggregation metricAggregation = osv3().cloudEye().metricsDatas().get(namespace, metric_name, from, to, period, filter, dimValues);
```

### Add Metric Data
```java
List<CloudEyeMetricData> metrics = new ArrayList<>();
//Must begin with the letter, can only contain 0-9 / a-z / A-Z / _ / -, the length of the shortest 1, the maximum 32.
String demensionName = "instance_id"; 
//Must start with a letter or number, only 0-9 / a-z / A-Z / _ / -, with a minimum length of 1 and a maximum of 64.
String demensionValue = "33328f02-3814-422e-b688-bfdba93d4050";
CloudEyeMetricDemension.CloudEyeMetricDemensionBuilder dimBuilder = CloudEyeMetricDemension.builder().name(demensionName).value(demensionValue);
CloudEyeMetricDemension dim1 = dimBuilder.build();
List<CloudEyeMetricDemension> dimList = new ArrayList<>();
dimList.add(dim1);

//Must begin with a letter and can only contain 0-9 / a-z / A-Z / _, with a minimum length of 1 and a maximum of 64.
String metricName = "cpu_util";
//Must start with the letter, can only contain 0-9 / az / AZ / _, the total length of the shortest 3, the maximum is 32, service can not be " SYS ".
String metricNamespace = "MINE.APP";

CloudEyeMetric.CloudEyeMetricBuilder metricBuilder = CloudEyeMetric.builder().namespace(metricNamespace)
        .metricName(metricName)
        .dimensions(dimList);
CloudEyeMetricData.CloudEyeMetricDataBuilder builder1 = CloudEyeMetricData.builder()
        .metric(metricBuilder.build())
        .ttl(172800) //the maximun is 604800
        .collectTime(new Date())
        .value(60)
        .unit("%");

CloudEyeMetric.CloudEyeMetricBuilder metricBuilder2 = CloudEyeMetric.builder().namespace("MINE.APP")
        .metricName(metricName)
        .dimensions(dimList);
CloudEyeMetricData.CloudEyeMetricDataBuilder builder2 = CloudEyeMetricData.builder()
        .metric(metricBuilder2.build())
        .ttl(172800)
        .collectTime(new Date())
        .value(70)
        .unit("%");
metrics.add(builder1.build());
metrics.add(builder2.build());

ActionResponse actionResponse = osclient.cloudEye().metricsDatas().add(metrics);
```

## Quota
### Get Quota
```java
List<Quota> list = osclient.cloudEye().quotas().get();
```