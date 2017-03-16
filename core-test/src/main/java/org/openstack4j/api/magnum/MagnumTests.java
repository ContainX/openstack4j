package org.openstack4j.api.magnum;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.magnum.Baymodel;
import org.openstack4j.model.magnum.Mservice;
import org.testng.annotations.Test;


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
