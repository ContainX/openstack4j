# Anti-DDoS SDK

HuaWei OpenStack4j MaaS SDK, entry point is: `osclient.antiddos()`

## API document
Not provided for now.

## SDK document

### initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .

## Anti-DDoS
### List Configuration
```java
AntiDDoSConfig configs = osclient.antiDDoS().antiddos().listConfigs();
```

### Enable Anti-DDoS
```java
AntiDDoS entity = AntiDDoS.builder()
				.enableL7(true)
				.trafficPos(TrafficPos.POS_1)
				.httpRequestPos(HttpRequestPos.POS_1)
				.cleaningAccessPos(CleaningAccessPos.POS_1)
				.appType(AppType.Type_0)
				.build();
Task task = osclient.antiDDoS().antiddos().create(entity, "floatingIpId");
```

### Disable Anti-DDoS
```java
Task task = osclient.antiDDoS().antiddos().delete("floatingIpId");
```

### Get Anti-DDoS
```java
AntiDDoS antiDDoS = osclient.antiDDoS().antiddos().get("floatingIpId");
```

### Update Anti-DDoS
```java
AntiDDoS entity = ...;//get antiddos
Task task = osclient.antiDDoS().antiddos().update(entity, "floatingIpId");
```

### Get Task
```java
Task task = osclient.antiDDoS().antiddos().getTask("taskId");
```

### List Anti-DDoS Status
```java
AntiDDoSStatus statuses = osclient.antiDDoS().antiddos().listStatus();

AntiDDoSStatusListOptions options = AntiDDoSStatusListOptions.create().status(Status.NORMAL);
AntiDDoSStatus statuses2 = osclient.antiDDoS().antiddos().listStatus(options);
```

### Get Anti-DDoS Status
```java
AntiDDoSStatusDetail status = osclient.antiDDoS().antiddos().getStatus("floatingIpId");
```

### Anti-DDoS Daily Report
```java
List<? extends AntiDDoSDailyData> dailyReport = osclient.antiDDoS().antiddos().dailyReport("floatingIpId")
```

### Anti-DDoS Weekly Report
```java
AntiDDoSWeeklyData weekly = osclient.antiDDoS().antiddos().weeklyReport();

Date date = ...;//start date
AntiDDoSWeeklyData weekly2 = osclient.antiDDoS().antiddos().weeklyReport(date);
```

### Anti-DDoS Logs
```java
List<? extends AntiDDoSLog> logs = osclient.antiDDoS().antiddos().listLogs("floatingIpId);

AntiDDoSLogListOptions options = AntiDDoSLogListOptions.create().limit(1).offset(1);
List<? extends AntiDDoSLog> logs2 = osclient.antiDDoS().antiddos().listLogs("floatingIpId", options);
```

## Warn Alert
### Query Warn Alert 
```java
AntiDDoSWarn query = osclient.antiDDoS().warnalert().query();
```