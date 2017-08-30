package org.openstack4j.api.octavia;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.LbMethod;
import org.openstack4j.model.octavia.LbPoolV2;
import org.openstack4j.model.octavia.LbPoolV2Update;
import org.openstack4j.model.octavia.Protocol;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 *
 * @author wei
 *
 */
@Test(suiteName="Octavia/lbpoolv2", enabled=true)
public class LbPoolV2Tests extends AbstractTest {
    private static final String LBPOOLSV2_JSON = "/octavia/lbpoolsv2.json";
    private static final String LBPOOLV2_JSON = "/octavia/lbpoolv2.json";
    private static final String LBPOOLV2_UPDATE_JSON = "/octavia/lbpoolv2_update.json";

    public void testListPoolV2() throws IOException {
        respondWith(LBPOOLSV2_JSON);
        List<? extends LbPoolV2> list = osv3().octavia().lbPoolV2().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), "b7f6a49f-ebd8-43c5-b792-5748366eff21");
    }

    public void testListPoolV2Filter() throws IOException {
        respondWith(LBPOOLSV2_JSON);
        Map<String, String> map = new HashMap<String, String>();
        map.put("protocol", "HTTP");
        List<? extends LbPoolV2> list = osv3().octavia().lbPoolV2().list(map);
        assertEquals(list.size(), 2);
    }

    public void testGetPoolV2() throws IOException {
        respondWith(LBPOOLV2_JSON);
        String id = "b7f6a49f-ebd8-43c5-b792-5748366eff21";
        LbPoolV2 pool = osv3().octavia().lbPoolV2().get(id);
        assertNotNull(pool);
        assertEquals(pool.getId(), id);
    }

    public void testCreatePoolV2() throws IOException {
        respondWith(LBPOOLV2_JSON);
        String name = "testlbpool";
        Protocol protocol = Protocol.HTTP;
        String projectId = "6f759d84e3ca496ab77f8c0ffaa0311e";
        LbPoolV2 create = Builders.octavia().lbPoolV2()
                .adminStateUp(true)
                .description("im a swimming pool")
                .lbMethod(LbMethod.LEAST_CONNECTIONS)
                .name(name)
                .projectId(projectId)
                .protocol(protocol)
                .build();
        LbPoolV2 result = osv3().octavia().lbPoolV2().create(create);
        assertEquals(result.getName(), name);
        assertEquals(result.getLbMethod(), LbMethod.LEAST_CONNECTIONS);
        assertEquals(result.getProtocol(), protocol);
        assertEquals(result.getProjectId(), projectId);
    }

    public void testUpdatePoolV2() throws IOException {
        respondWith(LBPOOLV2_UPDATE_JSON);
        String poolId = "b7f6a49f-ebd8-43c5-b792-5748366eff21";
        String name = "v2update";
        LbPoolV2Update update = Builders.octavia().lbPoolV2Update()
                .adminStateUp(false)
                .description("im a carpool")
                .lbMethod(LbMethod.ROUND_ROBIN)
                .name(name)
                .build();
        LbPoolV2 result = osv3().octavia().lbPoolV2().update(poolId, update);
        assertEquals(result.getName(), name);
        assertEquals(result.getLbMethod(), LbMethod.ROUND_ROBIN);
        assertFalse(result.isAdminStateUp());
    }

    public void testDeletePoolV2() {
        respondWith(204);
        ActionResponse result = osv3().octavia().lbPoolV2().delete("b7f6a49f-ebd8-43c5-b792-5748366eff21");
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.OCTAVIA;
    }
}
