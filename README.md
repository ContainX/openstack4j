OpenStack4j 
===========

[![Build Status](https://travis-ci.org/gondor/openstack4j.svg?branch=master)](https://travis-ci.org/gondor/openstack4j)


OpenStack4j is a fluent OpenStack client which allows provisioning and control of an OpenStack system.   This includes support for Identity, Compute, Image, Network, Block Storage and more.

### Documenation/Tutorials:  [www.OpenStack4j.com](http://www.openstack4j.com)                      

**Keep up to date with us on Twitter: [@openstack4j](https://twitter.com/openstack4j)**

Maven
-----

#### Latest Release

```
<dependency>
    <groupId>org.pacesys</groupId>
    <artifactId>openstack4j</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Current Development


```
<dependency>
    <groupId>org.pacesys</groupId>
    <artifactId>openstack4j</artifactId>
    <version>1.0.1-SNAPSHOT</version>
</dependency>
```

Quick Usage Guide
-----------------

Below are some examples of the API usage.  Please visit [www.OpenStack4j.com](http://www.openstack4j.com) for the full manual and getting started guides.


### Authenticating

Creating and authenticating against OpenStack is extremely simple. Below is an example of authenticating which will
result with the authorized OSClient.  OSClient allows you to invoke Compute, Identity, Neutron operations fluently. 

```java
OSClient os = OSFactory.builder()
                       .endpoint("http://127.0.0.1:5000/v2.0")
                       .credentials("admin","sample")
                       .tenantName("admin")
                       .authenticate();
```

#### Identity Operations (Keystone)

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


### Compute Operations (Nova)

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


### Networks (Neutron)

**Network Operations**
```java
// List the networks which the current authorized tenant has access to
List<Network> networks = os.networking().network().list();

// Create a Network
Network network = os.networking().network()
                    .create(Builders.network().name("MyNewNet").tenantId(tenant.getId()).build());
```

**Subnet Operations**
```java
// List all subnets which the current authorized tenant has access to
List<Subnet> subnets = os.networking().subnet().list();

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

**Router Operations**
```java
// List all Routers 
List<Router> = os.networking().router().list();

// Create a Router
Router router = os.networking().router().create(Builders.router()
                  .name("ext_net").adminStateUp(true).externalGateway("networkId").build());
                  
```

### Image Operations (Glance)

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
