package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.network.SecurityGroup;
import org.openstack4j.model.network.builder.NetSecurityGroupUpdateBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


/**
 * Tests the Compute -> Network API against the mock webserver and spec based
 * json responses
 *
 * Created by Ayberk CAL on 20.03.2017.
 */
@Test(suiteName = "SecurityGroup")
public class SecurityGroupTests extends AbstractTest {
    private static final String JSON_SECURITY_GROUP = "/network/security_group.json";
    private static final String GROUP_ID = "448675b7-a412-4030-9c05-4bb723f58387";

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void updateSecurityGroup() throws Exception {
        respondWith(JSON_SECURITY_GROUP);
        NetSecurityGroupUpdateBuilder builder = Builders.securityGroupUpdate();
        builder.name("updated_name");
        builder.description("updated_description");

        SecurityGroup update = osv3().networking().securitygroup().update(GROUP_ID, builder.build());
        assertEquals(update.getName(), "updated_name");
        assertEquals(update.getDescription(), "updated_description");
    }
}
