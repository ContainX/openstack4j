# HuaWei SDK Client Initial

## 1. credentials

Ask OpenStack environment administrator to get credentials, a credentials should contains infomation below:

- user
- secret
- auth url
- user domain id
- project id 

## 2. build V3 Client

To fixed the service endpoint is not provide through `/v3/auth/token` API issue, use the override endpoint resolver to register service endpoint:

```
// add endpoint for the service
OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_VOLUME_BACKUP,
		"https://vbs.eu-de.otc.t-systems.com/v2/%(project_id)s");
endpointResolver.addOverrideEndpoint(ServiceType.CLOUD_EYE,
		"https://ecs.eu-de.otc.t-systems.com/v2/%(project_id)s");
// add other service endpoint overrides ...
		

// initial SDK V3 Client
OSClientV3 osclient = OSFactory.builderV3()
						.withConfig(Config.newConfig().withEndpointURLResolver(endpointResolver))
		                .endpoint("http://<fqdn>:5000/v3")
		                .credentials("user", "secret", Identifier.byId("user domain id"))
		                .scopeToProject(Identifier.byId("project id"))
		                .authenticate());
```


## 3. Endpoint mapping

| ServiceName |  ServiceType  |                              URL example                             |
|:-----------:|:-------------:|:--------------------------------------------------------------------:|
|     VBS     | volume-backup |         https://vbs.eu-de.otc.t-systems.com/v2/%(project_id)s        |
|     CES     |   cloud-eye   |        https://ces.eu-de.otc.t-systems.com/v1.0/%(project_id)s       |
|      AS     |  auto-scaling | https://as.eu-de.otc.t-systems.com/autoscaling-api/v1/%(project_id)s |
|     ELB     |  load-balance |    https://elb.eu-de.otc.t-systems.com/v1.0/elbaas/%(project_id)s    |
|     DNS     |      dns      |                 https://dns.eu-de.otc.t-systems.com/v2               |
|     MRS     |   map-reduce  |        https://mrs.eu-de.otc.t-systems.com/v1.1/%(project_id)s       |
