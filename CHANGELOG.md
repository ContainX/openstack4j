# Change Log

### 2.0.0-SNAPSHOT

##### Enhancements/Improvements

* Issue #155 - Add flag to allow for Legacy endpoint handling (has broken some users)
* Issue #154 - Ceilometer - Support query criteria when retrieving samples
* Issue #152 - Add ability to dynamically change Endpoint URL for NAT Firewalls
* Issue #150 - Heat - ability to retreive raw JSON/Yaml template for a Stack
* Issue #145 - Enabled wire logging for HttpClient based connectors
* Issue #128 - Heat - ability to update a Stack
* Issue #125 - Compute - update a server (instance) name
* Issue #120 - Compute - flavor extra spec support for scheduler hints
* Issue #114 - Support for SPICE Html5 console
* PR    #107 - Server group support - Thank you @octupszhang
* Issue #105 - Full server metadata support
* Issue #102 - Support for backup server action
* Issue #101 - Limits for Cinder and Neutron
* Issue #98 - API Support for Object Store (Swift)
* Issue #87 - Software Configuration support for HEAT
* Issue #85 - Support for os-migrations extension
* Issue #83 - Live server migration
* Issue #79 - Support for OKHttp connector
* Issue #77 - Log4j and SLF4j Plugin support
* Issue #71 - Support for other connector choices (Jersey2, Resteasy, HttpClient)
* Issue #70 - Upgrade Jersey from 2.0 to 2.10.1
* Issue #69 - Break out our current connector (Jersey) into a sub-module to offer various connector choices
* Issue #67 - Upgrade Jackson 1 to Jackson 2
* Issue #33 - Ability to specify a custom socket factory and host name verifier

##### Fixes

* Issue #148 - Compute: Creating a keypair without specifying public key does not return auto-created private key
* Issue #147 - API version is not set in service URL when using HTTPS
* Issue #146 - Bad request when using HTTPS
* Issue #142 - Creating a volume from ImageRef returns volume object without populating original reference
* Issue #135 - Deleting Non-existent KeyPair yields: Can not deserialize instance of java.lang.String out of START_OBJECT token
* Issue #134 - JDK 1.6 compatibility support option with Jersey 2 connector
* Issue #131 - Regression: Error adding a floating IP
* Issue #130 - NPE when creating a new Network
* Issue #129 - Unable to add a user to a tenant using RestEasy connector
* Issue #127 - Strange return value for Server.getOsExtendedVolumesAttached
* Issue #117 - SecGroupExtension.Rule.IPProtocol can be null instead of ANY or UNRECOGNIZED
* Issue #116 - Some methods in Service classes return void instead of ActionResponse
* Issue #115 - Method waitForServerStatus throws NPE
* Issue #113 - Return value for deallocateIP is void instead of ActionResponse
* Issue #111 - Writer errors when using the resteasy-connector
* Issue #106 - ActionResponse status is incorrect for addFloatingIP
* Issue #103 - Issue when updating a port in network
* Issue #97 - Yaml based template ignored in Stack creation
* Issue #95 - Easy determination within an Image to determine if it's a snapshot of a volume
* Issue #93 - Error listing images (Error reading entity from input stream)
* Issue #92 - Problems in deserializing an object (Heat Stack)
* Issue #91 - Implementation of the method "getHosts()" in the class availabilityZoneList
* Issue #90 - Volume Status "downloading" not regonized
* Issue #75 - NPE in NeutronNetwork
* Issue #20 - Glance - creating a large image throws out of memory error

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
