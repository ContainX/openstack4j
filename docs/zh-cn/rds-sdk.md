# RDS SDK

OTC OpenStack4j RDS SDK 
- 服务入口: `osclient.database()`
- 服务类型: `database`


## API接口文档

请查阅 [官方接口文档](https://docs.otc.t-systems.com/en-us/api/rds/en-us_topic_0032347780.html)

## 语言设置

RDS SDK 可以选择性的设置客户端想要使用的语言。

```
// 设置语言为 zh-cn
Config config = Config.newConfig().withLanguage("zh-cn");

OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId))
		.authenticate();
```

    
## 获取API版本
### 查询API版本列表
```java
List<ServiceVersion> versions = osclient.database().versions().list();
```

### 查询API版本信息
```java
ServiceVersion serviceVersion = osclient.database().versions().get("version-id");
```


## 数据库版本
### 获取数据库版本信息
```java
List<DatastoreVersion> versions = osclient.database().datastores().listDatastoreVersions(DatastoreType.MySQL);
```


## 实例管理
### 创建实例
```java
// build datastore version
Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("6.3.35").build();

// get flavor
Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();

NIC nic = NIC.builder().subnetId("network-id").build();
BackupStrategy backupStrategy = BackupStrategy.builder().keepDays(1).startTime("15:00:00").build();
HA ha = HA.builder().enable(true).replicationMode(ReplicationMode.ASYNC).build();

DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().name("sdk-test").datastore(datastore)
		.flavorRef("flavor-id").volume(volume).region("eu-de").availabilityZone("eu-de-01").vpcId("vpc-id")
		.nic(nic).securityGroup(new IdResourceEntity("sg-id")).rootPassword("1qaz@WSX")
		.backupStrategy(backupStrategy).ha(ha).build();

DatabaseInstance instance = osclient.database().instances().create(instanceCreate);

```

### 调整实例容量
```java
Integer volumeSize = 200;
List<String> jobIds = osclient.database().instances().resize("instance-id", volumeSize);
```

### 调整实例规格
```java
List<String> jobIds = osclient.database().instances().resize("instance-id", "new-flavor-id");
```

### 重启实例
```java
List<String> jobIds = osclient.database().instances().restart("instance-id");
```

### 删除实例
```java
String jobId = osclient.database().instances().delete("instance-id");
```

### 获取实例列表
```java
List<DatabaseInstance> instances = osclient.database().instances().list();
```

### 获取指定实例详细信息
```java
DatabaseInstance instance = osclient.database().instances().get("instance-id");
```

### 获取所有实例规格信息
```java
List<InstanceFlavor> flavors = osclient.database().flavors().list("databaseId", "region");
```

### 获取指定实例规格信息
```java
InstanceFlavor flavor = osclient.database().flavors().get("flavor-id");
```


## 参数配置
### 获取参数列表
```java
List<DatabaseParam> params = osclient.database().params().list("databaseId");
```

### 获取配置参数信息	
```java
DatabaseParam param = osclient.database().params().get("databaseId", "parameter1");
```

### 设置配置参数
```java
Map<String, Object> params = Maps.newHashMap();
params.put("connect_timeout", 17);
params.put("sync_binlog", 1);
InstanceParamOperationResult result = osclient.database().params().config("instance-id", params);
```

### 恢复默认参数
```java
InstanceParamOperationResult result = osclient.database().params().restore("instance-id");
```

## 备份与恢复
### 设置自动备份策略
```java
DatabaseBackupPolicy policy = DatabaseBackupPolicy.builder().keepDay(3).startTime("03:10:00").build();
ActionResponse response = osclient.database().backups().updateBackupPolicy("instance-id", policy);
```

### 获取自动备份策略
```java
DatabaseBackupPolicy policy = osclient.database().backups().getBackupPolicy("instance-id");
```

### 创建快照
```java
DatabaseBackupCreate creation = DatabaseBackupCreate.builder().name("backup-name").description("sdk unittests")
				.instance("instance-id").build();
DatabaseBackupCreateResponse response = osclient.database().backups().create(creation);
```

### 以列表形式返回快照信息	
```java
List<DatabaseBackup> backups = osclient.database().backups().list();
```

### 删除快照信息
```java
ActionResponse response = osclient.database().backups().delete("backup-id");
```

### 恢复当前指定实例
```java
Date now = new Date();
DatabaseRestorePoint point = DatabaseRestorePoint.builder().backupRef("backup-ref").restoreTime(now).build();
List<String> jobIds = osclient.database().backups().restoreToExistInstance("restore-to", point);
```

### 恢复到新实例
```java
Date now = new Date(1502617216267L);
Volume volume = Volume.builder().size(100).build();
HA ha = HA.builder().enable(true).replicationMode(ReplicationMode.ASYNC).build();
DatabaseRestorePoint restorePoint = DatabaseRestorePoint.builder().backupRef("backup-ref").restoreTime(now)
		.sourceInstanceId("source-instance").build();
		
DatabaseInstanceRestore restore = DatabaseInstanceRestore.builder().name("bakup").flavorRef("flavor-ref")
		.volume(volume).ha(ha).restorePoint(restorePoint).build();
DatabaseInstance instance = osclient.database().backups().restoreToNewInstance(restore);
```

## 获取日志信息
### 查询数据库错误日志
```java
Date now = new Date(1502531254806L);
Date tenDaysAgo = new Date(now.getTime() - 10 * 24 * 3600 * 1000);
ErrorLogListOptions options = ErrorLogListOptions.create().instanceId("instance-id").startDate(tenDaysAgo)
		.endDate(now).curPage(1).perPage(20);
List<DatabaseErrorLog> list = osclient.database().logs().listErrorLogs(options);
```

### 查询数据库慢日志
```java
int top = 30;
List<DatabaseSlowLog> list = osclient.database().logs().listSlowLogs("instance-id", StatementType.SELECT, top);
```
