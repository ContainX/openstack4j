# Volume Backup SDK

OTC OpenStack4j Volume Backup SDK
- 服务入口: `osclient.storage()`
- 服务类型: `vbs`

!> volume backup 服务是同一挂载在 storage 下，里面包含了 native volume-backup 和 这次新增的 API

## 云硬盘备份
### 创建备份

```java
VBSVolumeBackupCreate vbc = Builders.asyncVolumeBackupCreate().name("backup-name").volumeId("volume-id")
				.build();
AsyncVolumeBackupJob job = osclient.blockStorage().asyncBackups().create(vbc);
AsyncVolumeBackupJob jobDetail = osclient.blockStorage().jobs().get(job.getId())
```

### 从备份恢复磁盘
```java
AsyncVolumeBackupJob job = osclient.blockStorage().asyncBackups().restore("volume-backup-id", "volume-id");
AsyncVolumeBackupJob jobDetail = osclient.blockStorage().jobs().get(job.getId());
```

### 创建备份（OpenStack原生）
```java
VolumeBackupCreate create = Builders.volumeBackupCreate().volumeId("volume-id").container("container123")
				.description("description123").name(name).incremental(false).build();
VolumeBackup backup = osclient.blockStorage().backups().create(create);
```

### 查询备份概要信息列表（OpenStack原生）
```java
// 无查询条件
List<? extends VolumeBackup> list = osclient.blockStorage().backups().list(false);

HashMap<String, String> filter = Maps.newHashMap();
filter.put("name", backupName);
List<? extends VolumeBackup> list2 = osclient.blockStorage().backups().list(false, filter);
```

### 查询备份详细信息列表（OpenStack原生）
```java
// 无查询条件
List<? extends VolumeBackup> list = osclient.blockStorage().backups().list(true);

HashMap<String, String> filter = Maps.newHashMap();
filter.put("name", backupName);
List<? extends VolumeBackup> list2 = osclient.blockStorage().backups().list(true, filter);
```

### 查询单个备份详情（OpenStack原生）
```java
VolumeBackup volumeBackup = osclient.blockStorage().backups().get("volume-backup-id");
```


### 删除备份（OpenStack原生）
```java
ActionResponse delete = osclient.blockStorage().backups().delete("volume-backup-id");
```

### 查询job的状态
```java
AsyncVolumeBackupJob jobDetail = osclient.blockStorage().jobs().get("job-id");
```

## 备份策略

### 创建备份策略
```java
// 先创建 scheduled policy
VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().frequency(10)
		.maxBackupAmount(10).retainFirstBackupOfCurrentMonth(true).startTime("01:00")
		.status(VolumeBackupPolicyStatus.ON).build();

// 创建 backup policy 对象
VolumeBackupPolicy create = VBSVolumeBackupPolicy.builder().name(policyName).scheduledPolicy(scheduledPolicy)
		.build();
VolumeBackupPolicy policy = osclient.blockStorage().policies().create(create);
```

### 查询备份策略
```java
List<? extends VolumeBackupPolicy> policies = osclient.blockStorage().policies().list();
```

### 修改备份策略
```java
// 可以只修改部分字段
VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().frequency(5)
				.maxBackupAmount(5).retainFirstBackupOfCurrentMonth(false).status(VolumeBackupPolicyStatus.OFF).build();

VBSVolumeBackupPolicy policy = VBSVolumeBackupPolicy.builder().id(this.policy.getId())
		.scheduledPolicy(scheduledPolicy).build();
osclient.blockStorage().policies().update(policy);
```

### 删除备份策略
```java
osclient.blockStorage().policies().delete("policy-id");
```

### 绑定资源到备份策略
```java
List<String> resources = Lists.newArrayList("volume-id-1", "volume-id-2");
VolumeBackupPolicyResourceActionResult result = osclient.blockStorage().policies()
				.linkResources("policy-id", resources);
```

### 从备份策略解绑资源
```java
List<String> resources = Lists.newArrayList("volume-id-1", "volume-id-2");
VolumeBackupPolicyResourceActionResult result = osclient.blockStorage().policies()
				.unlinkResources("policy-id", resources);
```

### 立即执行备份策略
```java
osclient.blockStorage().policies().execute("policy-id");
```

### 启用备份策略
```java
osclient.blockStorage().policies().enable("policy-id");
```

### 停用备份策略
```java
osclient.blockStorage().policies().disable("policy-id");
```

### 查询策略下的备份任务
```java
BakcupTaskListOptions options = BakcupTaskListOptions.create().asc().limit(10);
List<? extends VolumeBackupPolicyBackupTask> tasks = osclient.blockStorage().policies().tasks("policy-id", options);
```
