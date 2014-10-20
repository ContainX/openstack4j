SLF4j OpenStack4j Plugin
========================

A plugin which redirects OpenStack4j Logging to SLF4j (http://www.slf4j.org) as the logging provider

### Usage

Add the following dependency to your project and SLF4j will automatically be the provider for OpenStack4j logging.

```xml
<dependency>
	<groupId>org.pacesys.openstack4j.plugins</groupId>
	<artifactId>openstack4j-slf4j</artifactId>
	<version>${os4j.version}</version>
</dependency>
```