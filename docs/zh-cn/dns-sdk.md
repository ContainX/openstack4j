# Cloud Eye SDk

OTC OpenStack4j DNS SDK
- 服务入口: `osclient.dns()`
- 服务类型: `dns`

## Zone

### 查询Zone列表

```java
List<? extends Zone> list = osclient.dns().zones().list();
```

> 或者

```java
ZoneType type = ZoneType.public; //null -> query all zones, public -> query all public zones, private -> query all private zones
String marker = null; //the initial ID of a paging query, if null, query the first page
String limit = "2"; //per page's item quantity. Value can be 0~500
List<? extends Zone> list = osclient.dns().zones().list(type, marker, limit);
```

### 创建公网Zone
```java
String name = "example.com.";
String description = "This is an example zone.";
ZoneBuilder builder = Builders.zone();
Zone zone = builder.name(name).description(description).build();
Zone zoneResult = osclient.dns().zones().create(zone);
```

### 创建内网Zone
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

### 查询Zone详情

```java
Zone zone = osclient.dns().zones().get("zone-id");
```

### 删除Zone

```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
Zone deletedZone = osclient.dns().zones().delete(zone_id);
```

### 查询内网Zone的名称服务器

```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
List<? extends Nameserver> nameserversList = osclient.dns().zones().listNameservers(zone_id);
```

### 内网Zone关联VPC

```java
String router_id = "19664294-0bf6-4271-ad3a-94b8c79c6558";
String region = "eu-de";
String zone_id = "2c9eb155587194ec01587224c9f90149";
DesignateZone.Router router = new DesignateZone.Router(router_id, region, null);
DesignateZone.Router routerResult = osclient.dns().zones().associateRouter(zone_id, router);
```

### 内网Zone解关联VPC

```java
String router_id = "19664294-0bf6-4271-ad3a-94b8c79c6558";
String region = "eu-de";
String zone_id = "2c9eb155587194ec01587224c9f90149";
DesignateZone.Router router = new DesignateZone.Router(router_id, region, null);
DesignateZone.Router routerResult = osclient.dns().zones().disassociateRouter(zone_id, router);
```


## Recordset

### 查询 Recordset 列表

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

### 创建Recordset

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

### 查询Recordset详情
```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
String recordset_id = "2c9eb155587228570158722b6ac30007";

Recordset recordset = osclient.dns().recordsets().get(zone_id, recordset_id);
logger.info("Get recordset: {}", recordset);
```

### 删除Recordset
```java
String zone_id = "2c9eb155587194ec01587224c9f90149";
String recordset_id = "2c9eb155587228570158722b6ac30007";

Recordset recordset = osclient.dns().recordsets().delete(zone_id, recordset_id);
logger.info("Delete recordset: {}", recordset);
```

## PTR Record

### 查询 PTR Record详情
```java
String region = "eu-de";
String floatingIpId = "9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
DesignatePTR ptr = osclient.dns().reverseRecords().get(region, floatingIpId);
```

### 设置 PTR Record
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

### 恢复 PTR Record 默认值
```java
String region = "eu-de";
String floatingIpId = "9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
ActionResponse actionResponse = osclient.dns().ptrs().restore(region, floatingIpId);
```

### 查询 PTR Record 列表

```java
List<? extends PTR> list = osclient.dns().ptrs().list();
```

> 或者

```java
String limit = "limit"; 
String marker = "marker";
String source_id = "eu-de:9e9c6d33-51a6-4f84-b504-c13301f1cc8c";
Map<String, Object> filters = new HashMap<>();
filters.put(limit, "2");
filters.put(marker, source_id); 
List<? extends PTR> list = osclient.dns().ptrs().list(filters);
```

