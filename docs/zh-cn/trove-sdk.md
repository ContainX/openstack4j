# Trove SDK

OTC OpenStack4j Trove SDK (与OpenStack原生Trove API 兼容)
- 服务入口: `osclient.trove()`
- 服务类型: `database`


## API接口文档

请查阅 [官方接口文档](https://docs.otc.t-systems.com/en-us/api/rds/en-us_topic_0032347780.html)

## 语言设置

Trove API SDK 可以选择性的设置客户端想要使用的语言。

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
List<ServiceVersion> versions = osclient.trove().versions().list();
```

### 查询API版本信息
```java
ServiceVersion version = osclient.trove().versions().get("version-id");
```

## 实例管理
### 创建实例

> 创建新实例

```java
// build datastore version
Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("6.3.35").build();

Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();
NIC nic = NIC.builder().networkId("network-id").securityGroupId("sg-id").build();
DatabaseUser user = DatabaseUser.builder().username("root").password("Demo@234").build();

DatabaseInstanceCreate instanceCreate = DatabaseInstanceCreate.builder().name("name").datastore(datastore)
		.flavorRef("flavor-id").users(Lists.newArrayList(user)).volume(volume)
		.configurationId("config-id").availabilityZone("eu-de-01").vpcId("vpc-id").nics(Lists.newArrayList(nic))
		.build();

DatabaseInstance instance = osclient.trove().instances().create(instanceCreate);
```

> 创建只读实例

```java
Datastore datastore = Datastore.builder().type(org.openstack4j.openstack.trove.constant.DatastoreType.MySQL)
				.version("MySQL-5.6.33").build();
		
Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();
DatabaseReplicaInstanceCreate replicaCreate = DatabaseReplicaInstanceCreate.builder().name("sdk-replica").datastore(datastore)
		.flavorRef("flavor-id").volume(volume).replicaOf("master-id").replicaCount(1).build();
DatabaseInstance replica = osclient.trove().instances().createReplica(replicaCreate);
```


### 调整实例容量
```java
Integer volumeSize = 200;
ActionResponse response = osclient.trove().instances().resize("instance-id", volumeSize);
```

### 调整实例规格
```java
ActionResponse response = osclient.trove().instances().resize("instance-id", "new-flavor-id");
```

### 重启实例
```java
ActionResponse response = osclient.trove().instances().restart("instance-id");
```

### 删除实例
```java
ActionResponse response = osclient.trove().instances().delete("instance-id");
```

### 获取实例列表
```java
List<DatabaseInstance> instances = osclient.trove().instances().list();
```

### 获取指定实例详细信息
```java
DatabaseInstance instance = osclient.trove().instances().get("instance-id");
```

### 获取所有实例规格信息
```java
List<InstanceFlavor> flavors = osclient.trove().flavors().list();
```

### 获取指定实例规格信息
```java
InstanceFlavor get = osclient.trove().flavors().get("flavor-id");
```


## 参数配置
### 获取参数列表	
```java
List<DatabaseParam> params = osclient.trove().params().list("databaseId");
```

### 获取配置参数信息	
```java
DatabaseParam param = osclient.trove().params().get("databaseId", "parameter1");
```

### 获取实例默认参数	
```java
Map<String, String> params = osclient.trove().params().getDefaultParamsByInstance("instance-id");
```

### 获取参数组列表
```java
List<DatabaseConfig> configs = osclient.trove().configs().list();
```

### 获取参数组
```java
DatabaseConfig get = osclient.trove().configs().get("config-id");
```

### 获取参数组关联实例
```java
List<DatabaseInstance> instances = osclient.trove().configs().listDatabaseInstances("config-id");
```

### 创建参数组
```java
// 参数值
Map<String, Object> values = Maps.newHashMap();
values.put("max_connections", "10");
values.put("autocommit", "OFF");
		
Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("5.6").build();
DatabaseConfigCreate creation = DatabaseConfigCreate.builder().datastore(datastore).name("config-name")
		.description("desc").values(values).build();
DatabaseConfig create = osclient.trove().configs().create(creation);
```

### 修改参数组参数
```java
Map<String, String> values = Maps.newHashMap();
values.put("max_connections", "100");
values.put("autocommit", "ON");

DatabaseConfigUpdate update = DatabaseConfigUpdate.builder().id("config-id").name("name").description("desc")
		.values(values).build();
ActionResponse response = osclient.trove().configs().update(update);
```

### 新增自定义参数
```java
Map<String, String> params = Maps.newHashMap();
params.put("max_connections", "10");
params.put("autocommit", "OFF");

ActionResponse response = osclient.trove().configs().updateParams("config-id", params);
```

### 删除参数组
```java
ActionResponse response = osclient.trove().configs().delete("config-id");
```
