package org.openstack4j.api.manila;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.SecurityService;
import org.openstack4j.model.manila.SecurityServiceCreate;
import org.openstack4j.model.manila.SecurityServiceUpdateOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for security services
 *
 * @author Daniel Gonzalez Nothnagel
 */
@Test(suiteName="SecurityService")
public class SecurityServiceTests extends AbstractTest {
    private static final String JSON_SECURITY_SERVICE = "/manila/security_service.json";
    private static final String JSON_SECURITY_SERVICE_CREATE = "/manila/security_service_create.json";
    private static final String JSON_SECURITY_SERVICE_UPDATE = "/manila/security_service_update.json";
    private static final String JSON_SECURITY_SERVICES = "/manila/security_services.json";
    private static final String JSON_SECURITY_SERVICES_DETAIL = "/manila/security_services_detail.json";

    @Override
    protected Service service() {
        return Service.SHARE;
    }

    @Test
    public void create() throws Exception {
        respondWith(JSON_SECURITY_SERVICE_CREATE);

        SecurityServiceCreate securityServiceToCreate = Builders.securityService()
                .name("SecServ1")
                .description("Creating my first Security Service")
                .type(SecurityService.Type.KERBEROS)
                .dnsIp("10.0.0.0/24")
                .user("demo")
                .password("***")
                .build();

        SecurityService securityService = osv3().share().securityServices().create(securityServiceToCreate);

        assertEquals(securityService.getName(), "SecServ1");
        assertEquals(securityService.getDescription(), "Creating my first Security Service");
        assertEquals(securityService.getType(), SecurityService.Type.KERBEROS);
        assertEquals(securityService.getDnsIp(), "10.0.0.0/24");
        assertEquals(securityService.getUser(), "demo");
        assertEquals(securityService.getPassword(), "***");
    }

    @Test
    public void list() throws Exception {
        respondWith(JSON_SECURITY_SERVICES);

        List<? extends SecurityService> securityServices = osv3().share().securityServices().list();
        assertEquals(securityServices.size(), 2);

        SecurityService securityService1 = securityServices.get(0);
        SecurityService securityService2 = securityServices.get(1);

        assertEquals(securityService1.getStatus(), "new");
        assertEquals(securityService1.getType(), SecurityService.Type.LDAP);
        assertEquals(securityService1.getId(), "7d156ed8-6570-4898-bca4-eac5b6565f0d");
        assertEquals(securityService1.getName(), "SecServ2");

        assertEquals(securityService2.getStatus(), "new");
        assertEquals(securityService2.getType(), SecurityService.Type.KERBEROS);
        assertEquals(securityService2.getId(), "ec8a2619-faa5-4878-a63d-4c784b0545f1");
        assertEquals(securityService2.getName(), "SecServ1");
    }

    @Test
    public void listDetails() throws Exception {
        respondWith(JSON_SECURITY_SERVICES_DETAIL);

        List<? extends SecurityService> securityServices = osv3().share().securityServices().listDetails();
        assertEquals(securityServices.size(), 2);

        SecurityService securityService1 = securityServices.get(0);
        SecurityService securityService2 = securityServices.get(1);

        assertEquals(securityService1.getStatus(), "new");
        assertEquals(securityService1.getDomain(), "my_domain");
        assertEquals(securityService1.getPassword(), "***");
        assertEquals(securityService1.getName(), "SecServ2");
        assertEquals(securityService1.getCreatedAt(), "2016-02-13T15:29:37.000000");
        assertEquals(securityService1.getUpdatedAt(), "2016-02-13T21:52:01.000000");
        assertTrue(securityService1.getShareNetworks().isEmpty());
        assertEquals(securityService1.getDnsIp(), "10.0.1.0/24");
        assertEquals(securityService1.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(securityService1.getUser(), "demo");
        assertNull(securityService1.getServer());
        assertEquals(securityService1.getType(), SecurityService.Type.LDAP);
        assertEquals(securityService1.getId(), "7d156ed8-6570-4898-bca4-eac5b6565f0d");
        assertEquals(securityService1.getDescription(), "Creating my second Security Service");

        assertEquals(securityService2.getStatus(), "new");
        assertNull(securityService2.getDomain());
        assertEquals(securityService2.getPassword(), "***");
        assertEquals(securityService2.getName(), "SecServ1");
        assertEquals(securityService2.getCreatedAt(), "2016-02-12T20:40:00.000000");
        assertNull(securityService2.getUpdatedAt());
        assertEquals(securityService2.getShareNetworks().get(0), "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a");
        assertEquals(securityService2.getDnsIp(), "10.0.0.0/24");
        assertEquals(securityService2.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(securityService2.getUser(), "demo");
        assertNull(securityService2.getServer());
        assertEquals(securityService2.getType(), SecurityService.Type.KERBEROS);
        assertEquals(securityService2.getId(), "ec8a2619-faa5-4878-a63d-4c784b0545f1");
        assertEquals(securityService2.getDescription(), "Creating my first Security Service");
    }

    @Test
    public void get() throws Exception {
        respondWith(JSON_SECURITY_SERVICE);

        SecurityService securityService = osv3().share().securityServices().get("32e921ed-f399-4e7a-b05b-786f482bd369");

        assertEquals(securityService.getStatus(), "new");
        assertNull(securityService.getDomain());
        assertEquals(securityService.getPassword(), "***");
        assertEquals(securityService.getName(), "SecServ2");
        assertEquals(securityService.getCreatedAt(), "2016-02-13T21:51:02.930893");
        assertNull(securityService.getUpdatedAt());
        assertEquals(securityService.getDnsIp(), "10.0.1.0/24");
        assertEquals(securityService.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(securityService.getUser(), "demo");
        assertNull(securityService.getServer());
        assertEquals(securityService.getType(), SecurityService.Type.LDAP);
        assertEquals(securityService.getId(), "32e921ed-f399-4e7a-b05b-786f482bd369");
        assertEquals(securityService.getDescription(), "Creating my second Security Service");
    }

    @Test
    public void update() throws Exception {
        respondWith(JSON_SECURITY_SERVICE_UPDATE);

        SecurityService securityService = osv3().share().securityServices().update(
                "32e921ed-f399-4e7a-b05b-786f482bd369",
                SecurityServiceUpdateOptions.create()
                        .domain("my_domain")
                        .user("new_user")
                        .description("Updating my second Security Service"));

        assertEquals(securityService.getStatus(), "new");
        assertEquals(securityService.getDomain(), "my_domain");
        assertEquals(securityService.getPassword(), "***");
        assertEquals(securityService.getName(), "SecServ2");
        assertEquals(securityService.getCreatedAt(), "2016-02-13T21:51:02.000000");
        assertEquals(securityService.getUpdatedAt(), "2016-02-13T21:52:01.403251");
        assertEquals(securityService.getDnsIp(), "10.0.1.0/24");
        assertEquals(securityService.getProjectId(), "d401b6b1f81943e8919f2b6819755fb6");
        assertEquals(securityService.getUser(), "new_user");
        assertNull(securityService.getServer());
        assertEquals(securityService.getType(), SecurityService.Type.LDAP);
        assertEquals(securityService.getId(), "32e921ed-f399-4e7a-b05b-786f482bd369");
        assertEquals(securityService.getDescription(), "Updating my second Security Service");
    }

    @Test
    public void delete() throws Exception {
        respondWith(202);

        ActionResponse response = osv3().share().securityServices().delete("32e921ed-f399-4e7a-b05b-786f482bd369");
        assertTrue(response.isSuccess());
    }
}
