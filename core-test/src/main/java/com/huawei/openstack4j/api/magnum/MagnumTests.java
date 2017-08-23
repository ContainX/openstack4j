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
package com.huawei.openstack4j.api.magnum;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.model.magnum.Baymodel;
import com.huawei.openstack4j.model.magnum.Mservice;


/**
 * Test cases for Magnum Services
 * 
 * @author Sohan Sangwan
 */
@Test(suiteName = "magnum")
public class MagnumTests extends AbstractTest {
    private static final String JSON_MSERVICES = "/magnum/mservices.json";
    private static final String JSON_BAYMODEL_GETALL_RESPONSE = "/magnum/baymodel_get_all_resp.json";
    private static final String JSON_BAYMODEL_CRUD_RESPONSE = "/magnum/baymodel_create_resp.json";
    
    @Test
    public void magnum_mservice_list_test() throws IOException {
        respondWith(JSON_MSERVICES);
        List<? extends Mservice> resp = osv3().magnum().listMservices();
        assertEquals(resp.size(), 1);
    }
    
    @Test
    public void magnum_baymodel_list() throws IOException {
        respondWith(JSON_BAYMODEL_GETALL_RESPONSE);
        List<? extends Baymodel> resp = osv3().magnum().listBaymodels();
        assertEquals(resp.size(), 1);
    } 
    @Test
    public void magnum_baymodel_crud_test() throws IOException {
        
       
        Baymodel baymodel = Builders.baymodel()
                .name("k8s-bm2")
                .keypairId("magnum")
                .publicBaymodel(false)
                .dockerVolumeSize("3")
                .serverType("vm")
                .externalNetworkId("public")
                .imageId("fedora-atomic-latest")
                .volumeDriver("cinder")
                .registryEnabled(false)
                .coe("kubernetes")
                .flavorId("m1.small")
                .dnsNameserver("8.8.8.8")
                .uuid("085e1c4d-4f68-4bfd-8462-74b9e14e4f39")
                .build();
        
        //Create a baymodel
        respondWith(JSON_BAYMODEL_CRUD_RESPONSE);
        Baymodel resp = osv3().magnum().createBaymodel(baymodel);
        assertEquals(resp.getName(), baymodel.getName());        
 
    }
    
    

    @Override
    protected Service service() {
        return Service.MAGNUM;
    }
}
