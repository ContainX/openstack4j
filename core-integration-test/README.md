openstack4j-integrationtest
===========================

Integration tests for openstack4j's identity (keystone) v3 written in groovy using spock testframework and betamax to record/replay server-client communication

HOW TO
------
### Run ITests

* Integration tests are executed in the integration-test phase. Do a ```mvn clean integration-test``` or ```mvn clean verify``` (or any phase later) to trigger the tests.

### Write ITests

* Using the Spock Framework: http://spockframework.github.io/spock/docs/1.1-rc-1/index.html
* Recording cloud interactions with Betamax: https://github.com/betamaxteam/betamax

Important
---------
* the tests work completely without setting the following environment variables. In which case the tapes are used in **playback** mode.
* Adding or changing test cases with cloud interaction requires **recording** by providing the following required parameters in your system environment. They are similar to the ones the python openstack-client needs. Make sure they are set or tests will be skipped.
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