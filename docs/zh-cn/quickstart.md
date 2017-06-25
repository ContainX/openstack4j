# Quick start

基于 [OpenStack4j Tag-3.0.4](https://github.com/Huawei/openstack4j/tree/3.0.4), 
我们添加了6个新的华为OpenStack服务的SDK，这6个服务为：

- CES
- ELB
- VBS
- AS
- DNS
- MRS

新增的SDK的结构与 `OpenStack4j` 已有的SDK的大部分都保持一致。

!> 新的SDK使用了 [lombok](https://projectlombok.org) 来替代原有的Builder功能。


## 环境准备

1. `OpenStack4j` 适用于 JDK1.7+ 。不过我们建议您使用 JDK1.8，使用1.8的好处有很多，其中最重要的是：部分 connector 只支持 1.8，比如：``http``, ``jersey2``。你可以通过在命令行中执行如下命令来查看你的 JDK 版本：
```shell
$ java -version
```

2. 我们使用Maven来管理 OpenStack4j，所以请确保你本地已经可以正常使用Maven。你可以通过在命令行中执行如下命令来查看你的 mvn是否已经可以正常使用：
```shell
$ mvn -version
```

3. 要使用`OpenStack4j`，你需要如下的认证信息，这些认证信息也被称为 `OpenStack RC`，正常可以从管理后台直接下载到。
	- auth url
	- user
	- secret
	- user domain id
	- project id 

!> 如果你对上面的认证所需的信息没有任何头绪，请联系你的OpenStack管理员



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

