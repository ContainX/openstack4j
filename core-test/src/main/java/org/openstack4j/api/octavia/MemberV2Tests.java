package org.openstack4j.api.octavia;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.MemberV2;
import org.openstack4j.model.octavia.MemberV2Update;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/**
 *
 * @author wei
 *
 */
@Test(suiteName="Octavia/memberV2", enabled = true)
public class MemberV2Tests extends AbstractTest {
    private static final String MEMBERSV2_JSON = "/octavia/membersv2.json";
    private static final String MEMBERV2_JSON = "/octavia/memberv2.json";
    private static final String MEMBERV2_UPDATE_JSON = "/octavia/memberv2_update.json";

    public void testListMembersV2() throws IOException {
        respondWith(MEMBERSV2_JSON);
        List<? extends MemberV2> list = osv3().octavia().lbPoolV2().listMembers("4c0a0a5f-cf8f-44b7-b912-957daa8ce5e5");
        assertEquals(list.size(), 2);
        assertEquals("9a7aff27-fd41-4ec1-ba4c-3eb92c629313", list.get(0).getId());
    }

    public void testListMembersV2Filter() throws IOException {
        respondWith(MEMBERSV2_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("weight", "1");
        List<? extends MemberV2> list = osv3().octavia().lbPoolV2().listMembers("4c0a0a5f-cf8f-44b7-b912-957daa8ce5e5", map);
        assertEquals(list.size(), 2);
    }

    public void testGetMemberV2() throws IOException {
        respondWith(MEMBERV2_JSON);
        String id = "9a7aff27-fd41-4ec1-ba4c-3eb92c629313";
        MemberV2 hm = osv3().octavia().lbPoolV2().getMember("4c0a0a5f-cf8f-44b7-b912-957daa8ce5e5", id);
        assertNotNull(hm);
        assertEquals(hm.getId(), id);
    }

    public void testCreateMemberV2() throws IOException {
        respondWith(MEMBERV2_JSON);
        String address = "10.0.0.8";
        Integer port = 80;
        Integer weight = 1;
        MemberV2 create = Builders.octavia().memberV2()
                .adminStateUp(true)
                .address(address)
                .protocolPort(port)
                .weight(weight)
                .build();
        MemberV2 result = osv3().octavia().lbPoolV2().createMember("4c0a0a5f-cf8f-44b7-b912-957daa8ce5e5", create);
        assertEquals(result.getAddress(), address);
        assertEquals(result.getProtocolPort(), port);
        assertEquals(result.getWeight(), weight);
        assertTrue(result.isAdminStateUp());
    }

    public void testUpdateMemberV2() throws IOException {
        respondWith(MEMBERV2_UPDATE_JSON);
        Integer weight = 2;
        String id = "9a7aff27-fd41-4ec1-ba4c-3eb92c629313";
        MemberV2Update update = Builders.octavia().memberV2Update()
                .weight(weight)
                .adminStateUp(false)
                .build();
        MemberV2 result = osv3().octavia().lbPoolV2().updateMember("4c0a0a5f-cf8f-44b7-b912-957daa8ce5e5", id, update);
        assertEquals(result.getWeight(), weight);
        assertFalse(result.isAdminStateUp());
    }

    public void testDeleteMemberV2() {
        respondWith(204);
        ActionResponse result = osv3().octavia().lbPoolV2().deleteMember("4c0a0a5f-cf8f-44b7-b912-957daa8ce5e5", "9a7aff27-fd41-4ec1-ba4c-3eb92c629313");
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.OCTAVIA;
    }
}