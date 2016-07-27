package org.openstack4j.api.network;


import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.ListenerV2;
import org.openstack4j.model.network.ext.ListenerV2Update;
import org.openstack4j.model.network.ext.Protocol;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

/**
 *
 * @author ashleykasim
 *
 */
@Test(suiteName="Network/listener", enabled = true)
public class ListenerTests extends AbstractTest {
    private static final String LISTENERS_JSON = "/network/listeners.json";
    private static final String LISTENER_JSON = "/network/listener.json";
    private static final String LISTENER_UPDATE_JSON = "/network/listener_update.json";

    public void testListListeners() throws IOException {
        respondWith(LISTENERS_JSON);
        List<? extends ListenerV2> list = osv3().networking().lbaasV2().listenerV2().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getName(), "listener1");
    }

    public void testListListenersFilter() throws IOException {
        respondWith(LISTENERS_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("tenantId", "6f759d84e3ca496ab77f8c0ffaa0311e");
        List<? extends ListenerV2> list = osv3().networking().lbaasV2().listenerV2().list(map);
        assertEquals(list.size(), 2);
    }

    public void testGetlistener() throws IOException {
        respondWith(LISTENER_JSON);
        String id = "c07058a9-8d84-4443-b8f5-508d0facfe10";
        ListenerV2 listener = osv3().networking().lbaasV2().listenerV2().get(id);
        assertNotNull(listener);
        assertEquals(listener.getId(), id);
    }

    public void testCreatelistener() throws IOException {
        respondWith(LISTENER_JSON);
        String name = "listener1";
        String description = "";
        Protocol protocol = Protocol.HTTP;
        ListenerV2 create = Builders.listenerV2()
                .adminStateUp(true)
                .name(name)
                .description(description)
                .protocol(protocol)
                .build();
        ListenerV2 result = osv3().networking().lbaasV2().listenerV2().create(create);
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
        assertEquals(result.getProtocol(), protocol);
        assertTrue(result.isAdminStateUp());
    }

    public void testUpdatelistener() throws IOException {
        respondWith(LISTENER_UPDATE_JSON);
        String name = "listener_updated";
        String description = "im a good listener";
        Integer connectionLimit = 20;
        ListenerV2Update update = Builders.listenerV2Update()
                .adminStateUp(false)
                .description(description)
                .name(name)
                .connectionLimit(connectionLimit)
                .build();
        ListenerV2 result = osv3().networking().lbaasV2().listenerV2().update("c07058a9-8d84-4443-b8f5-508d0facfe10", update);
        assertFalse(result.isAdminStateUp());
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
        assertEquals(result.getConnectionLimit(), connectionLimit);
    }

    public void testDeletelistener() {
        respondWith(204);
        ActionResponse result = osv3().networking().lbaasV2().listenerV2().delete("c07058a9-8d84-4443-b8f5-508d0facfe10");
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
