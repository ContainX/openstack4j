# ELB SDk

HuaWei OpenStack4j ELB SDK, entry point is: `osclient.elasticLoadBalance()`

## API 文档
Not provided for now.

## SDK 文档

### 初始化 SDK 客户端
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .

## 弹性负载均衡
### 创建负载均衡器
```java
ELBLoadBalancerCreate loadBalancer = ELBLoadBalancerCreate.builder()
		.name("name")
		.vpcId("vpcId")
		.type(Type.External.name())
		.bandwidth(1)
		.adminStateUp(1).build();
ELBJob job = osclient.elasticLoadBalance().loadBalancers().create(loadBalancer);
```

### 删除负载均衡器
```java
ELBJob job = osclient.elasticLoadBalance().loadBalancers().delete("loadBalancerId");
```

### 修改负载均衡器
```java
LoadBalancer loadBalancer = osclient.elasticLoadBalance().loadBalancers()
		.get("loadBalancerId");
ELBLoadBalancerUpdate update = ELBLoadBalancerUpdate
		.fromLoadBalancer(loadBalancer)
		.toBuilder()
		.description("description")
		.build();
ELBJob updateJob = osclient.elasticLoadBalance().loadBalancers()
		.update("loadBalancerId", update);
```

### 查询负载均衡器详情
```java
LoadBalancer loadBalancer = osclient.elasticLoadBalance().loadBalancers().get("loadBalancerId");
```

### 查询负载均衡器列表
```java
List<? extends LoadBalancer> all = osclient.elasticLoadBalance().loadBalancers().list();

ELBLoadBalancerListOptions options = ELBLoadBalancerListOptions.create().name("name");
List<? extends LoadBalancer> list = osclient.elasticLoadBalance().loadBalancers().list(options);
```

## 监听器
### 创建监听器
```java
ListenerCreate listener = ELBListenerCreate.builder().name("SDK-test-listener")
		.loadBalancerId("loadBalancerId")
		.protocol(Protocol.TCP.name())
		.port(12345)
		.backendProtocol(BackendProtocol.TCP.name())
		.backendPort(54321)
		.lbAlgorithm(LbAlgorithm.roundrobin.name())
		.build();
ListenerCreate create = osclient.elasticLoadBalance().listeners().create(listener);
```

### 删除监听器
```java
ActionResponse resp = osclient.elasticLoadBalance().listeners().delete("listenerId");
```

### 修改监听器
```java
Listener listener = osclient.elasticLoadBalance().listeners().get("listenerId");

ELBListenerUpdate update = ELBListenerUpdate.fromListener(listener).toBuilder().name("name").build();

Listener afterUpdate = osclient.elasticLoadBalance().listeners().update("listenerId", update);
```

### 查询监听器详情
```java
Listener listener = osclient.elasticLoadBalance().listeners().get("listenerId");
```

### 查询监听器列表
```java
Listener[] all = osclient.elasticLoadBalance().listeners().list();

ELBListenerListOptions options = ELBListenerListOptions.create().name("name");
Listener[] list = osclient.elasticLoadBalance().listeners().list(options);
```

## 健康检查
### 创建健康检查
```java
HealthCheckCreate healthCheck = ELBHealthCheckCreate.builder().listenerId("listenerId").build();
HealthCheck create = osclient.elasticLoadBalance().healthchecks().create(healthCheck);
```

### 删除健康检查
```java
ActionResponse resp = osclient.elasticLoadBalance().healthchecks().delete("healthCheckId");
```

### 修改健康检查
```java
HealthCheck healthCheck = osclient.elasticLoadBalance().healthchecks().get("healthCheckId");

ELBHealthCheckUpdate update = ELBHealthCheckUpdate.fromHealthCheck(healthCheck).toBuilder()
		.healthCheckProtocol(HealthCheckProtocol.HTTP.name()).build();

HealthCheck afterUpdate = osclient.elasticLoadBalance().healthchecks().update("healthCheckId", update);
```

### 查询健康检查详情
```java
HealthCheck healthCheck = osclient.elasticLoadBalance().healthchecks().get("healthCheckId");
```

## 后端云服务器
### 创建后端云服务器
```java
ServerCreate server = ELBServerCreate.builder().serverId("serverId").address("address").build();
List<ServerCreate> servers = Lists.newArrayList(server);
ELBJob job = osclient.elasticLoadBalance().servers().create("listenerId", servers);
```

### 移除后端云服务器
```java
IdResourceEntity server = new IdResourceEntity();
server.setId("memberId");
List<IdResourceEntity> removeMember = Lists.newArrayList(server);
ServerDelete servers = ELBServerDelete.builder().removeMember(removeMember).build();
ELBJob job = osclient.elasticLoadBalance().servers().delete("listenerId", servers);
```

### 查询后端云服务器列表
```java
Server[] all = osclient.elasticLoadBalance().servers().list("listenerId");

ELBServerListOptions options = ELBServerListOptions.create().address("address");
Server[] list = osclient.elasticLoadBalance().servers().list(listenerId, options);
```

## 配额
### 查询配额
```java
Quotas quotas = osclient.elasticLoadBalance().quotas().list();
```

## 证书管理
### 创建证书
```java
Certificate cert = ELBCertificate.builder().name("name").description("desc")
		.certificate("certificate")
		.privateKey("privateKey")
		.build();
Certificate create = osclient.elasticLoadBalance().certs().create(cert);

```

### 删除证书
```java
ActionResponse resp = osclient.elasticLoadBalance().certs().delete("certificateId");
```

### 修改证书
```java
Certificate cert = ...;//get cert
ELBCertificateUpdate update = ELBCertificateUpdate.fromCertificate(cert).toBuilder()
		.description("description")
		.build();
Certificate afterUpdate = osclient.elasticLoadBalance().certs().update(cert.getId(), update);
```

### 查询证书列表
```java
Certificates certs = osclient.elasticLoadBalance().certs().list();
```