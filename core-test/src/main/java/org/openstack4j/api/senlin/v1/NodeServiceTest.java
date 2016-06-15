package org.openstack4j.api.senlin.v1;

import com.google.common.base.Preconditions;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.senlin.ActionID;
import org.openstack4j.model.senlin.Node;
import org.openstack4j.model.senlin.NodeActionCreate;
import org.openstack4j.model.senlin.NodeCreate;
import org.openstack4j.openstack.senlin.domain.SenlinNodeActionCreate;
import org.openstack4j.openstack.senlin.domain.SenlinNodeCreate;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for node on Senlin
 *
 * @author lion
 */
@Test(suiteName="senlin/node")
public class NodeServiceTest extends AbstractTest {

    private static final String NODES="/senlin/v1/nodes.json";
    private static final String NODE="/senlin/v1/node.json";
    private static final String RASPACTION="/senlin/v1/resp_action.json";
    private static final String ID="d5779bb0-f0a0-49c9-88cc-6f078adb5a0b";

    @Override
    protected Service service() {
        return Service.CLUSTERING;
    }
    @Test
    public void testListNode() throws Exception{
        respondWith(NODES);
        List<? extends Node> nodeList = osv3().senlin().node().list();
        assertEquals(3, nodeList.size());
        Preconditions.checkNotNull(nodeList.get(0));
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Node from List : "+nodeList.get(0));
        assertEquals(nodeList.get(0).getId(), "d3451489-708b-4bd0-a147-a0c02dd9cb00");
    }
    @Test
    public void testGetNode() throws Exception{
        respondWith(NODE);
        Node node = osv3().senlin().node().get(ID);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Node by ID : " + node);
        assertNotNull(node);
        assertEquals(ID, node.getId());
    }
    @Test
    public void testCreateNode() throws Exception{
        respondWith(NODE);
        String nodeName = "node1";
        NodeCreate newNode = new SenlinNodeCreate();
        newNode.toBuilder()
                .name(nodeName);
        Node node = osv3().senlin().node().create(newNode);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Node : " + node);
        assertEquals(nodeName, node.getName());
    }
    @Test
    public void testUpdateNode() throws Exception{
        respondWith(NODE);
        String nodeName = "node1";
        NodeCreate newNode = new SenlinNodeCreate();
        newNode.toBuilder()
                .name(nodeName);
        Node node = osv3().senlin().node().update(ID, newNode);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Updated Node : " + node);
        assertEquals(nodeName, node.getName());
    }
    @Test
    public void testDeleteNode() {
        respondWith(200);
        ActionResponse result = osv3().senlin().node().delete(ID);
        assertTrue(result.isSuccess());
    }
    @Test
    public void testNodeAction() throws Exception{
        respondWith(RASPACTION);
        NodeActionCreate newNodeAction = new SenlinNodeActionCreate();
        newNodeAction.toBuilder().check(new HashMap<String, String>());
        ActionID respAction = osv3().senlin().node().action("573aa1ba-bf45-49fd-907d-6b5d6e6adfd3", newNodeAction);
        Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Trigger node action : " + respAction);
        assertEquals("40a436b1-28d1-4de6-b2c3-0a34f478e2c9", respAction.getActionID());
    }

}
