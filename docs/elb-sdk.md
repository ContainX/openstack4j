# ELB SDK

HuaWei OpenStack4j ELB SDK, entry point is: `osclient.elasticLoadBalance()`

## API document
Not provided for now.

## SDK document

### initial SDK client
You can find how to initial SDK client in the [quickstart](huawei-sdk?id=_2-build-v3-client) page .

## Elastic Load Balancer
### Create Elastic Load Balancer
```java
ELBLoadBalancerCreate loadBalancer = ELBLoadBalancerCreate.builder()
		.name("name")
		.vpcId("vpcId")
		.type(Type.EXTERNAL)
		.bandwidth(1)
		.adminStateUp(1).build();
ELBJob job = osclient.elasticLoadBalance().loadBalancers().create(loadBalancer);
```

### Delete Load Balancer
```java
ELBJob job = osclient.elasticLoadBalance().loadBalancers().delete("loadBalancerId");
```

### Update Load Balancer
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

### Get Load Balancer
```java
LoadBalancer loadBalancer = osclient.elasticLoadBalance().loadBalancers().get("loadBalancerId");
```

### List Load Balancer
```java
List<? extends LoadBalancer> all = osclient.elasticLoadBalance().loadBalancers().list();

ELBLoadBalancerListOptions options = ELBLoadBalancerListOptions.create().name("name");
List<? extends LoadBalancer> list = osclient.elasticLoadBalance().loadBalancers().list(options);
```

## Listener
### Create Listener
```java
ListenerCreate listener = ELBListenerCreate.builder().name("SDK-test-listener")
		.loadBalancerId("loadBalancerId")
		.protocol(Protocol.TCP)
		.port(12345)
		.backendProtocol(BackendProtocol.TCP)
		.backendPort(54321)
		.lbAlgorithm(LbAlgorithm.ROUND_ROBIN)
		.build();
ListenerCreate create = osclient.elasticLoadBalance().listeners().create(listener);
```

### Delete Listener
```java
ActionResponse resp = osclient.elasticLoadBalance().listeners().delete("listenerId");
```

### Update Listener
```java
Listener listener = osclient.elasticLoadBalance().listeners().get("listenerId");

ELBListenerUpdate update = ELBListenerUpdate.fromListener(listener).toBuilder().name("name").build();

Listener afterUpdate = osclient.elasticLoadBalance().listeners().update("listenerId", update);
```

### Get Listener
```java
Listener listener = osclient.elasticLoadBalance().listeners().get("listenerId");
```

### List Listener
```java
Listener[] all = osclient.elasticLoadBalance().listeners().list();

ELBListenerListOptions options = ELBListenerListOptions.create().name("name");
Listener[] list = osclient.elasticLoadBalance().listeners().list(options);
```

## Health-Check
### Create Health-Check
```java
HealthCheckCreate healthCheck = ELBHealthCheckCreate.builder().listenerId("listenerId").build();
HealthCheck create = osclient.elasticLoadBalance().healthchecks().create(healthCheck);
```

### Delete Health-Check
```java
ActionResponse resp = osclient.elasticLoadBalance().healthchecks().delete("healthCheckId");
```

### Update Health-Check
```java
HealthCheck healthCheck = osclient.elasticLoadBalance().healthchecks().get("healthCheckId");

ELBHealthCheckUpdate update = ELBHealthCheckUpdate.fromHealthCheck(healthCheck).toBuilder()
		.healthCheckProtocol(HealthCheckProtocol.HTTP).build();

HealthCheck afterUpdate = osclient.elasticLoadBalance().healthchecks().update("healthCheckId", update);
```

### Get Health-Check
```java
HealthCheck healthCheck = osclient.elasticLoadBalance().healthchecks().get("healthCheckId");
```

## Server
### Create Server
```java
ServerCreate server = ELBServerCreate.builder().serverId("serverId").address("address").build();
List<ServerCreate> servers = Lists.newArrayList(server);
ELBJob job = osclient.elasticLoadBalance().servers().create("listenerId", servers);
```

### Delete Server
```java
IdResourceEntity server = new IdResourceEntity();
server.setId("memberId");
List<IdResourceEntity> removeMember = Lists.newArrayList(server);
ServerDelete servers = ELBServerDelete.builder().removeMember(removeMember).build();
ELBJob job = osclient.elasticLoadBalance().servers().delete("listenerId", servers);
```

### List Server
```java
Server[] all = osclient.elasticLoadBalance().servers().list("listenerId");

ELBServerListOptions options = ELBServerListOptions.create().address("address");
Server[] list = osclient.elasticLoadBalance().servers().list(listenerId, options);
```

## Quotas
### List Quotas
```java
Quotas quotas = osclient.elasticLoadBalance().quotas().list();
```

## Certificate
### Create Certificate
```java
Certificate cert = ELBCertificate.builder().name("name").description("desc")
		.certificate("certificate")
		.privateKey("privateKey")
		.build();
Certificate create = osclient.elasticLoadBalance().certs().create(cert);

```

### Delete Certificate
```java
ActionResponse resp = osclient.elasticLoadBalance().certs().delete("certificateId");
```

### Update Certificate
```java
Certificate cert = ...;//get cert
ELBCertificateUpdate update = ELBCertificateUpdate.fromCertificate(cert).toBuilder()
		.description("description")
		.build();
Certificate afterUpdate = osclient.elasticLoadBalance().certs().update(cert.getId(), update);
```

### List Certificate
```java
Certificates certs = osclient.elasticLoadBalance().certs().list();
```