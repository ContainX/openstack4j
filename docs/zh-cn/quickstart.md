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



## SDK获取和安装

我们使用Maven来管理 OpenStack4j，由于该项目并未发布到maven官方仓库中，所以需要你自行下载源码并使用 `mvn` 来安装到本地maven仓库。

```bash
cd your-workspace-folder
git clone https://github.com/huawei/openstack4j otc-openstack4j
cd otc-openstack4j
mvn clean package install -DskipTests
```

通过以上命令，可以将 openstack4j安装到本地的Maven仓库中

!> 目前master分支上的版本号是 `OTC-3.0.5-SNAPSHOT`, 在SDK全部完成后，会修改成 `OTC-3.0.5`


## 开始使用

?> 在项目源码中，新增了一个 `sample` 的示例项目，里面包含了本次新增的所有SDK的示例。

**1. 配置maven依赖**

你可以简单的将如下依赖加入你的pom.xml中，这个依赖会自动使用默认的 `resteasy`作为Connector
```xml
<dependency>
	<groupId>org.pacesys</groupId>
	<artifactId>openstack4j</artifactId>
	<version>OTC-3.0.5-SNAPSHOT</version>
</dependency>
```

或者，你也可以引入 all-in-one jar
```xml
<dependency>
  <groupId>org.pacesys</groupId>
  <artifactId>openstack4j</artifactId>
  <version>OTC-3.0.5-SNAPSHOT</version>
  <classifier>withdeps</classifier>
</dependency>
```

OpenStack4j 被设计成一个模块化的系统，模块化其中的一个好处是，你可以自由的选择你喜欢的`Connector`，所以如果你不喜欢默认的 resteasy connector 的话，那么你可以这样引入依赖：

```xml
<!-- 引入 核心包 -->
<dependency>
  <groupId>org.pacesys</groupId>
  <artifactId>openstack4j-core</artifactId>
  <version>OTC-3.0.5-SNAPSHOT</version>
</dependency>

<!-- 引入 你想要的connector -->
<dependency>
  <groupId>org.pacesys.openstack4j.connectors</groupId>
  <artifactId>[connector-artifactId]</artifactId>
  <version>OTC-3.0.5-SNAPSHOT</version>
</dependency>
```

目前，我们提供了以下的connector方案供选择：
- `openstack4j-jersey2`
- `openstack4j-resteasy`
- `openstack4j-okhttp`
- `openstack4j-httpclient`
- `openstack4j-http-connector`

你可以在[connector说明](https://github.com/huawei/openstack4j/tree/master/connectors)页面找到更多的资料


**2. 初始化SDK客户端并使用v3认证**

以德电环境为例，以下代码节选自 Sample项目的 [AbstractSample.class](https://github.com/Huawei/openstack4j/blob/master/sample/src/main/java/org/openstack4j/sample/AbstractSample.java)
```java
// 添加各个服务的Endpoint绑定
OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
		endpointResolver.addOverrideEndpoint(ServiceType.VOLUME_BACKUP,
				"https://vbs.eu-de.otc.t-systems.com/v2/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.AUTO_SCALING,
				"https://as.eu-de.otc.t-systems.com/autoscaling-api/v1/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_EYE,
				"https://ces.eu-de.otc.t-systems.com/V1.0/%(project_id)s");
		endpointResolver.addOverrideEndpoint(ServiceType.LOAD_BALANCER,
				"https://elb.eu-de.otc.t-systems.com/v1.0/%(project_id)s");
		
// 使用 credentials 进行认证
String user = "replace-with-your-username";
String password = "replace-with-your-password";
String projectId = "d4f2557d248e4860829f5fef030b209c";
String userDomainId = "bb42e2cd2b784ac4bdc350fb660a2bdb";
osclient = OSFactory.builderV3()
		.withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver))
		.endpoint("https://iam.eu-de.otc.t-systems.com/v3")
		.credentials(user, password, Identifier.byId(userDomainId))
		.scopeToProject(Identifier.byId(projectId)).authenticate();
```

!> 以上代码示例中的 `OverridableEndpointURLResolver` 的设置在正常情况下是多余的，因为OpenStack的`v3`认证会自动返回所有 Service的 Endpoint信息，但是，德电环境有问题，实际上并没有返回，所以需要我们手动设置缺失的endpoint信息。

**3. 使用SDK**

在osclient初始化完成后，osclient下会绑定所有的可用服务。比如 `osclient.compute()`, `osclient.dns()`, `osclient.autoScaling()` 等。下面以DNS服务的查询Zone列表为例：

```java
// 查询 DNS zone 列表
List<? extends Zone> list = osclient.dns().zones().list();
```

**4. SDK使用详情**

- 官方已经提供部分OpenStack基础服务的SDK，具体的用法请查阅 [官方文档](http://www.openstack4j.com/learn/getting-started)
	- Identity (Keystone) V2
	- Identity (Keystone) V3
	- Compute (Nova)
	- Network (Neutron)
	- Images (Glance)
	- Images (Glance) V2
	- Block Storage (Cinder)
	- Object Storage (Swift)
	- Telemetry (Ceilometer)
	- Orchestration (Heat)
	- Data Processing (Sahara)
	- Database as a Service (Trove)
	
- 我们在官方的基础上新增了6个华为云服务的SDK，具体的用法请查阅对应的使用手册
	- [VBS](zh-cn/vbs-sdk)
	- [CES](zh-cn/ces-sdk)
	- [AS](zh-cn/as-sdk)
	- [DNS](zh-cn/dns-sdk)
	- [ELB](zh-cn/elb-sdk)
	- [MRS](zh-cn/mrs-sdk)
	

## 使用例子项目

我们提供了一个[Sample](https://github.com/Huawei/openstack4j/blob/master/sample) 项目，里面包含了这次开发的所有服务的每一个API的使用例子。如果你想运行那些例子，
- 修改 AbstractSample 里的认证所需的 auth-url，user，password，user-domain-id, project-id
- 修改各个TestNG测试用例中的部分参数（因为环境不同，部分API需要关联的资源ID不一样）
