package org.openstack4j.api.network;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.InputStreamReader;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.Ethertype;
import org.openstack4j.model.network.ext.FlowClassifier;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.model.network.ext.PortPair;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.openstack.networking.domain.ext.NeutronFlowClassifier;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortChain;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPair;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPairGroup;
import org.testng.annotations.Test;

import com.google.common.base.Objects;

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
    public void testCreateFlowClassifier() throws Exception {
        FlowClassifier original = ObjectMapperSingleton.getContext(NeutronFlowClassifier.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_FLOW_CLASSIFIER)),
                           NeutronFlowClassifier.class);
        respondWith(JSON_FLOW_CLASSIFIER);
        FlowClassifier returned = osv3().sfc().flowclassifiers().create(original);
        server.takeRequest();
        assertNotNull(returned);
        assertFlowClassifiersEqual(returned, original);
    }

    @Test
    public void testUpdateFlowClassifier() throws Exception {
        FlowClassifier original = ObjectMapperSingleton.getContext(NeutronFlowClassifier.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_FLOW_CLASSIFIER)),
                           NeutronFlowClassifier.class);
        respondWith(JSON_FLOW_CLASSIFIER);
        FlowClassifier returned = osv3().sfc().flowclassifiers().update(FC_ID, original);
        server.takeRequest();
        assertNotNull(returned);
        assertFlowClassifiersEqual(returned, original);
    }

    @Test
    public void testDeleteFlowClassifier() throws Exception {
        FlowClassifier original = ObjectMapperSingleton.getContext(NeutronFlowClassifier.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_FLOW_CLASSIFIER)),
                           NeutronFlowClassifier.class);
        respondWith(200);
        ActionResponse response = osv3().sfc().flowclassifiers().delete(FC_ID);
        server.takeRequest();
        assertTrue(response.isSuccess());
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
    public void testCreatePortPair() throws Exception {
        PortPair original = ObjectMapperSingleton.getContext(NeutronPortPair.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_PAIR)),
                           NeutronPortPair.class);
        respondWith(JSON_PORT_PAIR);
        PortPair returned = osv3().sfc().portpairs().create(original);
        server.takeRequest();
        assertNotNull(returned);
        assertPortPairsEqual(returned, original);
    }

    @Test
    public void testUpdatePortPair() throws Exception {
        PortPair original = ObjectMapperSingleton.getContext(NeutronPortPair.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_PAIR)),
                           NeutronPortPair.class);
        respondWith(JSON_PORT_PAIR);
        PortPair returned = osv3().sfc().portpairs().update(FC_ID, original);
        server.takeRequest();
        assertNotNull(returned);
        assertPortPairsEqual(returned, original);
    }

    @Test
    public void testDeletePortPair() throws Exception {
        respondWith(200);
        ActionResponse response = osv3().sfc().portpairs().delete(FC_ID);
        server.takeRequest();
        assertTrue(response.isSuccess());
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
    public void testCreatePortPairGroup() throws Exception {
        PortPairGroup original = ObjectMapperSingleton.getContext(NeutronPortPairGroup.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_PAIR_GROUP)),
                           NeutronPortPairGroup.class);
        respondWith(JSON_PORT_PAIR_GROUP);
        PortPairGroup returned = osv3().sfc().portpairgroups().create(original);
        server.takeRequest();
        assertNotNull(returned);
        assertPortPairGroupsEqual(returned, original);
    }

    @Test
    public void testUpdatePortPairGroup() throws Exception {
        PortPairGroup original = ObjectMapperSingleton.getContext(NeutronPortPairGroup.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_PAIR_GROUP)),
                           NeutronPortPairGroup.class);
        respondWith(JSON_PORT_PAIR_GROUP);
        PortPairGroup returned = osv3().sfc().portpairgroups().update(FC_ID, original);
        server.takeRequest();
        assertNotNull(returned);
        assertPortPairGroupsEqual(returned, original);
    }

    @Test
    public void testDeletePortPairGroup() throws Exception {
        respondWith(200);
        ActionResponse response = osv3().sfc().portpairgroups().delete(FC_ID);
        server.takeRequest();
        assertTrue(response.isSuccess());
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
        assertEquals(portChains.get(1).getFlowClassifiers().get(0), FC_ID);
        assertEquals(portChains.get(1).getPortPairGroups().size(), 1);
        assertEquals(portChains.get(1).getPortPairGroups().get(0), PPG_ID);
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

    @Test
    public void testCreatePortChain() throws Exception {
        PortChain original = ObjectMapperSingleton.getContext(NeutronPortChain.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_CHAIN)),
                           NeutronPortChain.class);
        respondWith(JSON_PORT_CHAIN);
        PortChain returned = osv3().sfc().portchains().create(original);
        server.takeRequest();
        assertNotNull(returned);
        assertPortChainsEqual(returned, original);
    }

    @Test
    public void testUpdatePortChain() throws Exception {
        PortChain original = ObjectMapperSingleton.getContext(NeutronPortChain.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_CHAIN)),
                           NeutronPortChain.class);
        respondWith(JSON_PORT_CHAIN);
        PortChain returned = osv3().sfc().portchains().update(FC_ID, original);
        server.takeRequest();
        assertNotNull(returned);
        assertPortChainsEqual(returned, original);
    }

    @Test
    public void testDeletePortChain() throws Exception {
        respondWith(200);
        ActionResponse response = osv3().sfc().portchains().delete(FC_ID);
        server.takeRequest();
        assertTrue(response.isSuccess());
    }

    @Test
    public void testPortChainBuilder() throws Exception {
        PortChain original = ObjectMapperSingleton.getContext(NeutronPortChain.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_CHAIN)),
                           NeutronPortChain.class);
        PortChain built = Builders.portChain()
                .id(original.getId())
                .name(original.getName())
                .description(original.getDescription())
                .projectId(original.getTenantId())
                .chainId(original.getChainId())
                .chainParameters(original.getChainParameters())
                .flowClassifiers(original.getFlowClassifiers())
                .portPairGroups(original.getPortPairGroups())
                .build();

        assertPortChainsEqual(built, original);
    }

    @Test
    public void testPortPairGroupBuilder() throws Exception {
        PortPairGroup original = ObjectMapperSingleton.getContext(NeutronPortPairGroup.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_PAIR_GROUP)),
                           NeutronPortPairGroup.class);
        PortPairGroup built = Builders.portPairGroup()
                .id(original.getId())
                .name(original.getName())
                .description(original.getDescription())
                .projectId(original.getTenantId())
                .portPairGroupParameters(original.getPortPairGroupParameters())
                .portPairs(original.getPortPairs())
                .build();

        assertPortPairGroupsEqual(built, original);
    }

    @Test
    public void testPortPairBuilder() throws Exception {
        PortPair original = ObjectMapperSingleton.getContext(NeutronPortPair.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_PORT_PAIR)),
                           NeutronPortPair.class);
        PortPair built = Builders.portPair()
                .id(original.getId())
                .name(original.getName())
                .description(original.getDescription())
                .projectId(original.getTenantId())
                .egressId(original.getEgressId())
                .ingressId(original.getIngressId())
                .serviceFunctionParameters(original.getServiceFunctionParameters())
                .build();

        assertPortPairsEqual(built, original);
    }

    @Test
    public void testFlowClassifierBuilder() throws Exception {
        FlowClassifier original = ObjectMapperSingleton.getContext(NeutronFlowClassifier.class)
                .readValue(new InputStreamReader(getClass().getResourceAsStream(JSON_FLOW_CLASSIFIER)),
                           NeutronFlowClassifier.class);
        FlowClassifier built = Builders.flowClassifier()
                .id(original.getId())
                .name(original.getName())
                .description(original.getDescription())
                .projectId(original.getTenantId())
                .destinationIpPrefix(original.getDestinationIpPrefix())
                .sourceIpPrefix(original.getSourceIpPrefix())
                .sourcePortRangeMax(original.getSourcePortRangeMax())
                .destinationPortRangeMax(original.getDestinationPortRangeMax())
                .sourcePortRangeMin(original.getSourcePortRangeMin())
                .destinationPortRangeMin(original.getDestinationPortRangeMin())
                .logicalSourcePort(original.getLogicalSourcePort())
                .logicalDestinationPort(original.getLogicalDestinationPort())
                .l7Parameters(original.getL7Parameters())
                .ethertype(original.getEthertype())
                .protocol(original.getProtocol())
                .build();

        assertFlowClassifiersEqual(built, original);
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    private static void assertFlowClassifiersEqual(FlowClassifier returned, FlowClassifier original) {
        assertEquals(returned.getId(), original.getId());
        assertEquals(returned.getName(), original.getName());
        assertEquals(returned.getDescription(), original.getDescription());
        assertEquals(returned.getTenantId(), original.getTenantId());
        assertEquals(returned.getSourceIpPrefix(), original.getSourceIpPrefix());
        assertEquals(returned.getDestinationIpPrefix(), original.getDestinationIpPrefix());
        assertEquals(returned.getLogicalSourcePort(), original.getLogicalSourcePort());
        assertEquals(returned.getLogicalDestinationPort(), original.getLogicalDestinationPort());
        assertEquals(returned.getSourcePortRangeMin(), original.getSourcePortRangeMin());
        assertEquals(returned.getDestinationPortRangeMin(), original.getDestinationPortRangeMin());
        assertEquals(returned.getSourcePortRangeMax(), original.getSourcePortRangeMax());
        assertEquals(returned.getDestinationPortRangeMax(), original.getDestinationPortRangeMax());
        assertEquals(returned.getEthertype(), original.getEthertype());
        assertTrue(Objects.equal(returned.getL7Parameters(), original.getL7Parameters()));
    }

    private static void assertPortPairsEqual(PortPair returned, PortPair original) {
        assertEquals(returned.getId(), original.getId());
        assertEquals(returned.getName(), original.getName());
        assertEquals(returned.getDescription(), original.getDescription());
        assertEquals(returned.getTenantId(), original.getTenantId());
        assertEquals(returned.getIngressId(), original.getIngressId());
        assertEquals(returned.getEgressId(), original.getEgressId());
        assertTrue(Objects.equal(returned.getServiceFunctionParameters(), original.getServiceFunctionParameters()));
    }

    private static void assertPortChainsEqual(PortChain returned, PortChain original) {
        assertEquals(returned.getId(), original.getId());
        assertEquals(returned.getName(), original.getName());
        assertEquals(returned.getDescription(), original.getDescription());
        assertEquals(returned.getTenantId(), original.getTenantId());
        assertEquals(returned.getChainId(), original.getChainId());
        assertEquals(returned.getChainParameters(), original.getChainParameters());
        assertTrue(Objects.equal(returned.getPortPairGroups(), original.getPortPairGroups()));
        assertTrue(Objects.equal(returned.getFlowClassifiers(), original.getFlowClassifiers()));
    }

    private static void assertPortPairGroupsEqual(PortPairGroup returned, PortPairGroup original) {
        assertEquals(returned.getId(), original.getId());
        assertEquals(returned.getName(), original.getName());
        assertEquals(returned.getDescription(), original.getDescription());
        assertEquals(returned.getTenantId(), original.getTenantId());
        assertTrue(Objects.equal(returned.getPortPairGroupParameters(), original.getPortPairGroupParameters()));
        assertTrue(Objects.equal(returned.getPortPairs(), original.getPortPairs()));
    }
}
