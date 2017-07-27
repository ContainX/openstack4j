# Anti-DDoS SDK

OTC OpenStack4j Anti-DDoS SDK
- 服务入口: `osclient.antiddos()`
- 服务类型: `anti-ddos`

## Anti-DDoS
### 查询Anti-DDoS配置可选范围
```java
AntiDDoSConfig configs = osclient.antiDDoS().antiddos().listConfigs();
```

### 开通Anti-DDoS服务
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

### 关闭Anti-DDoS服务
```java
Task task = osclient.antiDDoS().antiddos().delete("floatingIpId");
```

### 查询Anti-DDoS服务
```java
AntiDDoS antiDDoS = osclient.antiDDoS().antiddos().get("floatingIpId");
```

### 更新Anti-DDoS服务
```java
AntiDDoS entity = ...;//get antiddos
Task task = osclient.antiDDoS().antiddos().update(entity, "floatingIpId");
```

### 查询Anti-DDoS任务
```java
Task task = osclient.antiDDoS().antiddos().getTask("taskId");
```

### 查询EIP防护状态列表
```java
AntiDDoSStatus statuses = osclient.antiDDoS().antiddos().listStatus();

AntiDDoSStatusListOptions options = AntiDDoSStatusListOptions.create().status(Status.NORMAL);
AntiDDoSStatus statuses2 = osclient.antiDDoS().antiddos().listStatus(options);
```

### 查询指定EIP防护状态列表
```java
AntiDDoSStatusDetail status = osclient.antiDDoS().antiddos().getStatus("floatingIpId");
```

### 查询指定EIP防护流量
```java
List<? extends AntiDDoSDailyData> dailyReport = osclient.antiDDoS().antiddos().dailyReport("floatingIpId")
```

### 查询周防护统计情况
```java
AntiDDoSWeeklyData weekly = osclient.antiDDoS().antiddos().weeklyReport();

Date date = ...;//start date
AntiDDoSWeeklyData weekly2 = osclient.antiDDoS().antiddos().weeklyReport(date);
```

### 查询指定EIP异常事件
```java
List<? extends AntiDDoSLog> logs = osclient.antiDDoS().antiddos().listLogs("floatingIpId);

AntiDDoSLogListOptions options = AntiDDoSLogListOptions.create().limit(1).offset(1);
List<? extends AntiDDoSLog> logs2 = osclient.antiDDoS().antiddos().listLogs("floatingIpId", options);
```

## 告警提醒
### 查询告警配置信息 
```java
AntiDDoSWarn query = osclient.antiDDoS().warnalert().query();
```