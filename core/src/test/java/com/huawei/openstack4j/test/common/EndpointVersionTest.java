/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package com.huawei.openstack4j.test.common;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.huawei.openstack4j.openstack.common.functions.EnforceVersionToURL;

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
