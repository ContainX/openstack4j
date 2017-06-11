# HuaWei SDK Client Initial

First, ask your OpenStack environment administrator to get credentials, a credentials should contains infomation below:

- user
- secret
- auth url
- user domain id
- project id 

To fixed the some service endpoint is not provide through `/v3/auth/token` API, 
we could use the override endpoint resolver to register service endpoint:

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

