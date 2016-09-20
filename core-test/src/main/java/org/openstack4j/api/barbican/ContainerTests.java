package org.openstack4j.api.barbican;

import com.google.common.collect.ImmutableMap;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.barbican.Container;
import org.openstack4j.model.barbican.ContainerSecret;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@Test(suiteName="Barbican/Containers", enabled = true)
public class ContainerTests extends AbstractTest {
    private static final String CONTAINER_JSON = "/barbican/container.json";
    private static final String CONTAINER_CREATE_JSON = "/barbican/container_create.json";
    private static final String CONTAINERS_JSON = "/barbican/containers.json";

    private final String containerId = "eecf6cad-65af-4a11-9e6f-692b23ffac08";
    private final String containerName = "test_container";

    public void testListContainersByName() throws IOException {
        respondWith(CONTAINERS_JSON);
        List<? extends Container> list = osv3().barbican().containers().list("test-container");
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getName(), containerName);
    }

    public void testListContainersWithFilter() throws IOException {
        respondWith(CONTAINERS_JSON);
        Map<String, String> filters = ImmutableMap.of("limit", "1");
        List<? extends Container> list = osv3().barbican().containers().list(filters);
        assertEquals(list.size(), 1);
    }

    public void testGetContainer() throws IOException {
        respondWith(CONTAINER_JSON);
        Container container = osv3().barbican().containers().get(containerId);
        assertNotNull(container);
        assertNotNull(container.getName());
    }

    public void testCreateContainer() throws IOException {
        respondWithCodeAndResource(201, CONTAINER_CREATE_JSON);
        ContainerSecret containerSecret = Builders.containerSecret().name("test").reference("http://localhost/containerSecret").build();
        Container test = Builders.container()
                .name("test-container")
                .type("generic")
                .secretReferences(Arrays.asList(containerSecret))
                .build();
        Container result = osv3().barbican().containers().create(test);
        assertNotNull(result.getContainerReference());
    }

    public void testDeleteContainer() throws IOException {
        respondWith(204);
        ActionResponse result = osv3().barbican().containers().delete(containerId);
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.BARBICAN;
    }
}
