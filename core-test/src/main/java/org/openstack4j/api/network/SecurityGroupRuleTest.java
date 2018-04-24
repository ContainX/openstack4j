package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.SecurityGroupRule;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * Security Group Rule Test
 * @author chenyan
 *
 */
public class SecurityGroupRuleTest extends AbstractTest {
	
	private static final String JSON_SECURITY_GROUP_RULES = "/network/security_group_rulesv2.json";
	
	@Test
	public void serviceListingTest() throws Exception {
        respondWith(JSON_SECURITY_GROUP_RULES);

        Map<String, String> params = new HashMap<>();
        String security_group_id = "85cc3048-abc3-43cc-89b3-377341426ac5";
        params.put("security_group_id", security_group_id);
        List<? extends SecurityGroupRule> ruleList = osv3().networking().securityrule().list(params);
        assertEquals(ruleList.size(),4);

        SecurityGroupRule rule = ruleList.get(0);
        assertEquals(rule.getSecurityGroupId(),security_group_id);
    }

	@Override
	protected Service service() {
		return Service.NETWORK;
	}

}
