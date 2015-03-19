# Change Log

### 2.0.2 (Latest Release / Stable)

##### Enhancements/Improvements

* Issue #290 - Update OKHttp Connector to version 2.3.0
* Issue #289 - AbsoluteLimit: `maxServerGroups` and `maxServerGroupMembers` are missing
* Issue #284 - Extending ceilometer alarms functionality (thank you @e3ky)
* Issue #278 - Filtering of Attached Interfaces
* Issue #264 - Keystone v3 Authentication for Project Scope
* Issue #258 - Reset VM State support
* Issue #255 - HttpURLConnection connector option (thank you @krishnabrucelee)
* Issue #247 - Support to update Quotas for Tenant and Class
* Issue #233 - Ability to set criteria on Ceilometer Statistics
* Issue #217 - Support to build a shared network (thank you @octupszhang)
* Issue #216 - Telemetry API doesn't support alarms (thank you @e3ky)
* Issue #216 - Add support for Sahara data processing (thank you @ekasitk)
* Issue #208 - Support for LBaaS (Load-balancer as a service) (thank you @liujunpengwork)
* Issue #203 - Support Host aggregates (thank you @liujunpengwork)
* Issue #187 - Support for Volume Transfer
* Issue #183 - Heat: Nested templates (Files & Env) support for a Stack (thank you @magixyu)

##### Fixes

* Issue #286 - Error while downloading the object from container
* Issue #285 - ResetAction not working against a Server
* Issue #282 - Quota set volume & gigabytes is always 0 and instances and do not update
* Issue #281 - NeutronError while detaching interface is not caught
* Issue #277 - Option to clear gateway once set
* Issue #268 - RouterExternal flag missing in NetworkBuilder interface
* Issue #263 - Not closing response when 404 happens
* Issue #262 - Creating a Port by using an existing as a template (cloning) fails
* Issue #261 - Unable to pass SampleCriteria as a parameter in sample() method
* Issue #257 - VM Migration Exception (ActionResponse fault)
* Issue #249 - Unable to capture Create Subnet Error
* Issue #241 - HttpClient Connector: Assign/Remove floating IP to the server throws JsonMappingException
* Issue #237 - Rackspace URL for images are incorrectly determined
* Issue #236 - HP Cloud: Failed to create network port
* Issue #234 - Volumes cannot be used on HP Cloud
* Issue #227 - OSFactory.clientFromAccess(access) doesn't fetch admin scoped tenant/users
* Issue #226 - Creating Network on HP Cloud fails
* Issue #225 - NullPointerException in HttpExecutor when ServiceLoader finds no HttpExecutorService
* Issue #222 - ObjectStorage: file download
* Issue #220 - NetworkType enum doesn't support "vxlan" value
* Issue #211 - NullPointerException: Find Tenant By Name
* Issue #201 - Find a specific Tenant using getByName function raise exception


### 2.0.1

##### Enhancements/Improvements

* Issue #182 - os-availability-zone support
* Issue #177 - Heat: Nested template support - Thanks @magixyu 
* Issue #175 - Implement os-floating-ip-dns resource
* Issue #174 - Neutron: Ability to set DNS Nameserver and Host Route within SubnetBuilder  
* Issue #173 - Ceilometer: Ability to specify 'project' in obtaining samples using SampleCriteria
* Issue #171 - Upload volume to image  
* Issue #169 - Update a neutron network
* Issue #165 - volume migrate and force delete support  
* Issue #164 - Interface attachment support

##### Fixes

* Issue #181 - Can't get VNCConsoleURL using RestEasy connector
* Issue #172 - ClassCastException with HttpClient-2.0.1 connecter while downloading image
* Issue #163  - State vs Status in the NeutronPort
* Issue #161  - Issue with attaching a Volume
* Issue #160 - Adding users to a tenant is not working

### 2.0.0

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
* Issue #90 - Volume Status "downloading" not recognized
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
