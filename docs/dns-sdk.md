# DNS SDk

HuaWei OpenStack4j DNS SDK, entry point is: `osclient.dns()`

## API document
Not provided for now.

## SDK document

### initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk) page .

### List Zones
```java
List<? extends Zone> list = osclient.dns().zones().list();
```

### Create Zone
```java
```

### Get Zone
```java
Zone zone = osclient.dns().zones().get("zone-id");
```

### Delete Zone
```java
ActionResponse response = osclient.dns().zones().delete("zone-id");
if (response.isSuccess()) {
	//
}
```

### List Recordsets
```java
List<? extends Recordset> recordsetsOfZone = osclient.dns().recordsets().list("zone-id");
logger.info("recordsets of zone: {}", recordsetsOfZone);

List<? extends Recordset> allRecordsets = osclient.dns().recordsets().list();
logger.info("all recordsets", allRecordsets);
```

### Create Recordset
```java
// create with recordset model
Recordset recordset = Builders.recordset().name("").type("A").build();
Recordset created = osclient.dns().recordsets().create("zone-id", recordset);
logger.info("{}", created);

// create directly
Recordset created2 = osclient.dns().recordsets().create("zone-id", "name", "dns-type",
		Lists.newArrayList("record1", "records2", "..."));
logger.info("{}", created2);
```

### Get Recordset
```java
Recordset recordset = osclient.dns().recordsets().get("zone-id", "recordset-id");
logger.info("recordset", recordset);
```

### Delete Recordset
```java
ActionResponse response = osclient.dns().recordsets().delete("zone-id", "recordset-id");
if (response.isSuccess()) {
	// 
}
```



