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
package com.huawei.openstack4j.api.identity.v2;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.resolvers.LatestServiceVersionResolver;
import com.huawei.openstack4j.openstack.internal.OSClientSession.OSClientSessionV2;

@Test(suiteName = "Identity/ServiceVersions")
public class ServiceVersionTests extends AbstractTest {

    @Test
    public void thatComputeV2IsReturnedByDefault() {
        assertTrue(session().useConfig(Config.DEFAULT).getEndpoint(ServiceType.COMPUTE).contains("/v2/"), "Endpoint was not version 2");
    }

    @Test
    public void thatComputeV21IsReturned() {
        Config config = Config.newConfig().withResolver(LatestServiceVersionResolver.INSTANCE);
        assertTrue(session().useConfig(config).getEndpoint(ServiceType.COMPUTE).contains("/v2.1/"), "Endpoint was not version 2.1");
    }

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

    private OSClientSessionV2 session() {
        return (OSClientSessionV2) osv2();
    }
}
