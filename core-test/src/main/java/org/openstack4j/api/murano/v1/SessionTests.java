package org.openstack4j.api.murano.v1;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.murano.v1.domain.AppCatalogSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author nmakhotkin
 */
@Test(suiteName="Murano/AppCatalog", enabled = true)
public class SessionTests extends AbstractTest {
    private static final String SESSION_JSON = "/murano/v1/session.json";
    private static final String envId = "e1c1b5a0b3284f188c5d91ab18bf0451";

    public void testGetSession() throws IOException {
        respondWith(SESSION_JSON);
        String id = "b8f4006064d24c10a33d9ed68e554f0f";
        AppCatalogSession ses = osv3().murano().sessions().get(envId, id);
        assertNotNull(ses);
        assertNotNull(ses.getId());
        assertEquals(ses.getId(), id);
    }

    public void testConfigureSession() throws IOException {
        respondWith(SESSION_JSON);
        AppCatalogSession session = osv3().murano().sessions().configure(envId);
        assertNotNull(session);
        assertEquals(envId, session.getEnvId());
    }

    public void testDeleteSession() throws IOException {
        respondWith(200);
        String id = "b8f4006064d24c10a33d9ed68e554f0f";
        ActionResponse delete = osv3().murano().sessions().delete(envId, id);
        assertTrue(delete.isSuccess());
    }

    public void testDeploySession() throws IOException {
        respondWith(200);
        String id = "b8f4006064d24c10a33d9ed68e554f0f";
        ActionResponse deploy = osv3().murano().sessions().deploy(envId, id);
        assertTrue(deploy.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.APP_CATALOG;
    }
}
