openstack4j-integrationtest
===========================

integration tests for openstack4j's identity (keystone) v3 using spock, groovy (and in next commit: betamax to record/replay server-client communication)

HOW TO
------
* tests are executed in integration-test phase. Do a ```mvn clean integration-test``` or ```mvn clean install``` (or any phase later should include the ones before)

Important
---------
* required parameters used for integration test are read from your system environment and they are similar to the ones the python openstack-client needs. Make sure they are set or tests will be skipped. 
* attributes used:
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