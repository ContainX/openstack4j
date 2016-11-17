package org.openstack4j.api.compute;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.compute.domain.NovaServerTag;
import org.testng.annotations.Test;

/**
 * Test cases for Server Tag based Services
 * 
 * @author whaon
 */
@Test(suiteName="ServerTags")
public class ServerTagTests extends AbstractTest {

    private static final String JSON_SERVER_TAGS = "/compute/tags.json";

    @Test
    public void listTags() throws Exception {
        respondWith(JSON_SERVER_TAGS);
        NovaServerTag novaTags = osv3().compute().serverTags().list("1");
        List<String> tags = novaTags.getTags();
        assertEquals(2, tags.size());
        assertEquals("tag1", tags.get(0));
    }
    
    @Test
    public void replaceTags() {
    	String jsonResponse = "{\"tags\": [\"new1\", \"new2\"]}";
        respondWith(200, jsonResponse);
        NovaServerTag novaTags = new NovaServerTag();
        novaTags.addTag("new1");
        novaTags.addTag("new2");
        NovaServerTag newTags = osv3().compute().serverTags().replace("1", novaTags);
        
        assertEquals(novaTags.getTags(), newTags.getTags());
    }
    
    @Test
    public void deleteAllTags() {
    	respondWith(204);
    	ActionResponse delete = osv3().compute().serverTags().deleteAll("1");
    	System.out.println(delete.getCode());
    	assertTrue(delete.isSuccess());
    }
    
    @Test
    public void checkTag() {
    	respondWith(204);
    	ActionResponse check = osv3().compute().serverTags().check("1", "tag1");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void addTag() {
    	respondWith(204);
    	ActionResponse check = osv3().compute().serverTags().addSingle("1", "tag");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void deleteTag() {
    	respondWith(204);
    	ActionResponse delete = osv3().compute().serverTags().delete("1", "tag1");
    	assertTrue(delete.isSuccess());
    }
    
    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

}
