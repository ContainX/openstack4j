openstack4j core-test
===========================

Tests for OpenStack4j using [TestNG](http://testng.org) test framework and an web server to mock server-client communication.

HOW TO
------

### Write Tests

Tests are created in the folder corresponding to the OpenStack service.
The following code shows a simple example.
 
    @Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
    public class KeystoneCredentialServiceTests extends AbstractTest {
        
        # Mocking response using json. Define path to file in /resources folder.
        private static final String JSON_CREDENTIALS_UPDATE = "/identity/v3/credentials_update_response.json";
        
        
        # Initialize corresponding service
        @Override
        protected Service service() {
            return Service.IDENTITY;
        }
     
        // ------------ Tests ------------
     
        public void credentials_update_Test() throws Exception {
            
            # respond to subsequent request with the JSON
            respondWith(JSON_CREDENTIALS_UPDATE);
     
            Credential updatedCredential = osv3().identity().credentials().update(Builders.credential()
                    .id(CREDENTIAL_CRUD_ID)
                    .blob(CREDENTIAL_CRUD_BLOB_UPDATE)
                    .build());
     
            assertEquals(updatedCredential.getId(), CREDENTIAL_CRUD_ID);
            assertEquals(updatedCredential.getBlob(), CREDENTIAL_CRUD_BLOB_UPDATE);
            assertEquals(updatedCredential.getUserId(), CREDENTIAL_CRUD_USER_ID);
            assertEquals(updatedCredential.getProjectId(), CREDENTIAL_CRUD_PROJECT_ID);
     
        }
 
    }


### Run Tests

* Tests in the `core-test` module are executed in the test phase via ```mvn clean test``` (or any phase later).

#### Adding a new service

OpenStack services require an endpoint in the service catalog of a access object (Keystone v2) or of token (Keystone v3).
Since the initial authentication request and its response are mocked, the endpoints have to added manually to the corresponding files.
The following fragments might be helpful.

#####Keystone V2:
    
    # Add to service catalog in /resources/idenity/v2/access.json

    {
	"endpoints": [
	    {
		"adminURL": "http:\/\/127.0.0.1:8774\/v2\/b80f8d4e28b74188858b654cb1fccf7d",
		"region": "RegionOne",
		"internalURL": "http:\/\/127.0.0.1:8774\/v2\/b80f8d4e28b74188858b654cb1fccf7d",
		"id": "3d44054fe0ec440e9d4641a7219002dd",
		"publicURL": "http:\/\/127.0.0.1:8774\/v2\/b80f8d4e28b74188858b654cb1fccf7d"
		}
		],
	"endpoints_links": [],
	"type": "compute",
	"name": "nova"
    }
    

#####Keystone V3:

    # Add to catalog in /resources/idenity/v3/auth_v3_project.json

    {
    "endpoints": [
        {
		"region_id": "RegionOne",
		"url": "http://127.0.0.1:<port>",
		"region": "RegionOne",
		"interface": "public",
		"id": "<id>"
		}, 
		{
		"region_id": "RegionOne",
		"url": "http://127.0.0.1:<port>",
		"region": "RegionOne",
		"interface": "admin",
		"id": "<id>"
		},
		{
		"region_id": "RegionOne",
		"url": "http://127.0.0.1:<port>",
		"region": "RegionOne",
		"interface": "internal",
		"id": "<id>"
	    }
	],
	"type": "<type>",
	"id": "<id>",
	"name": "<name>"
	}
