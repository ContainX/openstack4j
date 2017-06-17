# DNS SDk

HuaWei OpenStack4j DNS SDK, entry point is: `osclient.dns()`

## API document
Not provided for now.

## initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .


## Zone

### List All Zones
```java
List<? extends Zone> list = osclient.dns().zones().list();
```

### List Specific Zones 
```java
String type = "public"; //null -> query all zones, public -> query all public zones, private -> query all private zones
String marker = null; //the initial ID of a paging query, if null, query the first page
String limit = "2"; //per page's item quantity. Value can be 0~500
List<? extends Zone> list = osclient.dns().zones().list(type, marker, limit);
```

### Create Zone
```java
String name = "example.com.";
String description = "This is an example zone.";
ZoneBuilder builder = Builders.zone();
Zone zone = builder.name(name).description(description).build();
Zone zoneResult = osclient.dns().zones().create(zone);
```

### Create Private Zone
```java
String router_id = "19664294-0bf6-4271-ad3a-94b8c79c6558";
String region = "eu-de";
String name = "example.com.";
String description = "This is an example zone.";
DesignateZone.Router router = new DesignateZone.Router(router_id, region, null);
ZoneBuilder builder = Builders.zone();
Zone sourceZone = builder.name(name).description(description).type(ZoneType.PRIVATE).router(router).build();
Zone zoneResult = osclient.dns().zones().create(sourceZone);
```

### Get Zone
```java
Zone zone = osclient.dns().zones().get("zone-id");
```

### Delete Zone
```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
Zone deletedZone = osclient.dns().zones().delete(zone_id);
```

### Get Namesevers
```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
List<? extends Nameserver> nameserversList = osclient.dns().zones().listNameservers(zone_id);
```

### Associate Router
```java
String router_id = "19664294-0bf6-4271-ad3a-94b8c79c6558";
String region = "eu-de";
String zone_id = "2c9eb155587194ec01587224c9f90149";
DesignateZone.Router router = new DesignateZone.Router(router_id, region, null);
DesignateZone.Router routerResult = osclient.dns().zones().associateRouter(zone_id, router);
```

### Disassociate Router
```java
String router_id = "19664294-0bf6-4271-ad3a-94b8c79c6558";
String region = "eu-de";
String zone_id = "2c9eb155587194ec01587224c9f90149";
DesignateZone.Router router = new DesignateZone.Router(router_id, region, null);
DesignateZone.Router routerResult = osclient.dns().zones().disassociateRouter(zone_id, router);
```


### 
## Recordset
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

## PTR
### Get PTR
```java
String region = "eu-de";
String floatingIpId = "9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
DesignatePTR ptr = osclient.dns().reverseRecords().get(region, floatingIpId);
```

### Setup PTR
```java
String ptrDname = "www.example.com";
String description = "Description for this PTR record";
Stirng region = "eu-de";
String floatingIpId = "9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
int ttl = 300;
DesignatePTRBuilder builder = DesignatePTR.builder().ptrdname(ptrDname).description(description).region(region).floatingIpId(floatingIpId).ttl(ttl);
DesignatePTR ptrRecord = builder.build();
DesignatePTR ptr = osclient.dns().ptrs().setup(ptrRecord);
```

### Restore PTR
```java
String region = "eu-de";
String floatingIpId = "9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
ActionResponse actionResponse = osclient.dns().ptrs().restore(region, floatingIpId);
```

### List PTR without filters
```java
List<? extends PTR> list = osclient.dns().ptrs().list();
```

### List PTR with filters
```java
String limit = "limit"; 
String marker = "marker";
String source_id = "eu-de:9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
Map<String, Object> filters = new HashMap<>();
filters.put(limit, "2");
filters.put(marker, source_id); 
List<? extends PTR> list = osclient.dns().ptrs().list(filters);
```


