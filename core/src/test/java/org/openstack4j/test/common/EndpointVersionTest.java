package org.openstack4j.test.common;

import static org.testng.Assert.assertTrue;

import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.testng.annotations.Test;

/**
 * Tests API Endpoint version enforcement
 * 
 * @author Jeremy Unruh
 */
@Test(suiteName="Endpoint Version Tests")
public class EndpointVersionTest {

    private static String[] ENDPOINTS = new String[] {
        "https://v1.testme.com:8888",
        "http://10.2.2.2:2323/v2",
        "http://1.1.1.2:1234",
        "https://2.2.3.4:2222/v2.0"
    };

    @Test
    public void endpointVersionOneTest() {
        EnforceVersionToURL func = EnforceVersionToURL.instance("/v1");
        for (String ep : ENDPOINTS) {
            assertTrue(func.apply(ep).endsWith("/v1"));
        }
    }
    
    @Test
    public void endpointVersionTwoTest() {
        EnforceVersionToURL func = EnforceVersionToURL.instance("/v2");
        for (String ep : ENDPOINTS) {
            assertTrue(func.apply(ep).endsWith("/v2"));
        }
    }
    
    public void endpointVersionTwoDotTest() {
        EnforceVersionToURL func = EnforceVersionToURL.instance("/v2.0");
        for (String ep : ENDPOINTS) {
            assertTrue(func.apply(ep).endsWith("/v2.0"));
        }
    }
}
