# Change Log

### 2.0.0-SNAPSHOT

##### Enhancements/Improvements

* Issue #87 - Software Configuration support for HEAT
* Issue #85 - Support for os-migrations extension
* Issue #83 - Live server migration
* Issue #79 - Support for OKHttp connector
* Issue #77 - Log4j and SLF4j Plugin support
* Issue #71 - Support for other connector choices (Jersey2, Resteasy, HttpClient)
* Issue #70 - Upgrade Jersey from 2.0 to 2.10.1
* Issue #69 - Break out our current connector (Jersey) into a sub-module to offer various connector choices
* Issue #67 - Upgrade Jackson 1 to Jackson 2

##### Fixes

* Issue #97 - Yaml based template ignored in Stack creation
* Issue #93 - Error listing images (Error reading entity from input stream)
* Issue #92 - Problems in deserializing an object (Heat Stack)
* Issue #91 - Implementation of the method "getHosts()" in the class availabilityZoneList
* Issue #90 - Volume Status "downloading" not regonized

### 1.0.2

##### Enhancements/Improvements

* Heat Orchestration Support
* Issue #65 - Instance rebuild with options support
* Issue #56 - Set userdata when creating a server
* Issue #34, Issue #27 - Support for HP Cloud / Identity V3 authentication
* Issue #45 - Seamless retry/auto-renew support when a token has expired. Will re-execute original request after renewal
* Issue #15 - Ability to specify a Region to call against
* Issue #26 - Ability to set Availibility Zone to a Server
* Issue #19 - Boot and Wait for Server to become ACTIVE based upon max wait time
 

##### Fixes
* Issue #58 - Server status "SHUTOFF" showing as UNRECOGNIZED enum value on query
* Issue #50 - List of SecurityGroupRule returns null
* Issue #49 - Listing of servers error in some environments
* Issue #18 - Network Facing Perspectives to determine endpoint resolution
* Issue #5, #47 PR - Availability Zones listing

### 1.0.1

* Block Storage (Cinder) support
* Floating IP/Pools support in Compute
* VNC and Console Output support in Compute -> Servers
* Personality support on Compute -> Server -> Create
* Security Group & Rules in Compute
* Keypair support
* Floating IP in Network
* Security Groups & Rules in Network
* Telemetry (Ceilometer) support

### 1.0.0

* Initial public release
