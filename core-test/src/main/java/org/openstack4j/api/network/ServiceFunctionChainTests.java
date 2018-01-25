package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.network.ext.Ethertype;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.testng.annotations.Test;

@Test(suiteName = "ServiceFunctionChain")
public class ServiceFunctionChainTests extends AbstractTest {

    private static final String JSON_FLOW_CLASSIFIERS = "/network/sfc/flow_classifiers.json";
    private static final String JSON_FLOW_CLASSIFIER = "/network/sfc/flow_classifier.json";
    private static final String JSON_PORT_PAIRS = "/network/sfc/port_pairs.json";
    private static final String JSON_PORT_PAIR = "/network/sfc/port_pair.json";
    private static final String JSON_PORT_PAIR_GROUPS = "/network/sfc/port_pair_groups.json";
    private static final String JSON_PORT_PAIR_GROUP = "/network/sfc/port_pair_group.json";
    private static final String JSON_PORT_CHAINS = "/network/sfc/port_chains.json";
    private static final String JSON_PORT_CHAIN = "/network/sfc/port_chain.json";

    private static final String INGRESS_ID = "12345678-abcd-abcd-abcd-abcdefabcdef";
    private static final String EGRESS_ID = "abcdefab-abcd-abcd-abcd-abcdefabcdef";

    private static final String FC_ID = INGRESS_ID;
    private static final String PPG_ID = EGRESS_ID;

    private static final String PROJECT_ID = "12345678909876543210abcdefabcdef";
    private static final String PARAM_KEY_A = "aparam";
    private static final String PARAM_KEY_B = "bparam";
    private static final String PARAM_VALUE_A = "A";
    private static final String PARAM_VALUE_B = "B";
    private static final String FC_NAME2 = "FC2";
    private static final String FC_NAME1 = "FC1";
    private static final String PP_NAME2 = "PP2";
    private static final String PP_NAME1 = "PP1";
    private static final String PC_NAME2 = "PC2";
    private static final String PC_NAME1 = "PC1";

    @Test
    public void testFlowClassifiersList() throws Exception {
        respondWith(JSON_FLOW_CLASSIFIERS);
        List<? extends FlowClassifier> flowClassifiers = osv3().sfc().flowclassifiers().list();
        server.takeRequest();
        assertNotNull(flowClassifiers);
        assertEquals(2, flowClassifiers.size());
        assertEquals(flowClassifiers.get(0).getName(), FC_NAME1);
        assertNotNull(flowClassifiers.get(0).getEthertype());
        assertEquals(flowClassifiers.get(0).getEthertype(), Ethertype.IPv4);
        assertNotNull(flowClassifiers.get(0).getL7Parameters());
        assertNotNull(flowClassifiers.get(0).getL7Parameters().get(PARAM_KEY_A));
        assertEquals(flowClassifiers.get(0).getL7Parameters().get(PARAM_KEY_A), PARAM_VALUE_A);

        assertEquals(flowClassifiers.get(1).getName(), FC_NAME2);
        assertNotNull(flowClassifiers.get(1).getEthertype());
        assertEquals(flowClassifiers.get(1).getEthertype(), Ethertype.IPv4);
        assertNotNull(flowClassifiers.get(1).getL7Parameters());
        assertNotNull(flowClassifiers.get(1).getL7Parameters().get(PARAM_KEY_B));
        assertEquals(flowClassifiers.get(1).getL7Parameters().get(PARAM_KEY_B), PARAM_VALUE_B);
}

    @Test
    public void testGetFlowClassifier() throws Exception {
        respondWith(JSON_FLOW_CLASSIFIER);
        FlowClassifier flowClassifier = osv3().sfc().flowclassifiers().get(FC_ID);
        server.takeRequest();
        assertNotNull(flowClassifier);
        assertEquals(flowClassifier.getName(), FC_NAME1);
        assertNotNull(flowClassifier.getEthertype());
        assertEquals(flowClassifier.getEthertype(), Ethertype.IPv4);
        assertNotNull(flowClassifier.getL7Parameters());
        assertNotNull(flowClassifier.getL7Parameters().get(PARAM_KEY_A));
        assertEquals(flowClassifier.getL7Parameters().get(PARAM_KEY_A), PARAM_VALUE_A);
        assertEquals(flowClassifier.getTenantId(), PROJECT_ID);
    }

    @Test
    public void testPortPairsList() throws Exception {
        respondWith(JSON_PORT_PAIRS);
        List<? extends PortPair> portPairs = osv3().sfc().portpairs().list();
        server.takeRequest();
        assertNotNull(portPairs);
        assertEquals(2, portPairs.size());
        assertEquals(portPairs.get(0).getName(), PP_NAME1);
        assertEquals(portPairs.get(0).getEgressId(), EGRESS_ID);
        assertEquals(portPairs.get(0).getIngressId(), INGRESS_ID);
        assertEquals(portPairs.get(0).getServiceFunctionParameters().size(), 1);
        assertEquals(portPairs.get(0).getServiceFunctionParameters().get(PARAM_KEY_A), PARAM_VALUE_A);

        assertEquals(portPairs.get(1).getName(), PP_NAME2);
        assertEquals(portPairs.get(1).getEgressId(), EGRESS_ID);
        assertEquals(portPairs.get(1).getIngressId(), INGRESS_ID);
        assertEquals(portPairs.get(1).getServiceFunctionParameters().size(), 1);
        assertEquals(portPairs.get(1).getServiceFunctionParameters().get(PARAM_KEY_B), PARAM_VALUE_B);
    }

    @Test
    public void testGetPortPair() throws Exception {
        respondWith(JSON_PORT_PAIR);
        PortPair portPair = osv3().sfc().portpairs().get("abcdef");
        server.takeRequest();
        assertNotNull(portPair);
        assertEquals(portPair.getName(), PP_NAME1);
        assertEquals(portPair.getEgressId(), EGRESS_ID);
        assertEquals(portPair.getIngressId(), INGRESS_ID);
        assertEquals(portPair.getServiceFunctionParameters().size(), 1);
        assertEquals(portPair.getServiceFunctionParameters().get(PARAM_KEY_A), PARAM_VALUE_A);
    }

    @Test
    public void testPortPairGroupsList() throws Exception {
        respondWith(JSON_PORT_PAIR_GROUPS);
        List<? extends PortPairGroup> portPairGroups = osv3().sfc().portpairgroups().list();
        server.takeRequest();
        assertNotNull(portPairGroups);
        assertEquals(2, portPairGroups.size());
        assertEquals(portPairGroups.get(0).getName(), PP_NAME1);
        assertEquals(portPairGroups.get(1).getName(), PP_NAME2);
    }

    @Test
    public void testGetPortPairGroup() throws Exception {
        respondWith(JSON_PORT_PAIR_GROUP);
        PortPairGroup portPairGroup = osv3().sfc().portpairgroups().get("abcdef");
        server.takeRequest();
        assertNotNull(portPairGroup);
        assertEquals(portPairGroup.getName(), PP_NAME1);
    }

    @Test
    public void testPortChainsList() throws Exception {
        respondWith(JSON_PORT_CHAINS);
        List<? extends PortChain> portChains = osv3().sfc().portchains().list();
        server.takeRequest();
        assertNotNull(portChains);
        assertEquals(2, portChains.size());
        assertEquals(portChains.get(0).getName(), PC_NAME1);
        assertEquals(portChains.get(0).getChainId(), "43210");
        assertEquals(portChains.get(0).getTenantId(), PROJECT_ID);
        assertEquals(portChains.get(0).getFlowClassifiers().size(), 1);
        assertEquals(portChains.get(0).getFlowClassifiers().get(0),
                FC_ID);
        assertEquals(portChains.get(0).getPortPairGroups().size(), 1);
        assertEquals(portChains.get(0).getPortPairGroups().get(0),
                PPG_ID);
        assertEquals(portChains.get(0).getChainParameters().size(), 1);
        assertEquals(portChains.get(0).getChainParameters().get(PARAM_KEY_A), PARAM_VALUE_A);

        assertEquals(portChains.get(1).getName(), PC_NAME2);
        assertEquals(portChains.get(1).getChainId(), "98765");
        assertEquals(portChains.get(1).getTenantId(), PROJECT_ID);
        assertEquals(portChains.get(1).getFlowClassifiers().size(), 1);
        assertEquals(portChains.get(1).getFlowClassifiers().get(0),
                FC_ID);
        assertEquals(portChains.get(1).getPortPairGroups().size(), 1);
        assertEquals(portChains.get(1).getPortPairGroups().get(0),
                PPG_ID);
        assertEquals(portChains.get(1).getChainParameters().size(), 1);
        assertEquals(portChains.get(1).getChainParameters().get(PARAM_KEY_A), PARAM_VALUE_A);

    }

    @Test
    public void testGetPortChain() throws Exception {
        respondWith(JSON_PORT_CHAIN);
        PortChain portChain = osv3().sfc().portchains().get("0abcdef");
        server.takeRequest();
        assertNotNull(portChain);
        assertEquals(portChain.getName(), PC_NAME1);
        assertEquals(portChain.getName(), PC_NAME1);
        assertEquals(portChain.getChainId(), "43210");
        assertEquals(portChain.getTenantId(), PROJECT_ID);
        assertEquals(portChain.getFlowClassifiers().size(), 1);
        assertEquals(portChain.getFlowClassifiers().get(0),
                FC_ID);
        assertEquals(portChain.getPortPairGroups().size(), 1);
        assertEquals(portChain.getPortPairGroups().get(0),
                PPG_ID);
        assertEquals(portChain.getChainParameters().size(), 1);
        assertEquals(portChain.getChainParameters().get(PARAM_KEY_A), PARAM_VALUE_A);
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}
