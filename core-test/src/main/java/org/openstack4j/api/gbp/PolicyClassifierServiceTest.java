package org.openstack4j.api.gbp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.gbp.Direction;
import org.openstack4j.model.gbp.PolicyClassifier;
import org.openstack4j.model.gbp.PolicyClassifierUpdate;
import org.openstack4j.model.gbp.Protocol;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;
/**
 * Test cases for policy classifier on GBP
 *
 * @author vinod borole
 */
@Test(suiteName="grouppolicy/policy_classifiers")
public class PolicyClassifierServiceTest extends AbstractTest {

    private static final String POLICY_CLASSIFIERS="/network/gbp/policy_classifiers.json";
    private static final String POLICY_CLASSIFIER="/network/gbp/policy_classifier.json";
    private static final String POLICY_CLASSIFIER_UPDATE="/network/gbp/policy_classifier_update.json";
    
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    @Test
    public void testListPolicyClassifier() throws Exception{
        respondWith(POLICY_CLASSIFIERS);
        List<? extends PolicyClassifier> policyClasifierList = osv2().gbp().policyClassifier().list();
        assertEquals(2, policyClasifierList.size()); 
        Preconditions.checkNotNull(policyClasifierList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Classifier from List : "+policyClasifierList.get(0));
        assertEquals(policyClasifierList.get(0).getId(), "36e41adb-0b9b-4a11-abd5-66e5386139d4");
    }
    @Test
    public void testGetPolicyClassifier() throws Exception{
        respondWith(POLICY_CLASSIFIER);
        String id = "36e41adb-0b9b-4a11-abd5-66e5386139d4";
        PolicyClassifier policyclassifier = osv2().gbp().policyClassifier().get(id);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Policy Classifier by ID : "+policyclassifier);
        assertNotNull(policyclassifier);
        assertEquals(id, policyclassifier.getId());
    }
    @Test
    public void testCreatePolicyClassifier() throws Exception{
        respondWith(POLICY_CLASSIFIER);
        PolicyClassifier policyClassifierCreate= Builders.policyClassifier().name("icmp").direction(Direction.BI).protocol(Protocol.ICMP).build();
        PolicyClassifier policyClassifier = osv2().gbp().policyClassifier().create(policyClassifierCreate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Create Policy Classifier : "+policyClassifier);
        assertEquals("icmp", policyClassifier.getName());
    }
    @Test
    public void testUpdatePolicyClassifier() throws Exception{
        respondWith(POLICY_CLASSIFIER_UPDATE);
        String id = "36e41adb-0b9b-4a11-abd5-66e5386139d4";
        PolicyClassifierUpdate policyClassifierUpdate= Builders.policyClassifierUpdate().name("icmp").description("icmp-update").build();
        PolicyClassifier policyClassifier =osv2().gbp().policyClassifier().update(id, policyClassifierUpdate);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Upate Policy Action : "+policyClassifier);
        assertEquals("icmp-update", policyClassifier.getDescription());

    }
    @Test
    public void testDeletePolicyClassifier() {
        respondWith(200);
        String id = "36e41adb-0b9b-4a11-abd5-66e5386139d4";
        ActionResponse result = osv2().gbp().policyClassifier().delete(id);
        assertTrue(result.isSuccess());
    }


}
