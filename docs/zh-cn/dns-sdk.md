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


## Recordset
### List Recordsets
```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
String limit = "2";
String marker = null;

List<? extends Recordset> allRecordsetsOfZone = osclient.dns().recordsets().list(zone_id);
logger.info("all recordsets of zone: {}", allRecordsetsOfZone);

List<? extends Recordset> recordsetsOfZone = osclient.dns().recordsets().list(zone_id, limit, marker);
logger.info("recordsets of zone: {}", recordsetsOfZone);

List<? extends Recordset> allRecordsets = osclient.dns().recordsets().list();
logger.info("all recordsets for project: {}", allRecordsets);

List<? extends Recordset> recordsetsOfProject = osclient.dns().recordsets().list(limit, marker);
logger.info("recordsets for project: {}", recordsetsOfProject);
```

### Create Recordset
```java
// create with recordset model
Recordset recordset = Builders.recordset().name("name").type(RecordSetType.A).ttl(300).records(Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3")).build();
Recordset created = osclient.dns().recordsets().create(ZONE_ID, recordset);
logger.info("Create record set with recordset model: {}", created);

// create Type A directly
Recordset created2 = osclient.dns().recordsets().create(ZONE_ID, "name", "description", "A", ttl,
    Lists.newArrayList("192.168.10.1", "192.168.10.2", "192.168.10.3"));
logger.info("Create type A record set directly: {}", created2);

// create Type AAAA directly
Recordset created3 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type AAAA example record set.", "AAAA", 7200,
    Lists.newArrayList("fe80:0:0:0:202:b3ff:fe1e:8329", "ff03:0db8:85a3:0:0:8a2e:0370:7334"));
logger.info("Create type AAAA record set directly: {}", created3);

// create Type MX directly
Recordset created4 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type MX example record set.", "MX", 7200,
    Lists.newArrayList("1 mail.example.com"));
logger.info("Create type MX record set directly: {}", created4);

// create Type CNAME directly
Recordset created5 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type CNAME example record set.", "CNAME", 7200,
    Lists.newArrayList("server1.example.com"));
logger.info("Create type CNAME record set directly: {}", created5);

// create Type TXT directly
Recordset created6 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type TXT record set.", "TXT", 7200,
    Lists.newArrayList("This host is used for sale."));
logger.info("Create type TXT record set directly: {}", created6);

// create Type NS directly
Recordset created7 = osclient.dns().recordsets().create(ZONE_ID, "name", "This is a type NS record set.", "NS", 7200,
    Lists.newArrayList("node1.example.com.", "node2.example.com."));
logger.info("Create type NS record set directly: {}", created7);
```

### Get Recordset
```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
String recordset_id = "2c9eb155587228570158722b6ac30007";

Recordset recordset = osclient.dns().recordsets().get(zone_id, recordset_id);
logger.info("Get recordset: {}", recordset);
```

### Delete Recordset
```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
String recordset_id = "2c9eb155587228570158722b6ac30007";

Recordset recordset = osclient.dns().recordsets().delete(zone_id, recordset_id);
logger.info("Delete recordset: {}", recordset);
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


