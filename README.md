# OpenStack4j

OpenStack4j is a fluent OpenStack client which allows provisioning and control of an OpenStack system.   This include Identity, Compute and Neutron using Java.  


# Full Documentation

Visit [OpenStack4j.com](http://www.openstack4j.com)

Maven
-----
```
<dependency>
    <groupId>org.pacesys</groupId>
    <artifactId>openstack4j</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Working with Model Objects
--------------------------
Any object returned or specified within the API that is decorated with the "buildable" interface has the following characteristics.

In this scenario lets assume we are dealing with a Tenant object and a Image object (both implement buildable).

**Creating a new Object**
```java
Tenant tenant = Builders.tenant().name("My Tenant").description("Some meaning...").build();

Image image = Builders().image().name("My Image").diskFormat(DiskFormat.QCOW2).minDisk(1024).build();
```

**Updating an Object retrieved or already assigned**
```java
Tenant tenant = // some get operation
tenant.builder().name("New Name");

Image image = //...
image.builder().name("New Name").minRam(2048);
```

As you can see in the above examples any Buildable can be created via **Builders** and mutated via **Object.builder()**


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
After successful authentication you can invoke any Identity (Keystone) directly from the OSClient. 

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
OpenStack4j covers most the major common compute based operations.  With the simplistic API approach you can fully manage Servers, Flavors, Images, Quota-Sets, Diagnostics, Tenant Usage and more.  As the API evolves additional providers and extensions will be covered and documented within the API.

**Create a Flavor and Boot a Server/VM**
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
are PAUSE, UNPAUSE, STOP, START, LOCK, UNLOCK, SUSPEND, RESUME, RESCUE, UNRESCUE, SHELVE, SHELVE_OFFLOAD, UNSHELVE.

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
List<ComputeImage> images = os.compute().images().list();

// Get an Image by ID
ComputeImage img = os.compute().images().get("imageId");

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
// List the networks which the current authorized tenant has access to
List<Network> networks = os.networking().network().list();

// Network by ID
Network network = os.networking().network().get("networkId");

// Delete a Network
os.networking().network().delete("networkId");

// Create a Network
Network network = os.networking().network()
                    .create(Builders.network().name("MyNewNet").tenantId(tenant.getId()).build());
```

**Subnet Operations**
```java
// List all subnets which the current authorized tenant has access to
List<Subnet> subnets = os.networking().subnet().list();

// Get a Subnet by ID
Subnet subnet = os.networking().subnet().get("subnetId");

// Delete a Subnet
os.networking().subnet().delete("subnetId");

// Create a Subnet
Subnet subnet = os.networking().subnet().create(Builders.subnet()
                  .name("MySubnet")
                  .networkId("networkId")
                  .tenantId("tenantId")
                  .addPool("192.168.0.1", "192.168.0.254")
                  .ipVersion(IPVersionType.V4)
                  .cidr("192.168.0.0/24")
                  .build());
```

**Port Operations**
```java
// List all Ports which the current authorized tenant has access to
List<Port> ports = os.networking().port().list();

// Get a Port by ID
Port port = os.networking().port().get("portId");

// Delete a Port
os.networking().port().delete("portId");

// Create a Port
Port port = os.networking().port().create(Builders.port()
              .name("port1").networkId("networkId").fixedIp("52.51.1.253", "subnetId").build());
              
// Update a Port
Port updatedPort = os.networking().port().update(port);
```

**Router Operations**
```java
// List all Routers 
List<Router> = os.networking().router().list();

// Get a Router by ID
Router router = os.networking().router().get("routerId");

// Delete a Router
os.networking().router().delete("routerId");

// Create a Router
Router router = os.networking().router().create(Builders.router()
                  .name("ext_net").adminStateUp(true).externalGateway("networkId").build());
                  
// Update a Router
router = os.networking().router().update(router.toBuilder().name("ext_net2").build());

// Toggle Administrative State
Router router = os.networking().router().toggleAdminStateUp("routerId", true);

// Attach an External Interface
RouterInterface iface = os.networking().router().attachInterface("routerId", AttachInterfaceType.SUBNET, "subnetId");

// Detach an External Interface
RouterInterface iface = os.networking().router().detachInterface("routerId", "subnetId", null);
```

Image Operations (Glance)
-------------------------

**Basic Operations**
```java
// List all Images
List<Image> images = os.images().list();

// Get an Image by ID
Image image = os.images().get("imageId");

// Delete a Image
os.images().delete("imageId");

// Update a Image
Image image = os.images().get("imageId");

os.images().update(image.toBuilder()
           .name("New VM Image Name").minDisk(1024).property("personal-distro", "true"));
```

**Download the Image Data**
```java
InputStream is = os.images().getAsStream("imageId"); 
```

**Create a Image**
```java
// (URL Payload in this example, File, InputStream are other payloads available)
Image image = os.images().create(Builders.image()
                .name("Cirros 0.3.0 x64")
				.isPublic(true)
				.containerFormat(ContainerFormat.BARE)
				.diskFormat(DiskFormat.QCOW2)
				.build()
				), Payloads.create(new URL("https://launchpad.net/cirros/trunk/0.3.0/+download/cirros-0.3.0-x86_64-disk.img")));
```

**Reserve and Upload a Image**
```java
Image image = os.images().reserve(Builders.image()
				.name("Cirros 0.3.0 x64")
				.isPublic(true)
				.build());

// Passing the image is optional and can be null but in this example we want to make sure 
// the ContainerFormat and DiskFormat are correct
image = os.images().upload(image.getId(), 
           				   Payloads.create(new File("/path/to/vmimage.img")), 
						   image.builder().containerFormat(ContainerFormat.BARE).diskFormat(DiskFormat.QCOW2));
```

**Image Membership Operations**
```java
// List Members who have access to a private image
List<ImageMember> members = os.images().listMembers("imageId");

// Add a Member (give a Tenant access to a private image) - returns true for success
os.images().addMember("imageId", "tenantId");

// Remove a Member (revoke a Tenant access to a private image) - returns true for success
os.images().removeMember("imageId", "tenantId");
```

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
