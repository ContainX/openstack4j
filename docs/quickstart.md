# Quick start

This project is based on [openstack4j v3.0.4](https://github.com/ContainX/openstack4j/tree/3.0.4) 
and add several SDK implement for HuaWei.TLD OpenStack services like CES, ELB, VBS, AS, DNS, MRS.
All new added service SDK is the same as exists SDK of openstack4j, you can get more document 
from the [openstack4j](https://github.com/ContainX/openstack4j) project.


## Prepare

we use maven as build tool

> For now, you need to build the SDK and install to your maven repository by hand

```bash
cd your-workspace-folder
git clone https://github.com/huawei/openstack4j huawei-openstack4j
cd huawei-openstack4j
mvn clean package install
```

## Maven integration

> OpenStack4j is modular. One of the benefits to this is the ability to choose the connector that you would like to use in your environment.  


**1. Using OpenStack4j with the default resteasy Connector**

```
<dependency>
    <groupId>org.pacesys</groupId>
    <artifactId>openstack4j</artifactId>
    <version>huawei-3.0.5</version>
</dependency>
```


**2. Using OpenStack4j with other connector modules**

To configure OpenStack4j to use one of our supported connectors (Jersey 2, Resteasy, Apache HttpClient, OKHttp) [see the usage guide](https://github.com/ContainX/openstack4j/tree/master/connectors)

## SDK usage

> Visit [www.OpenStack4j.com](http://www.openstack4j.com) for the full manual and getting started guides.

**1. Initialize OpenStack V3 Client**

Initialize client and authenticate within project-scope

```java
OSClientV3 osclient = OSFactory.builderV3()
		                .endpoint("http://<fqdn>:5000/v3")
		                .credentials("user", "secret", Identifier.byId("user domain id"))
		                .scopeToProject(Identifier.byId("project id"))
		                .authenticate());
```


In case of service endpoint is not registered or override service endpoint, 
OverridableEndpointURLResolver is provided to register endpoint for services.

```
// add endpoint for the service
OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_VOLUME_BACKUP,
		"https://vbs.eu-de.otc.t-systems.com/v2/%(project_id)s");
endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_EYE,
		"https://ecs.eu-de.otc.t-systems.com/v2/%(project_id)s");
		
OSClientV3 osclient = OSFactory.builderV3()
						.withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver))
		                .endpoint("http://<fqdn>:5000/v3")
		                .credentials("user", "secret", Identifier.byId("user domain id"))
		                .scopeToProject(Identifier.byId("project id"))
		                .authenticate());
```

**2. use SDK**

Base on openstack4j, we add six more HuaWei OpenStack service SDK(VBS, CES, AS, ELB, DNS, MRS). Use huawei create volume backup as example, the code may like :

```java
// build VBS volume backup create model
VBSVolumeBackupCreate vbc = VBSVolumeBackupCreate.builder().name("qianbiao-ng-os4j-1")
				.volumeId("0a3218ef-7841-45c5-b9a1-5da6e0b70b85").build();
				
// use cloud-volume-backup SDK to create a volume backup
CloudVolumeBackupJob job = osclient.cloudVolumeBackup().create(vbc);
```

## Showcase

We have a showcase project [openstack4j-sample](https://github.com/huawei/openstack4j/openstack-sample), 
it may enlighten u about how to use the SDK.

note:: The showcase project use `Apache HttpClient` as connector but not default `Resteasy`

