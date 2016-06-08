package org.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.senlin.PolicyType;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for policyType on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/policyType")
public class PolicyTypeServiceTest extends AbstractTest {

    private static final String POLICYTYPES="/senlin/v1/policy_types.json";
    private static final String POLICYTYPE="/senlin/v1/policy_type.json";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListPolicyType() throws Exception{
        respondWith(POLICYTYPES);
        List<? extends PolicyType> policyTypeList = osv3().senlin().policyType().list();
        assertEquals(6, policyTypeList.size());
        Preconditions.checkNotNull(policyTypeList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : PolicyType from List : "+ policyTypeList.get(0));
        assertEquals(policyTypeList.get(0).getName(), "ScalingPolicy");
    }
    @Test
    public void testGetPolicyType() throws Exception{
        respondWith(POLICYTYPE);
        PolicyType policyType = osv3().senlin().policyType().get("senlin.policy.deletion");
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : PolicyType by name : "+ policyType);
        assertNotNull(policyType);
        assertEquals("senlin.policy.deletion", policyType.getName());
    }

}
