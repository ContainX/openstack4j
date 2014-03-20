openstack4j
===========
OpenStack4j is a fluent OpenStack client which allows provisioning and control of an OpenStack system.   This include Identity, Compute and Neutron using Java.  

API Highlights
--------------

* **Expected Results**
All API calls that return a single object will be null if no data is found.  Calls that return a List/Collection will
return an empty list if not found.  

* **Easy Exception Handling**
Exceptions will never be thrown for 404 (Not Found) See above for standards.  Based on the OpenStack specifications of errors all exceptions extends a common unchecked ResponseException.  Server and Client exceptions also have an enumerated type fully classifying the exact reason for failure.  Exceptions are broken up into 3 categories.
 * ServerResponseException - is thrown if error codes are between 500 and 600.  
 * ClientResponseException - is thrown if error codes are between 400 and 499 excluding 404
 * ResponseException - Base exception is for all other failures

* **Fluent Interface**
All calls are fluent by nature. To shorten fluent calls an interface can be assigned during the chain to shorten the calls.  

* **Concrete API**
All APIs are interface defined as well as corresponding models and builders.  No need to ever refer to the implementation. Implementations are always defined within an "internal" package.


Authenticating
--------------
Creating and authenticating against OpenStack is extremely simple. Below is an example of authenticating which will
result with the authorized OSClient.  OSClient allows you to invoke Compute, Identity, Neutron operations fluently. 

```java
OSClient os = OSFactory.builder()
                       .endpoint("http://127.0.0.1:5000/v2.0")
                       .credentials("admin","sample")
                       .tenantName("admin")
                       .authenticate();
```

Identity Operations (Keystone)
------------------------------
After successful authentication you can invoke any Identity (Keysyone) directly from the OSClient. The examples below do not utilize all possible operations.  They are meant to give you a high level example of Identity (Keystone) based operations.

Identity Services fully cover Tenants, Users, Roles, Services, Endpoints and Identity Extension listings.  The examples below are only a small fraction of the existing API so please refer to the API documentation for more details.

**Create a Tenant, User and associate a Role**
```java
// Create a Tenant (could also be created fluent within user create)
Tenant tenant = os.identity().tenants().create(Builders.tenant().name("MyNewTenant").build());

// Create a User associated to the new Tenant
User user = os.identity().users().create(Builders.user().name("jack").password("sample").tenant(tenant).build());

// Add a Tenant based Role to the User
os.identity().roles().addUserRole(tenant.getId(), user.getId(), os.identity().roles().getByName("Member").getId());

```

**User Management**
```java
// Find all Users
List<User> users = os.identity().users().list();

// Find all Users for a Tenant
List<User> users = os.identity().users().listTenantUsers("tenantId");

// List User Global Roles
List<Role> roles = os.identity().users().listRoles("userId");

// List User roles on Tenant
List<Role> roles = os.identity().users().listRolesOnTenant("userId", "tenantId");

// Change a User Password
os.identity().users().changePassword("userId", "newPassword");

// Enable/Disable a user
os.identity().users().enableUser("userId", false); 

// Delete a User
os.identity().users().delete("userId");

// Get a User By ID
User user = os.identity().users().get("userId");
```

**Services and Endpoints**
```java
// Go direct to API to slim down method chaining
ServiceManagerService sm = os.identity().services();

// List Services
List<Service> services = sm.list();

// List Endpoints
List<ServiceEndpoint> ep = sm.listEndpoints();

// Create a Service and Endpoint
Service service = sm.create("Name", "Type", "Description");
ServiceEndpoint sep = sm.createEndpoint("region", service.getId(), "pubURL", "admURL", "intURL");

// Get a Service by ID
Service service = sm.get("serviceId");

// Delete a Service and Endpoint
sm.delete("serviceId");
sm.deleteEndpoint("endpointId");
```

Compute Operations (Nova)
-------------------------
OpenStack4j covers most the major common compute based operations and more.  With the simplistic API approach you can fully manage Servers, Flavors, Images, Quota-Sets, Diagnostics, Tenant Usage and more.  As the API evolves additional providers and extensions will be covered and documented within the API.

**Create a Flavor and Boot a Server/VM **
```java
// Create a Flavor for a special customer base
Flavor flavor = os.compute().flavors()
                  .create(Builders.flavor().name("Gold").vcpus(4).disk(80).ram(2048).build());
                  
// Create and Boot a new Server (minimal builder options shown in example)
Server server = os.compute().servers()
                  .boot(Builders.server().name("Ubuntu 2").flavor(flavor.getId()).image("imageId").build());

```

**Server Actions**
Simple Actions are a single command giving the Server ID and desired Action.  The available simple actions
are PAUSE, UNPAUSE, STOP, START, LOCK, UNLOCK, SUSPEND, RESUMT, RESCUE, UNRESCUE, SHELVE, SHELVE_OFFLOAD, UNSHELVE.

Extended actions are handled via direct API calls.  
```java

/* Simple Actions */

// Suspend a Server
os.compute().servers().action(server.getId(), Action.SUSPEND);

// Resume a Server
os.compute().servers().action(server.getId(), Action.RESUME);

/* Extended Actions */

// Reboot
os.compute().servers().reboot(server.getId(), RebootType.SOFT);

// Resize
os.compute().servers().resize(server.getId(), newFlavor.getId());

// Confirm Resize
os.compute().servers().confirmResize(server.getId());

// Revert Resize
os.compute().servers().revertResize(server.getId());

```

**Create a new Server Snapshot**
```java
String imageId = os.compute().servers().createSnapshot(server.getId(), "Clean State Snapshot");
```

**Server Diagnostics**

Diagnostics are usage information about the server.  Usage includes CPU, Memory and IO.  Information is
dependant on the hypervisor used by the OpenStack installation.  As of right now there is no concrete diagnostic
specification which is why the information is variable and in map form (key and value)
```java
Map<String, ? extends Number> diagnostics = os.compute().servers().diagnostics("serverId");
```

**Server CRUD**
```java
// List all Servers
List<Server> servers = os.compute().servers().list();

// List all servers (light) ID, Name and Links populated
List<Server> servers = os.compute().servers().list(false);

// Get a specific Server by ID
Server server = os.compute().servers().get("serverId");

// Delete a Server
os.compute().servers().delete("serverId");
```

**Quota-Sets, Limits and Simple Tenant Usage (os-simple-tenant-usage)**
```java
// Quota-Set for a specific Tenant
QuotaSet qs = os.compute().quotaSets().get(tenant.getId());

// Quota-Set for a specific Tenant and User
QuotaSet qs = os.compute().quotaSets().get(tenant.getId(), user.getId());

// Limits (Rate Limit and Absolute)
Limits limits = os.compute().quotaSets().limits();

// Tenant Usage for All Tenants
List<? extends SimpleTenantUsage> tenantUsages = os.compute().quotaSets().listTenantUsages();

// Tenant Usage (detailed) for specific Tenant
SimpleTenantUsage usage = os.compute().quotaSets().getTenantUsage("tenantId");
```

**Flavors**
```java
// List all Flavor(s)
List<Flavor> flavors = os.compute().flavors().list();

// Get a Flavor by ID
Flavor f = os.compute().flavors().get("flavorId");

// Delete a Flavor
os.compute().flavors().delete(flavor.getId());

// Create a Flavor - By Params (name, ram, vcpus, disk, etc)
Flavor f = os.compute().flavors().create("WebServer Template", 1024, 2, 10, ...//);

// By Builder
Flavor f = os.compute().flavors().create(Builders.flavor().name("Ubuntu").vcpus(2).disk(80).build());
```

**Images**
```java
// List all Images (detailed @see #list(boolean detailed) for brief)
List<Image> images = os.compute().images().list();

// Get an Image by ID
Image img = os.compute().images().get("imageId");

// Delete an Image
os.compute().images().delete("imageId");

// MetaData

// Get
Map<String, String> md = os.compute().images().getMetaData("imageId");

// Set
Map<String, String> md = os.compute().images().setMetaData("imageId", newMetaMap);

// Delete Keys
os.compute().images().deleteMetaData("imageId", "key1", key2", ...//);
```

Networks (Neutron)
------------------

**Network Operations**
```java
// List the networks to which the current authorized tenant has access to
List<Network> networks = os.networking().network().list();

// Network by ID
Network network = os.networking().network().get("networkId");

// Delete a Network
os.networking().network().delete("networkId");

// Create a Network
Network network = os.networking().network()
                    .create(Builders.network().name("MyNewNet").tenantId(tenant.getId()).build());
```

**TODO Finish Network Doc**

Contributing
------------
If you would like to contribute to this project we welcome you. Please contact us and let us know your area of expertise. We are looking for engineers to extend API functionality as well as add extensions and extended providers.

License
-------
```
The MIT License (MIT)

Copyright (c) 2014 Jeremy Unruh

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
