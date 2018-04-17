package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.SecurityGroup;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * Security Group Test
 * @author chenyan
 *
 */
public class SecurityGroupTest extends AbstractTest {
	
	private static final String JSON_SECURITY_GROUPS = "/network/security_groupsv2.json";
	
	@Test
	public void serviceListingTest() throws Exception {
        respondWith(JSON_SECURITY_GROUPS);

        Map<String, String> params = new HashMap<>();
        String projectId = "e4f50856753b4dc6afee5fa6b9b6c550";
        params.put("tenant_id", projectId);
        String tenantId = "e4f50856753b4dc6afee5fa6b9b6c550";
        params.put("project_id", tenantId);
        List<? extends SecurityGroup> securityGroupList = osv3().networking().securitygroup().list(params);
        assertEquals(securityGroupList.size(),1);

        SecurityGroup securityGroup = securityGroupList.get(0);
        assertEquals(securityGroup.getTenantId(),tenantId);
    }

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}
