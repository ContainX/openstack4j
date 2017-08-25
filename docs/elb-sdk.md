# ELB SDK

HuaWei OpenStack4j ELB SDK, entry point is: `osclient.loadBalancer()`

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
ELBJob job = osclient.loadBalancer().loadBalancers().create(loadBalancer);
```

### Delete Load Balancer
```java
ELBJob job = osclient.loadBalancer().loadBalancers().delete("loadBalancerId");
```

### Update Load Balancer
```java
LoadBalancer loadBalancer = osclient.loadBalancer().loadBalancers()
		.get("loadBalancerId");
ELBLoadBalancerUpdate update = ELBLoadBalancerUpdate
		.fromLoadBalancer(loadBalancer)
		.toBuilder()
		.description("description")
		.build();
ELBJob updateJob = osclient.loadBalancer().loadBalancers()
		.update("loadBalancerId", update);
```

### Get Load Balancer
```java
LoadBalancer loadBalancer = osclient.loadBalancer().loadBalancers().get("loadBalancerId");
```

### List Load Balancer
```java
List<? extends LoadBalancer> all = osclient.loadBalancer().loadBalancers().list();

ELBLoadBalancerListOptions options = ELBLoadBalancerListOptions.create().name("name");
List<? extends LoadBalancer> list = osclient.loadBalancer().loadBalancers().list(options);
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
Listener create = osclient.loadBalancer().listeners().create(listener);
```

### Delete Listener
```java
ActionResponse resp = osclient.loadBalancer().listeners().delete("listenerId");
```

### Update Listener
```java
Listener listener = osclient.loadBalancer().listeners().get("listenerId");

ELBListenerUpdate update = ELBListenerUpdate.fromListener(listener).toBuilder().name("name").build();

Listener afterUpdate = osclient.loadBalancer().listeners().update("listenerId", update);
```

### Get Listener
```java
Listener listener = osclient.loadBalancer().listeners().get("listenerId");
```

### List Listener
```java
Listener[] all = osclient.loadBalancer().listeners().list();

ELBListenerListOptions options = ELBListenerListOptions.create().name("name");
Listener[] list = osclient.loadBalancer().listeners().list(options);
```

## Health-Check
### Create Health-Check
```java
ELBHealthCheckCreate create = ELBHealthCheckCreate.builder().listenerId("listener-id")
				.healthCheckProtocol(HealthCheckProtocol.HTTP).healthCheckConnectPort(80).healthCheckInterval(5)
				.healthCheckTimeout(10).healthCheckUri("/ok").healthyThreshold(3).unhealthyThreshold(3).build();
HealthCheck create = osclient.loadBalancer().healthchecks().create(healthCheck);
```

### Delete Health-Check
```java
ActionResponse resp = osclient.loadBalancer().healthchecks().delete("healthCheckId");
```

### Update Health-Check
```java
HealthCheck healthCheck = osclient.loadBalancer().healthchecks().get("healthCheckId");

ELBHealthCheckUpdate update = ELBHealthCheckUpdate.fromHealthCheck(healthCheck).toBuilder()
		.healthCheckProtocol(HealthCheckProtocol.HTTP).build();

HealthCheck afterUpdate = osclient.loadBalancer().healthchecks().update("healthCheckId", update);
```

### Get Health-Check
```java
HealthCheck healthCheck = osclient.loadBalancer().healthchecks().get("healthCheckId");
```

## Server
### Create Server
```java
ServerCreate server = ELBServerCreate.builder().serverId("serverId").address("address").build();
List<ServerCreate> servers = Lists.newArrayList(server);
ELBJob job = osclient.loadBalancer().servers().create("listenerId", servers);
```

### Delete Server
```java
ArrayList<String> servers = Lists.newArrayList("server-id-1", "server-id-2");
ELBJob job = osclient.loadBalancer().servers().delete("listenerId", servers);
```

### List Server
```java
Server[] all = osclient.loadBalancer().servers().list("listenerId");

ELBServerListOptions options = ELBServerListOptions.create().address("address");
Server[] list = osclient.loadBalancer().servers().list(listenerId, options);
```

## Quotas
### List Quotas
```java
Quotas quotas = osclient.loadBalancer().quotas().list();
```

## Certificate
### Create Certificate
```java
Certificate cert = ELBCertificate.builder().name("name").description("desc")
		.certificate("certificate")
		.privateKey("privateKey")
		.build();
Certificate create = osclient.loadBalancer().certs().create(cert);

```

### Delete Certificate
```java
ActionResponse resp = osclient.loadBalancer().certs().delete("certificateId");
```

### Update Certificate
```java
Certificate cert = ...;//get cert
ELBCertificateUpdate update = ELBCertificateUpdate.fromCertificate(cert).toBuilder()
		.description("description")
		.build();
Certificate afterUpdate = osclient.loadBalancer().certs().update(cert.getId(), update);
```

### List Certificate
```java
Certificates certs = osclient.loadBalancer().certs().list();
```