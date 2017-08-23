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
package com.huawei.openstack4j.api.murano.v1;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.murano.v1.domain.Deployment;
import com.huawei.openstack4j.model.murano.v1.domain.Report;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author nmakhotkin
 */
@Test(suiteName="Murano/AppCatalog", enabled = true)
public class DeploymentTests extends AbstractTest {
    private static final String DEPLOYMENTS_JSON = "/murano/v1/deployments.json";
    private static final String REPORTS_JSON = "/murano/v1/reports.json";
    private static final String FILTERED_REPORTS_JSON = "/murano/v1/filtered_reports.json";
    private static final String envId = "3e57cee8b55448f6af0752d31d7e27d6";
    private static final String deploymentId = "ccfd5e951c70428c852bee3b2a9a132e";

    public void testDeploymentList() throws IOException {
        respondWith(DEPLOYMENTS_JSON);
        List<? extends Deployment> deployments = osv3().murano().deployments().list(envId);

        assertNotNull(deployments);
        assertEquals(deployments.size(), 1);
        assertEquals(deployments.get(0).getEnvironmentId(), envId);
        assertTrue(deployments.get(0).getResult().isException());
    }

    public void testReports() throws IOException {
        respondWith(REPORTS_JSON);
        List<? extends Report> reports = osv3().murano().deployments().reports(envId, deploymentId);

        assertNotNull(reports);
        assertEquals(reports.size(), 8);
        assertEquals(reports.get(reports.size() - 1).getLevel(), "info");
    }

    public void testFilteredReports() throws IOException {
        respondWith(FILTERED_REPORTS_JSON);
        List<? extends Report> reports = osv3().murano().deployments().reports(
            envId,
            deploymentId,
            Collections.singletonList("5c2a7dae-097d-4a1c-85cb-e7db2ed62d90")
        );

        assertNotNull(reports);
        assertEquals(reports.size(), 3);
        assertEquals(reports.get(reports.size() - 1).getId(), "e5558d941efb4371a8a316c29cb77cf0");
    }

    @Override
    protected Service service() {
        return Service.APP_CATALOG;
    }
}
