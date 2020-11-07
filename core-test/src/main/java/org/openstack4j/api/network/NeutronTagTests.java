package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.Resource;
import org.openstack4j.openstack.networking.domain.NeutronResourceTag;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for (Neutron) Resource Tag based Services
 * 
 * @author bboyHan
 */
@Test(suiteName="NeutronTagTests")
public class NeutronTagTests extends AbstractTest {

    private static final String JSON_SECURITY_GROUP_TAGS = "/network/tags.json";

    @Test
    public void listTags() throws Exception {
        respondWith(JSON_SECURITY_GROUP_TAGS);
        NeutronResourceTag sgTags = osv3().networking().resourceTags().list(Resource.NETWORK, "1");
        List<String> tags = sgTags.getTags();
        assertEquals(2, tags.size());
        assertEquals("tag1", tags.get(0));
    }
    
    @Test
    public void replaceTags() {
    	String jsonResponse = "{\"tags\": [\"newTag1\", \"newTag2\"]}";
        respondWith(200, jsonResponse);
        NeutronResourceTag sgTags = new NeutronResourceTag();
        sgTags.addTag("newTag1");
        sgTags.addTag("newTag2");
        NeutronResourceTag newTags = osv3().networking().resourceTags().replace(Resource.NETWORK, "1", sgTags);
        
        assertEquals(sgTags.getTags(), newTags.getTags());
    }
    
    @Test
    public void deleteAllTags() {
    	respondWith(204);
    	ActionResponse delete = osv3().networking().resourceTags().deleteAll(Resource.NETWORK, "1");
    	System.out.println(delete.getCode());
    	assertTrue(delete.isSuccess());
    }
    
    @Test
    public void checkTag() {
    	respondWith(204);
    	ActionResponse check = osv3().networking().resourceTags().check(Resource.NETWORK, "1", "tag1");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void addTag() {
    	respondWith(204);
    	ActionResponse check = osv3().networking().resourceTags().addSingle(Resource.NETWORK, "1", "tag");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void deleteTag() {
    	respondWith(204);
    	ActionResponse delete = osv3().networking().resourceTags().delete(Resource.NETWORK, "1", "tag1");
    	assertTrue(delete.isSuccess());
    }
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }

}
