# Trove SDK

OTC OpenStack4j Trove SDK (Compatibility with OpenStack native Trove API)
- Service Entry: `osclient.trove()`
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
List<ServiceVersion> versions = osclient.trove().versions().list();
```

### Get Service Version
```java
ServiceVersion version = osclient.trove().versions().get("version-id");
```

## Instance management
### Create instance

> Create new instance

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

> Create replica instance

```java
Datastore datastore = Datastore.builder().type(org.openstack4j.openstack.trove.constant.DatastoreType.MySQL)
				.version("MySQL-5.6.33").build();
		
Volume volume = Volume.builder().type(VolumeType.COMMON).size(100).build();
DatabaseReplicaInstanceCreate replicaCreate = DatabaseReplicaInstanceCreate.builder().name("sdk-replica").datastore(datastore)
		.flavorRef("flavor-id").volume(volume).replicaOf("master-id").replicaCount(1).build();
DatabaseInstance replica = osclient.trove().instances().createReplica(replicaCreate);
```


### Resize instance volume
```java
Integer volumeSize = 200;
ActionResponse response = osclient.trove().instances().resize("instance-id", volumeSize);
```

### Resize instance flavor
```java
ActionResponse response = osclient.trove().instances().resize("instance-id", "new-flavor-id");
```

### Restart instance
```java
ActionResponse response = osclient.trove().instances().restart("instance-id");
```

### Delete instance
```java
ActionResponse response = osclient.trove().instances().delete("instance-id");
```

### List instance
```java
List<DatabaseInstance> instances = osclient.trove().instances().list();
```

### Get instance
```java
DatabaseInstance instance = osclient.trove().instances().get("instance-id");
```

### List instance flavor
```java
List<InstanceFlavor> flavors = osclient.trove().flavors().list();
```

### Get instance flavor
```java
InstanceFlavor get = osclient.trove().flavors().get("flavor-id");
```


## Parameter Configuration
### List Parameter
```java
List<DatabaseParam> params = osclient.trove().params().list("databaseId");
```

### Get Parameter
```java
DatabaseParam param = osclient.trove().params().get("databaseId", "parameter1");
```

### Get parameter of instance
```java
Map<String, String> params = osclient.trove().params().getDefaultParamsByInstance("instance-id");
```

### List Configuration
```java
List<DatabaseConfig> configs = osclient.trove().configs().list();
```

### Get Configuration
```java
DatabaseConfig get = osclient.trove().configs().get("config-id");
```

### List instance by configuration
```java
List<DatabaseInstance> instances = osclient.trove().configs().listDatabaseInstances("config-id");
```

### Create Configuration
```java
// parameters
Map<String, Object> values = Maps.newHashMap();
values.put("max_connections", "10");
values.put("autocommit", "OFF");
		
Datastore datastore = Datastore.builder().type(DatastoreType.MySQL).version("5.6").build();
DatabaseConfigCreate creation = DatabaseConfigCreate.builder().datastore(datastore).name("config-name")
		.description("desc").values(values).build();
DatabaseConfig create = osclient.trove().configs().create(creation);
```

### Update Configuration
```java
Map<String, String> values = Maps.newHashMap();
values.put("max_connections", "100");
values.put("autocommit", "ON");

DatabaseConfigUpdate update = DatabaseConfigUpdate.builder().id("config-id").name("name").description("desc")
		.values(values).build();
ActionResponse response = osclient.trove().configs().update(update);
```

### Update parameter of Configuration
```java
Map<String, String> params = Maps.newHashMap();
params.put("max_connections", "10");
params.put("autocommit", "OFF");

ActionResponse response = osclient.trove().configs().updateParams("config-id", params);
```

### Delete Configuration
```java
ActionResponse response = osclient.trove().configs().delete("config-id");
```
