package org.openstack4j.api.identity.v3;

import static org.testng.Assert.*;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.identity.v3.ApplicationCredential;
import org.openstack4j.openstack.identity.v3.domain.KeystoneApplicationCredential;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Test(groups = "idV3", suiteName = "Identity/V3/Keystone")
public class KeystoneApplicationCredentialServiceTests extends AbstractTest {

    private static final String JSON_APPLICATION_CREDENTIAL_CREATE = "/identity/v3/applicationcredential_create.json";
    private static final String JSON_APPLICATION_CREDENTIAL_GET = "/identity/v3/applicationcredential_get.json";
    private static final String JSON_APPLICATION_CREDENTIAL_LIST = "/identity/v3/applicationcredential_list.json";
    private static final String USER_ID = "testuserid";
    private static final String ID = "58d61ff8e6e34accb35874016d1dba8b";
    private static final String SECRET = "rEaqvJka48mpv";
    private static final String NAME = "monitoring";
    private static final String PROJECT_ID = "231c62fb0fbd485b995e8b060c3f0d98";
    private static final String ROLE_ID = "6aff702516544aeca22817fd3bc39683";
    private static final String ROLE_NAME = "Reader";
    private static final String DESCRIPTION = "Application credential for monitoring.";

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    public void application_credential_create_Test() throws Exception {

        respondWith(JSON_APPLICATION_CREDENTIAL_CREATE);

        ApplicationCredential credential = osv3().identity()
                .applicationCredential()
                .create(USER_ID,
                        KeystoneApplicationCredential.builder()
                                .name(NAME)
                                .expiresAt(new Date())
                                .description(USER_ID)
                                .projectId(PROJECT_ID)
                                .build());

        performAssertions(credential);
        assertEquals(credential.getSecret(), SECRET);
    }

    public void application_credential_get_Test() throws Exception {
        respondWith(JSON_APPLICATION_CREDENTIAL_GET);

        ApplicationCredential credential = osv3().identity()
                .applicationCredential()
                .get(USER_ID, ID);
        performAssertions(credential);
    }

    public void application_credential_list_Test() throws Exception {
        respondWith(JSON_APPLICATION_CREDENTIAL_LIST);

        List<? extends ApplicationCredential> credentialList = osv3().identity()
                .applicationCredential()
                .list(USER_ID);

        performAssertions(credentialList.get(1));
    }

    public void performAssertions(ApplicationCredential credential) {
        assertNotNull(credential);
        assertEquals(credential.getId(), ID);
        assertEquals(credential.getProjectId(), PROJECT_ID);
        assertEquals(credential.getDescription(), DESCRIPTION);
        assertTrue(credential.getRoles().get(0).getName().equals(ROLE_NAME));
        assertTrue(credential.getRoles().get(0).getId().equals(ROLE_ID));
        assertTrue(credential.getExpiresAt().getTime() > 0);
    }
}
