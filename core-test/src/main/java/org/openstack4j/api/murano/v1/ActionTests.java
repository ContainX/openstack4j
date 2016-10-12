package org.openstack4j.api.murano.v1;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.murano.v1.domain.ActionInfo;
import org.openstack4j.model.murano.v1.domain.ActionResult;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * @author nmakhotkin
 */
@Test(suiteName="Murano/AppCatalog", enabled = true)
public class ActionTests extends AbstractTest {
    private static final String ENVIRONMENT_JSON = "/murano/v1/environment.json";
    private static final String ACTION_RESULT = "/murano/v1/action_result.json";
    private static final String envId = "e1c1b5a0b3284f188c5d91ab18bf0451";
    private static final String serviceId = "e8d61b75-df6a-42c6-be2c-5b6aeaa60f5c";
    private static final String actionId = "e8d61b75-df6a-42c6-be2c-5b6aeaa60f5c_getTest";

    public void testListActions() throws IOException {
        respondWith(ENVIRONMENT_JSON);
        List<? extends ActionInfo> actions = osv3().murano().actions().list(envId);

        assertNotNull(actions);
        assertEquals(actions.size(), 2);
    }

    public void testListActionsSpecificService() throws IOException {
        respondWith(ENVIRONMENT_JSON);
        List<? extends ActionInfo> actions = osv3().murano().actions().list(envId, serviceId);

        assertNotNull(actions);
        assertEquals(actions.size(), 2);
    }

    public void testGetOneAction() throws IOException {
        respondWith(ENVIRONMENT_JSON);

        ActionInfo action = osv3().murano().actions().get(envId, actionId);
        assertNotNull(action);
        assertEquals(action.getName(), "getTest");
    }

    public void testFindAction() throws IOException {
        respondWith(ENVIRONMENT_JSON);

        ActionInfo action = osv3().murano().actions().find(envId, "getTest");
        assertNotNull(action);
        assertEquals(action.getName(), "getTest");
    }

    public void testFindAllActions() throws IOException {
        respondWith(ENVIRONMENT_JSON);
        List<? extends ActionInfo> actions = osv3().murano().actions().findAll(envId, "getTest");

        assertNotNull(actions);
        assertEquals(actions.size(), 1);
    }

    public void testCastActionWithoutArguments() throws IOException {
        respondWith(200, "{\"task_id\": \"some_id\"}");
        String taskId = osv3().murano().actions().cast(envId, actionId);

        assertNotNull(taskId);
        assertEquals(taskId, "some_id");
    }

    public void testCastActionStringArguments() throws IOException {
        respondWith(200, "{\"task_id\": \"some_id\"}");
        String json = "{\"some_var\": \"some_value\"}";

        String taskId = osv3().murano().actions().cast(envId, actionId, json);

        assertNotNull(taskId);
        assertEquals(taskId, "some_id");
    }

    public void testCastActionMapArguments() throws IOException {
        respondWith(200, "{\"task_id\": \"some_id\"}");
        Map<String, Object> map = new HashMap<>();
        map.put("some_var", "some_value");

        String taskId = osv3().murano().actions().cast(envId, actionId, map);
        assertNotNull(taskId);
        assertEquals(taskId, "some_id");
    }

    public void testGetResult() throws IOException {
        respondWith(ACTION_RESULT);

        ActionResult result = osv3().murano().actions().getResult(envId, "some_id");

        assertNotNull(result);
        assertFalse(result.isException());
    }

    public void testGetEmptyResult() throws IOException {
        respondWith(200, "{}");

        ActionResult result = osv3().murano().actions().getResult(envId, "some_id");

        assertNotNull(result);
        assertNull(result.isException());
    }

    @Override
    protected Service service() {
        return Service.APP_CATALOG;
    }
}
