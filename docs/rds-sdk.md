# RDS SDK

OTC OpenStack4j RDS SDK 
- Service Entry: `osclient.database()`
- Service Type: `database`


## API Documentation

Refer: [Official API documentation](https://docs.otc.t-systems.com/en-us/api/rds/en-us_topic_0032347780.html)

## Language Setting

Trove API SDK support language switch

```
// set language to zh-cn
Config config = Config.newConfig().withLanguage("zh-cn");

OSClientV3 osclient = OSFactory.builderV3().withConfig(config).endpoint(authUrl)
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId))
		.authenticate();
```
    
## Service Version
### List Service Version
```java
List<ServiceVersion> versions = osclient.database().versions().list();
```

### Get Service Version
```java
ServiceVersion serviceVersion = osclient.database().versions().get("version-id");
```


## 数据库版本
### 获取数据库版本信息
```java
List<DatastoreVersion> versions = osclient.database().datastores().listDatastoreVersions(DatastoreType.MySQL);
```


## Instance management
### Create instance
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

### Resize instance volume
```java
Integer volumeSize = 200;
List<String> jobIds = osclient.database().instances().resize("instance-id", volumeSize);
```

### Resize instance flavor
```java
List<String> jobIds = osclient.database().instances().resize("instance-id", "new-flavor-id");
```

### Restart instance
```java
List<String> jobIds = osclient.database().instances().restart("instance-id");
```

### Delete instance
```java
String jobId = osclient.database().instances().delete("instance-id");
```

### List instance
```java
List<DatabaseInstance> instances = osclient.database().instances().list();
```

### Get instance
```java
DatabaseInstance instance = osclient.database().instances().get("instance-id");
```

### List instance flavor
```java
List<InstanceFlavor> flavors = osclient.database().flavors().list("databaseId", "region");
```

### Get instance flavor
```java
InstanceFlavor flavor = osclient.database().flavors().get("flavor-id");
```


## Parameter Configuration
### List Parameter
```java
List<DatabaseParam> params = osclient.database().params().list("databaseId");
```

### Get Parameter
```java
DatabaseParam param = osclient.database().params().get("databaseId", "parameter1");
```

### Update parameters of instance
```java
Map<String, Object> params = Maps.newHashMap();
params.put("connect_timeout", 17);
params.put("sync_binlog", 1);
InstanceParamOperationResult result = osclient.database().params().config("instance-id", params);
```

### Restore parameters of instance
```java
InstanceParamOperationResult result = osclient.database().params().restore("instance-id");
```

## Backup and Restore
### Update backup policy
```java
DatabaseBackupPolicy policy = DatabaseBackupPolicy.builder().keepDay(3).startTime("03:10:00").build();
ActionResponse response = osclient.database().backups().updateBackupPolicy("instance-id", policy);
```

### Get backup policy
```java
DatabaseBackupPolicy policy = osclient.database().backups().getBackupPolicy("instance-id");
```

### Create snapshot
```java
DatabaseBackupCreate creation = DatabaseBackupCreate.builder().name("backup-name").description("sdk unittests")
				.instance("instance-id").build();
DatabaseBackupCreateResponse response = osclient.database().backups().create(creation);
```

### List snapshot
```java
List<DatabaseBackup> backups = osclient.database().backups().list();
```

### Delete snapshot
```java
ActionResponse response = osclient.database().backups().delete("backup-id");
```

### Restore backup to exist instance
```java
Date now = new Date();
DatabaseRestorePoint point = DatabaseRestorePoint.builder().backupRef("backup-ref").restoreTime(now).build();
List<String> jobIds = osclient.database().backups().restoreToExistInstance("restore-to", point);
```

### Restore backup to new instance
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

## Database log
### List Error Log
```java
Date now = new Date(1502531254806L);
Date tenDaysAgo = new Date(now.getTime() - 10 * 24 * 3600 * 1000);
ErrorLogListOptions options = ErrorLogListOptions.create().instanceId("instance-id").startDate(tenDaysAgo)
		.endDate(now).curPage(1).perPage(20);
List<DatabaseErrorLog> list = osclient.database().logs().listErrorLogs(options);
```

### List slow query log
```java
int top = 30;
List<DatabaseSlowLog> list = osclient.database().logs().listSlowLogs("instance-id", StatementType.SELECT, top);
```
