package org.openstack4j.api.heat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.heat.AdoptStackData;
import org.openstack4j.model.heat.Stack;
import org.openstack4j.openstack.heat.domain.HeatAdoptStackData;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 * Test cases for Heat Stack Services function
 *
 * @author Ales Kemr
 */
@Test(suiteName="heat/stacks", enabled = true)
public class StackServiceTests extends AbstractTest {

    private static final String JSON_ABANDON = "/heat/abandon.json";
    private static final String JSON_ADOPT = "/heat/adopt.json";
    
    public void testAbandonStack() throws Exception {
        respondWith(JSON_ABANDON);
        
        AdoptStackData adoptStackData = osv3().heat().stacks().abandon("stack_123", "416c09e9-2022-4d43-854b-0292ddff3f5d");
        takeRequest();
        
        assertEquals(adoptStackData.getName(), "stack_123");
        assertEquals(adoptStackData.getStatus(), "COMPLETE");
        final Map<String, Object> portResource = adoptStackData.getResources().get("network_port");
        assertEquals(portResource.get("type"), "OS::Neutron::Port");
    }
    
    public void testAdoptStack() throws Exception {
        respondWith(JSON_ADOPT);
        
        AdoptStackData adoptStackData = new ObjectMapper().readValue(getResource(JSON_ABANDON), HeatAdoptStackData.class);
        Stack adoptedStack = osv3().heat().stacks().adopt(adoptStackData, new HashMap<String, String>(), false, 30L, null);
        takeRequest();
        
        assertEquals(adoptedStack.getId(), "79370050-6038-4ea2-baaa-3e4706d59e0e");
    }

    @Override
    protected Service service() {
        return Service.ORCHESTRATION;
    }

}
