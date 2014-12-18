OpenStack4j 
===========

[![Build Status](https://travis-ci.org/gondor/openstack4j.svg?branch=master)](https://travis-ci.org/gondor/openstack4j)  [![License](https://pypip.in/license/apache-libcloud/badge.png)]()


OpenStack4j is a fluent OpenStack client that allows provisioning and control of an OpenStack deployment.   This includes support for Identity, Compute, Image, Network, Block Storage, Telemetry and more.

## Documentation and Support

* Website: [OpenStack4j.com](http://www.openstack4j.com)
* Documentation/Tutorials: [OpenStack4j.com/learn/](http://www.openstack4j.com/learn/)
* Questions - Use Google Groups: [groups.google.com/group/openstack4j](http://groups.google.com/group/openstack4j)
* Questions - Stackoverflow use **openstack4j** tag
* Twitter: [@openstack4j](https://twitter.com/openstack4j)
* Facebook: [facebook.com/openstack4j](http://www.facebook.com/openstack4j)
* Changelog: [Changelog](https://github.com/gondor/openstack4j/blob/master/CHANGELOG.md)

## Bug Reports

* GitHub Issues: [/gondor/openstack4j/issues](https://github.com/gondor/openstack4j/issues)

Maven
-----

#### Latest Release (Stable)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.pacesys/openstack4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.pacesys/openstack4j)

OpenStack4j version 2.0.0+ is now modular.  One of the benefits to this is the ability to choose the connector that you would like to use in your environment.  

**Using OpenStack4j with the default Jersey2 Connector**
```xml
<dependency>
    <groupId>org.pacesys</groupId>
    <artifactId>openstack4j</artifactId>
    <version>2.0.0</version>
</dependency>
```

**Using OpenStack4j with one of our connector modules**

To configure OpenStack4j to use one of our supported connectors (Jersey 2, Resteasy, Apache HttpClient, OKHttp) [see the usage guide](https://github.com/gondor/openstack4j/tree/master/connectors)

#### Current (Master Branch)

See notes above about connectors (same rules apply) to development branches.  

```xml
<dependency>
    <groupId>org.pacesys</groupId>
    <artifactId>openstack4j</artifactId>
    <version>2.0.1-SNAPSHOT</version>
</dependency>
```

**A note about referencing Snapshots without Source**

Snapshots are deploys to sonatype.  You will need to add the repository to your POM or Settings file.  Releases (above) are deployed to maven central and this step is not required.

Example POM based repository declaration to grab snapshots:
```xml
<repositories>
    <repository>
      <id>st-snapshots</id>
      <name>sonatype-snapshots</name>
      <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    </repository>
</repositories>
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
List<? extends Network> networks = os.networking().network().list();

// Create a Network
Network network = os.networking().network()
                    .create(Builders.network().name("MyNewNet").tenantId(tenant.getId()).build());
```

**Subnet Operations**
```java
// List all subnets which the current authorized tenant has access to
List<? extends Subnet> subnets = os.networking().subnet().list();

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
List<? extends Router> = os.networking().router().list();

// Create a Router
Router router = os.networking().router().create(Builders.router()
                  .name("ext_net").adminStateUp(true).externalGateway("networkId").build());
                  
```

### Image Operations (Glance)

**Basic Operations**
```java
// List all Images
List<? extends Image> images = os.images().list();

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
If you would like to contribute please see our contributing [guidelines](https://github.com/gondor/openstack4j/blob/master/CONTRIBUTING.md)

License
-------
```
This software is licensed under the Apache 2 license, quoted below.

Copyright 2014 Jeremy Unruh and OpenStack4j

Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
