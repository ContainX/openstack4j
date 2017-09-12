package org.openstack4j.api.octavia;


import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.ListenerV2;
import org.openstack4j.model.octavia.ListenerV2Update;
import org.openstack4j.model.octavia.ListenerProtocol;
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
@Test(suiteName="Octavia/listener", enabled = true)
public class ListenerV2Tests extends AbstractTest {
    private static final String LISTENERSV2_JSON = "/octavia/listenersv2.json";
    private static final String LISTENERV2_JSON = "/octavia/listenerv2.json";
    private static final String LISTENERV2_UPDATE_JSON = "/octavia/listenerv2_update.json";

    public void testListListenersV2() throws IOException {
        respondWith(LISTENERSV2_JSON);
        List<? extends ListenerV2> list = osv3().octavia().listenerV2().list();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getName(), "listener1");
    }

    public void testListListenersV2Filter() throws IOException {
        respondWith(LISTENERSV2_JSON);
        Map<String, String> map = new HashMap<>();
        map.put("tenantId", "6f759d84e3ca496ab77f8c0ffaa0311e");
        List<? extends ListenerV2> list = osv3().octavia().listenerV2().list(map);
        assertEquals(list.size(), 2);
    }

    public void testGetListenerV2() throws IOException {
        respondWith(LISTENERV2_JSON);
        String id = "c07058a9-8d84-4443-b8f5-508d0facfe10";
        ListenerV2 listener = osv3().octavia().listenerV2().get(id);
        assertNotNull(listener);
        assertEquals(listener.getId(), id);
    }

    public void testCreateListenerV2() throws IOException {
        respondWith(LISTENERV2_JSON);
        String name = "listener1";
        String description = "";
        ListenerProtocol protocol = ListenerProtocol.HTTP;
        String tlsContainerRef = "http://0.0.0.0:9311/v1/containers/52594300-d996-49e4-8bf1-a4e000171ad8";
        ListenerV2 create = Builders.octavia().listenerV2()
                .adminStateUp(true)
                .name(name)
                .description(description)
                .protocol(protocol)
                .defaultTlsContainerRef(tlsContainerRef)
                .build();
        ListenerV2 result = osv3().octavia().listenerV2().create(create);
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
        assertEquals(result.getProtocol(), protocol);
        assertEquals(result.getDefaultTlsContainerRef(), tlsContainerRef);
        assertTrue(result.isAdminStateUp());
    }

    public void testUpdateListenerV2() throws IOException {
        respondWith(LISTENERV2_UPDATE_JSON);
        String name = "listener_updated";
        String description = "im a good listener";
        Integer connectionLimit = 20;
        String tlsContainerRef = "http://0.0.0.0:9311/v1/containers/52594300-d996-49e4-8bf1-a4e000171ad9";
        ListenerV2Update update = Builders.octavia().listenerV2Update()
                .adminStateUp(false)
                .description(description)
                .name(name)
                .connectionLimit(connectionLimit)
                .defaultTlsContainerRef(tlsContainerRef)
                .build();
        ListenerV2 result = osv3().octavia().listenerV2().update("c07058a9-8d84-4443-b8f5-508d0facfe10", update);
        assertFalse(result.isAdminStateUp());
        assertEquals(result.getName(), name);
        assertEquals(result.getDescription(), description);
        assertEquals(result.getConnectionLimit(), connectionLimit);
        assertEquals(result.getDefaultTlsContainerRef(), tlsContainerRef);

    }

    public void testDeleteListenerV2() {
        respondWith(204);
        ActionResponse result = osv3().octavia().listenerV2().delete("c07058a9-8d84-4443-b8f5-508d0facfe10");
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.OCTAVIA;
    }
}
