openstack4j-integrationtest
===========================

Integration tests for OpenStack4j written in [Groovy](http://www.groovy-lang.org/) using [Spock](http://spockframework.org/) test-framework and [Betamax](https://github.com/betamaxteam/betamax) to record/replay server-client communication.

HOW TO
------

### Write Integration Tests (IT)

Betamax is used to record and replay client-server communication to/from a tape. 
Therefore it is necessary to setup a RecorderRule, which specifies in which folder the tape can be found (tapeRoot),
 some match rules (e.g. by request/response method, path, query parameters) and a default mode for the tape which should always be read & write.  
Each test method that wants to use the recordings needs to specify a tape via the annotation `@Betamax(tape="<tapeName>")`.

Here are the basics of how a Specification should look like:
```
@Slf4j
class KeystoneRegionServiceSpec extends AbstractSpec {
    
    // setup test name
    @Rule TestName KeystoneRegionServiceTest
    
    // setup recorder rule used by Betamax
    @Rule public RecorderRule recorderRule = new RecorderRule(
            Configuration.builder()
                    .tapeRoot(new File(TAPEROOT + "identity.v3"))
                    .defaultMatchRules(MatchRules.method, MatchRules.path, MatchRules.queryParams)
                    .defaultMode(TapeMode.READ_WRITE)
                    .build());;
    
    // the following logic ensures all required attributes are set                 
    static final boolean skipTest
    
        static {
            if(
            USER_ID == null ||
            AUTH_URL == null ||
            PASSWORD == null ||
            DOMAIN_ID == null  ) {
    
                skipTest = true
            }
            else{
                skipTest = false
            }
        }
    
        def setupSpec() {
    
            if( skipTest != true ) {
                log.info("USER_ID: " + USER_ID)
                log.info("AUTH_URL: " + AUTH_URL)
                log.info("PASSWORD: " + PASSWORD)
                log.info("DOMAIN_ID: " + DOMAIN_ID)
                log.info("PROJECT_ID: " + PROJECT_ID)
            }
            else {
                log.warn("Skipping integration-test cases because not all mandatory attributes are set.")
            }
        }
    
        def setup() {
            log.info("-> Test: '$KeystoneRegionServiceTest.methodName'")
        }                
        
        @IgnoreIf({ skipTest })
        
        // specify the tape name
        @Betamax(tape="regionService_all.tape")
        def "region service CRUD test cases"() {
        
            // spock test cases
        
        }
}                    
``` 


### Run ITests

* Integration tests are executed in the integration-test phase. Do a ```mvn clean integration-test``` or ```mvn clean verify``` (or any phase later) to trigger the tests.

#### Recording

In order to record the communication to the tapes the following environment attributes must be set:
* attributes used: (incomplete)
	* OS_AUTH_URL = ``` "http://<fqdn>:5000/v3" ```
	* OS_USER_ID
	* OS_USER_NAME
	* OS_PASSWORD
	* OS_PROJECT_ID
	* OS_PROJECT_NAME
	* OS_PROJECT_DOMAIN_ID
	* OS_PROJECT_DOMAIN_NAME
	* OS_DOMAIN_ID
	* OS_DOMAIN_NAME 
	
Afterwards these values in the tapes must be replaced using the default ones defined in the AbstractSpec.
	
#### Playback

In playback mode the recordings are used. Make sure no OS_*-attributes are set.


Important
---------

In case of `com.fasterxml.jackson.databind.JsonMappingException: Unexpected end-of-input in VALUE_STRING` 
remove the Content-Length header from the recording. Use this regex for search & replace: `^\s*Content-Length: '[0-9]*'\R` .
