Log4j OpenStack4j Plugin
========================

A plugin which redirects OpenStack4j Logging to Log4j (http://logging.apache.org/log4j) as the logging provider

### Usage

Add the following dependency to your project and Log4j will automatically be the provider for OpenStack4j logging.

```xml
<dependency>
	<groupId>org.pacesys.openstack4j.plugins</groupId>
	<artifactId>openstack4j-log4j</artifactId>
	<version>${os4j.version}</version>
</dependency>
```