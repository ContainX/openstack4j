OpenStack4j Connectors
======================

By default OpenStack4j uses Jersey 2.  You can break this out to allow usage of any of the following connectors Jersey 2, Resteasy, Apache HttpClient or OKHttp.

### Usage

Instead of depending on the normal "openstack4j" artifactId, change the dependency to be "openstack4j-core" like below:

```xml
<dependency>
    <groupId>org.pacesys</groupId>
    <artifactId>openstack4j-core</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

Now choose a connector by adding the applicable dependency below:

**Jersey 2**
```xml
<dependency>
    <groupId>org.pacesys.openstack4j.connectors</groupId>
    <artifactId>openstack4j-jersey2</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

**Resteasy**
```xml
<dependency>
    <groupId>org.pacesys.openstack4j.connectors</groupId>
    <artifactId>openstack4j-resteasy</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

**Apache HttpClient**
```xml
<dependency>
    <groupId>org.pacesys.openstack4j.connectors</groupId>
    <artifactId>openstack4j-httpclient</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

**OKHttp**
```xml
<dependency>
    <groupId>org.pacesys.openstack4j.connectors</groupId>
    <artifactId>openstack4j-okhttp</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```



