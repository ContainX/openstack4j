# Quick start

Base on [python-openstacksdk v0.9.16](https://github.com/openstack/python-openstacksdk/tree/0.9.16), 
we add six more libraries for HuaWei.Tld OpenStack Services:

- CES
- ELB
- VBS
- AS
- DNS
- MRS

All new libraries are simulating the implementation of existing components/architecture/design. Please refer to the official libraries design documentation when you have any questions.


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


**1. Using OpenStack4j with the default jersey2 Connector**

```
<dependency>
    <groupId>com.huawei</groupId>
    <artifactId>openstack4j</artifactId>
    <version>3.0.5.1</version>
</dependency>
```


**2. Using OpenStack4j with other connector modules**

To configure OpenStack4j to use one of our supported connectors (Jersey 2, Resteasy, Apache HttpClient, OKHttp) [see the usage guide](https://github.com/ContainX/openstack4j/tree/master/connectors)

## Usage

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

**2. Use libraries**

- For libraries provided by official, please refer to the official documentation.
- For new added libraries::
	- [VBS](vbs-sdk)
	- [CES](ces-sdk)
	- [AS](as-sdk)
	- [DNS](dns-sdk)
	- [ELB](elb-sdk)
	- [MRS](mrs-sdk)
	

## Showcase

We have a showcase project [openstack4j-sample](https://github.com/huawei/openstack4j/openstack-sample), 
it may enlighten u about how to use the SDK.

note:: The showcase project use `Apache HttpClient` as connector but not default `Resteasy`

