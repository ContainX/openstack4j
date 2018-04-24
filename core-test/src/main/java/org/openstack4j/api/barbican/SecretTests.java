package org.openstack4j.api.barbican;

import com.google.common.collect.ImmutableMap;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.barbican.Secret;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * Created by reneschollmeyer on 18.08.17.
 */
@Test(suiteName = "Barbican/Secrets", enabled = true)
public class SecretTests extends AbstractTest {

    private static final String SECRET_JSON = "/barbican/secret.json";
    private static final String SECRET_CREATE_JSON = "/barbican/secret_create.json";
    private static final String SECRETS_JSON = "/barbican/secrets.json";

    private final String secretId = "520405bc-c7c5-41ea-97ad-6c67a8d41a9e";
    private final String secretName = "test_secret";
    private final String content_type = "application/octet-stream";

    private final Date expiration = new Date(1451330264394l);

    public void testListSecretsByName() throws IOException {
        respondWith(SECRETS_JSON);
        List<? extends Secret> list = osv3().barbican().secrets().list("test-secret");
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getName(), secretName);
    }

    public void testListSecretWithFilter() throws IOException {
        respondWith(SECRETS_JSON);
        Map<String, String> filters = ImmutableMap.of("limit", "1");
        List<? extends Secret> list = osv3().barbican().secrets().list(filters);
        assertEquals(list.size(), 1);
    }

    public void testGetSecret() throws IOException {
        respondWith(SECRET_JSON);
        Secret secret = osv3().barbican().secrets().get(secretId);
        assertNotNull(secret);
        assertNotNull(secret.getName());
        assertEquals(secret.getExpiration(), expiration);
        assertTrue(!secret.getContentTypes().isEmpty());
        assertEquals(secret.getContentTypes().get("default"), content_type);
    }

    public void testCreateSecret() throws IOException {
        respondWithCodeAndResource(201, SECRET_CREATE_JSON);
        Secret test = Builders.secret()
                .name("test-secret")
                .algorithm("aes")
                .bitLength(256)
                .expiration(new Date())
                .mode("cbc")
                .secretType("opaque")
                .payload("test-payload")
                .payloadContentType("text/plain")
                .build();
        Secret result = osv3().barbican().secrets().create(test);
        assertNotNull(result);
        assertNotNull(result.getSecretReference());
    }

    public void testDeleteSecret() throws IOException {
        respondWith(204);
        ActionResponse result = osv3().barbican().secrets().delete(secretId);
        assertTrue(result.isSuccess());
    }

    @Override
    protected Service service() {
        return Service.BARBICAN;
    }
}
