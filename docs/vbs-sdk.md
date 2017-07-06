# Volume Backup SDK

OTC OpenStack4j Volume Backup SDK
- Service Entry: `osclient.storage()`
- Service name: `vbs`

!> volume backup add several addition API to native openstack "volume-backup" API

## Volume Backup
### Create Volume Backup

```java
VBSVolumeBackupCreate vbc = Builders.asyncVolumeBackupCreate().name("backup-name").volumeId("volume-id")
				.build();
AsyncVolumeBackupJob job = osclient.blockStorage().asyncBackups().create(vbc);
AsyncVolumeBackupJob jobDetail = osclient.blockStorage().jobs().get(job.getId())
```

### Restore Volume Backup
```java
AsyncVolumeBackupJob job = osclient.blockStorage().asyncBackups().restore("volume-backup-id", "volume-id");
AsyncVolumeBackupJob jobDetail = osclient.blockStorage().jobs().get(job.getId());
```

### Create Volume Backup（OpenStack Native）
```java
VolumeBackupCreate create = Builders.volumeBackupCreate().volumeId("volume-id").container("container123")
				.description("description123").name(name).incremental(false).build();
VolumeBackup backup = osclient.blockStorage().backups().create(create);
```

### List Volume Backup Brief（OpenStack Native）
```java
// No filter
List<? extends VolumeBackup> list = osclient.blockStorage().backups().list(false);

// with filter
HashMap<String, String> filter = Maps.newHashMap();
filter.put("name", backupName);
List<? extends VolumeBackup> list2 = osclient.blockStorage().backups().list(false, filter);
```

### List Volume Backup Detail（OpenStack Native）
```java
// No filter
List<? extends VolumeBackup> list = osclient.blockStorage().backups().list(true);

// with filter
HashMap<String, String> filter = Maps.newHashMap();
filter.put("name", backupName);
List<? extends VolumeBackup> list2 = osclient.blockStorage().backups().list(true, filter);
```

### Get Volume Backup Detail（OpenStack Native）
```java
VolumeBackup volumeBackup = osclient.blockStorage().backups().get("volume-backup-id");
```


### Delete Volume Backup（OpenStack Native）
```java
ActionResponse delete = osclient.blockStorage().backups().delete("volume-backup-id");
```

### Query asynchronous job
```java
AsyncVolumeBackupJob jobDetail = osclient.blockStorage().jobs().get("job-id");
```

## Volume Backup Policy

### Create Volume Backup Policy
```java
// Create scheduled policy
VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().frequency(10)
		.maxBackupAmount(10).retainFirstBackupOfCurrentMonth(true).startTime("01:00")
		.status(VolumeBackupPolicyStatus.ON).build();

// Create backup policy 
VolumeBackupPolicy create = VBSVolumeBackupPolicy.builder().name(policyName).scheduledPolicy(scheduledPolicy)
		.build();
VolumeBackupPolicy policy = osclient.blockStorage().policies().create(create);
```

### List Volume Backup Policy
```java
List<? extends VolumeBackupPolicy> policies = osclient.blockStorage().policies().list();
```

### Update Volume Backup Policy
```java
// update part of scheduled policy
VBSVolumeBackupScheduledPolicy scheduledPolicy = VBSVolumeBackupScheduledPolicy.builder().frequency(5)
				.maxBackupAmount(5).retainFirstBackupOfCurrentMonth(false).status(VolumeBackupPolicyStatus.OFF).build();

VBSVolumeBackupPolicy policy = VBSVolumeBackupPolicy.builder().id(this.policy.getId())
		.scheduledPolicy(scheduledPolicy).build();
osclient.blockStorage().policies().update(policy);
```

### Delete Volume Backup Policy
```java
osclient.blockStorage().policies().delete("policy-id");
```

### Link resources to Volume Backup Policy
```java
List<String> resources = Lists.newArrayList("volume-id-1", "volume-id-2");
VolumeBackupPolicyResourceActionResult result = osclient.blockStorage().policies()
				.linkResources("policy-id", resources);
```

### Unlink Volume Backup Policy Resources
```java
List<String> resources = Lists.newArrayList("volume-id-1", "volume-id-2");
VolumeBackupPolicyResourceActionResult result = osclient.blockStorage().policies()
				.unlinkResources("policy-id", resources);
```

### Execute Volume Backup Policy
```java
osclient.blockStorage().policies().execute("policy-id");
```

### Enable Volume Backup Policy
```java
osclient.blockStorage().policies().enable("policy-id");
```

### Disable Volume Backup Policy
```java
osclient.blockStorage().policies().disable("policy-id");
```

### List Volume Backup Policy Tasks
```java
BakcupTaskListOptions options = BakcupTaskListOptions.create().asc().limit(10);
List<? extends VolumeBackupPolicyBackupTask> tasks = osclient.blockStorage().policies().tasks("policy-id", options);
```
