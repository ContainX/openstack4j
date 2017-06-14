# VBS SDk

HuaWei Volume Backup service SDK

## API document

Visit official [document](http://support.hwclouds.com/api-vbs/zh-cn_topic_0020237255.html) for API documents.

## SDK document

### initial SDK client

You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .


### create volume backup

```
VBSVolumeBackupCreate vbc = VBSVolumeBackupCreate.builder().name("qianbiao-ng-os4j-1")
				.volumeId("0a3218ef-7841-45c5-b9a1-5da6e0b70b85").build();
				
// use cloud-volume-backup SDK to create a volume backup
CloudVolumeBackupJob job = osclient.cloudVolumeBackup().create(vbc);
```

### restore volume backup